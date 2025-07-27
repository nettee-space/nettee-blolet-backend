val authApi: String by project

dependencies {
    api(project(authApi))
    api(project(":security-password"))
    implementation("org.springframework.security:spring-security-crypto")
}