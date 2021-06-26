package com.shiraj.core

fun getImageUrl(url: String): String {
    val index = url.split("/".toRegex()).dropLast(1).last()
    return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
}