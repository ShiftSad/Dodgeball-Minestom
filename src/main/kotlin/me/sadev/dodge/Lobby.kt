package me.sadev.dodge

import net.minestom.server.MinecraftServer
import net.minestom.server.instance.InstanceManager
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.generator.GenerationUnit


object Lobby {

    private val instanceManager: InstanceManager = MinecraftServer.getInstanceManager()
    @JvmStatic
    val instance = instanceManager.createInstanceContainer()

    fun init() {
        instance.setGenerator { unit: GenerationUnit ->
            unit.modifier().fillHeight(0, 1, Block.BEDROCK)
            unit.modifier().fillHeight(1, 40, Block.GRASS_BLOCK)
        }
    }
}
