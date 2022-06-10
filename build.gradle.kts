import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("com.github.johnrengelman.shadow") version ("7.1.2")

    kotlin("plugin.serialization") version "1.6.21"
}

group = "me.sadev"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://repo.jorisg.com/snapshots")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "me.sadev.dodge.DodgeKt"
    }
}

dependencies {
    testImplementation(kotlin("test"))

    // Minestom
    implementation("com.github.Minestom:Minestom:58b6e90142")
    implementation("com.github.Project-Cepi:KStom:77c22a6912")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")

    // Logging utils
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta6") // Cool Terminal thingys

    // Mysql
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("mysql:mysql-connector-java:8.0.29")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}