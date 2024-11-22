package org.example.ideas

import org.example.finishclasses.Species
import java.net.URL

data class Pokemon(
    val id: Int,
    val name: String,
    val baseExperience: Int?,
    val height: Int?,
    val weight: Int?,
    val abilities: List<AbilityURL>?,
    val isDefault: Boolean?,
    val cries: URL?,
    val moves: List<MoveURL>?,
    val species: Species?,
    val stats: List<Stat>?,
    val sprites: Sprite?,
    //val forms: Forms,
    //val game_indices: List<Game_indice>,
    //val held_items: List,
    //val location_area_encounters
) {
    override fun toString(): String {
        return """
            Pokemon(
                id=$id,
                name='$name',
                baseExperience=$baseExperience,
                height=$height,
                weight=$weight,
                abilities=${abilities?.joinToString { it.toString() }},
                isDefault=$isDefault,
                cries=$cries,
                stats=${stats?.joinToString { it.toString() }},
                sprite=${sprites.toString()},
            )
        """.trimIndent()
    }
}

data class PokemonType(
    val name: String?,
    val url: URL?,
)
