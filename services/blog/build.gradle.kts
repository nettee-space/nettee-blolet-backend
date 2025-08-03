val blogApi: String by project
val blogApplication: String by project
val blogRdbAdapter: String by project
val blogWebMvcAdapter: String by project

dependencies {
    api(project(blogWebMvcAdapter))
    api(project(blogRdbAdapter))
}