val authApi: String by project

dependencies {
    api(project(authApi))
    api(project(":security-password"))
    api(project(":security-jwt-issuer"))
}