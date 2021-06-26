package com.shiraj.core.model

data class PokemonDetail(
    val abilityData: List<AbilityData>,
    val stats: List<Stats>,
    val height: Int,
    val id: Int,
    val weight: Int
) {
    data class Stats(
        val baseStat: Int,
        val stat: Stat
    ) {
        data class Stat(
            val name: String
        )
    }

    data class AbilityData(
        val ability: Ability,
    ) {
        data class Ability(
            val name: String,
            val url: String,
        )
    }

    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
}
