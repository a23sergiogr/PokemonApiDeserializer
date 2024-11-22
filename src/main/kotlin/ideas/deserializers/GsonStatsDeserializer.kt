package org.example.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.example.ideas.Stat
import org.example.ideas.StatType
import java.lang.reflect.Type
import java.net.URL

class GsonStatsDeserializer: JsonDeserializer<Stat> {
    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext
    ): Stat {
        val jsonObject = json.asJsonObject

        val baseStat = jsonObject.get("base_stat").asInt
        val effort = jsonObject.get("effort").asInt
        val name = StatType.fromString(jsonObject.getAsJsonObject("stat").get("name").asString)
        val url = URL(jsonObject.getAsJsonObject("stat").get("url").asString)

        return Stat(baseStat, effort, name, url)
    }
}