val draftApi: String by project
val blogClientWebMvc: String by project

dependencies {
    api(project(draftApi))

    // internal clients
    api(project(blogClientWebMvc))

    // local image upload
    api(project(":upload-image-local"))

    // spring
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework:spring-tx")
}