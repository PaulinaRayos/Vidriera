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
import modelo.CanceleriaFijaDetalle;
import modelo.CatalogoTrabajo;
import modelo.Cotizacion;
import modelo.DetalleCotizacion;
import modelo.MaterialDetalle;
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

    public List<PuertaAbatibleDetalle> obtenerPuertasPorCotizacion(int idCotizacion) throws SQLException {
        List<PuertaAbatibleDetalle> lista = new ArrayList<>();
        String sql = "SELECT * FROM puertaabatibledetalle WHERE id_cotizacion = ?";

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

    private VentanaDetalle mapearVentanaDetalle(ResultSet rs) throws SQLException {

        VentanaDetalle d = new VentanaDetalle();

        d.setIdVentanaDetalle(rs.getInt("idVentanaDetalle"));
        d.setMedidaHorizontal(rs.getBigDecimal("medidaHorizontal"));
        d.setMedidaVertical(rs.getBigDecimal("medidaVertical"));
        d.setCantidad(rs.getInt("cantidad"));
        d.setTipoCristal(rs.getString("tipoCristal"));
        d.setNoHojas(rs.getInt("noHojas"));
        d.setDescripcion(rs.getString("descripcion"));
        d.setMosquitero(rs.getBoolean("mosquitero"));

        d.setTipoMosquitero(rs.getString("tipoMosquitero"));
        d.setTipoPerfil(rs.getString("tipoPerfil"));
        d.setNoEscuadras(rs.getInt("noEscuadras"));
        d.setTipoTela(rs.getString("tipoTela"));
        d.setLargoTela(rs.getBigDecimal("largoTela"));

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
        d.setDescripcion(rs.getString("descripcion"));

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
        d.setDescripcion(rs.getString("descripcion"));

        d.setMosquitero(rs.getBoolean("mosquitero"));
        d.setTipoMosquitero(rs.getString("tipoMosquitero"));
        d.setTipoPerfil(rs.getString("tipoPerfil"));
        d.setNoEscuadras(rs.getInt("noEscuadras"));
        d.setTipoTela(rs.getString("tipoTela"));
        d.setLargoTela(rs.getBigDecimal("largoTela"));

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

    public boolean crearDetalleVentana(List<VentanaDetalle> detalles) throws SQLException {
        String sql = """
        INSERT INTO ventanadetalle(
            id_tipo_trabajo, id_cotizacion, medidaHorizontal, medidaVertical, cantidad,
            tipoCristal, noHojas, descripcion, tipoVentana, mosquitero,
            tipoMosquitero, tipoPerfil, noEscuadras, tipoTela, largoTela,
            arco, tipoArco, medidaArco, tipoCanalillo, medidaCanalillo
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (VentanaDetalle d : detalles) {
                ps.setInt(1, d.getTipoTrabajo().getIdCatalogo());
                ps.setInt(2, d.getCotizacion().getIdCotizacion());
                ps.setBigDecimal(3, d.getMedidaHorizontal());
                ps.setBigDecimal(4, d.getMedidaVertical());
                ps.setInt(5, d.getCantidad());
                ps.setString(6, d.getTipoCristal());
                ps.setInt(7, d.getNoHojas());
                ps.setString(8, d.getDescripcion());
                ps.setString(9, d.getTipoVentana() != null ? d.getTipoVentana().getDescripcion() : null);
                ps.setBoolean(10, d.isMosquitero());
                ps.setString(11, d.getTipoMosquitero());
                ps.setString(12, d.getTipoPerfil());
                ps.setInt(13, d.getNoEscuadras());
                ps.setString(14, d.getTipoTela());
                ps.setBigDecimal(15, d.getLargoTela());
                ps.setBoolean(16, d.isArco());
                ps.setString(17, d.getTipoArco());
                ps.setBigDecimal(18, d.getMedidaArco());
                ps.setString(19, d.getTipoCanalillo());
                ps.setBigDecimal(20, d.getMedidaCanalillo());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        }
    }

    public boolean crearDetalleCanceleria(List<CanceleriaFijaDetalle> detalles) throws SQLException {
        String sql = """
        INSERT INTO canceleriafijadetalle(
            id_tipo_trabajo, id_cotizacion, medidaHorizontal, medidaVertical, cantidad,
            tipoCristal, noHojas, descripcion, tipoCanceleria, bolsa,
            numFijosVerticales, numFijosHorizontales, tipoTapa, cantidadTapa,
            zoclo, tipoZoclo, junquillo, tipoJunquillo,
            arco, tipoArco, medidaArco,
            canalillo, tipoCanalillo, medidaCanalillo
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (CanceleriaFijaDetalle d : detalles) {
                ps.setInt(1, d.getTipoTrabajo().getIdCatalogo());
                ps.setInt(2, d.getCotizacion().getIdCotizacion());
                ps.setBigDecimal(3, d.getMedidaHorizontal());
                ps.setBigDecimal(4, d.getMedidaVertical());
                ps.setInt(5, d.getCantidad());
                ps.setString(6, d.getTipoCristal());
                ps.setInt(7, d.getNoHojas());
                ps.setString(8, d.getDescripcion());
                ps.setString(9, d.getTipoCanceleria().getDescripcion());
                ps.setBoolean(10, d.isBolsa());
                ps.setInt(11, d.getNumFijosVerticales());
                ps.setInt(12, d.getNumFijosHorizontales());
                ps.setString(13, d.getTipoTapa());
                ps.setInt(14, d.getCantidadTapa());
                ps.setBoolean(15, d.isZoclo());
                ps.setString(16, d.getTipoZoclo());
                ps.setBoolean(17, d.isJunquillo());
                ps.setString(18, d.getTipoJunquillo());
                ps.setBoolean(19, d.isArco());
                ps.setString(20, d.getTipoArco());
                ps.setBigDecimal(21, d.getMedidaArco());
                ps.setBoolean(22, d.isCanalillo());
                ps.setString(23, d.getTipoCanalillo());
                ps.setBigDecimal(24, d.getMedidaCanalillo());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        }
    }

    public boolean crearDetallePuerta(List<PuertaAbatibleDetalle> detalles) throws SQLException {
        String sql = """
        INSERT INTO puertaabatibledetalle(
            id_tipo_trabajo, id_cotizacion, medidaHorizontal, medidaVertical, cantidad,
            tipoCristal, noHojas, descripcion, tipo_puerta,
            mosquitero, tipoMosquitero, tipoPerfil, noEscuadras, tipoTela, largoTela,
            duela, tipo_duela, medida_duela,
            adaptador, tipo_adaptador,
            junquillo, tipo_junquillo,
            canal, tipo_canal,
            pivote, tipo_pivote, cantidad_pivote,
            jaladera, tipo_jaladera, cantidad_jaladera,
            barra, tipo_barra
        ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
    """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (PuertaAbatibleDetalle d : detalles) {
                ps.setInt(1, d.getTipoTrabajo().getIdCatalogo());
                ps.setInt(2, d.getCotizacion().getIdCotizacion());
                ps.setBigDecimal(3, d.getMedidaHorizontal());
                ps.setBigDecimal(4, d.getMedidaVertical());
                ps.setInt(5, d.getCantidad());
                ps.setString(6, d.getTipoCristal());
                ps.setInt(7, d.getNoHojas());
                ps.setString(8, d.getDescripcion());
                ps.setString(9, d.getTipoPuerta().name());
                ps.setBoolean(10, d.isMosquitero());
                ps.setString(11, d.getTipoMosquitero());
                ps.setString(12, d.getTipoPerfil());
                ps.setInt(13, d.getNoEscuadras());
                ps.setString(14, d.getTipoTela());
                ps.setBigDecimal(15, d.getLargoTela());
                ps.setBoolean(16, d.isDuela());
                ps.setString(17, d.getTipoDuela());
                ps.setBigDecimal(18, d.getMedidaDuela());
                ps.setBoolean(19, d.isAdaptador());
                ps.setString(20, d.getTipoAdaptador());
                ps.setBoolean(21, d.isJunquillo());
                ps.setString(22, d.getTipoJunquillo());
                ps.setBoolean(23, d.isCanal());
                ps.setString(24, d.getTipoCanal());
                ps.setBoolean(25, d.isPivote());
                ps.setString(26, d.getTipoPivote());
                ps.setInt(27, d.getCantidadPivote());
                ps.setBoolean(28, d.isJaladera());
                ps.setString(29, d.getTipoJaladera());
                ps.setInt(30, d.getCantidadJaladera());
                ps.setBoolean(31, d.isBarra());
                ps.setString(32, d.getTipoBarra());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        }
    }
    // ===============================
// ELIMINAR DETALLES POR COTIZACION
// ===============================

    public boolean eliminarDetallesVentanaPorCotizacionId(int idCotizacion) throws SQLException {
        String sql = "DELETE FROM ventanadetalle WHERE id_cotizacion = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCotizacion);
            ps.executeUpdate();
            return true;
        }
    }

    public boolean eliminarDetallesCanceleriaPorCotizacionId(int idCotizacion) throws SQLException {
        String sql = "DELETE FROM canceleriafijadetalle WHERE id_cotizacion = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCotizacion);
            ps.executeUpdate();
            return true;
        }
    }

    public boolean eliminarDetallesPuertaPorCotizacionId(int idCotizacion) throws SQLException {
        String sql = "DELETE FROM puertaabatibledetalle WHERE id_cotizacion = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCotizacion);
            ps.executeUpdate();
            return true;
        }
    }

}
