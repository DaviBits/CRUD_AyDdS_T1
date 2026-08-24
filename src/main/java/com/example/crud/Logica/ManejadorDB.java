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


                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Dirección: " );
                p.add(new Persona(id, nombre));

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

    public void agregar(Persona p) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.prepareStatement(
                    "INSERT INTO Personas (nombre) VALUES (?)");
            stmt.setString(1, p.getNombre());
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

    public void agregarTelefonoA(int personaID, String tel){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.prepareStatement(
                    "INSERT INTO telefonos (personaId, telefono) VALUES (?, ?)");
            stmt.setInt(1, personaID);
            stmt.setString(2, tel);
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

    public void eliminarTelefono(Telefono t, Persona p){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.prepareStatement(
                    "DELETE FROM telefonos WHERE personaId = (?) AND telefono = (?) AND id = (?)");
            stmt.setInt(1, p.getId());
            stmt.setString(2, t.getTelefono());
            stmt.setInt(3, t.getId());
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

    public void actualizarPersona(Persona p) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.prepareStatement(
                    "UPDATE Personas SET nombre = ? WHERE id = ?");
            stmt.setString(1, p.getNombre());
            stmt.setInt(2, p.getId());
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
    public void editarTelefono(int id, String nuevoNumero) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.prepareStatement(
                    "UPDATE Telefonos SET telefono = ? WHERE id = ?");
            stmt.setString(1, nuevoNumero);
            stmt.setInt(2, id);
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

    public void agregarDireccionA(int personaId, String calle, String ciudad) {
        Connection conn = null;
        PreparedStatement stmtDireccion = null;
        PreparedStatement stmtRelacion = null;
        ResultSet generatedKeys = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 1. Crear la dirección
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

            // 2. Asociarla a la persona en la tabla puente
            stmtRelacion = conn.prepareStatement(
                    "INSERT INTO PersonaDireccion (personaId, direccionId) VALUES (?, ?)");
            stmtRelacion.setInt(1, personaId);
            stmtRelacion.setInt(2, direccionId);
            stmtRelacion.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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

    // NUEVO: quita SOLO la asociación, no borra la dirección (otros podrían compartirla)
    public void quitarDireccionDe(int personaId, int direccionId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.prepareStatement(
                    "DELETE FROM PersonaDireccion WHERE personaId = ? AND direccionId = ?");
            stmt.setInt(1, personaId);
            stmt.setInt(2, direccionId);
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

    public ArrayList<Direccion> getDireccionesDe(Persona p) {
        ArrayList<Direccion> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.prepareStatement(
                    "SELECT d.id, d.calle, d.ciudad FROM Direcciones d " +
                            "JOIN PersonaDireccion pd ON d.id = pd.direccionId " +
                            "WHERE pd.personaId = ?");
            stmt.setInt(1, p.getId());
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Direccion(
                        rs.getInt("id"),
                        rs.getString("calle"),
                        rs.getString("ciudad")
                ));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return lista;
    }
}
