dependencies {
    implementation(project(":security-jwt-parser"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}
