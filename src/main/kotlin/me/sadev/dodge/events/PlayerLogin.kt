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
            this.setSpawningInstance(Lobby.instance) // Mundo no qual o jogador vai entrar
            player.respawnPoint = Pos(0.0, 42.0, 0.0) // Teleporta o jogador ao spawn

            Audiences.all().sendMessage(Component.text(  // Manda para todos os jogadores mensagem de entrada
                "${player.username} entrou.",
                NamedTextColor.GREEN
            ))

            object : MinestomRunnable(executionType = ExecutionType.ASYNC) { // Runnable para executar ASYNC call no database
                override fun run() {
                    if (DBCrud().existsPlayer(player)) return // Se o jogador existe, não precisa criar no db
                    DBCrud().createPlayer(player)
                }
            }.also { it.schedule() } // Rodar o schedule
        }
    }
}