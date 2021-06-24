package com.shiraj.network

import com.shiraj.core.Pokemon
import com.shiraj.core.PokemonWebService
import javax.inject.Inject

internal class AppListingWebService @Inject constructor(
    private val pokemonWebService: RetrofitPokemonWebService
) : PokemonWebService {

    override suspend fun getListItems(): List<Pokemon> = networkCall(
        { pokemonWebService.getPokemon() },
        { response -> response.results.map { it.toResult() } }
    )

}