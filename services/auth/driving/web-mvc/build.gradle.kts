val authApi: String by project
val authApplication: String by project

dependencies {
    api(project(authApi))
    api(project(authApplication))

    implementation("org.springframework.boot:spring-boot-starter-web")

    // validation
    compileOnly("jakarta.validation:jakarta.validation-api")
    compileOnly("jakarta.annotation:jakarta.annotation-api")
}