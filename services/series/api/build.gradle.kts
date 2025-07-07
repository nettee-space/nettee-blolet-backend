val seriesDomain: String by project
val seriesException: String by project
val seriesReadModel: String by project

dependencies {
    api(project(seriesDomain))
    api(project(seriesException))
    api(project(seriesReadModel))
}