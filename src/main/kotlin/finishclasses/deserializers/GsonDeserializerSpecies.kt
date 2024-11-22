package finishclasses.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.example.finishclasses.Species
import java.lang.reflect.Type

class GsonDeserializerSpecies: JsonDeserializer<Species> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ):  Species {
        var jsonObject = json.asJsonObject

        val flavor = jsonObject.getAsJsonArray("flavor_text_entries")
        val texts: MutableList<String> = mutableListOf()

        for (element in flavor){
            val entry = element.asJsonObject
            val language = entry.getAsJsonObject("language").get("name").asString
            if (language == "es") {
                texts.add(entry.get("flavor_text").asString)
            }
        }

        val varieties = jsonObject.getAsJsonArray("varieties")

        for (element in flavor){
            var elementObject = element.asJsonObject

        }

        return Species(
            baseHappiness = jsonObject.get("base_happines").asInt,
            captureRate = jsonObject.get("capture_rate").asInt,
            color = jsonObject.getAsJsonObject("color").get("name").asString,
            evolutionChainURL = jsonObject.getAsJsonObject("evolution_chain").get("url").asString,
            evolvesFromSpeciesName = jsonObject.getAsJsonObject("evolves_from_species").get("name").asString,
            evolvesFromSpeciesURL = jsonObject.getAsJsonObject("evolves_from_species").get("url").asString,
            flavorText = texts,
            formDescription = listOf(jsonObject.getAsJsonArray("form_descriptions").get(0).asJsonObject.get("description").asString),
            formSwitchable = jsonObject.get("forms_switchable").asBoolean,
            genderRate = jsonObject.get("gender_rate").asInt,
            genera = jsonObject.getAsJsonArray("genera").get(5).asJsonObject.get("genus").asString,
            growthRate = jsonObject.getAsJsonObject("growth_rate").get("name").asString,
            habitad = jsonObject.get("habitat").asString,
            id = jsonObject.get("id").asInt,
            name = jsonObject.get("name").asString,
            varieties = null,
        )
    }
}

/**
 * data class Species(
 *     val baseHappiness: Int,
 *     val captureRate: Int,
 *     val color: String,
 *     val evolutionChainURL: String?,
 *     val evolvesFromSpeciesName: String?,
 *     val evolvesFromSpeciesURL: String?,
 *     val flavorText: List<String>?,
 *     val formDescription: List<String>?,
 *     val formSwitchable: Boolean,
 *     val genderRate: Int,
 *     val genera: String,
 *     val growthRate: String,
 *     val habitad: String?,
 *     val id: Int,
 *     val name: String,
 *     val varieties: List<PokemonURL>
 * )
 */