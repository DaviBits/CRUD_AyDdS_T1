package com.example.crud.Logica;

import java.sql.*;
import java.util.ArrayList;

public class PersonaDAO implements IPersonaDAO{

    public ArrayList<Persona> getPersonas(){
        ArrayList<Persona> personas = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionDB.obtener();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Personas");

            while (rs.next()) {
                personas.add(new Persona(rs.getInt("id"), rs.getString("nombre")));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return personas;
    }

    public void agregar(Persona p) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionDB.obtener();
            stmt = conn.prepareStatement("INSERT INTO Personas (nombre) VALUES (?)");
            stmt.setString(1, p.getNombre());
            stmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
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
            conn = ConexionDB.obtener();
            stmt = conn.prepareStatement("DELETE FROM Personas WHERE id = ?");
            stmt.setInt(1, p.getId());
            stmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void actualizar(Persona p) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionDB.obtener();
            stmt = conn.prepareStatement("UPDATE Personas SET nombre = ? WHERE id = ?");
            stmt.setString(1, p.getNombre());
            stmt.setInt(2, p.getId());
            stmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
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