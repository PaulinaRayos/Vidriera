/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cliente;
import modelo.Proyecto;

/**
 *
 * @author delll
 */
public class ProyectoDAO {
    private Connection conexion;
    private ClienteDAO clienteDAO;

    public ProyectoDAO(Connection conexion) {
        this.conexion = conexion;
        this.clienteDAO = new ClienteDAO(conexion);
    }

    public Proyecto obtenerPorId(int idProyecto) {
        Proyecto proyecto = null;
        String sql = "SELECT * FROM proyecto WHERE idProyecto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idProyecto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                proyecto = new Proyecto();
                proyecto.setIdProyecto(rs.getInt("idProyecto"));
                proyecto.setEstado(rs.getString("estado"));
                proyecto.setFechaInicio(rs.getDate("fechaInicio"));
                proyecto.setFechaEntregaEstimada(rs.getDate("fechaEntregaEstimada"));
                // Obtener el cliente asociado por su id
                int idCliente = rs.getInt("idCliente");
                Cliente cliente = clienteDAO.obtenerPorId(idCliente);
                proyecto.setCliente(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proyecto;
    }
}

