package org.example.deserializers

import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.example.PokemonAbility
import java.lang.reflect.Type
import com.google.gson.JsonDeserializationContext
import org.example.PokemonType

class GsonPokemonAbilityDeserializer : JsonDeserializer<PokemonAbility> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): PokemonAbility {
        val jsonObject = json.asJsonObject

        val isHidden = jsonObject.get("is_hidden")?.asBoolean
        val slot = jsonObject.get("slot")?.asInt
        val pokemonJson = jsonObject.get("pokemon")?.asJsonObject
        val pokemon = pokemonJson?.let { context.deserialize<PokemonType>(it, PokemonType::class.java) }

        return PokemonAbility(
            pokemon = pokemon,
            isHidden = isHidden,
            slot = slot
        )
    }
}