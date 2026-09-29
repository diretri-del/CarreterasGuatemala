package carreteras;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/GuatemalaCarreteras?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println(">> Conexión a la base de datos establecida con éxito.");
        } catch (SQLException e) {
            System.err.println(">> Error de conexión: " + e.getMessage());
        }
        return conexion;
    }
}