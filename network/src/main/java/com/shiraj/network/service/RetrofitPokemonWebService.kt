package com.shiraj.network.service

import retrofit2.http.GET

internal interface RetrofitPokemonWebService {

    @GET("pokemon/?offset=10&limit=10")
    suspend fun getPokemon(): ListingResponse
}