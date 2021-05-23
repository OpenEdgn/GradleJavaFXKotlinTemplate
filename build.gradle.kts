group = "com.github.OpenEDGN"
// 你可以修改此为自己的组织地址
version = "last"
// 你可以指定此为项目 版本号

buildscript {
    repositories {
        mavenLocal()
        maven { url = project.uri("https://maven.aliyun.com/repository/public/") }
        maven { url = project.uri("https://mirrors.163.com/maven/repository/maven-public/") }
        // maven 中国镜像，提供加速
        mavenCentral()
        maven { url = project.uri("https://jitpack.io") }
    }

    val kotlinVersion = "1.4.32"

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    repositories {
        mavenLocal()
        maven { url = project.uri("https://maven.aliyun.com/repository/gradle-plugin/") }
        maven { url = project.uri("https://maven.aliyun.com/repository/public/") }
        maven { url = project.uri("https://mirrors.163.com/maven/repository/maven-public/") }
        // maven 中国镜像，提供加速
        mavenCentral()
        maven { url = project.uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
    for (childProject in childProjects.values) {
        delete(childProject.buildDir)
    }
}
