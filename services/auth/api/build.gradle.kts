val authDomain: String by project
val authException: String by project
val authReadModel: String by project

dependencies {
    api(project(authDomain))
    api(project(authException))
    api(project(authReadModel))
}