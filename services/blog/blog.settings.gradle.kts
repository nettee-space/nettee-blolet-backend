val blog: String by settings
val blogApi: String by settings
val blogDomain: String by settings
val blogException: String by settings
val blogReadModel: String by settings
val blogApplication: String by settings
val blogRdbAdapter: String by settings
val blogWebMvcAdapter: String by settings

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

val blogDirectory = getDirectories("services", "blog")

// SERVICE/blog
include(
    blog,
    blogApi,
    blogDomain,
    blogException,
    blogReadModel,
    blogApplication,
    blogRdbAdapter,
    blogWebMvcAdapter,
)

project(blog).projectDir = blogDirectory("blog")
project(blogApi).projectDir = blogDirectory("api")
project(blogDomain).projectDir = blogDirectory("domain")
project(blogException).projectDir = blogDirectory("exception")
project(blogReadModel).projectDir = blogDirectory("readmodel")
project(blogApplication).projectDir = blogDirectory("application")
//project(blogRdbAdapter).projectDir = blogDirectory("rdb")
project(blogWebMvcAdapter).projectDir = blogDirectory("web-mvc")