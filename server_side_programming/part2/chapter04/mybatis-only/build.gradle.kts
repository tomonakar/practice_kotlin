import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    id("com.arenagod.gradle.MybatisGenerator") version "1.4" // 追加
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.mybatis:mybatis:3.5.6")
    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.2.1")
    implementation("mysql:mysql-connector-java:8.0.23")
    mybatisGenerator("org.mybatis.generator:mybatis-generator-core:1.4.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

mybatisGenerator {
    verbose = true
    configFile = "${projectDir}/src/main/resources/generatorConfig.xml"
}