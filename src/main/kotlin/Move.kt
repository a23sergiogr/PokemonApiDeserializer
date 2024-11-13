package org.example

data class Move(
    val accuracy: Int?,
    val contestEffect: ContestEffect?,
    val contestType: ContestType?,
    val damageClass: DamageClass?,
    val effectChance: Int?,
   // val effectChanges: List<EffectChange>,
    val effectEntries: List<EffectEntry>,
    val flavorTextEntries: List<FlavorTextEntry>
)

data class MoveDetails(
    val name: String,
    val url: String
)

data class ContestEffect(
    val url: String
)

data class ContestType(
    val name: String,
    val url: String
)

data class DamageClass(
    val name: String,
    val url: String
)

data class EffectEntry(
    val effect: String,
    val language: Language,
    val shortEffect: String
)

data class Language(
    val name: String,
    val url: String
)

data class FlavorTextEntry(
    val flavorText: String,
    val language: Language,
    val versionGroup: VersionGroup
)

data class VersionGroup(
    val name: String,
    val url: String
)