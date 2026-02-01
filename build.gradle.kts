plugins {
    id("java")
    id("org.springframework.boot") version "3.3.4" // 👈 plugin Spring Boot
    id("io.spring.dependency-management") version "1.1.6"
    id("application")
    id("io.freefair.lombok") version "8.10"
}

group = "it.coloni"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")


    // Starter Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")


    // Database
    runtimeOnly("org.postgresql:postgresql:42.7.3")
    runtimeOnly("com.h2database:h2:2.2.224")

    //mapstruct
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor( "org.mapstruct:mapstruct-processor:1.5.5.Final")

    //Jwt
    implementation ("io.jsonwebtoken:jjwt-api:0.13.0")
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.13.0")
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.13.0")




//    compileOnly("io.github.wimdeblauwe:error-handling-spring-boot-starter:4.6.0")

}

tasks.test {
    useJUnitPlatform()
}

application{
    mainClass.set("it.unicam.coloni.hackhub.Main")
}
