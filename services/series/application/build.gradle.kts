val seriesApi: String by project
val blogClientWebMvc: String by project

dependencies {
    api(project(seriesApi))
    api(project(blogClientWebMvc))
}