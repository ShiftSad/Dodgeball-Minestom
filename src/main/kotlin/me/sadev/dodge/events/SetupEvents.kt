package me.sadev.dodge.events

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.MinecraftServer
import net.minestom.server.adventure.audience.Audiences
import net.minestom.server.entity.Player
import net.minestom.server.event.server.ServerTickMonitorEvent
import net.minestom.server.monitoring.TickMonitor
import net.minestom.server.timer.TaskSchedule
import net.minestom.server.utils.MathUtils
import world.cepi.kstom.Manager
import world.cepi.kstom.event.listenOnly
import java.util.concurrent.atomic.AtomicReference

class SetupEvents {
    init {
        PlayerChat()        // Init PlayerChat
        PlayerDisconnect()  // Init PlayerDisconnect
        PlayerLogin()       // Init PlayerLogin
        PlayerSkinInit()    // Init PlayerSkin

        runStats()          // Cool server stats on tab
    }

    private fun runStats() {
        val lastTick: AtomicReference<TickMonitor> = AtomicReference()
        Manager.globalEvent.listenOnly<ServerTickMonitorEvent> { lastTick.set(this.tickMonitor) }

        MinecraftServer.getSchedulerManager().scheduleTask({
            val players: Collection<Player> = MinecraftServer.getConnectionManager().onlinePlayers
            if (players.isEmpty()) return@scheduleTask

            val runtime = Runtime.getRuntime()
            val tickMonitor: TickMonitor = lastTick.get()
            val ramUsage = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024

            val header = Component.newline()
                .append(Component.text("Sapiens Dodge", NamedTextColor.GOLD)
                .append(Component.newline().append(Component.text("Players: " + players.size))
                .append(Component.newline()).append(Component.newline())
                .append(Component.text("USO DE RAM -> $ramUsage MB", NamedTextColor.GRAY)
                .append(Component.newline())
                .append(Component.text("MSPT -> " + MathUtils.round
                (tickMonitor.tickTime, 2) + " ms",
                NamedTextColor.GRAY)).append(Component.newline()))))

            Audiences.players().sendPlayerListHeader(header)
        }, TaskSchedule.tick(10), TaskSchedule.tick(10))
    }
}