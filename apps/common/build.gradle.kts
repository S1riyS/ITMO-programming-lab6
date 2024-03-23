plugins {
    java
}

group = "s1riys.lab6"

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.google.guava:guava:32.1.1-jre")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}