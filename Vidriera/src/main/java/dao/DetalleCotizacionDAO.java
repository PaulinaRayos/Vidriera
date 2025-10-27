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
import modelo.CanceleriaFijaDetalle;
import modelo.CatalogoTrabajo;
import modelo.Cotizacion;
import modelo.PuertaAbatibleDetalle;
import modelo.TipoCanceleria;
import modelo.TipoPuerta;
import modelo.TipoVentana;
import modelo.VentanaDetalle;

/**
 *
 * @author Vidrieria
 */
public class DetalleCotizacionDAO {

    private Connection conexion;

    public DetalleCotizacionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Guardar detalles de Ventana
    public boolean crearDetalleVentana(List<VentanaDetalle> detalles) {
        String sql = "INSERT INTO ventanadetalle(id_tipo_trabajo, id_cotizacion, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, precioSoloUnaUnidadCalculado, subtotalLinea, descripcion, tipoVentana, mosquitero, arco, tipoArco, medidaArco, tipoCanalillo, medidaCanalillo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (VentanaDetalle d : detalles) {
                ps.setInt(1, d.getTipoTrabajo().getIdCatalogo());
                ps.setInt(2, d.getCotizacion().getIdCotizacion());
                ps.setBigDecimal(3, d.getMedidaHorizontal());
                ps.setBigDecimal(4, d.getMedidaVertical());
                ps.setInt(5, d.getCantidad());
                ps.setString(6, d.getTipoCristal());
                ps.setInt(7, d.getNoHojas());
                ps.setBigDecimal(8, d.getPrecioSoloUnaUnidadCalculado());
                ps.setBigDecimal(9, d.getSubtotalLinea());
                ps.setString(10, d.getDescripcion());
                ps.setString(11, d.getTipoVentana() != null ? d.getTipoVentana().getDescripcion() : null);
                ps.setBoolean(12, d.isMosquitero());
                ps.setBoolean(13, d.isArco());
                ps.setString(14, d.getTipoArco());
                ps.setBigDecimal(15, d.getMedidaArco());
                ps.setString(16, d.getTipoCanalillo());
                ps.setBigDecimal(17, d.getMedidaCanalillo());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Guardar detalles de Cancelería
    public boolean crearDetalleCanceleria(List<CanceleriaFijaDetalle> detalles) {
        String sql = "INSERT INTO canceleriafijadetalle(id_tipo_trabajo, id_cotizacion, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, precioSoloUnaUnidadCalculado, subtotalLinea, descripcion, tipoCanceleria, bolsa, numFijosVerticales, numFijosHorizontales, tipoTapa, cantidadTapa, zoclo, tipoZoclo, junquillo, tipoJunquillo, arco, tipoArco, medidaArco, canalillo, tipoCanalillo, medidaCanalillo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (CanceleriaFijaDetalle d : detalles) {
                ps.setInt(1, d.getTipoTrabajo().getIdCatalogo());
                ps.setInt(2, d.getCotizacion().getIdCotizacion());
                ps.setBigDecimal(3, d.getMedidaHorizontal());
                ps.setBigDecimal(4, d.getMedidaVertical());
                ps.setInt(5, d.getCantidad());
                ps.setString(6, d.getTipoCristal());
                ps.setInt(7, d.getNoHojas());
                ps.setBigDecimal(8, d.getPrecioSoloUnaUnidadCalculado());
                ps.setBigDecimal(9, d.getSubtotalLinea());
                ps.setString(10, d.getDescripcion());
                ps.setString(11, d.getTipoCanceleria() != null ? d.getTipoCanceleria().getDescripcion() : null);
                ps.setBoolean(12, d.isBolsa());
                ps.setInt(13, d.getNumFijosVerticales());
                ps.setInt(14, d.getNumFijosHorizontales());
                ps.setString(15, d.getTipoTapa());
                ps.setInt(16, d.getCantidadTapa());
                ps.setBoolean(17, d.isZoclo());
                ps.setString(18, d.getTipoZoclo());
                ps.setBoolean(19, d.isJunquillo());
                ps.setString(20, d.getTipoJunquillo());
                ps.setBoolean(21, d.isArco());
                ps.setString(22, d.getTipoArco());
                ps.setBigDecimal(23, d.getMedidaArco());
                ps.setBoolean(24, d.isCanalillo());
                ps.setString(25, d.getTipoCanalillo());
                ps.setBigDecimal(26, d.getMedidaCanalillo());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Guardar detalles de Puerta Abatible
    public boolean crearDetallePuerta(List<PuertaAbatibleDetalle> detalles) {
        String sql = "INSERT INTO detalle_puertaabatible(id_tipo_trabajo, id_cotizacion, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, precioSoloUnaUnidadCalculado, subtotalLinea, descripcion, tipo_puerta, mosquitero, duela, tipo_duela, medida_duela, adaptador, tipo_adaptador, junquillo, tipo_junquillo, canal, tipo_canal, pivote, tipo_pivote, cantidad_pivote, jaladera, tipo_jaladera, cantidad_jaladera, barra, tipo_barra) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (PuertaAbatibleDetalle d : detalles) {
                ps.setInt(1, d.getTipoTrabajo().getIdCatalogo());
                ps.setInt(2, d.getCotizacion().getIdCotizacion());
                ps.setBigDecimal(3, d.getMedidaHorizontal());
                ps.setBigDecimal(4, d.getMedidaVertical());
                ps.setInt(5, d.getCantidad());
                ps.setString(6, d.getTipoCristal());
                ps.setInt(7, d.getNoHojas());
                ps.setBigDecimal(8, d.getPrecioSoloUnaUnidadCalculado());
                ps.setBigDecimal(9, d.getSubtotalLinea());
                ps.setString(10, d.getDescripcion());
                ps.setString(11, d.getTipoPuerta() != null ? d.getTipoPuerta().name() : null);
                ps.setBoolean(12, d.isMosquitero());
                ps.setBoolean(13, d.isDuela());
                ps.setString(14, d.getTipoDuela());
                ps.setBigDecimal(15, d.getMedidaDuela());
                ps.setBoolean(16, d.isAdaptador());
                ps.setString(17, d.getTipoAdaptador());
                ps.setBoolean(18, d.isJunquillo());
                ps.setString(19, d.getTipoJunquillo());
                ps.setBoolean(20, d.isCanal());
                ps.setString(21, d.getTipoCanal());
                ps.setBoolean(22, d.isPivote());
                ps.setString(23, d.getTipoPivote());
                ps.setInt(24, d.getCantidadPivote());
                ps.setBoolean(25, d.isJaladera());
                ps.setString(26, d.getTipoJaladera());
                ps.setInt(27, d.getCantidadJaladera());
                ps.setBoolean(28, d.isBarra());
                ps.setString(29, d.getTipoBarra());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarDetallesVentanaPorCotizacionId(int idCotizacion) {
        String sql = "DELETE FROM ventanadetalle WHERE id_cotizacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCotizacion);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarDetallesCanceleriaPorCotizacionId(int idCotizacion) {
        String sql = "DELETE FROM canceleriafijadetalle WHERE id_cotizacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCotizacion);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarDetallesPuertaPorCotizacionId(int idCotizacion) {
        String sql = "DELETE FROM detalle_puertaabatible WHERE id_cotizacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCotizacion);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<VentanaDetalle> obtenerVentanasPorCotizacion(int idCotizacion) throws SQLException {
        List<VentanaDetalle> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventanadetalle WHERE id_cotizacion = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCotizacion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearVentanaDetalle(rs));
                }
            }
        }
        return lista;
    }

    public List<CanceleriaFijaDetalle> obtenerCanceleriasPorCotizacion(int idCotizacion) throws SQLException {
        List<CanceleriaFijaDetalle> lista = new ArrayList<>();
        String sql = "SELECT * FROM canceleriafijadetalle WHERE id_cotizacion = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCotizacion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearCanceleriaDetalle(rs));
                }
            }
        }
        return lista;
    }

    public List<PuertaAbatibleDetalle> obtenerPuertasPorCotizacion(int idCotizacion) throws SQLException {
        List<PuertaAbatibleDetalle> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_puertaabatible WHERE id_cotizacion = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCotizacion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPuertaAbatible(rs));
                }
            }
        }
        return lista;
    }

    private VentanaDetalle mapearVentanaDetalle(ResultSet rs) throws SQLException {
        VentanaDetalle d = new VentanaDetalle();

        d.setIdVentanaDetalle(rs.getInt("idVentanaDetalle"));
        d.setMedidaHorizontal(rs.getBigDecimal("medidaHorizontal"));
        d.setMedidaVertical(rs.getBigDecimal("medidaVertical"));
        d.setCantidad(rs.getInt("cantidad"));
        d.setTipoCristal(rs.getString("tipoCristal"));
        d.setNoHojas(rs.getInt("noHojas"));
        d.setPrecioSoloUnaUnidadCalculado(rs.getBigDecimal("precioSoloUnaUnidadCalculado"));
        d.setSubtotalLinea(rs.getBigDecimal("subtotalLinea"));
        d.setDescripcion(rs.getString("descripcion"));
        d.setTipoVentana(TipoVentana.fromDescripcion(rs.getString("tipoVentana")));
        d.setMosquitero(rs.getBoolean("mosquitero"));
        d.setArco(rs.getBoolean("arco"));
        d.setTipoArco(rs.getString("tipoArco"));
        d.setMedidaArco(rs.getBigDecimal("medidaArco"));
        d.setTipoCanalillo(rs.getString("tipoCanalillo"));
        d.setMedidaCanalillo(rs.getBigDecimal("medidaCanalillo"));

        Cotizacion cot = new Cotizacion();
        cot.setIdCotizacion(rs.getInt("id_cotizacion"));
        d.setCotizacion(cot);

        CatalogoTrabajo ct = new CatalogoTrabajo();
        ct.setIdCatalogo(rs.getInt("id_tipo_trabajo"));
        d.setTipoTrabajo(ct);

        return d;
    }

    private CanceleriaFijaDetalle mapearCanceleriaDetalle(ResultSet rs) throws SQLException {
        CanceleriaFijaDetalle d = new CanceleriaFijaDetalle();

        d.setIdCanceleriaDetalle(rs.getInt("idCanceleriaDetalle"));
        d.setMedidaHorizontal(rs.getBigDecimal("medidaHorizontal"));
        d.setMedidaVertical(rs.getBigDecimal("medidaVertical"));
        d.setCantidad(rs.getInt("cantidad"));
        d.setTipoCristal(rs.getString("tipoCristal"));
        d.setNoHojas(rs.getInt("noHojas"));
        d.setPrecioSoloUnaUnidadCalculado(rs.getBigDecimal("precioSoloUnaUnidadCalculado"));
        d.setSubtotalLinea(rs.getBigDecimal("subtotalLinea"));
        d.setDescripcion(rs.getString("descripcion"));
        d.setTipoCanceleria(TipoCanceleria.fromDescripcion(rs.getString("tipoCanceleria")));
        d.setBolsa(rs.getBoolean("bolsa"));
        d.setNumFijosVerticales(rs.getInt("numFijosVerticales"));
        d.setNumFijosHorizontales(rs.getInt("numFijosHorizontales"));
        d.setTipoTapa(rs.getString("tipoTapa"));
        d.setCantidadTapa(rs.getInt("cantidadTapa"));
        d.setZoclo(rs.getBoolean("zoclo"));
        d.setTipoZoclo(rs.getString("tipoZoclo"));
        d.setJunquillo(rs.getBoolean("junquillo"));
        d.setTipoJunquillo(rs.getString("tipoJunquillo"));
        d.setArco(rs.getBoolean("arco"));
        d.setTipoArco(rs.getString("tipoArco"));
        d.setMedidaArco(rs.getBigDecimal("medidaArco"));
        d.setCanalillo(rs.getBoolean("canalillo"));
        d.setTipoCanalillo(rs.getString("tipoCanalillo"));
        d.setMedidaCanalillo(rs.getBigDecimal("medidaCanalillo"));

        Cotizacion cot = new Cotizacion();
        cot.setIdCotizacion(rs.getInt("id_cotizacion"));
        d.setCotizacion(cot);

        CatalogoTrabajo ct = new CatalogoTrabajo();
        ct.setIdCatalogo(rs.getInt("id_tipo_trabajo"));
        d.setTipoTrabajo(ct);

        return d;
    }

    private PuertaAbatibleDetalle mapearPuertaAbatible(ResultSet rs) throws SQLException {
        PuertaAbatibleDetalle d = new PuertaAbatibleDetalle();

        d.setIdDetallePuerta(rs.getInt("id_detalle_puerta"));
        d.setMedidaHorizontal(rs.getBigDecimal("medidaHorizontal"));
        d.setMedidaVertical(rs.getBigDecimal("medidaVertical"));
        d.setCantidad(rs.getInt("cantidad"));
        d.setTipoCristal(rs.getString("tipoCristal"));
        d.setNoHojas(rs.getInt("noHojas"));
        d.setPrecioSoloUnaUnidadCalculado(rs.getBigDecimal("precioSoloUnaUnidadCalculado"));
        d.setSubtotalLinea(rs.getBigDecimal("subtotalLinea"));
        d.setDescripcion(rs.getString("descripcion"));
        d.setTipoPuerta(TipoPuerta.fromDescripcion(rs.getString("tipo_puerta")));
        d.setMosquitero(rs.getBoolean("mosquitero"));
        d.setDuela(rs.getBoolean("duela"));
        d.setTipoDuela(rs.getString("tipo_duela"));
        d.setMedidaDuela(rs.getBigDecimal("medida_duela"));
        d.setAdaptador(rs.getBoolean("adaptador"));
        d.setTipoAdaptador(rs.getString("tipo_adaptador"));
        d.setJunquillo(rs.getBoolean("junquillo"));
        d.setTipoJunquillo(rs.getString("tipo_junquillo"));
        d.setCanal(rs.getBoolean("canal"));
        d.setTipoCanal(rs.getString("tipo_canal"));
        d.setPivote(rs.getBoolean("pivote"));
        d.setTipoPivote(rs.getString("tipo_pivote"));
        d.setCantidadPivote(rs.getInt("cantidad_pivote"));
        d.setJaladera(rs.getBoolean("jaladera"));
        d.setTipoJaladera(rs.getString("tipo_jaladera"));
        d.setCantidadJaladera(rs.getInt("cantidad_jaladera"));
        d.setBarra(rs.getBoolean("barra"));
        d.setTipoBarra(rs.getString("tipo_barra"));

        Cotizacion cot = new Cotizacion();
        cot.setIdCotizacion(rs.getInt("id_cotizacion"));
        d.setCotizacion(cot);

        CatalogoTrabajo ct = new CatalogoTrabajo();
        ct.setIdCatalogo(rs.getInt("id_tipo_trabajo"));
        d.setTipoTrabajo(ct);

        return d;
    }
}