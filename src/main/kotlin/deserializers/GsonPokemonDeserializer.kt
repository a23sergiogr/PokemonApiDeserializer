package org.example.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.example.*
import java.lang.reflect.Type
import java.net.URL

class GsonPokemonDeserializer: JsonDeserializer<Pokemon> {
    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext
    ): Pokemon {

        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id").asInt
        val name = jsonObject.get("name").asString
        val baseExperience = jsonObject.get("base_experience").asInt
        val height = jsonObject.get("height").asInt
        val weight = jsonObject.get("weight").asInt

        val abilitiesArray = jsonObject.get("abilities").asJsonArray
        val abilities = mutableListOf<AbilityType>()
        for (element in abilitiesArray)
            abilities.add(context.deserialize(element, AbilityType::class.java))

        val cries = URL(jsonObject.get("cries").asJsonObject.get("latest").asString)
        val isDefault = jsonObject.get("is_default").asBoolean
        val moves = mutableListOf<Move>()
        val species = Species("Ejemplo Especie")

        val statsArray = jsonObject.get("stats").asJsonArray
        val stats = mutableListOf<Stat>()
        for (element in statsArray)
            stats.add(context.deserialize(element, Stat::class.java))

        val sprites = context.deserialize<Sprite>(jsonObject.get("sprites") ,Sprite::class.java)

        return Pokemon(
            id,
            name,
            baseExperience,
            height,
            weight,
            abilities,
            isDefault,
            cries,
            moves,
            species,
            stats,
            sprites
        )
    }
}