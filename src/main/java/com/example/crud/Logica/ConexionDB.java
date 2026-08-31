package com.example.crud.Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mariadb://localhost:3306/agenda";
    private static final String USER = "usuario1";
    private static final String PASSWORD = "superpassword";

    public static Connection obtener() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de MariaDB", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}