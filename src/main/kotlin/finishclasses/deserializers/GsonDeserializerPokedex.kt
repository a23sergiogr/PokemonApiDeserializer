package org.example.finishclasses.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.example.finishclases.Pokedex
import org.example.finishclasses.urlclasses.SpeciesURL
import java.lang.reflect.Type

class GsonDeserializerPokedex : JsonDeserializer<Pokedex> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Pokedex {
        val jsonObject = json.asJsonObject

        val pokemonEntriesJsonArray = jsonObject.getAsJsonArray("pokemon_entries")

        val pokemonEntries = pokemonEntriesJsonArray.map { entry ->
            val entryObject = entry.asJsonObject

            val species = context.deserialize<SpeciesURL>(
                entryObject.get("pokemon_species"),
                SpeciesURL::class.java
            )

            Pokedex.PokemonEntries(
                entryNumber = entryObject.get("entry_number").asInt,
                pokemonSpecies = species
            )
        }

        return Pokedex(
            descriptions = jsonObject.get("descriptions").asJsonArray.get(2).asJsonObject.get("description").asString,
            id = jsonObject.get("id").asInt,
            isMainSeries = jsonObject.get("is_main_series").asBoolean,
            name = jsonObject.get("name").asString,
            pokemonEntries = pokemonEntries
        )
    }
}