package org.example.ideas

import com.google.gson.GsonBuilder
import java.io.IOException
import com.google.gson.Gson
import org.example.deserializers.*
import java.net.HttpURLConnection
import java.net.URL

fun main(){
    println("prueba")
    val gsonPokemon = GsonBuilder()
        .registerTypeAdapter(Pokemon::class.java, GsonPokemonDeserializer())
        .registerTypeAdapter(Stat::class.java, GsonStatsDeserializer())
        .registerTypeAdapter(AbilityURL::class.java, GsonAbilityTypeDeserializer())
        .registerTypeAdapter(Sprite::class.java, GsonSpritesDeserializer())
        .setPrettyPrinting().create()

    val gsonAbility = GsonBuilder()
        .registerTypeAdapter(PokemonAbility::class.java, GsonPokemonAbilityDeserializer())
        .registerTypeAdapter(Ability::class.java, GsonAbilityDeserializer())
        .setPrettyPrinting().create()


    //ability(gsonAbility)
    pokemon(gsonPokemon)

}

fun ability(gson: Gson){
    val url = URL("https://pokeapi.co/api/v2/ability/7/")

    val ability: Ability

    try {
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            inputStream.bufferedReader().use { br ->
                ability = gson.fromJson(br, Ability::class.java)
            }
        }
    } catch (e: IOException) {
        throw RuntimeException(e)
    }

    println(ability)
}

fun pokemon(gson: Gson){
    //https://pokeapi.co/api/v2/pokemon/pikachu

    val url = URL("https://pokeapi.co/api/v2/pokemon/pikachu")

    val pokemon: Pokemon

    try {
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            inputStream.bufferedReader().use { br ->
                pokemon = gson.fromJson(br, Pokemon::class.java)
            }
        }
    } catch (e: IOException) {
        throw RuntimeException(e)
    }

    println(pokemon)

}



