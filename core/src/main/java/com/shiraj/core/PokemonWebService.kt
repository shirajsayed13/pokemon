package com.shiraj.core


interface PokemonWebService {
    suspend fun getListItems(): List<Pokemon>
}