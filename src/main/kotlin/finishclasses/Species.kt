package org.example.finishclasses

import org.example.finishclasses.urlclasses.PokemonURL
import java.net.URL

data class Species(
    val baseHappiness: Int,
    val captureRate: Int,
    val color: String,
    val evolutionChainURL: String?,
    val evolvesFromSpeciesName: String?,
    val evolvesFromSpeciesURL: String?,
    val flavorText: List<String>?,
    val formDescription: List<String>?,
    val formSwitchable: Boolean,
    val genderRate: Int,
    val genera: String,
    val growthRate: String,
    val habitad: String?,
    val id: Int,
    val name: String,
    val varieties: List<PokemonURL>?
)