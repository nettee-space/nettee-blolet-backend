val notificationDomain: String by project

dependencies {
    api(project(notificationDomain))
}