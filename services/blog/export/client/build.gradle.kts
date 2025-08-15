val blogApi: String by project

dependencies {
    api(project(blogApi))
    implementation(project(":rest-client"))
}