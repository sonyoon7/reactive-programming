

plugins {
    id("java")

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/release") }
}



dependencies {
    implementation("io.projectreactor:reactor-core:3.7.3")
    // import a BOM
    implementation(platform("io.projectreactor:reactor-bom:2024.0.3"))

    // define dependencies without versions
    implementation("io.projectreactor.netty:reactor-netty-core")
    implementation("io.projectreactor.netty:reactor-netty-http")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

}

tasks.test {
    useJUnitPlatform()
}