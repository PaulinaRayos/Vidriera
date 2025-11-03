/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
/**
 *
 * @author pauli
 */
public class CanceleriaFijaDetalleDAO {

    private Connection conn;

    public CanceleriaFijaDetalleDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Obtiene el precio unitario de la cancelería fija según coincidencias.
     *
     * @param tipoCanceleria Tipo de cancelería ("2\"", "3\"")
     * @param tipoCristal    Tipo de cristal
     * @param noHojas        Número de hojas
     * @return Precio unitario, o BigDecimal.ZERO si no se encuentra
     * @throws SQLException
     */
    public BigDecimal obtenerPrecio(String tipoCanceleria, String tipoCristal, int noHojas) throws SQLException {
        String sql = "SELECT precioSoloUnaUnidadCalculado FROM canceleriafijadetalle "
                   + "WHERE tipoCanceleria = ? AND tipoCristal = ? AND noHojas = ? "
                   + "LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tipoCanceleria);
            ps.setString(2, tipoCristal);
            ps.setInt(3, noHojas);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    BigDecimal precio = rs.getBigDecimal("precioSoloUnaUnidadCalculado");
                    return precio != null ? precio : BigDecimal.ZERO;
                } else {
                    return BigDecimal.ZERO; // Si no hay coincidencias
                }
            }
        }
    }
}
