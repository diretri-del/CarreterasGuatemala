package carreteras;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CARRETERAS DE GUATEMALA ===");
        
        Connection con = ConexionBD.obtenerConexion();
        
        if (con != null) {
            String query = "SELECT c.nombre AS carretera, c.categoria, t.id_tramo, " +
                           "cm.nombre AS comuna, tc.km_inicio_comuna, tc.km_fin_comuna " +
                           "FROM Carretera c " +
                           "JOIN Tramo t ON c.id_carretera = t.id_carretera " +
                           "JOIN Tramo_Comuna tc ON t.id_tramo = tc.id_tramo " +
                           "JOIN Comuna cm ON tc.id_comuna = cm.id_comuna " +
                           "ORDER BY c.nombre, t.id_tramo";
            
            try (PreparedStatement stmt = con.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                
                System.out.println("\n--------------------------------------------------------------------------------");
                System.out.printf("%-18s | %-10s | %-8s | %-20s | %-12s\n", 
                                  "CARRETERA", "CATEGORÍA", "TRAMO ID", "COMUNA", "KM EN COMUNA");
                System.out.println("--------------------------------------------------------------------------------");
                
                while (rs.next()) {
                    System.out.printf("%-18s | %-10s | %-8d | %-20s | Km %.2f - %.2f\n", 
                                      rs.getString("carretera"), 
                                      rs.getString("categoria"), 
                                      rs.getInt("id_tramo"), 
                                      rs.getString("comuna"), 
                                      rs.getDouble("km_inicio_comuna"), 
                                      rs.getDouble("km_fin_comuna"));
                }
                System.out.println("--------------------------------------------------------------------------------");
                
                con.close();
            } catch (SQLException e) {
                System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            }
        }
    }
}