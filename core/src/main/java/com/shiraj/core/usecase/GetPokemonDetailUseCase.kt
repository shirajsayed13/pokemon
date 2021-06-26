package com.shiraj.core.usecase

import com.shiraj.core.model.PokemonDetail
import com.shiraj.core.webservice.PokemonWebService
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonWebService: PokemonWebService
) {

    suspend operator fun invoke(id: Int): PokemonDetail {
        return pokemonWebService.getPokemon(id)
    }
}