val seriesApi: String by project
val seriesApplication: String by project

// FIXME 모듈 통합 전이므로 코드 확인을 위해 임시로 사용합니다:
val articleRdbAdapter: String by project

dependencies {
    val bom = dependencyManagement.importedProperties

    api(project(seriesApi))
    api(project(seriesApplication))
    api(project(articleRdbAdapter))
    api(project(":jpa-core"))

    // querydsl
    implementation("com.querydsl:querydsl-jpa:${bom["querydsl.version"]}:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:${bom["querydsl.version"]}:jakarta")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    // mapstruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2")
}