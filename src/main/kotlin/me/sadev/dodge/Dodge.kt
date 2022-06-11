package me.sadev.dodge

import com.github.ajalt.mordant.rendering.TextColors
import me.sadev.dodge.commands.CommandGamemode
import me.sadev.dodge.commands.CommandStop
import me.sadev.dodge.database.Database
import me.sadev.dodge.events.SetupEvents
import net.minestom.server.MinecraftServer
import org.slf4j.LoggerFactory
import kotlin.system.measureTimeMillis

fun main() {

    val logger = LoggerFactory.getLogger("Dodge")

    val time = measureTimeMillis {
        // Initialize the server
        val minecraftServer = MinecraftServer.init()

        // Initialize config
        ConfigManager.loadConfig()

        // Load lobby
        Lobby.init()

        // Initialize database
        Database.init()

        // Register events
        SetupEvents()

        // Register commands
        CommandStop.register()
        CommandGamemode.register()

        // Boots up the server
        minecraftServer.start(ConfigManager.config.ip!!, ConfigManager.config.port!!)
    }
    logger.info("Servidor iniciado -> ${TextColors.green("${time}ms")}")
}
