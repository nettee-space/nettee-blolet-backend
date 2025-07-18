val draftDomain: String by project
val draftException: String by project
val draftReadModel: String by project

dependencies {
    api(project(draftDomain))
    api(project(draftException))
    api(project(draftReadModel))
}