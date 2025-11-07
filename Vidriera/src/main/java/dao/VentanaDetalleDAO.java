/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Material;
import modelo.MaterialDetalle;

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
    public BigDecimal obtenerPrecio(int idVentanaDetalle) {
        BigDecimal precio = BigDecimal.ZERO;

        String sql = "SELECT m.precio, vm.cantidad "
                + "FROM VentanaDetalle_Material vm "
                + "JOIN Material m ON vm.idMaterial = m.idMaterial "
                + "WHERE vm.idVentanaDetalle = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVentanaDetalle);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BigDecimal precioMaterial = rs.getBigDecimal("precio");
                    BigDecimal cantidad = rs.getBigDecimal("cantidad");
                    precio = precio.add(precioMaterial.multiply(cantidad));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener precio: " + e.getMessage());
        }

        return precio;
    }

    public List<MaterialDetalle> obtenerMateriales(int idVentanaDetalle) {
        List<MaterialDetalle> lista = new ArrayList<>();
        String sql = "SELECT m.idMaterial, m.descripcion, m.precio, vm.cantidad "
                + "FROM VentanaDetalle_Material vm "
                + "JOIN Material m ON vm.idMaterial = m.idMaterial "
                + "WHERE vm.idVentanaDetalle = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVentanaDetalle);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Crear el objeto Material
                    Material m = new Material();
                    m.setIdMaterial(rs.getInt("idMaterial"));
                    m.setDescripcion(rs.getString("descripcion"));
                    m.setPrecio(rs.getBigDecimal("precio"));

                    // Obtener cantidad y precio unitario
                    BigDecimal cantidad = rs.getBigDecimal("cantidad");
                    BigDecimal precioUnitario = m.getPrecio();

                    // Crear MaterialDetalle con cantidad, precioUnitario y calcular total automáticamente
                    lista.add(new MaterialDetalle(m, cantidad, precioUnitario));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener materiales: " + e.getMessage());
        }

        return lista;
    }

}
