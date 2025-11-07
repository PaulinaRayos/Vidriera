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
import java.util.ArrayList;
import java.util.List;
import modelo.Material;
import modelo.MaterialDetalle;

/**
 *
 * @author pauli
 */
public class PuertaAbatibleDetalleDAO {

    private Connection conn;

    public PuertaAbatibleDetalleDAO(Connection conn) {
        this.conn = conn;
    }

    // Obtener materiales de un detalle de puerta abatible
    public List<MaterialDetalle> obtenerMateriales(int idPuertaDetalle) {
        List<MaterialDetalle> lista = new ArrayList<>();
        String sql = "SELECT m.idMaterial, m.descripcion, m.precio, pam.cantidad "
                + "FROM PuertaAbatibleDetalle_Material pam "
                + "JOIN Material m ON pam.idMaterial = m.idMaterial "
                + "WHERE pam.idPuertaDetalle = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPuertaDetalle);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Material m = new Material();
                    m.setIdMaterial(rs.getInt("idMaterial"));
                    m.setDescripcion(rs.getString("descripcion"));
                    m.setPrecio(rs.getBigDecimal("precio"));

                    BigDecimal cantidad = rs.getBigDecimal("cantidad");
                    lista.add(new MaterialDetalle(m, cantidad, m.getPrecio()));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener materiales: " + e.getMessage());
        }
        return lista;
    }

    // Calcular subtotal del detalle de puerta
    public BigDecimal calcularSubtotal(int idPuertaDetalle) {
        List<MaterialDetalle> materiales = obtenerMateriales(idPuertaDetalle);
        BigDecimal subtotal = BigDecimal.ZERO;

        for (MaterialDetalle md : materiales) {
            if (md.getCantidad() != null && md.getPrecioUnitario() != null) {
                subtotal = subtotal.add(md.getPrecioTotal());
            }
        }

        return subtotal;
    }
}
