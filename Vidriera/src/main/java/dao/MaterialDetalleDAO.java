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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Material;
import modelo.MaterialDetalle;

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
     *
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<MaterialDetalle> obtenerMaterialesVentana(int idVentanaDetalle) {
        String sql = """
        SELECT m.idMaterial, m.descripcion, m.precio, m.tipo, vdm.cantidad
        FROM VentanaDetalle_Material vdm
        JOIN material m ON vdm.idMaterial = m.idMaterial
        WHERE vdm.idVentanaDetalle = ?
    """;
        List<MaterialDetalle> materiales = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idVentanaDetalle);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Material m = new Material();
                m.setIdMaterial(rs.getInt("idMaterial"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setPrecio(rs.getBigDecimal("precio"));
                m.setTipo(Material.TipoMaterial.valueOf(rs.getString("tipo")));

                MaterialDetalle md = new MaterialDetalle();
                md.setMaterial(m);
                md.setCantidad(rs.getBigDecimal("cantidad"));

                materiales.add(md);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materiales;
    }

    /**
     * Eliminar materiales asociados a un detalle de ventana
     *
     * @param idVentanaDetalle ID del detalle de ventana
     * @return true si se eliminaron correctamente
     */
    public boolean eliminarMaterialesVentana(int idVentanaDetalle) {
        String sql = "DELETE FROM VentanaDetalle_Material WHERE idVentanaDetalle = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idVentanaDetalle);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Para Canceleria 
    /**
     * Asociar materiales a un detalle de cancelería
     *
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<MaterialDetalle> obtenerMaterialesCanceleria(int idCanceleriaDetalle) {
        String sql = """
        SELECT m.idMaterial, m.descripcion, m.precio, m.tipo, cdm.cantidad
        FROM CanceleriaFijaDetalle_Material cdm
        JOIN material m ON cdm.idMaterial = m.idMaterial
        WHERE cdm.idCanceleriaDetalle = ?
    """;

        List<MaterialDetalle> materiales = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCanceleriaDetalle);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Material m = new Material();
                m.setIdMaterial(rs.getInt("idMaterial"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setPrecio(rs.getBigDecimal("precio"));
                m.setTipo(Material.TipoMaterial.valueOf(rs.getString("tipo")));

                MaterialDetalle md = new MaterialDetalle();
                md.setMaterial(m);
                md.setCantidad(rs.getBigDecimal("cantidad"));

                materiales.add(md);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materiales;
    }

    /**
     * Eliminar materiales asociados a un detalle de cancelería
     *
     * @param idCanceleriaDetalle ID del detalle de cancelería
     * @return true si se eliminaron correctamente
     */
    public boolean eliminarMaterialesCanceleria(int idCanceleriaDetalle) {
        String sql = "DELETE FROM CanceleriaFijaDetalle_Material WHERE idCanceleriaDetalle = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCanceleriaDetalle);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //   Para Puerta 
    /**
     * Asociar materiales a un detalle de puerta
     *
     * @param idDetallePuerta ID del detalle de puerta
     * @param materiales Map con idMaterial -> cantidad
     * @return true si se asociaron correctamente
     */
    public boolean asociarMaterialesPuerta(int idDetallePuerta, Map<Integer, BigDecimal> materiales) {
        String sql = "INSERT INTO PuertaAbatibleDetalle_Material (id_detalle_puerta, idMaterial, cantidad) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (Map.Entry<Integer, BigDecimal> entry : materiales.entrySet()) {
                ps.setInt(1, idDetallePuerta);
                ps.setInt(2, entry.getKey());
                ps.setBigDecimal(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<MaterialDetalle> obtenerMaterialesPuerta(int idDetallePuerta) {
        String sql = """
        SELECT m.idMaterial, m.descripcion, m.precio, m.tipo, pdm.cantidad
        FROM PuertaAbatibleDetalle_Material pdm
        JOIN material m ON pdm.idMaterial = m.idMaterial
        WHERE pdm.id_detalle_puerta = ?
    """;

        List<MaterialDetalle> materiales = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idDetallePuerta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Material m = new Material();
                m.setIdMaterial(rs.getInt("idMaterial"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setPrecio(rs.getBigDecimal("precio"));
                m.setTipo(Material.TipoMaterial.valueOf(rs.getString("tipo")));

                MaterialDetalle md = new MaterialDetalle();
                md.setMaterial(m);
                md.setCantidad(rs.getBigDecimal("cantidad"));

                materiales.add(md);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materiales;
    }

    /**
     * Eliminar materiales asociados a un detalle de puerta
     *
     * @param idDetallePuerta ID del detalle de puerta
     * @return true si se eliminaron correctamente
     */
    public boolean eliminarMaterialesPuerta(int idDetallePuerta) {
        String sql = "DELETE FROM PuertaAbatibleDetalle_Material WHERE id_detalle_puerta = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idDetallePuerta);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //detallepuertabatible
    public List<String> obtenerValoresDistinctPuerta(String columna) throws SQLException {
        List<String> valores = new ArrayList<>();
        String sql = "SELECT DISTINCT " + columna
                + " FROM detalle_puertaabatible "
                + " WHERE " + columna + " IS NOT NULL AND " + columna + " <> '' "
                + " ORDER BY " + columna;

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                valores.add(rs.getString(columna));
            }
        }
        return valores;
    }
//detallecanceleria

    public List<String> obtenerValoresDistinctCanceleria(String columna) throws SQLException {
        List<String> valores = new ArrayList<>();
        String sql = "SELECT DISTINCT " + columna
                + " FROM canceleriafijadetalle "
                + " WHERE " + columna + " IS NOT NULL AND " + columna + " <> '' "
                + " ORDER BY " + columna;

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                valores.add(rs.getString(columna));
            }
        }
        return valores;
    }

    //detalleventana
    public List<String> obtenerValoresDistinctVentana(String columna) throws SQLException {
        List<String> valores = new ArrayList<>();
        String sql = "SELECT DISTINCT " + columna
                + " FROM ventanadetalle "
                + " WHERE " + columna + " IS NOT NULL AND " + columna + " <> '' "
                + " ORDER BY " + columna;

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                valores.add(rs.getString(columna));
            }
        }
        return valores;
    }

}
