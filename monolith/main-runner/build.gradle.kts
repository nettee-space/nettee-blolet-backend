import org.springframework.boot.gradle.tasks.bundling.BootJar

val series: String by project
val draft: String by project
val article: String by project
val blog: String by project
val auth: String by project

version = "0.0.1-SNAPSHOT"

dependencies {
    // core
    implementation(project(":exception-handler-core"))
    implementation(project(":jpa-core"))
    implementation(project(":cors-webmvc"))
    implementation(project(":rest-client"))
    implementation(project(":security-blolet-jwt-filter"))

    // service
    api(project(series))
    api(project(draft))
    api(project(article))
    api(project(blog))
    api(project(auth))

    // webmvc
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // db
    runtimeOnly("org.postgresql:postgresql:42.7.4")

    // flyway
    implementation("org.flywaydb:flyway-database-postgresql")

    // test
    testImplementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.fasterxml.jackson.core:jackson-databind")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootJar>{
    enabled = true
}

tasks.withType<Jar>{
    enabled = true
}