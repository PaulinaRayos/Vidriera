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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author delll
 */
public class MaterialDetalleDAO {
     private Connection conexion;

    public MaterialDetalleDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    // Para Ventana 
    
    /**
     * Asociar materiales a un detalle de ventana
     * @param idVentanaDetalle ID del detalle de ventana
     * @param materiales Map con idMaterial -> cantidad
     * @return true si se asociaron correctamente
     */
    public boolean asociarMaterialesVentana(int idVentanaDetalle, Map<Integer, BigDecimal> materiales) {
        String sql = "INSERT INTO VentanaDetalle_Material (idVentanaDetalle, idMaterial, cantidad) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (Map.Entry<Integer, BigDecimal> entry : materiales.entrySet()) {
                ps.setInt(1, idVentanaDetalle);
                ps.setInt(2, entry.getKey());
                ps.setBigDecimal(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtener materiales asociados a un detalle de ventana
     * @param idVentanaDetalle ID del detalle de ventana
     * @return Map con idMaterial -> cantidad
     */
    public Map<Integer, BigDecimal> obtenerMaterialesVentana(int idVentanaDetalle) {
        String sql = "SELECT idMaterial, cantidad FROM VentanaDetalle_Material WHERE idVentanaDetalle = ?";
        Map<Integer, BigDecimal> materiales = new HashMap<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idVentanaDetalle);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                materiales.put(rs.getInt("idMaterial"), rs.getBigDecimal("cantidad"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return materiales;
    }

    /**
     * Eliminar materiales asociados a un detalle de ventana
     * @param idVentanaDetalle ID del detalle de ventana
     * @return true si se eliminaron correctamente
     */
    public boolean eliminarMaterialesVentana(int idVentanaDetalle) {
        String sql = "DELETE FROM VentanaDetalle_Material WHERE idVentanaDetalle = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idVentanaDetalle);
            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Para Canceleria 
    
    /**
     * Asociar materiales a un detalle de cancelería
     * @param idCanceleriaDetalle ID del detalle de cancelería
     * @param materiales Map con idMaterial -> cantidad
     * @return true si se asociaron correctamente
     */
    public boolean asociarMaterialesCanceleria(int idCanceleriaDetalle, Map<Integer, BigDecimal> materiales) {
        String sql = "INSERT INTO CanceleriaFijaDetalle_Material (idCanceleriaDetalle, idMaterial, cantidad) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (Map.Entry<Integer, BigDecimal> entry : materiales.entrySet()) {
                ps.setInt(1, idCanceleriaDetalle);
                ps.setInt(2, entry.getKey());
                ps.setBigDecimal(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtener materiales asociados a un detalle de cancelería
     * @param idCanceleriaDetalle ID del detalle de cancelería
     * @return Map con idMaterial -> cantidad
     */
    public Map<Integer, BigDecimal> obtenerMaterialesCanceleria(int idCanceleriaDetalle) {
        String sql = "SELECT idMaterial, cantidad FROM CanceleriaFijaDetalle_Material WHERE idCanceleriaDetalle = ?";
        Map<Integer, BigDecimal> materiales = new HashMap<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCanceleriaDetalle);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                materiales.put(rs.getInt("idMaterial"), rs.getBigDecimal("cantidad"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return materiales;
    }

    /**
     * Eliminar materiales asociados a un detalle de cancelería
     * @param idCanceleriaDetalle ID del detalle de cancelería
     * @return true si se eliminaron correctamente
     */
    public boolean eliminarMaterialesCanceleria(int idCanceleriaDetalle) {
        String sql = "DELETE FROM CanceleriaFijaDetalle_Material WHERE idCanceleriaDetalle = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCanceleriaDetalle);
            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //   Para Puerta 
    
    /**
     * Asociar materiales a un detalle de puerta
     * @param idDetallePuerta ID del detalle de puerta
     * @param materiales Map con idMaterial -> cantidad
     * @return true si se asociaron correctamente
     */
    public boolean asociarMaterialesPuerta(int idDetallePuerta, Map<Integer, BigDecimal> materiales) {
        String sql = "INSERT INTO PuertaAbatible_Material (id_detalle_puerta, idMaterial, cantidad) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (Map.Entry<Integer, BigDecimal> entry : materiales.entrySet()) {
                ps.setInt(1, idDetallePuerta);
                ps.setInt(2, entry.getKey());
                ps.setBigDecimal(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtener materiales asociados a un detalle de puerta
     * @param idDetallePuerta ID del detalle de puerta
     * @return Map con idMaterial -> cantidad
     */
    public Map<Integer, BigDecimal> obtenerMaterialesPuerta(int idDetallePuerta) {
        String sql = "SELECT idMaterial, cantidad FROM PuertaAbatible_Material WHERE id_detalle_puerta = ?";
        Map<Integer, BigDecimal> materiales = new HashMap<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idDetallePuerta);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                materiales.put(rs.getInt("idMaterial"), rs.getBigDecimal("cantidad"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return materiales;
    }

    /**
     * Eliminar materiales asociados a un detalle de puerta
     * @param idDetallePuerta ID del detalle de puerta
     * @return true si se eliminaron correctamente
     */
    public boolean eliminarMaterialesPuerta(int idDetallePuerta) {
        String sql = "DELETE FROM PuertaAbatible_Material WHERE id_detalle_puerta = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idDetallePuerta);
            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}