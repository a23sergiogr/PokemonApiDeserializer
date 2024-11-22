package org.example.finishclasses

import com.google.gson.GsonBuilder
import org.example.finishclases.Pokedex
import org.example.finishclasses.deserializers.GsonDeserializerPokedex
import org.example.finishclasses.urlclasses.SpeciesURL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val gson = GsonBuilder()
        .registerTypeAdapter(Pokedex::class.java, GsonDeserializerPokedex())
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val apiService = retrofit.create(PokeApiService::class.java)

    fetchPokedex(apiService, 2)
}

suspend fun fetchPokedex(apiService: PokeApiService, id: Int) {
    try {
        val pokedex = apiService.getPokedex(id)
        println("Pokedex obtenida: ${pokedex.name}")
        println("Description: ${pokedex.descriptions}")
        println("Número de entradas: ${pokedex.pokemonEntries.size}")
        for (entries in pokedex.pokemonEntries){
            println("\tID: ${entries.entryNumber}")
            println("\tName: ${entries.pokemonSpecies.name}")
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

