package com.example.pharmamobile

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform