package com.unir.operador.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLAppender extends AppenderBase<ILoggingEvent> {

    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;

    // Métodos Setter para Logback
    public void setDB_URL(String DB_URL) { this.DB_URL = DB_URL; }
    public void setDB_USER(String DB_USER) { this.DB_USER = DB_USER; }
    public void setDB_PASSWORD(String DB_PASSWORD) { this.DB_PASSWORD = DB_PASSWORD; }

    private static final String SQL_INSERT = "INSERT INTO logs (timestamp, level, logger, message, exception) VALUES (CURRENT_TIMESTAMP, ?, ?, ?, ?)";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS logs ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                    + "level VARCHAR(10), "
                    + "logger VARCHAR(255), "
                    + "message TEXT, "
                    + "exception TEXT"
                    + ")";

    private void createTableIfNotExists() {
        if (DB_URL == null || DB_USER == null || DB_PASSWORD == null) {
            addError("Las propiedades de conexión no están inicializadas.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_TABLE)) {

            statement.executeUpdate();

        } catch (SQLException e) {
            addError("Error guardando log en MySQL", e);
        }
    }

    @Override
    public void start() {
        super.start();
        createTableIfNotExists();
    }

    @Override
    protected void append(ILoggingEvent event) {
        if (DB_URL == null || DB_USER == null || DB_PASSWORD == null) {
            addError("Las propiedades de conexión no están inicializadas.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {

            statement.setString(1, event.getLevel().toString());
            statement.setString(2, event.getLoggerName());
            statement.setString(3, event.getFormattedMessage());
            statement.setString(4, event.getThrowableProxy() != null ? event.getThrowableProxy().getMessage() : null);

            statement.executeUpdate();

        } catch (SQLException e) {
            addError("Error guardando log en MySQL", e);
        }
    }
}

