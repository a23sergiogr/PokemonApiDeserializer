package org.example

import java.net.URL


data class Ability(
    val id: Int,
    val name: String,
    val pokemon: List<PokemonAbility>?,
    val flavorText: String?,
    //generation
)

data class AbilityType(
    val name: String?,
    val url: URL?,
)

data class PokemonAbility(
    val pokemon: PokemonType?,
    val isHidden: Boolean?,
    val slot: Int?,
)
