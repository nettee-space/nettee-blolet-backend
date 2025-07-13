val notificationDomain: String by project
val notificationException: String by project
val notificationReadModel: String by project

dependencies {
    api(project(notificationDomain))
    api(project(notificationException))
    api(project(notificationReadModel))
}