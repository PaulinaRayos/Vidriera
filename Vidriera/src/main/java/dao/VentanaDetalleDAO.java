/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author pauli
 */
public class VentanaDetalleDAO {

    private Connection conn;

    public VentanaDetalleDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Obtiene el precio unitario de una ventana según coincidencias
     */
    public BigDecimal obtenerPrecio(String tipoVentana, String tipoCristal, int noHojas) {
        BigDecimal precio = BigDecimal.ZERO;
        String sql = "SELECT precioSoloUnaUnidadCalculado FROM ventanadetalle " +
                     "WHERE tipoVentana = ? AND tipoCristal = ? AND noHojas = ? LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tipoVentana);
            ps.setString(2, tipoCristal);
            ps.setInt(3, noHojas);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    precio = rs.getBigDecimal("precioSoloUnaUnidadCalculado");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener precio: " + e.getMessage());
        }

        return precio;
    }
}
