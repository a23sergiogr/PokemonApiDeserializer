package finishclasses

import com.google.gson.GsonBuilder
import finishclasses.deserializers.*
import finishclasses.urlclasses.NamedURLs
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val gson = GsonBuilder()
        .registerTypeAdapter(NamedURLs::class.java, GsonDeserializerNamedURL())
        .registerTypeAdapter(Pokedex::class.java, GsonDeserializerPokedex())
        .registerTypeAdapter(Species::class.java, GsonDeserializerSpecies())
        .registerTypeAdapter(Pokemon::class.java, GsonDeserializerPokemon())
        .registerTypeAdapter(Sprites::class.java, GsonDeserializerSprites())
        .registerTypeAdapter(Ability::class.java, GsonDeserializerAbility())
        .registerTypeAdapter(Stat::class.java, GsonDeserializerStats())
        .registerTypeAdapter(Form::class.java, GsonDeserializerForm())
        .registerTypeAdapter(Move::class.java, GsonDeserializerMove())
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val apiService = retrofit.create(PokeApiService::class.java)

    //fetchPokedex(apiService, 2)
    //fetchSpecies(apiService, "aegislash")
    //fetchPokemonForm(apiService, 10041)
    //fetchPokemon(apiService, "bulbasaur")
    //printAbilityDetails(fetchAbility(apiService,1))
    fetchMove(apiService,1)
}

suspend fun fetchPokedex(apiService: PokeApiService, id: Int) {
    try {
        val pokedex = apiService.getPokedex(id)
        println("Pokedex obtenida: ${pokedex.name}")
        println("Description: ${pokedex.descriptions}")
        println("Número de entradas: ${pokedex.pokemonEntries.size}")
        for (entries in pokedex.pokemonEntries){
            println("\tID: ${entries.entryNumber}")
            println("\tName: ${entries.pokemonSpecies.name}")
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

suspend fun fetchSpecies(apiService: PokeApiService, speciesName: String) {
    try {
        val species = apiService.getPokemonSpecies(speciesName)

        println("Especie obtenida:")
        println("Nombre: ${species.name}")
        println("Base Happiness: ${species.baseHappiness}")
        println("Capture Rate: ${species.captureRate}")
        println("Color: ${species.color}")
        println("Evoluciona de: ${species.evolvesFromSpeciesName ?: "Ninguna"}")
        println("Evolución URL: ${species.evolvesFromSpeciesURL ?: "N/A"}")
        println("Cadena evolutiva URL: ${species.evolutionChainURL ?: "N/A"}")
        println("Género Rate: ${species.genderRate}")
        println("Hábitat: ${species.habitat ?: "Desconocido"}")
        println("Tasa de crecimiento: ${species.growthRate}")
        println("Textos descriptivos: ${species.flavorText?.joinToString(", ") ?: "No disponible"}")
        println("Formas: ${species.formDescription?.joinToString(", ") ?: "No disponible"}")
        println("Form Switchable: ${species.formSwitchable}")
        println("Variedades: ${species.varieties?.joinToString(", ") { it.name } ?: "No disponible"}")
    } catch (e: Exception) {
        println("Error al obtener la especie: ${e.message}")
        e.printStackTrace()
    }
}


suspend fun fetchPokemonForm(apiService: PokeApiService, id: Int) {
    try {
        val pokemonForm = apiService.getPokemonForm(id)

        println("ID: ${pokemonForm.id}")
        println("Name: ${pokemonForm.name}")
        println("Order: ${pokemonForm.order}")
        println("Form Order: ${pokemonForm.formOrder}")
        println("Is Default: ${pokemonForm.isDefault}")
        println("Is Battle Only: ${pokemonForm.isBattleOnly}")
        println("Is Mega: ${pokemonForm.isMega}")
        println("Form Name: ${pokemonForm.formName}")
        println("Pokemon: ${pokemonForm.pokemon?.name}, URL: ${pokemonForm.pokemon?.url}")

        println("Version Group: ${pokemonForm.versionGroup?.name}, URL: ${pokemonForm.versionGroup?.url}")

    } catch (e: Exception) {
        println("Error al obtener los datos: ${e.message}")
    }
}

suspend fun fetchPokemon(apiService: PokeApiService, pokemonName: String) {
    try {
        val pokemon = apiService.getPokemon(pokemonName)

        println("ID: ${pokemon.id}")
        println("Nombre: ${pokemon.name}")
        println("Experiencia base: ${pokemon.baseExperience}")
        println("Altura: ${pokemon.height}")
        println("Peso: ${pokemon.weight}")
        println("Es predeterminado: ${pokemon.isDefault}")
        println("Especies: ${pokemon.species?.name}")
        println("Habilidades: ${pokemon.abilities?.joinToString { it.name }}")
        println("Formas: ${pokemon.forms?.joinToString { it.name }}")
        println("Movimientos: ${pokemon.moves?.joinToString { it.name }}")
        println("Sprites normales: ${pokemon.sprites?.normalSprites}")
        println("Sprites shiny: ${pokemon.sprites?.shinySprites}")
        println("Cries: ${pokemon.cries}")
        println("Estadísticas: ${pokemon.stats?.joinToString { "${it.name}: ${it.baseStat}" }}")
        println("Tipos: ${pokemon.types?.joinToString { it.name }}")
    } catch (e: Exception) {
        println("Error al obtener los datos del Pokémon: ${e.message}")
    }
}

suspend fun fetchAbility(apiService: PokeApiService, abilityId: Int): Ability? {
    return try {
        val abilityResponse = apiService.getAbility(abilityId) // Assuming a similar endpoint exists
        abilityResponse
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
fun printAbilityDetails(ability: Ability?) {
    if (ability == null) {
        println("Ability not found or unable to fetch details.")
        return
    }

    println("Ability Details:")
    println("ID: ${ability.id}")
    println("Name: ${ability.name}")
    println("Effect: ${ability.effect ?: "No effect available"}")

    if (ability.pokemon.isNullOrEmpty()) {
        println("No Pokémon associated with this ability.")
    } else {
        println("Associated Pokémon:")
        ability.pokemon.forEach { pokemonURL ->
            val hiddenStatus = if (pokemonURL.isHidden) "Hidden" else "Visible"
            println("- ${pokemonURL.url.name} ($hiddenStatus)")
        }
    }
}

suspend fun fetchMove(apiService: PokeApiService, id: Int) {
    try {
        val move = apiService.getMove(id)
        println("Move ID: ${move.id}")
        println("Name: ${move.name ?: "Unknown"}")
        println("Accuracy: ${move.accuracy ?: "N/A"}")
        println("PP: ${move.pp ?: "N/A"}")
        println("Priority: ${move.priority ?: "N/A"}")
        println("Power: ${move.power ?: "N/A"}")
        println("Damage Class: ${move.damageClass ?: "Unknown"}")
        println("Type: ${move.type ?: "Unknown"}")
        println("Short Effect: ${move.shortEffect ?: "No description available."}")
        println("Flavor Text: ${move.flavorText ?: "No flavor text available."}")
    } catch (e: Exception) {
        println("Error fetching move:")
        e.printStackTrace()
    }
}



