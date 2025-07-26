val core = rootDir.resolve("core")
    .walkTopDown()
    .maxDepth(3)
    .filter(File::isDirectory)
    .associateBy(File::getName)

include(
    ":client-api",
    ":cors-api",
    ":cors-webmvc",
    ":exception-handler-core",
    ":jpa-core",
    ":jwt-api",
    ":jwt-issuer",
    ":redis-api",
    ":redis-cache",
    ":redis-template",
    ":rest-client",
    ":security",
    ":snowflake-id-api",
    ":snowflake-id-hibernate",
    ":time-util",
)

project(":client-api").projectDir = core["nettee-client-api"]!!
project(":cors-api").projectDir = core["nettee-cors-api"]!!
project(":cors-webmvc").projectDir = core["nettee-cors-webmvc"]!!
project(":exception-handler-core").projectDir = core["exception-handler-core"]!!
project(":jpa-core").projectDir = core["jpa-core"]!!
project(":jwt-api").projectDir = core["jwt-api"]!!
project(":jwt-issuer").projectDir = core["jwt-issuer"]!!
project(":redis-api").projectDir = core["nettee-redis-api"]!!
project(":redis-cache").projectDir = core["nettee-redis-cache"]!!
project(":redis-template").projectDir = core["nettee-redis-template"]!!
project(":rest-client").projectDir = core["nettee-rest-client"]!!
project(":security").projectDir = core["security"]!!
project(":snowflake-id-api").projectDir = core["nettee-snowflake-id-api"]!!
project(":snowflake-id-hibernate").projectDir = core["nettee-snowflake-id-hibernate"]!!
project(":time-util").projectDir = core["time-util"]!!