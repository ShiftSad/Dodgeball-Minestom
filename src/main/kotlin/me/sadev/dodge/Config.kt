package me.sadev.dodge

class Config {
    // Config
    val ip: String = "0.0.0.0"
    val port: Int = 10014
    val unknownCommand: String = "Comando desconhecido!"

    // Mysql configuration
    val jdbcUrl = "jdbc:mysql://190.115.196.20:3306/s21410_jogo"
    val username = "u21410_uPxelVqfvT"
    val password = "@K5tZBttoqpFVzapppl+bC3x"
    val driverClassName = "com.mysql.jdbc.Driver"
    val poolSize = 20
}

object ConfigManager {

    @JvmStatic
    val config = Config()
}
