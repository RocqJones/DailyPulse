package com.rocqjones.dailypulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform