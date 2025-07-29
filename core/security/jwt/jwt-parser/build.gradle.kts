dependencies {
    api(project(":security-jwt-api"))

    // test
    testImplementation(project(":security-jwt-issuer"))
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
}