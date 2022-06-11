package me.sadev.dodge.objects


data class DodgePlayer(
    val name: String, // Player's username (Identifier)
    val UUID: String? = null, // Auto generated on mysql side, player's unique UUID
    val created: Long? = System.currentTimeMillis(), // Auto generated, when user was created

    var playedTime: Long? = 0, // How long has the player played for

    var status: DodgeStatus? = DodgeStatus(name), // Player global status, like wins, matches, kills...
    var matches: Collection<DodgeMatches>? = null //Collection of all player matches, to calculate achievements easier
)
