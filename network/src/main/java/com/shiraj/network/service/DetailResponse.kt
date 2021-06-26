package com.shiraj.network.service

import com.shiraj.core.model.PokemonDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


internal fun DetailResponse.toDetailResponse() = PokemonDetail(
    abilityData = abilityData.map { it.toAbilityData() },
    stats = stats.map { it.toStats() },
    height = height,
    id = id,
    weight = weight,
)

internal fun DetailResponse.AbilityData.toAbilityData() = PokemonDetail.AbilityData(
    ability = ability.toAbility(),
)


internal fun DetailResponse.AbilityData.Ability.toAbility() = PokemonDetail.AbilityData.Ability(
    name = name,
    url = url
)

internal fun DetailResponse.Stats.toStats() = PokemonDetail.Stats(
    baseStat = base_stat,
    stat = stat.toStat()
)

internal fun DetailResponse.Stats.Stat.toStat() = PokemonDetail.Stats.Stat(
    name = name,
)

@JsonClass(generateAdapter = true)
internal data class DetailResponse(
    @Json(name = "abilities")
    val abilityData: List<AbilityData>,
    @Json(name = "stats")
    val stats: List<Stats>,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "weight")
    val weight: Int
) {

    @JsonClass(generateAdapter = true)
    internal data class Stats(
        @Json(name = "base_stat")
        val base_stat: Int,
        @Json(name = "stat")
        val stat: Stat
    ) {
        @JsonClass(generateAdapter = true)
        internal data class Stat(
            @Json(name = "name")
            val name: String
        )
    }

    @JsonClass(generateAdapter = true)
    internal data class AbilityData(
        @Json(name = "ability")
        val ability: Ability,
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