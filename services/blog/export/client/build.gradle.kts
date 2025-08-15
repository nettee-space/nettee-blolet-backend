val blogApi: String by project

dependencies {
    api(project(blogApi))
    implementation(project(":rest-client"))

    // cache
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.2")
}