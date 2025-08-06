val authApi: String by project
val authApplication: String by project
val authRdbAdapter: String by project
val authRedisAdapter: String by project
val authWebMvcAdapter: String by project

dependencies {
    api(project(authApi))
    api(project(authApplication))
    api(project(authRdbAdapter))
    api(project(authRedisAdapter))
    api(project(authWebMvcAdapter))
}