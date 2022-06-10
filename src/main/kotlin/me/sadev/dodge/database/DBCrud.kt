package me.sadev.dodge.database

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.sadev.dodge.objects.DodgeStatus
import net.minestom.server.entity.Player
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DBCrud {

    private val logger: Logger = LoggerFactory.getLogger("Database")

    fun createPlayer(player: Player) {
        val query: String = """
        INSERT INTO DodgePlayers(name, UUID, created, playedTime, status, matches)
        VALUES ('${player.username}', uuid(), ${System.currentTimeMillis()}, 0, '${Json.encodeToString(DodgeStatus(player.username))}', null);
        """.trimIndent()

        with (Database.dataSource.connection) {
            createStatement().execute(query)
            this.close()
        }

        logger.info("Usuario ${player.username} criado com sucesso")
    }

    fun existsPlayer(player: Player): Boolean {
        val query: String = """
        SELECT * FROM DodgePlayers WHERE name = ${player.username};
        """.trimIndent()

        with (Database.dataSource.connection) {
            val result = createStatement().executeQuery(query)
            this.close()
            logger.info(result.first().toString())
            if (result.first()) return true
        }

        logger.info("Usuario ${player.username} criado com sucesso")
        return true
    }
}
