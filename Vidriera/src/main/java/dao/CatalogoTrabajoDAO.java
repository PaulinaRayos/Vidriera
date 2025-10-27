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
}
