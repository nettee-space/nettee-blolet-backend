val blogApi: String by project
val blogClientApi: String by project

dependencies {
    api(project(blogApi))
    api(project(blogClientApi))
    api(project(":rest-client"))

    // cache
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.2")
}