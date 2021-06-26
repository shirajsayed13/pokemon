package com.shiraj.core.model

data class PokemonDetail(
    val abilityData: List<AbilityData>,
    val stats: List<Stats>,
    val height: Int,
    val id: String,
    val weight: Int
) {
    data class Stats(
        val baseStat: Stat
    ) {
        data class Stat(
            val name: String
        )
    }

    data class AbilityData(
        val ability: Ability,
        val isHidden: Boolean,
    ) {
        data class Ability(
            val name: String,
            val url: String,
        )
    }
}
