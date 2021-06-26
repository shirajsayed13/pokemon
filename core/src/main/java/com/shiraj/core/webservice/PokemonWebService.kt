package com.shiraj.core.webservice

import com.shiraj.core.model.Pokemon


interface PokemonWebService {
    suspend fun getListItems(): List<Pokemon>
}