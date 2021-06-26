package com.shiraj.network.service

import com.shiraj.core.model.Pokemon
import com.shiraj.core.webservice.PokemonWebService
import com.shiraj.network.networkCall
import javax.inject.Inject

internal class AppPokemonWebService @Inject constructor(
    private val pokemonWebService: RetrofitPokemonWebService
) : PokemonWebService {

    override suspend fun getListItems(): List<Pokemon> = networkCall(
        { pokemonWebService.getPokemon() },
        { response -> response.results.map { it.toResult() } }
    )

}