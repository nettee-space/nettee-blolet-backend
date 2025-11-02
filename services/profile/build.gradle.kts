val profileApi: String by project
val profileApplication: String by project
val profileRdbAdapter: String by project
val profileWebMvcAdapter: String by project

dependencies {
    api(project(profileApi))
    api(project(profileApplication))
    api(project(profileRdbAdapter))
    api(project(profileWebMvcAdapter))
}
