val draftApi: String by project
val draftApplication: String by project
val draftRdbAdapter: String by project
val draftWebMvcAdapter: String by project

dependencies {
    api(project(draftApi))
    api(project(draftWebMvcAdapter))
}