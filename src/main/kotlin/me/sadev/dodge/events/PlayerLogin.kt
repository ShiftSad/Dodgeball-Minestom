package me.sadev.dodge.events

import me.sadev.dodge.Lobby
import me.sadev.dodge.database.DBCrud
import me.sadev.dodge.database.Database
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.adventure.audience.Audiences
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.entity.PlayerSkin
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.timer.ExecutionType
import world.cepi.kstom.Manager
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.util.MinestomRunnable

class PlayerLogin {
    init {
        Manager.globalEvent.listenOnly<PlayerLoginEvent> {
            val player: Player = this.player
            this.setSpawningInstance(Lobby.instance)
            player.respawnPoint = Pos(0.0, 42.0, 0.0)

            Audiences.all().sendMessage(Component.text(
                "${player.username} entrou.",
                NamedTextColor.GREEN
            ))

            object : MinestomRunnable(executionType = ExecutionType.ASYNC) {
                override fun run() {
                    if (DBCrud().existsPlayer(player)) return
                    DBCrud().createPlayer(player)
                }
            }.also { it.schedule() }
        }
    }
}