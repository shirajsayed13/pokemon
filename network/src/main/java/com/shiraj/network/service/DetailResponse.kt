package com.shiraj.network.service

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class DetailResponse(
    @Json(name = "abilities")
    val success: List<AbilityData>,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val next: String,
    @Json(name = "weight")
    val weight: Int
) {
    @JsonClass(generateAdapter = true)
    internal data class AbilityData(
        @Json(name = "ability")
        val name: Ability,
        @Json(name = "is_hidden")
        val isHidden: Boolean,
    ) {
        @JsonClass(generateAdapter = true)
        internal data class Ability(
            @Json(name = "name")
            val name: String,
            @Json(name = "url")
            val url: String,
        )
    }
}