package org.example.finishclases

import org.example.finishclasses.urlclasses.SpeciesURL

data class Pokedex(
    val descriptions: String,
    val id: Int,
    val isMainSeries: Boolean,
    var name: String,
    val pokemonEntries: List<PokemonEntries>
) {
    data class PokemonEntries(
        val entryNumber: Int,
        val pokemonSpecies: SpeciesURL
    )
}