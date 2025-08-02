val blogApi: String by project
val blogApplication: String by project

dependencies {
    api(project(blogApi))
    api(project(blogApplication))
    api(project(":jpa-core"))
}