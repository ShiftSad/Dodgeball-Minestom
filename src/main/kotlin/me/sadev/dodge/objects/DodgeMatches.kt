package me.sadev.dodge.objects

import kotlinx.serialization.Serializable

@Serializable
data class DodgeMatches(
    val name: String, // Player's username (Identifier)
    val date: Long? = System.currentTimeMillis(), // Quando a partida ocorreu

    var kills: Int? = 0, // Quantidade de abates que o jogador conseguiu durante a partida
    var powers: Int? = 0, // Quantidade de poderes que o jogador usou durante a partida
    var arrowsShoot: Int? = 0, // Quantidade de flechas que o jogador atirou

    var won: Boolean? = false // Jogador ganhou a partida?
)
