package finishclasses

import finishclasses.urlclasses.NamedURLs

data class Form(
    val id: Int?,
    val name: String?,
    val order: Int?,
    val formOrder: Int?,
    val isDefault: Boolean?,
    val isBattleOnly: Boolean?,
    val isMega: Boolean?,
    val formName: String?,
    val pokemon: NamedURLs?,
    val types: List<NamedURLs>?,
    val versionGroup: NamedURLs?
)

