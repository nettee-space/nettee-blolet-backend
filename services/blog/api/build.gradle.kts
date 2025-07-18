val blogDomain: String by project
val blogException: String by project
val blogReadModel: String by project

dependencies {
    api(project(blogDomain))
    api(project(blogException))
    api(project(blogReadModel))
}