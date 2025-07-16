val blogApi: String by project
val blogApplication: String by project

dependencies {
    api(project(blogApi))
}