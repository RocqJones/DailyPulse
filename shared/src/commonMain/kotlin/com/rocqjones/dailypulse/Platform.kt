package com.rocqjones.dailypulse

interface Platform {
    val osName: String
    val osVersion: String
    val deviceModel: String
    val density: Int
}

expect fun logSystemInfo(): Platform