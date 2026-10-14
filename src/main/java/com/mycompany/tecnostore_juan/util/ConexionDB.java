
package com.mycompany.tecnostore_juan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL =
            "jdbc:mysql://localhost:3306/tecnostore_db?serverTimezone=America/Bogota&useSSL=false";

    private static final String USUARIO = "root";

    // Reemplaza este texto por tu contraseña de MySQL.
    private static final String CONTRASENA =
            "Mysql115.";

    private static ConexionDB instancia;
    private Connection conexion;

    // Constructor privado: patrón Singleton
    private ConexionDB() {
    }

    public static synchronized ConexionDB getInstancia() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }

        return instancia;
    }

    // Crear o recuperar la conexión
    public Connection conectar() throws SQLException {

        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    CONTRASENA
            );
        }

        return conexion;
    }

    // Cerrar la conexión
    public void cerrarConexion() {

        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                System.out.println("Conexion cerrada.");

            } catch (SQLException e) {
                System.out.println(
                        "Error al cerrar: " + e.getMessage()
                );
            }
        }
    }

    // Prueba de conexión
    public static void main(String[] args) {

        ConexionDB db = ConexionDB.getInstancia();

        try {
            db.conectar();

            System.out.println(
                    "CONEXION EXITOSA CON MYSQL"
            );

        } catch (SQLException e) {
            System.out.println(
                    "No se pudo conectar con MySQL."
            );
            System.out.println(
                    "Detalle: " + e.getMessage()
            );

        } finally {
            db.cerrarConexion();
        }
    }
}
