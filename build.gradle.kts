
plugins {
    java
}

group = "com.sukolenvo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-io:commons-io:2.7")


    testImplementation( "org.assertj:assertj-core:3.17.1")
    testImplementation("org.junit.jupiter", "junit-jupiter", "5.6.2")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}