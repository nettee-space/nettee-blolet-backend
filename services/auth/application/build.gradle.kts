val authApi: String by project

dependencies {
    api(project(authApi))
}