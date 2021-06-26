package com.shiraj.network.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface RetrofitPokemonWebService {

    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ListingResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") name: Int
    ): DetailResponse
}