/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Proyecto;



/**
 *
 * @author delll
 */
public class ProyectoDAO {

    private final Connection conexion;

    public ProyectoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Crear
    public boolean crearProyecto(Proyecto p) {
        String sql = "INSERT INTO proyecto (estado, fechaInicio, fechaEntregaEstimada, idCliente) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getEstado());
            ps.setDate(2, new java.sql.Date(p.getFechaInicio().getTime()));
            ps.setDate(3, new java.sql.Date(p.getFechaEntregaEstimada().getTime()));
            ps.setInt(4, p.getCliente().getIdCliente());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    p.setIdProyecto(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Obtener por id
    public Proyecto obtenerPorId(int idProyecto) {
        String sql = "SELECT p.*, c.idCliente, c.nombre "
                + "FROM proyecto p JOIN cliente c ON p.idCliente = c.idCliente "
                + "WHERE p.idProyecto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearProyecto(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Listar todos
    public List<Proyecto> obtenerTodos() {
        String sql = "SELECT p.*, c.idCliente, c.nombre "
                + "FROM proyecto p JOIN cliente c ON p.idCliente = c.idCliente "
                + "ORDER BY p.idProyecto DESC";
        List<Proyecto> lista = new ArrayList<>();
        try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearProyecto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizar
    public boolean actualizarProyecto(Proyecto p) {
        String sql = "UPDATE proyecto SET estado = ?, fechaInicio = ?, "
                + "fechaEntregaEstimada = ?, idCliente = ? "
                + "WHERE idProyecto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, p.getEstado());
            ps.setDate(2, new java.sql.Date(p.getFechaInicio().getTime()));
            ps.setDate(3, new java.sql.Date(p.getFechaEntregaEstimada().getTime()));
            ps.setInt(4, p.getCliente().getIdCliente());
            ps.setInt(5, p.getIdProyecto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Eliminar
    public boolean eliminarProyecto(int idProyecto) {
        String sql = "DELETE FROM proyecto WHERE idProyecto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Proyecto mapearProyecto(ResultSet rs) throws SQLException {
        Proyecto p = new Proyecto();
        p.setIdProyecto(rs.getInt("idProyecto"));
        p.setEstado(rs.getString("estado"));
        p.setFechaInicio(rs.getDate("fechaInicio"));
        p.setFechaEntregaEstimada(rs.getDate("fechaEntregaEstimada"));

        Cliente c = new Cliente();
        c.setIdCliente(rs.getInt("idCliente"));
        c.setNombre(rs.getString("nombre"));
        p.setCliente(c);

        return p;
    }
}
