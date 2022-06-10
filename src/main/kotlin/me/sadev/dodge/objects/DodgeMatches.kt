package me.sadev.dodge.objects

import kotlinx.serialization.Serializable

@Serializable
data class DodgeMatches(
    val name: String,
    val date: Long? = System.currentTimeMillis(),

    var powers: Int? = 0,
    var arrowsShoot: Int? = 0,

    var won: Boolean? = false
)
