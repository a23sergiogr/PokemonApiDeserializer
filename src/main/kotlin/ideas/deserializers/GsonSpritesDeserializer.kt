package org.example.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import org.example.ideas.Sprite
import java.net.URL

class GsonSpritesDeserializer: JsonDeserializer<Sprite> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Sprite {
        val jsonObject = json?.asJsonObject ?: throw JsonParseException("Invalid JSON for Sprites")

        val backDefault = URL(jsonObject.get("back_default")?.asString)
        val backFemale = URL(jsonObject.get("back_female")?.asString)
        val backShiny = URL(jsonObject.get("back_shiny")?.asString)
        val backShinyFemale = URL(jsonObject.get("back_shiny_female")?.asString)
        val frontDefault = URL(jsonObject.get("front_default")?.asString)
        val frontFemale = URL(jsonObject.get("front_female")?.asString)
        val frontShiny = URL(jsonObject.get("front_shiny")?.asString)
        val frontShinyFemale = URL(jsonObject.get("front_shiny_female")?.asString)


        return Sprite(
            backDefault,
            backFemale,
            backShiny,
            backShinyFemale,
            frontDefault,
            frontFemale,
            frontShiny,
            frontShinyFemale
        )
    }
}