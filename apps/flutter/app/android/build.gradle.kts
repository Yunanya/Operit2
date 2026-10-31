allprojects {
    buildscript {
        repositories {
            if (System.getenv("GITHUB_ACTIONS") == null) maven { url = uri("https://maven.aliyun.com/repository/google") }
            if (System.getenv("GITHUB_ACTIONS") == null) maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
            if (System.getenv("GITHUB_ACTIONS") == null) maven { url = uri("https://maven.aliyun.com/repository/public") }
            google()
            mavenCentral()
        }
    }
    repositories {
        if (System.getenv("GITHUB_ACTIONS") == null) maven { url = uri("https://maven.aliyun.com/repository/google") }
        if (System.getenv("GITHUB_ACTIONS") == null) maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://jitpack.io") }
        google()
        mavenCentral()
    }
}

val newBuildDir: Directory =
    rootProject.layout.buildDirectory
        .dir("../../build")
        .get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}
subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
