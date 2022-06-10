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
            CREATE TABLE IF NOT EXISTS sapiens_Vips (
                Name       varchar(50) not null primary key,
                UUID       varchar(36) not null,
                Started    BIGINT      not null,
                Permission varchar(50) not null
            );
        """.trimIndent()
        with (dataSource.connection) {
            createStatement().executeQuery(query)
            this.close()
        }
    }
}
