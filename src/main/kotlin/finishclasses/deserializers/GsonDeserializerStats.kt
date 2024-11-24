package finishclasses.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import finishclasses.Stat
import finishclasses.StatType
import java.lang.reflect.Type

class GsonDeserializerStats: JsonDeserializer<Stat> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Stat {
        val jsonObject = json.asJsonObject

        val baseStat = jsonObject.get("base_stat").asInt
        val effort = jsonObject.get("effort").asInt

        val statObject = jsonObject.getAsJsonObject("stat")
        val name = statObject.get("name").asString
        val url = statObject.get("url").asString

        return Stat(
            baseStat = baseStat,
            effort = effort,
            name = StatType.fromString(name),
            url = url
        )
    }
}