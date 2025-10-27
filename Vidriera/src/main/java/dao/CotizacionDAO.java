/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Cliente;
import modelo.Cotizacion;
import modelo.Proyecto;
import modelo.Vendedor;

/**
 *
 * @author Vidrieria
 */
public class CotizacionDAO {

    private Connection conexion;

    public CotizacionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public Connection getConexion() {
        return this.conexion;
    }

    // Crear Cotizacion
    public boolean crearCotizacion(Cotizacion cotizacion) {
        String sql = "INSERT INTO cotizacion (fecha, subtotal, manoObra, iva, descuentoMonto, total, estado, idCliente, idProyecto, idVendedor) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, new java.sql.Date(cotizacion.getFecha().getTime()));
            ps.setBigDecimal(2, cotizacion.getSubtotal());
            ps.setBigDecimal(3, cotizacion.getManoObra());
            ps.setBigDecimal(4, cotizacion.getIva());
            ps.setBigDecimal(5, cotizacion.getDescuentoMonto());
            ps.setBigDecimal(6, cotizacion.getTotal());
            ps.setString(7, cotizacion.getEstado());

            // Aquí pasamos el id del cliente, no el objeto
            ps.setInt(8, cotizacion.getCliente().getIdCliente());
            ps.setInt(9, cotizacion.getProyecto().getIdProyecto());
            ps.setInt(10, cotizacion.getVendedor().getIdVendedor());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    cotizacion.setIdCotizacion(rs.getInt(1));
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
    String sql = "SELECT * FROM cotizacion ORDER BY fecha DESC"; // O el orden que prefieras
    
    // Necesitas los DAOs de las otras entidades para cargar los objetos
    ClienteDAO clienteDAO = new ClienteDAO(conexion);
    ProyectoDAO proyectoDAO = new ProyectoDAO(conexion);
    VendedorDAO vendedorDAO = new VendedorDAO(conexion);

    try (Statement stmt = conexion.createStatement()) {
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Cotizacion c = new Cotizacion();
            c.setIdCotizacion(rs.getInt("idCotizacion"));
            c.setFecha(rs.getDate("fecha"));
            c.setSubtotal(rs.getBigDecimal("subtotal"));
            c.setManoObra(rs.getBigDecimal("manoObra"));
            c.setIva(rs.getBigDecimal("iva"));
            c.setDescuentoMonto(rs.getBigDecimal("descuentoMonto"));
            c.setTotal(rs.getBigDecimal("total"));
            c.setEstado(rs.getString("estado"));
            
            // --- ESTO ES LO QUE FALTABA ---
            // Le decimos que busque el cliente, proyecto y vendedor usando sus IDs
            c.setCliente(clienteDAO.obtenerPorId(rs.getInt("idCliente")));
            c.setProyecto(proyectoDAO.obtenerPorId(rs.getInt("idProyecto")));
            c.setVendedor(vendedorDAO.obtenerPorId(rs.getInt("idVendedor")));
            // --- FIN DE LA CORRECCIÓN ---
            
            lista.add(c);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}

    // ========= METODO CORREGIDO =========
    // Actualizar Cotizacion (Version Completa)
    public boolean actualizarCotizacion(Cotizacion cotizacion) {
        String sql = "UPDATE cotizacion SET fecha=?, subtotal=?, manoObra=?, iva=?, descuentoMonto=?, total=?, estado=?, idCliente=?, idProyecto=?, idVendedor=? "
                + "WHERE idCotizacion=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(cotizacion.getFecha().getTime()));
            ps.setBigDecimal(2, cotizacion.getSubtotal());
            ps.setBigDecimal(3, cotizacion.getManoObra());
            ps.setBigDecimal(4, cotizacion.getIva());
            ps.setBigDecimal(5, cotizacion.getDescuentoMonto());
            ps.setBigDecimal(6, cotizacion.getTotal());
            ps.setString(7, cotizacion.getEstado());
            ps.setInt(8, cotizacion.getCliente().getIdCliente());

            if (cotizacion.getProyecto() != null) {
                ps.setInt(9, cotizacion.getProyecto().getIdProyecto());
            } else {
                ps.setNull(9, java.sql.Types.INTEGER);
            }

            ps.setInt(10, cotizacion.getVendedor().getIdVendedor());
            ps.setInt(11, cotizacion.getIdCotizacion());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Eliminar Cotizacion
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

    // ========= METODO CORRECTO =========
    // Obtener Cotizacion por ID con datos relacionados
    public Cotizacion obtenerPorId(int id) {
        Cotizacion c = null;
        String sql = "SELECT c.*, cl.nombre AS cliente_nombre, v.nombre AS vendedor_nombre "
                + "FROM cotizacion c "
                + "JOIN cliente cl ON c.idCliente = cl.idCliente "
                + "JOIN vendedor v ON c.idVendedor = v.idVendedor "
                + "WHERE c.idCotizacion = ?";

        try (PreparedStatement ps = this.conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Cotizacion();
                c.setIdCotizacion(rs.getInt("idCotizacion"));
                c.setFecha(rs.getDate("fecha"));
                c.setSubtotal(rs.getBigDecimal("subtotal"));
                c.setManoObra(rs.getBigDecimal("manoObra"));
                c.setIva(rs.getBigDecimal("iva"));
                c.setDescuentoMonto(rs.getBigDecimal("descuentoMonto"));
                c.setTotal(rs.getBigDecimal("total"));
                c.setEstado(rs.getString("estado"));

                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("cliente_nombre"));
                c.setCliente(cliente);

                Vendedor vendedor = new Vendedor();
                vendedor.setIdVendedor(rs.getInt("idVendedor"));
                vendedor.setNombre(rs.getString("vendedor_nombre"));
                c.setVendedor(vendedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public List<Cotizacion> obtenerCotizacionesCliente(String nombreCliente) {
        List<Cotizacion> cotizaciones = new ArrayList<>();
        String sql = "SELECT c.* FROM cotizacion c INNER JOIN cliente cl ON c.idCliente = cl.idCliente WHERE cl.nombre LIKE ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombreCliente + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cotizacion cotizacion = crearCotizacionDesdeResultSet(rs);
                cotizaciones.add(cotizacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cotizaciones;
    }

    public Cotizacion obtenerCotizacionPorNumero(int numeroCotizacion) {
        String sql = "SELECT * FROM cotizacion WHERE idCotizacion = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, numeroCotizacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return crearCotizacionDesdeResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Cotizacion crearCotizacionDesdeResultSet(ResultSet rs) throws SQLException {
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setIdCotizacion(rs.getInt("idCotizacion"));
        cotizacion.setFecha(rs.getDate("fecha"));
        cotizacion.setSubtotal(rs.getBigDecimal("subtotal"));
        cotizacion.setManoObra(rs.getBigDecimal("manoObra"));
        cotizacion.setIva(rs.getBigDecimal("iva"));
        cotizacion.setDescuentoMonto(rs.getBigDecimal("descuentoMonto"));
        cotizacion.setTotal(rs.getBigDecimal("total"));
        cotizacion.setEstado(rs.getString("estado"));
        // Poblar objetos

        ClienteDAO clienteDAO = new ClienteDAO(this.conexion);
        ProyectoDAO proyectoDAO = new ProyectoDAO(this.conexion);
        VendedorDAO vendedorDAO = new VendedorDAO(this.conexion);
        Cliente cliente = clienteDAO.obtenerPorId(rs.getInt("idCliente"));
        cotizacion.setCliente(cliente);

        Proyecto proyecto = proyectoDAO.obtenerPorId(rs.getInt("idProyecto"));
        cotizacion.setProyecto(proyecto);

        Vendedor vendedor = vendedorDAO.obtenerPorId(rs.getInt("idVendedor"));
        cotizacion.setVendedor(vendedor);
        return cotizacion;
    }

    public List<Cotizacion> obtenerCotizacionesPorRangoFechas(Date fechaInicio, Date fechaFin) {
        List<Cotizacion> cotizaciones = new ArrayList<>();
        String sql = "SELECT * FROM cotizacion WHERE fecha BETWEEN ? AND ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = stmt.executeQuery();
            ClienteDAO clienteDAO = new ClienteDAO(conexion);
            ProyectoDAO proyectoDAO = new ProyectoDAO(conexion);
            VendedorDAO vendedorDAO = new VendedorDAO(conexion);
            while (rs.next()) {
                Cotizacion cotizacion = new Cotizacion();
                cotizacion.setIdCotizacion(rs.getInt("idCotizacion"));
                cotizacion.setFecha(rs.getDate("fecha"));
                cotizacion.setSubtotal(rs.getBigDecimal("subtotal"));
                cotizacion.setManoObra(rs.getBigDecimal("manoObra"));
                cotizacion.setIva(rs.getBigDecimal("iva"));
                cotizacion.setDescuentoMonto(rs.getBigDecimal("descuentoMonto"));
                cotizacion.setTotal(rs.getBigDecimal("total"));
                cotizacion.setEstado(rs.getString("estado"));
                cotizacion.setCliente(clienteDAO.obtenerPorId(rs.getInt("idCliente")));
                cotizacion.setProyecto(proyectoDAO.obtenerPorId(rs.getInt("idProyecto")));
                cotizacion.setVendedor(vendedorDAO.obtenerPorId(rs.getInt("idVendedor")));
                cotizaciones.add(cotizacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cotizaciones;
    }

    
    public List<String> obtenerEstadosCotizacion() {
    List<String> estados = new ArrayList<>();
    String sql = "SHOW COLUMNS FROM cotizacion LIKE 'estado'";
    try (Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        if (rs.next()) {
            String enumStr = rs.getString("Type");
            // Extrae los valores del ENUM: enum('Pendiente','Aceptado','Cancelada')
            enumStr = enumStr.substring(enumStr.indexOf("(") + 1, enumStr.indexOf(")"));
            String[] valores = enumStr.replace("'", "").split(",");
            for (String v : valores) {
                estados.add(v);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return estados;
}
    
    public List<String> obtenerTiposTrabajo() {
        List<String> tipos = new ArrayList<>();
        // Suponiendo tabla tipoTrabajo(id, nombre)
        String sql = "SELECT nombre FROM tipoTrabajo ORDER BY nombre";
        try (Statement stmt = conexion.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tipos.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipos;
    }
    
}
