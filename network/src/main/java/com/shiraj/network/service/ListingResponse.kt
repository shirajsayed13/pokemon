package com.shiraj.network.service

import com.shiraj.core.model.Pokemon
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


internal fun ListingResponse.Result.toResult() = Pokemon(
    name = name,
    url = url
)


@JsonClass(generateAdapter = true)
data class ListingResponse(
    @Json(name = "count")
    val success: Int,
    @Json(name = "next")
    val next: String,
    @Json(name = "results")
    val results: List<Result>
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "name")
        val name: String,
        @Json(name = "url")
        val url: String,
    )
}