package me.sadev.dodge.events

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.event.player.PlayerChatEvent
import world.cepi.kstom.Manager
import world.cepi.kstom.event.listenOnly

class PlayerChat {
    init {
        Manager.globalEvent.listenOnly<PlayerChatEvent> {
            this.setChatFormat {
                Component.text(it.player.username).append(Component.text(" | ",
                    NamedTextColor.DARK_GRAY)).append(Component.text(it.message, NamedTextColor.WHITE))
            }
        }
    }
}
