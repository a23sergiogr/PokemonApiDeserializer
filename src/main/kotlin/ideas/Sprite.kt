package org.example.ideas

import java.net.URL

data class Sprite(
    val backDefault: URL?,
    val backFemale: URL?,
    val backShiny: URL?,
    val backShinyFemale: URL?,
    val frontDefault: URL?,
    val frontFemale: URL?,
    val frontShiny: URL?,
    val frontShinyFemale: URL?,
){
    override fun toString(): String {
        return """
            Sprite(
                backDefault: $backDefault,
                backFemale: $backFemale,
                backShiny: $backShiny,
                backShinyFemale: $backShinyFemale,
                frontDefault: $frontDefault,
                frontFemale: $frontFemale,
                frontShiny: $frontShiny,
                frontShinyFemale: $frontShinyFemale
            )
        """.trimIndent()
    }
}