val auth: String by settings
val authApi: String by settings
val authDomain: String by settings
val authException: String by settings
val authReadModel: String by settings
val authApplication: String by settings
val authRdbAdapter: String by settings
val authRedisAdapter: String by settings
val authWebMvcAdapter: String by settings

fun getDirectories(vararg names: String): (String) -> File {
    var dir = rootDir
    for (name in names) {
        dir = dir.resolve(name)
    }
    return { targetName ->
        val directory = dir.walkTopDown().maxDepth(3)
            .filter(File::isDirectory)
            .associateBy { it.name }
        directory[targetName] ?: throw Error("그런 폴더가 없습니다: $targetName")
    }
}

val authDirectory = getDirectories("services", "auth")

// SERVICE/auth
include(
    auth,
    authApi,
    authDomain,
    authException,
    authReadModel,
    authApplication,
    authRdbAdapter,
    authRedisAdapter,
    authWebMvcAdapter,
)

project(auth).projectDir = authDirectory("auth")
project(authApi).projectDir = authDirectory("api")
project(authDomain).projectDir = authDirectory("domain")
project(authException).projectDir = authDirectory("exception")
project(authReadModel).projectDir = authDirectory("readmodel")
project(authApplication).projectDir = authDirectory("application")
project(authRdbAdapter).projectDir = authDirectory("rdb")
project(authRedisAdapter).projectDir = authDirectory("redis")
project(authWebMvcAdapter).projectDir = authDirectory("web-mvc")