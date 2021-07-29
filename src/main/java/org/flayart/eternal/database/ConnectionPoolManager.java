package org.flayart.eternal.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.SneakyThrows;
import org.flayart.eternal.database.objects.Credentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionPoolManager {
    @Getter private final HikariDataSource dataSource;

    public ConnectionPoolManager(Credentials credentials) {
        HikariConfig config = new HikariConfig();
        
        config.setJdbcUrl(String.format("jdbc:mysql://%s/%s", credentials.getHostname(), credentials.getDatabase()));
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setUsername(credentials.getUsername());
        config.setPassword(credentials.getPassword());
        config.addDataSourceProperty("useSSL", credentials.isUseSSL());
        
        dataSource = new HikariDataSource(config);
    }

    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

    @SneakyThrows
    public Connection getConnection() {
        return dataSource.getConnection();
    }

    @SneakyThrows
    public void close(Connection connection, ResultSet rs, PreparedStatement... stmt) {
        connection.close();
        rs.close();

        for(PreparedStatement statement : stmt) statement.close();
    }
}
