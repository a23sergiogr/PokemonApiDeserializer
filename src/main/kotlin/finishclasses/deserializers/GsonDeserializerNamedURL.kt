package finishclasses.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import finishclasses.urlclasses.NamedURLs
import java.lang.reflect.Type

class GsonDeserializerNamedURL: JsonDeserializer<NamedURLs> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): NamedURLs  {
        var jsonObject = json.asJsonObject
        return NamedURLs(
            jsonObject.get("name").asString,
            jsonObject.get("url").asString
        )
    }}