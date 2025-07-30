val blogApi: String by project
val blogApplication: String by project

dependencies {
    api(project(blogApi))
    api(project(blogApplication))
    api(project(":jpa-core"))

    // mapstruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
}