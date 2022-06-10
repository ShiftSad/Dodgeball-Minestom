package me.sadev.dodge.events

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.adventure.audience.Audiences
import net.minestom.server.entity.Player
import net.minestom.server.event.player.PlayerDisconnectEvent
import world.cepi.kstom.Manager
import world.cepi.kstom.event.listenOnly

class PlayerDisconnect {
    init {
        Manager.globalEvent.listenOnly<PlayerDisconnectEvent> {
            val player: Player = this.player

            Audiences.all().sendMessage(
                Component.text(
                "${player.username} saiu.",
                NamedTextColor.RED
            ))
        }
    }
}