package org.example.finishclasses

import org.example.finishclases.Pokedex
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokedex/{id}")
    suspend fun getPokedex(@Path("id") id: Int): Pokedex
}
