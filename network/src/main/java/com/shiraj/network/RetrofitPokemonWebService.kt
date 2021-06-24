package com.shiraj.network

import retrofit2.http.GET

internal interface RetrofitPokemonWebService {

    @GET("pokemon/?offset=0&limit=300")
    suspend fun getPokemon(): ListingResponse
}