/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cotizacion;
import utils.Conexion;

/**
 *
 * @author pauli
 */
public class CotizacionDAO {

    private Connection conexion;

    public CotizacionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Crear Cotización
    public boolean crearCotizacion(Cotizacion cotizacion) {
        String sql = "INSERT INTO cotizacion(fecha, subtotal, manoObra, iva, descuentoMonto, total, estado, idCliente, idProyecto, idVendedor) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, new java.sql.Date(cotizacion.getFecha().getTime()));
            ps.setBigDecimal(2, cotizacion.getSubtotal());
            ps.setBigDecimal(3, cotizacion.getManoObra());
            ps.setBigDecimal(4, cotizacion.getIva());
            ps.setBigDecimal(5, cotizacion.getDescuentoMonto());
            ps.setBigDecimal(6, cotizacion.getTotal());
            ps.setString(7, cotizacion.getEstado());
            ps.setInt(8, cotizacion.getCliente().getIdCliente());

            // Permitir proyecto nulo
            if (cotizacion.getProyecto() != null) {
                ps.setInt(9, cotizacion.getProyecto().getIdProyecto());
            } else {
                ps.setNull(9, java.sql.Types.INTEGER);
            }

            ps.setInt(10, cotizacion.getVendedor().getIdVendedor());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    cotizacion.setIdCotizacion(rs.getInt(1)); // asigna el ID generado
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Leer todas las Cotizaciones
    public List<Cotizacion> obtenerTodas() {
        List<Cotizacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM cotizacion";
        try (Statement stmt = conexion.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Cotizacion c = new Cotizacion();
                c.setIdCotizacion(rs.getInt("idCotizacion"));
                c.setFecha(rs.getDate("fecha"));
                c.setSubtotal(rs.getBigDecimal("subtotal"));
                c.setDescuentoMonto(rs.getBigDecimal("descuentoMonto"));
                c.setTotal(rs.getBigDecimal("total"));
                c.setEstado(rs.getString("estado"));
                // despues aqui agregar para cargar Cliente y Vendedor 
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizar Cotización
    public boolean actualizarCotizacion(Cotizacion cotizacion) {
        String sql = "UPDATE cotizacion SET fecha=?, subtotal=?, descuentoMonto=?, total=?, estado=? "
                + "WHERE idCotizacion=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(cotizacion.getFecha().getTime()));
            ps.setBigDecimal(2, cotizacion.getSubtotal());
            ps.setBigDecimal(3, cotizacion.getDescuentoMonto());
            ps.setBigDecimal(4, cotizacion.getTotal());
            ps.setString(5, cotizacion.getEstado());
            ps.setInt(6, cotizacion.getIdCotizacion());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Eliminar Cotización
    public boolean eliminarCotizacion(int idCotizacion) {
        String sql = "DELETE FROM cotizacion WHERE idCotizacion=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCotizacion);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //obtener cotizacion por id
    public Cotizacion obtenerPorId(int id) {
        Cotizacion c = null;
        try {
            Connection con = Conexion.getConnection();
            String sql = "SELECT * FROM cotizacion WHERE idCotizacion = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Cotizacion();
                c.setIdCotizacion(rs.getInt("idCotizacion"));
                c.setSubtotal(rs.getBigDecimal("subtotal"));
                c.setDescuentoMonto(rs.getBigDecimal("descuentoMonto"));
                c.setTotal(rs.getBigDecimal("total"));
                c.setEstado(rs.getString("estado"));
                // aquí puedes asignar cliente, vendedor, proyecto si quieres
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
