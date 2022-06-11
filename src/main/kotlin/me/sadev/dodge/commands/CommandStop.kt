package me.sadev.dodge.commands

import net.minestom.server.MinecraftServer
import net.minestom.server.command.ConsoleSender
import world.cepi.kstom.command.kommand.Kommand
import kotlin.system.exitProcess

internal object CommandStop : Kommand({
    condition {
        sender.hasPermission("server.stop")
                || sender is ConsoleSender
    }

    syntax {
        MinecraftServer.stopCleanly()
        exitProcess(0)
    }
}, "stop")
