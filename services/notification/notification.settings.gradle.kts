val notification: String by settings
val notificationApi: String by settings
val notificationDomain: String by settings
val notificationException: String by settings
val notificationReadModel: String by settings
val notificationApplication: String by settings
val notificationRdbAdapter: String by settings
val notificationWebMvcAdapter: String by settings

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

val notificationDirectory = getDirectories("services", "notification")

include(
    notification,
    notificationApi,
    notificationDomain,
    notificationException,
    notificationReadModel,
    notificationApplication,
    notificationRdbAdapter,
    notificationWebMvcAdapter
)

project(notification).projectDir = notificationDirectory("notification")
project(notificationApi).projectDir = notificationDirectory("api")
project(notificationDomain).projectDir = notificationDirectory("domain")
project(notificationException).projectDir = notificationDirectory("exception")
project(notificationReadModel).projectDir = notificationDirectory("readmodel")
project(notificationApplication).projectDir = notificationDirectory("application")
project(notificationRdbAdapter).projectDir = notificationDirectory("rdb")
project(notificationWebMvcAdapter).projectDir = notificationDirectory("web-mvc")