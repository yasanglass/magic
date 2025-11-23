package glass.yasan.magic

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform