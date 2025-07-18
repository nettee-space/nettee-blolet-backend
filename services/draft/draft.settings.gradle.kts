val draft: String by settings
val draftApi: String by settings
val draftDomain: String by settings
val draftException: String by settings
val draftReadModel: String by settings
val draftApplication: String by settings
val draftRdbAdapter: String by settings
val draftWebMvcAdapter: String by settings

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

val draftDirectory = getDirectories("services", "draft")

// SERVICE/draft
include(
    draft,
    draftApi,
    draftDomain,
    draftException,
    draftReadModel,
    draftApplication,
    draftRdbAdapter,
    draftWebMvcAdapter,
)

project(draft).projectDir = draftDirectory("draft")
project(draftApi).projectDir = draftDirectory("api")
project(draftDomain).projectDir = draftDirectory("domain")
project(draftException).projectDir = draftDirectory("exception")
project(draftReadModel).projectDir = draftDirectory("readmodel")
project(draftApplication).projectDir = draftDirectory("application")
project(draftRdbAdapter).projectDir = draftDirectory("rdb")
project(draftWebMvcAdapter).projectDir = draftDirectory("web-mvc")