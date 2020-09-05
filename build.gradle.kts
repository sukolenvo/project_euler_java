
plugins {
    java
}

group = "com.sukolenvo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation( "org.assertj:assertj-core:3.17.1")

    testImplementation("org.junit.jupiter", "junit-jupiter", "5.6.2")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}