/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.CatalogoTrabajo;

/**
 *
 * @author Vidrieria
 */
public class CatalogoTrabajoDAO {

    private Connection conexion;

    public CatalogoTrabajoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<CatalogoTrabajo> obtenerTodos() {
        List<CatalogoTrabajo> catalogo = new ArrayList<>();
        String sql = "SELECT * FROM catalogotrabajos ORDER BY nombre";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CatalogoTrabajo trabajo = new CatalogoTrabajo();
                trabajo.setIdCatalogo(rs.getInt("idCatalogo"));
                trabajo.setNombre(rs.getString("nombre"));
                catalogo.add(trabajo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalogo;
    }
    
    /**
     * Busca un trabajo del catalogo por su ID.
     * @param idCatalogo El ID a buscar.
     * @return El objeto CatalogoTrabajo completo, o null si no se encuentra.
     */
    public CatalogoTrabajo obtenerPorId(int idCatalogo) {
        // Si el ID es 0, devuelve null
        if (idCatalogo == 0) {
            return null;
        }
        
        String sql = "SELECT * FROM catalogotrabajos WHERE idCatalogo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCatalogo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CatalogoTrabajo trabajo = new CatalogoTrabajo();
                    trabajo.setIdCatalogo(rs.getInt("idCatalogo"));
                    trabajo.setCodigoInterno(rs.getString("codigoInterno"));
                    trabajo.setNombre(rs.getString("nombre"));
                    trabajo.setDescripcion(rs.getString("descripcion"));
                    trabajo.setSerieBase(rs.getString("serieBase"));
                    trabajo.setPrecioBase(rs.getBigDecimal("precioBase"));
                    return trabajo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
