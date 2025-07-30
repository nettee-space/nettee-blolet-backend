val articleDomain: String by project
val articleReadModel: String by project

dependencies {
    api(project(articleDomain))
    api(project(articleReadModel))

    // spring
    implementation("org.springframework:spring-tx")
}
