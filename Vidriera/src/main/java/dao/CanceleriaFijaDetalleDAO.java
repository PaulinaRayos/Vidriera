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
public class CanceleriaFijaDetalleDAO {

    private Connection conn;

    public CanceleriaFijaDetalleDAO(Connection conn) {
        this.conn = conn;
    }

    // Obtener materiales de un detalle de cancelería fija
    public List<MaterialDetalle> obtenerMateriales(int idCanceleriaDetalle) {
        List<MaterialDetalle> lista = new ArrayList<>();
        String sql = "SELECT m.idMaterial, m.descripcion, m.precio, cfm.cantidad "
                + "FROM CanceleriaFijaDetalle_Material cfm "
                + "JOIN Material m ON cfm.idMaterial = m.idMaterial "
                + "WHERE cfm.idCanceleriaDetalle = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCanceleriaDetalle);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Material m = new Material();
                    m.setIdMaterial(rs.getInt("idMaterial"));
                    m.setDescripcion(rs.getString("descripcion"));
                    m.setPrecio(rs.getBigDecimal("precio"));

                    BigDecimal cantidad = rs.getBigDecimal("cantidad");
                    BigDecimal precioUnitario = m.getPrecio();
                    lista.add(new MaterialDetalle(m, cantidad, precioUnitario));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener materiales: " + e.getMessage());
        }
        return lista;
    }

    // Calcular subtotal del detalle de cancelería
    public BigDecimal calcularSubtotal(int idCanceleriaDetalle) {
        List<MaterialDetalle> materiales = obtenerMateriales(idCanceleriaDetalle);
        BigDecimal subtotal = BigDecimal.ZERO;

        for (MaterialDetalle md : materiales) {
            if (md.getCantidad() != null && md.getPrecioUnitario() != null) {
                subtotal = subtotal.add(md.getPrecioTotal());
            }
        }

        return subtotal;
    }

    
}
