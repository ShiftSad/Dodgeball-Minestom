package me.sadev.dodge.objects

import kotlinx.serialization.Serializable

@Serializable
data class DodgeStatus(
    val name: String,

    var wins: Long? = 0,
    var matches: Long? = 0,
    var kills: Long? = 0,
    var deaths: Long? = 0,

    var money: Double? = 0.0
)
