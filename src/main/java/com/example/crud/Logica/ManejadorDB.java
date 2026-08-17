package com.example.crud.Logica;

import java.sql.*;
import java.util.ArrayList;

public class ManejadorDB {
    private static final String URL = "jdbc:mariadb://localhost:3306/agenda";
    private static final String USER = "usuario1";
    private static final String PASSWORD = "superpassword";

    public ArrayList<Persona> getPersonas(){
        ArrayList<Persona> p=new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Registrar el driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. Establecer la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 3. Consultar la tabla Personas
            System.out.println("\n=== LISTADO DE PERSONAS ===");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Personas");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Dirección: " + direccion);
                p.add(new Persona(id, nombre, direccion));

            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("\nConexión cerrada. Programa terminado.");
        return p;
    }

    public ArrayList<Telefono> getTelefonosDe(Persona p){
        ArrayList<Telefono> t=new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Registrar el driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. Establecer la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

                int id = p.getId();

                Statement stmtTelefonos = conn.createStatement();
                ResultSet rsTelefonos = stmtTelefonos.executeQuery(
                        "SELECT * FROM Telefonos WHERE personaId = " + id);

                while (rsTelefonos.next()) {
                    t.add(new Telefono(
                            rsTelefonos.getInt("id"),
                            rsTelefonos.getInt("personaId"),
                            rsTelefonos.getString("telefono")
                    ));
                    System.out.println("    - " + rsTelefonos.getString("telefono"));
                }
                rsTelefonos.close();
                stmtTelefonos.close();


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return t;
    }

    public void agregar(Persona p){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.prepareStatement(
                    "INSERT INTO Personas (nombre, direccion) VALUES (?, ?)");
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getDireccion());
            stmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void eliminar(Persona p){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.prepareStatement(
                    "DELETE FROM Personas WHERE ID = ?");
            stmt.setInt(1, p.getId());
            stmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
