val profile: String by settings
val profileApi: String by settings
val profileDomain: String by settings
val profileException: String by settings
val profileReadModel: String by settings
val profileApplication: String by settings
val profileRdbAdapter: String by settings
val profileWebMvcAdapter: String by settings

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

val profileDirectory = getDirectories("services", "profile")

// SERVICE/profile
include(
    profile,
    profileApi,
    profileDomain,
    profileException,
    profileReadModel,
    profileApplication,
    profileRdbAdapter,
    profileWebMvcAdapter,
)

project(profile).projectDir = profileDirectory("profile")
project(profileApi).projectDir = profileDirectory("api")
project(profileDomain).projectDir = profileDirectory("domain")
project(profileException).projectDir = profileDirectory("exception")
project(profileReadModel).projectDir = profileDirectory("readmodel")
project(profileApplication).projectDir = profileDirectory("application")
project(profileRdbAdapter).projectDir = profileDirectory("rdb")
project(profileWebMvcAdapter).projectDir = profileDirectory("web-mvc")
