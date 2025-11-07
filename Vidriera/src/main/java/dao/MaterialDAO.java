/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.math.BigDecimal;
import modelo.Material;
import modelo.Material.TipoMaterial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author delll
 */
public class MaterialDAO {

    private Connection conexion;

    public MaterialDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Crear un nuevo material
     *
     * @param material Material a insertar
     * @return true si se insertó correctamente
     */
    public boolean crearMaterial(Material material) {
        String sql = "INSERT INTO material (descripcion, precio, stockActual, tipo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, material.getDescripcion());
            ps.setBigDecimal(2, material.getPrecio());
            ps.setInt(3, material.getStockActual());
            ps.setString(4, material.getTipo().name());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    material.setIdMaterial(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Crear múltiples materiales en batch
     *
     * @param materiales Lista de materiales a insertar
     * @return true si se insertaron correctamente
     */
    public boolean crearMaterialesBatch(List<Material> materiales) {
        String sql = "INSERT INTO material (descripcion, precio, stockActual, tipo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (Material material : materiales) {
                ps.setString(1, material.getDescripcion());
                ps.setBigDecimal(2, material.getPrecio());
                ps.setInt(3, material.getStockActual());
                ps.setString(4, material.getTipo().name());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtener un material por su ID
     *
     * @param idMaterial ID del material
     * @return Material encontrado o null
     */
    public Material obtenerPorId(int idMaterial) {
        String sql = "SELECT * FROM material WHERE idMaterial = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idMaterial);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearMaterial(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtener todos los materiales
     *
     * @return Lista de materiales
     */
    public List<Material> obtenerTodos() {
        String sql = "SELECT * FROM material ORDER BY tipo, descripcion";
        List<Material> materiales = new ArrayList<>();

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                materiales.add(mapearMaterial(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiales;
    }

    /**
     * Obtener materiales por tipo
     *
     * @param tipo Tipo de material (Vidrio, Aluminio, Accesorio)
     * @return Lista de materiales del tipo especificado
     */
    public List<Material> obtenerPorTipo(TipoMaterial tipo) {
        String sql = "SELECT * FROM material WHERE tipo = ? ORDER BY descripcion";
        List<Material> materiales = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tipo.name());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                materiales.add(mapearMaterial(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiales;
    }

    /**
     * Buscar materiales por descripción (búsqueda parcial)
     *
     * @param descripcionBusqueda Texto a buscar en la descripción
     * @return Lista de materiales que coinciden
     */
    public List<Material> buscarPorDescripcion(String descripcionBusqueda) {
        String sql = "SELECT * FROM material WHERE descripcion LIKE ? ORDER BY tipo, descripcion";
        List<Material> materiales = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, "%" + descripcionBusqueda + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                materiales.add(mapearMaterial(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiales;
    }

    /**
     * Obtener materiales con stock disponible
     *
     * @param stockMinimo Stock mínimo requerido
     * @return Lista de materiales con stock mayor o igual al mínimo
     */
    public List<Material> obtenerConStockDisponible(int stockMinimo) {
        String sql = "SELECT * FROM material WHERE stockActual >= ? ORDER BY tipo, descripcion";
        List<Material> materiales = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, stockMinimo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                materiales.add(mapearMaterial(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiales;
    }

    /**
     * Actualizar un material existente
     *
     * @param material Material con datos actualizados
     * @return true si se actualizó correctamente
     */
    public boolean actualizarMaterial(Material material) {
        String sql = "UPDATE material SET descripcion = ?, precio = ?, stockActual = ?, tipo = ? WHERE idMaterial = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, material.getDescripcion());
            ps.setBigDecimal(2, material.getPrecio());
            ps.setInt(3, material.getStockActual());
            ps.setString(4, material.getTipo().name());
            ps.setInt(5, material.getIdMaterial());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Actualizar solo el stock de un material
     *
     * @param idMaterial ID del material
     * @param nuevoStock Nuevo valor de stock
     * @return true si se actualizó correctamente
     */
    public boolean actualizarStock(int idMaterial, int nuevoStock) {
        String sql = "UPDATE material SET stockActual = ? WHERE idMaterial = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, nuevoStock);
            ps.setInt(2, idMaterial);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Incrementar o decrementar el stock de un material
     *
     * @param idMaterial ID del material
     * @param cantidad Cantidad a sumar (positiva) o restar (negativa)
     * @return true si se actualizó correctamente
     */
    public boolean ajustarStock(int idMaterial, int cantidad) {
        String sql = "UPDATE material SET stockActual = stockActual + ? WHERE idMaterial = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            ps.setInt(2, idMaterial);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Actualizar el precio de un material
     *
     * @param idMaterial ID del material
     * @param nuevoPrecio Nuevo precio
     * @return true si se actualizó correctamente
     */
    public boolean actualizarPrecio(int idMaterial, java.math.BigDecimal nuevoPrecio) {
        String sql = "UPDATE material SET precio = ? WHERE idMaterial = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setBigDecimal(1, nuevoPrecio);
            ps.setInt(2, idMaterial);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Eliminar un material
     *
     * @param idMaterial ID del material a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean eliminarMaterial(int idMaterial) {
        String sql = "DELETE FROM material WHERE idMaterial = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idMaterial);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Verificar si un material existe
     *
     * @param idMaterial ID del material
     * @return true si el material existe
     */
    public boolean existeMaterial(int idMaterial) {
        String sql = "SELECT COUNT(*) FROM material WHERE idMaterial = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idMaterial);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtener el conteo de materiales por tipo
     *
     * @return Map con el conteo por cada tipo
     */
    public java.util.Map<TipoMaterial, Integer> obtenerContadorPorTipo() {
        String sql = "SELECT tipo, COUNT(*) as cantidad FROM material GROUP BY tipo";
        java.util.Map<TipoMaterial, Integer> contadores = new java.util.HashMap<>();

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TipoMaterial tipo = TipoMaterial.valueOf(rs.getString("tipo"));
                int cantidad = rs.getInt("cantidad");
                contadores.put(tipo, cantidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contadores;
    }

    /**
     * Mapear ResultSet a objeto Material
     *
     * @param rs ResultSet posicionado en una fila
     * @return Material mapeado
     */
    private Material mapearMaterial(ResultSet rs) throws SQLException {
        Material material = new Material();
        material.setIdMaterial(rs.getInt("idMaterial"));
        material.setDescripcion(rs.getString("descripcion"));
        material.setPrecio(rs.getBigDecimal("precio"));
        material.setStockActual(rs.getInt("stockActual"));
        material.setTipo(TipoMaterial.valueOf(rs.getString("tipo")));
        return material;
    }

    public BigDecimal obtenerPrecioMaterial(int idMaterial) {
        String sql = "SELECT precio FROM material WHERE idMaterial = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idMaterial);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal("precio");
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerPrecioMaterial(): " + e.getMessage());
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }
}
