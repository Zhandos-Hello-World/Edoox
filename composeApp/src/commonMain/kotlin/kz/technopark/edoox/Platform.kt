package kz.technopark.edoox

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform