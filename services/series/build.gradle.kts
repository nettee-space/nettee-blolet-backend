val seriesApi: String by project
val seriesApplication: String by project
val seriesRdbAdapter: String by project
val seriesWebMvcAdapter: String by project

dependencies {
    api(project(seriesApi))
    api(project(seriesWebMvcAdapter))
}