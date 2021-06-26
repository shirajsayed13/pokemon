package com.shiraj.network.service

import com.shiraj.core.model.Pokemon
import com.shiraj.core.model.PokemonDetail
import com.shiraj.core.webservice.PokemonWebService
import com.shiraj.network.networkCall
import javax.inject.Inject

internal class AppPokemonWebService @Inject constructor(
    private val pokemonWebService: RetrofitPokemonWebService
) : PokemonWebService {

    override suspend fun getListItems(page: Int): List<Pokemon> = networkCall(
        { pokemonWebService.getPokemon(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        ) },
        { response -> response.results.map { it.toResult() } }
    )

    override suspend fun getPokemon(id: Int): PokemonDetail = networkCall(
        { pokemonWebService.getPokemonDetail(id) },
        { response -> response.toDetailResponse() }
    )

    companion object {
        private const val PAGING_SIZE = 20
    }
}