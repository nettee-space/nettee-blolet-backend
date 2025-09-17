val authApi: String by project
val profileReadModel: String by project

dependencies {
    api(project(authApi))
    api(project(":security-password"))
    api(project(":security-jwt-issuer"))
    implementation(project(":client-api"))
    implementation(project(":rest-client"))
    implementation(project(profileReadModel))

    implementation("org.springframework.boot:spring-boot-starter-mail")
}
