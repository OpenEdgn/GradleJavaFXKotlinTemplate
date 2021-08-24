import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
    id("org.beryx.jlink") version "2.24.1"
    // 使用 JavaFX 官方 Gradle 插件会抛出一个警告导致 `gradlew run` 出现错误，现已暂停使用
    // https://docs.gradle.org/6.8.3/userguide/upgrading_version_5.html#implicit_duplicate_strategy_for_copy_or_archive_tasks_has_been_deprecated
}

val mainClassStr = "com.github.template.MainKt"
val mainModuleStr = "gradle.kotlin.template"

dependencies {
    val arch = OperatingSystem.current().run {
        if (isWindows) {
            "win"
        } else if (isMacOsX) {
            "mac"
        } else if (isLinux) {
            "linux"
        } else {
            throw RuntimeException("未知操作系统，无法找到其对应实现.")
        }
    }
    // 注意，JavaFX 17 及以上包含不同CPU的本地实现，请自行适配
    val jfxVersion = "16"
    // https://repo.maven.apache.org/maven2/org/openjfx/javafx-base/17-ea+9/
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    implementation("org.openjfx:javafx-base:$jfxVersion")
    implementation("org.openjfx:javafx-controls:$jfxVersion")
    implementation("org.openjfx:javafx-fxml:$jfxVersion")
    implementation("org.openjfx:javafx-graphics:$jfxVersion")
    implementation("org.openjfx:javafx-base:$jfxVersion:$arch")
    implementation("org.openjfx:javafx-controls:$jfxVersion:$arch")
    implementation("org.openjfx:javafx-fxml:$jfxVersion:$arch")
    implementation("org.openjfx:javafx-graphics:$jfxVersion:$arch")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.7.2")
}
java.sourceCompatibility = JavaVersion.VERSION_11

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileKotlin.destinationDirectory.set(compileJava.destinationDirectory.get())

java {
    modularity.inferModulePath.set(true)
}

application {
    mainClass.set(mainClassStr)
    mainModule.set(mainModuleStr)
}

jlink {
    options.addAll("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
    launcher {
        name = "template"
        jvmArgs = arrayListOf()
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}
