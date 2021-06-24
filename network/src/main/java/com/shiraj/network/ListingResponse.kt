package com.shiraj.network

import com.shiraj.core.Pokemon
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


internal fun ListingResponse.Result.toResult() = Pokemon(
    name = name,
    url = url
)


@JsonClass(generateAdapter = true)
internal data class ListingResponse(
    @Json(name = "count")
    val success: Int,
    @Json(name = "previous")
    val previous: String,
    @Json(name = "next")
    val next: String,
    @Json(name = "results")
    val results: List<Result>
) {
    @JsonClass(generateAdapter = true)
    internal data class Result(
        @Json(name = "name")
        val name: String,
        @Json(name = "url")
        val url: String,
    )
}