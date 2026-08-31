package com.example.crud.Logica;

import java.sql.*;
import java.util.ArrayList;

public class TelefonoDAO implements  ITelefonoDAO{

    public ArrayList<Telefono> getTelefonosDe(Persona p){
        ArrayList<Telefono> telefonos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionDB.obtener();
            stmt = conn.prepareStatement("SELECT * FROM Telefonos WHERE personaId = ?");
            stmt.setInt(1, p.getId());
            rs = stmt.executeQuery();

            while (rs.next()) {
                telefonos.add(new Telefono(
                        rs.getInt("id"),
                        rs.getInt("personaId"),
                        rs.getString("telefono")
                ));
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
        return telefonos;
    }

    public void agregarA(int personaId, String telefono){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionDB.obtener();
            stmt = conn.prepareStatement("INSERT INTO Telefonos (personaId, telefono) VALUES (?, ?)");
            stmt.setInt(1, personaId);
            stmt.setString(2, telefono);
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

    public void eliminar(Telefono t, Persona p){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionDB.obtener();
            stmt = conn.prepareStatement(
                    "DELETE FROM Telefonos WHERE personaId = ? AND telefono = ? AND id = ?");
            stmt.setInt(1, p.getId());
            stmt.setString(2, t.getTelefono());
            stmt.setInt(3, t.getId());
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

    public void editar(int id, String nuevoNumero) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionDB.obtener();
            stmt = conn.prepareStatement("UPDATE Telefonos SET telefono = ? WHERE id = ?");
            stmt.setString(1, nuevoNumero);
            stmt.setInt(2, id);
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