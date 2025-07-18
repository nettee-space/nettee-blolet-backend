val draftDomain: String by project
val draftException: String by project
val draftReadModel: String by project
val draftApplication: String by project

plugins {
    id("java-library")
}

dependencies {
    val bom = dependencyManagement.importedProperties

    api(project(draftDomain))
    api(project(draftException))
    api(project(draftReadModel))
    api(project(draftApplication))
    api(project(":jpa-core"))

    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:${bom["querydsl.version"]}:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:${bom["querydsl.version"]}:jakarta")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    // mapstruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
}