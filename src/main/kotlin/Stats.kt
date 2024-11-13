package org.example

import java.net.URL

data class Stat(
    val baseStat: Int,
    val effort: Int,
    val name: StatType,
    val url: URL
)

enum class StatType(val displayName: String) {
    HP("hp"),
    ATTACK("attack"),
    DEFENSE("defense"),
    SPECIAL_ATTACK("special-attack"),
    SPECIAL_DEFENSE("special-defense"),
    SPEED("speed");

    companion object {
        fun fromString(name: String): StatType {
            return values().find { it.displayName.equals(name, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown StatType: $name")
        }
    }
}