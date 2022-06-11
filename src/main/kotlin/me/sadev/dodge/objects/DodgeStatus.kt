package me.sadev.dodge.objects

import kotlinx.serialization.Serializable

@Serializable
data class DodgeStatus(
    val name: String,           // Player's username

    var wins: Long? = 0,        // Quantidade de vitorias do jogador
    var matches: Long? = 0,     // Quantidade de partidas do jogador
    var kills: Long? = 0,       // Quantidade de abates do jogador
    var deaths: Long? = 0,      // Quantidade de mortes do jogador

    var money: Double? = 0.0    // Dinheiro do jogador
)
