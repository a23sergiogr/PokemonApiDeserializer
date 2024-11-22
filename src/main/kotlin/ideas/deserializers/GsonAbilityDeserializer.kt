package org.example.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.example.ideas.Ability
import org.example.ideas.PokemonAbility
import java.lang.reflect.Type

class GsonAbilityDeserializer: JsonDeserializer<Ability> {
    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext
    ): Ability {
        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id").asInt
        val name = jsonObject.get("name").asString

        val pokemonArray = jsonObject.get("pokemon").asJsonArray
        val pokemon = mutableListOf<PokemonAbility>()
        for (element in pokemonArray)
            pokemon.add(context.deserialize(element, PokemonAbility::class.java))


        val flavorText = jsonObject.getAsJsonArray("flavor_text_entries")
            ?.firstOrNull { it.asJsonObject.getAsJsonObject("language").get("name").asString == "en" }
            ?.asJsonObject
            ?.get("flavor_text")
            ?.asString

        return Ability(
            id = id,
            name = name,
            pokemon = pokemon,
            flavorText = flavorText
        )
    }
}