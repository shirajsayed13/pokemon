package com.shiraj.core.webservice

import com.shiraj.core.model.Pokemon
import com.shiraj.core.model.PokemonDetail


interface PokemonWebService {
    suspend fun getListItems(page: Int): List<Pokemon>
    suspend fun getPokemon(id: String): PokemonDetail
}