val series: String by settings
val seriesApi: String by settings
val seriesDomain: String by settings
val seriesException: String by settings
val seriesReadModel: String by settings
val seriesApplication: String by settings
val seriesRdbAdapter: String by settings
val seriesWebMvcAdapter: String by settings

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

val seriesDirectory = getDirectories("services", "series")

// SERVICE/series
include(
    series,
    seriesApi,
    seriesDomain,
    seriesException,
    seriesReadModel,
    seriesApplication,
    seriesRdbAdapter,
    seriesWebMvcAdapter,
)

project(series).projectDir = seriesDirectory("series")
project(seriesApi).projectDir = seriesDirectory("api")
project(seriesDomain).projectDir = seriesDirectory("domain")
project(seriesException).projectDir = seriesDirectory("exception")
project(seriesReadModel).projectDir = seriesDirectory("readmodel")
project(seriesApplication).projectDir = seriesDirectory("application")
project(seriesRdbAdapter).projectDir = seriesDirectory("rdb")
project(seriesWebMvcAdapter).projectDir = seriesDirectory("web-mvc")