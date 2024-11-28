package finishclasses

import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokedex/{id}")
    suspend fun getPokedex(@Path("id") id: Int): Pokedex

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(@Path("name") name: String): Species

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") name: Int): Species

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") name: Int): Pokemon

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Pokemon

    @GET("pokemon-form/{id}/")
    suspend fun getPokemonForm(@Path("id") id: Int): Form

    @GET("ability/{id}")
    suspend fun getAbility(@Path("id") id: Int): Ability


    @GET("move/{id}")
    suspend fun getMove(@Path("id") id: Int): Move
}
