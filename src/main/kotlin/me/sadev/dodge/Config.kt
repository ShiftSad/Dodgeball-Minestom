package me.sadev.dodge

import com.uchuhimo.konf.source.yaml
import com.uchuhimo.konf.toValue

data class Config(
    // Server config
    var ip: String? = "0.0.0.0",
    var port: Int? = 25565,
    var unknownCommand: String? = "Comando desconhecido",

    // MySQL configuration
    var jdbcUrl: String? = "jdbc:mysql://localhost:3306/database",
    var username: String? = "username",
    var password: String? = "password123",
    var driverClassName: String? = "com.mysql.jdbc.Driver",
    var poolSize: Int? = 20
)

object ConfigManager {

    @JvmStatic
    lateinit var config: Config

    fun loadConfig() {
        config = com.uchuhimo.konf.Config()
            .from.yaml.resource("config.json")
            .toValue()
    }
}
