package me.sadev.dodge.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import me.sadev.dodge.ConfigManager

object Database {
    lateinit var dataSource: HikariDataSource

    fun init() {
        // Setup DataSource
        val config = HikariConfig()
        config.jdbcUrl = ConfigManager.config.jdbcUrl
        config.username = ConfigManager.config.username
        config.password = ConfigManager.config.password
        config.driverClassName = ConfigManager.config.driverClassName
        config.maximumPoolSize = ConfigManager.config.poolSize!!

        dataSource = HikariDataSource(config)

        // Create tables if not exist
        createTable()
    }

    private fun createTable() {
        val query: String = """
            CREATE TABLE IF NOT EXISTS DodgeStatus (
                name       varchar(50) not null primary key,
                UUID       varchar(36) not null,
                created    bigint      not null,
                playedTime bigint      null,
                status     text        null,
                matches    mediumtext  null
            );
        """.trimIndent()
        with (dataSource.connection) {
            createStatement().execute(query)
            this.close()
        }
    }
}
