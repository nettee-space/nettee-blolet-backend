val authApi: String by project

dependencies {
    api(project(authApi))
    api(project(":security-password"))
    api(project(":security-jwt-issuer"))

    implementation("org.springframework.boot:spring-boot-starter-mail")
}