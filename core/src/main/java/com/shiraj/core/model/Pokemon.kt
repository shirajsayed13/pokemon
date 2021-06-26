package com.shiraj.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val url: String,
) : Parcelable {

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
    }
}
