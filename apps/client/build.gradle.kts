val MAIN_CLASS = "s1riys.lab6.client.Main"

plugins {
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
    java
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(project(":apps:common"))
    implementation("com.google.guava:guava:32.1.1-jre")
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.20.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set(MAIN_CLASS)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.shadowJar {
    archiveBaseName.set("lab6Client")
    archiveClassifier.set("")
    minimize()
}

tasks.jar {
    enabled = false
    manifest.attributes["Main-Class"] = MAIN_CLASS
    dependsOn("shadowJar")
}