import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
    id("org.openjfx.javafxplugin") version "0.0.10"
    id("org.beryx.jlink") version "2.23.8"
}

val mainClassStr = "com.github.template.MainKt"
val mainModuleStr = "gradle.kotlin.template"

javafx {
    version = "16"
    modules("javafx.controls", "javafx.fxml")
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.6.2")
}

java.sourceCompatibility = JavaVersion.VERSION_11

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDir = compileKotlin.destinationDir

java {
    modularity.inferModulePath.set(true)
}

application {
    mainClass.set(mainClassStr)
    mainModule.set(mainModuleStr)
}

jlink {
    options.addAll("--strip-debug","--compress","2","--no-header-files","--no-man-pages")
    launcher{
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



