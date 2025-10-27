/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Vendedor;

/**
 *
 * @author delll
 */
public class VendedorDAO {
    private Connection conexion;

    public VendedorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public Vendedor obtenerPorId(int idVendedor) {
        Vendedor vendedor = null;
        String sql = "SELECT * FROM vendedor WHERE idVendedor = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idVendedor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                vendedor = new Vendedor();
                vendedor.setIdVendedor(rs.getInt("idVendedor"));
                vendedor.setNombre(rs.getString("nombre"));
                vendedor.setTelefono(rs.getString("telefono"));
                vendedor.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendedor;
    }


}
