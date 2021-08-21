plugins {
    java
    kotlin("jvm") version "1.5.21"
}

group = "com.revtekk"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(platform("software.amazon.awssdk:bom:2.10.86"))
    implementation("software.amazon.awssdk:s3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testImplementation("org.mockito:mockito-core:3.+")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
