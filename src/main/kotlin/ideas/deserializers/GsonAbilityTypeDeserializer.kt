package org.example.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.example.ideas.AbilityURL
import java.lang.reflect.Type
import java.net.URL

class GsonAbilityTypeDeserializer:JsonDeserializer<AbilityURL> {
    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext
                             ): AbilityURL {
        val jsonObject = json.asJsonObject

        val abilityObject = jsonObject.getAsJsonObject("ability")

        val name = abilityObject.get("name").asString
        val url = URL(abilityObject.get("url").asString)

        return AbilityURL(name, url)
    }
}