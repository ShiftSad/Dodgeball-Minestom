package me.sadev.dodge.events

import net.minestom.server.entity.Player
import net.minestom.server.entity.PlayerSkin
import net.minestom.server.event.player.PlayerSkinInitEvent
import net.minestom.server.timer.ExecutionType
import world.cepi.kstom.Manager
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.util.MinestomRunnable

class PlayerSkinInit {
    init {
        Manager.globalEvent.listenOnly<PlayerSkinInitEvent> {
            val player: Player = this.player
            object : MinestomRunnable(executionType = ExecutionType.ASYNC) {
                override fun run() {
                    player.skin = PlayerSkin.fromUsername(player.username)
                }
            }.also { it.schedule() }
        }
    }
}
