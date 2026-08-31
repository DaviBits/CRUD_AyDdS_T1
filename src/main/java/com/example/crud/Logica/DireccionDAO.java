package com.example.crud.Logica;

import java.sql.*;
import java.util.ArrayList;

public class DireccionDAO implements IDireccionDAO {

    public ArrayList<Direccion> getDireccionesDe(Persona p) {
        ArrayList<Direccion> direcciones = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionDB.obtener();
            stmt = conn.prepareStatement(
                    "SELECT d.id, d.calle, d.ciudad FROM Direcciones d " +
                            "JOIN PersonaDireccion pd ON d.id = pd.direccionId " +
                            "WHERE pd.personaId = ?");
            stmt.setInt(1, p.getId());
            rs = stmt.executeQuery();

            while (rs.next()) {
                direcciones.add(new Direccion(
                        rs.getInt("id"),
                        rs.getString("calle"),
                        rs.getString("ciudad")
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
        return direcciones;
    }

    public void agregarA(int personaId, String calle, String ciudad) {
        Connection conn = null;
        PreparedStatement stmtDireccion = null;
        PreparedStatement stmtRelacion = null;
        ResultSet generatedKeys = null;

        try {
            conn = ConexionDB.obtener();

            stmtDireccion = conn.prepareStatement(
                    "INSERT INTO Direcciones (calle, ciudad) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmtDireccion.setString(1, calle);
            stmtDireccion.setString(2, ciudad);
            stmtDireccion.executeUpdate();

            generatedKeys = stmtDireccion.getGeneratedKeys();
            int direccionId = 0;
            if (generatedKeys.next()) {
                direccionId = generatedKeys.getInt(1);
            }

            stmtRelacion = conn.prepareStatement(
                    "INSERT INTO PersonaDireccion (personaId, direccionId) VALUES (?, ?)");
            stmtRelacion.setInt(1, personaId);
            stmtRelacion.setInt(2, direccionId);
            stmtRelacion.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (stmtDireccion != null) stmtDireccion.close();
                if (stmtRelacion != null) stmtRelacion.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void quitarDe(int personaId, int direccionId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionDB.obtener();
            stmt = conn.prepareStatement(
                    "DELETE FROM PersonaDireccion WHERE personaId = ? AND direccionId = ?");
            stmt.setInt(1, personaId);
            stmt.setInt(2, direccionId);
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