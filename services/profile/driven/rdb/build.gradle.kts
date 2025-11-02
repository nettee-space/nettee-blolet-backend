val profileApplication: String by project
val profileApi: String by project

dependencies {
    api(project(profileApi))
    api(project(profileApplication))
    api(project(":jpa-core"))
    api(project(":snowflake-id-hibernate"))

    // mapstruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
}
