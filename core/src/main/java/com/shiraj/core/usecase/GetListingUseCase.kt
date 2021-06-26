package com.shiraj.core.usecase

import com.shiraj.core.model.Pokemon
import com.shiraj.core.webservice.PokemonWebService
import javax.inject.Inject

class GetListingUseCase @Inject constructor(
    private val pokemonWebService: PokemonWebService
) {

    suspend operator fun invoke(page: Int): List<Pokemon> {
        return pokemonWebService.getListItems(page)
    }
}