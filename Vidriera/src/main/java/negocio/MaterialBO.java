/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dao.MaterialDAO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import modelo.Material;
import utils.Conexion;

/**
 *
 * @author User
 */
public class MaterialBO {

    public String validarMaterial(Material material, boolean esEdicion) throws SQLException {
        if (material.getDescripcion() == null || material.getDescripcion().trim().isEmpty()) {
            return "La descripción es obligatoria.";
        }
        if (material.getTipo() == null) {
            return "Debe seleccionar un tipo de material.";
        }
        if (material.getPrecio() == null || material.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
            return "El precio debe ser un valor positivo.";
        }

        try (Connection conn = Conexion.getConnection()) {
            MaterialDAO dao = new MaterialDAO(conn);
            Integer idExcluir = esEdicion ? material.getIdMaterial() : null;

            if (dao.verificarDuplicado(material.getDescripcion(), idExcluir)) {
                return "Ya existe un material con esa descripción.";
            }
        }

        return null;
    }

    public boolean registrarMaterial(Material material) throws SQLException {
        try (Connection conn = Conexion.getConnection()) {
            MaterialDAO dao = new MaterialDAO(conn);
            return dao.crearMaterial(material);
        }
    }

    public boolean actualizarMaterial(Material material) throws SQLException {
        try (Connection conn = Conexion.getConnection()) {
            MaterialDAO dao = new MaterialDAO(conn);
            return dao.actualizarMaterial(material);
        }
    }

    public boolean cambiarEstado(int idMaterial, boolean activo) throws SQLException {
        try (Connection conn = Conexion.getConnection()) {
            MaterialDAO dao = new MaterialDAO(conn);
            return dao.cambiarEstado(idMaterial, activo);
        }
    }

    // --- Obtener datos para el Listado ---
    public List<Material> obtenerTodos() throws SQLException {
        try (Connection conn = Conexion.getConnection()) {
            MaterialDAO dao = new MaterialDAO(conn);
            return dao.obtenerTodos();
        }
    }

    public Material obtenerPorId(int idMaterial) throws SQLException {
        try (Connection conn = Conexion.getConnection()) {
            MaterialDAO dao = new MaterialDAO(conn);
            return dao.obtenerPorId(idMaterial);
        }
    }

    public List<Material> buscarPorDescripcion(String texto) throws SQLException {
        try (Connection conn = Conexion.getConnection()) {
            MaterialDAO dao = new MaterialDAO(conn);
            return dao.buscarPorDescripcion(texto);
        }
    }
}
