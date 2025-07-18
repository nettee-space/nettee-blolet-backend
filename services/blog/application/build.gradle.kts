val blogApi: String by project

dependencies {
    api(project(blogApi))
}