val profileDomain: String by project
val profileException: String by project
val profileReadModel: String by project

dependencies {
    api(project(profileDomain))
    api(project(profileException))
    api(project(profileReadModel))
}
