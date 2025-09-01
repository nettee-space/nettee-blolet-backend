val articleApplication: String by project
val articleDomain: String by project
val articleException: String by project
val articleReadModel: String by project


dependencies {
    api(project(articleDomain))
    api(project(articleException))
    api(project(articleReadModel))
    api(project(articleApplication))
    compileOnly(project(":security-blolet-jwt-filter"))

    // validation
    compileOnly("jakarta.validation:jakarta.validation-api")
    compileOnly("jakarta.annotation:jakarta.annotation-api")

    // mapstruct
    compileOnly("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
}

