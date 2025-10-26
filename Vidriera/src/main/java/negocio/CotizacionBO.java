/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dao.CotizacionDAO;
import dao.DetalleCotizacionDAO;
import dao.MaterialDAO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import modelo.CanceleriaFijaDetalle;
import modelo.Cotizacion;
import modelo.PuertaAbatibleDetalle;
import modelo.VentanaDetalle;

/**
 *
 * @author Vidrieria
 */
public class CotizacionBO {

    private CotizacionDAO cotizacionDAO;
    private DetalleCotizacionDAO detalleDAO;
     private MaterialDAO materialDetalleDAO;


    public CotizacionBO(Connection conexion) {
        this.cotizacionDAO = new CotizacionDAO(conexion);
        this.detalleDAO = new DetalleCotizacionDAO(conexion);
        
    }

    public CotizacionBO() {

    }

    // Crear cotizacion con validacion de campos minimos
    public boolean crearCotizacion(Cotizacion c) {
        if (c.getTotal() == null || c.getSubtotal() == null) {
            System.out.println("Error: Subtotal o total no pueden ser nulos");
            return false;
        }
        // Opcional: asegurarte de que el total no sea negativo
        if (c.getTotal().compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Error: Total no puede ser negativo");
            return false;
        }
        return cotizacionDAO.crearCotizacion(c);
    }

    public boolean crearCotizacionCompleta(Cotizacion c,
            List<VentanaDetalle> ventanas,
            List<CanceleriaFijaDetalle> cancelerias,
            List<PuertaAbatibleDetalle> puertas) {
        // Calcular Mano de Obra
        // 10% del subtotal
        BigDecimal porcentajeManoObra = new BigDecimal("0.10");
        BigDecimal manoObra = c.getSubtotal().multiply(porcentajeManoObra);

        // Calcular IVA 
        // IVA del 16% sobre subtotal + mano de obra
        BigDecimal iva = (c.getSubtotal().add(manoObra)).multiply(new BigDecimal("0.16"));

        // Calcular Total 
        // Total = subtotal + manoObra + iva - descuento
        BigDecimal total = c.getSubtotal().add(manoObra).add(iva)
                .subtract(c.getDescuentoMonto() != null ? c.getDescuentoMonto() : BigDecimal.ZERO);

        // Asignar los valores a la cotizacion
        c.setManoObra(manoObra);
        c.setIva(iva);
        c.setTotal(total);

        // Guardar cotizacion 
        boolean ok = cotizacionDAO.crearCotizacion(c);
        if (!ok) {
            return false;
        }

        // Guardar detalles 
        boolean okVentanas = ventanas != null && !ventanas.isEmpty()
                ? detalleDAO.crearDetalleVentana(ventanas)
                : true;
        boolean okCanceleria = cancelerias != null && !cancelerias.isEmpty()
                ? detalleDAO.crearDetalleCanceleria(cancelerias)
                : true;
        boolean okPuertas = puertas != null && !puertas.isEmpty()
                ? detalleDAO.crearDetallePuerta(puertas)
                : true;

        return okVentanas && okCanceleria && okPuertas;
    }

    // Obtener todas las cotizaciones
    public List<Cotizacion> obtenerCotizaciones() {
        return cotizacionDAO.obtenerTodas();
    }

    // Metodo para obtener cotizacion por ID
    public Cotizacion obtenerCotizacionPorId(int id) {
        return cotizacionDAO.obtenerPorId(id);
    }

    // Actualizar cotizacion (solo la tabla principal)
    public boolean actualizarCotizacion(Cotizacion c) {
        if (c.getEstado().equalsIgnoreCase("Aceptado") || c.getEstado().equalsIgnoreCase("Rechazada")) {
            System.out.println("No se puede modificar cotizacion aceptada o rechazada");
            return false;
        }
        return cotizacionDAO.actualizarCotizacion(c);
    }

    // Eliminar cotizacion
    public boolean eliminarCotizacion(int idCotizacion) {
        return cotizacionDAO.eliminarCotizacion(idCotizacion);
    }

    /**
     * Actualiza una cotizacion completa, incluyendo sus detalles. La estrategia
     * es borrar los detalles existentes y volver a crearlos. Toda la operacion
     * se ejecuta dentro de una transaccion.
     */
    public boolean actualizarCotizacionCompleta(Cotizacion c,
            List<VentanaDetalle> ventanas,
            List<CanceleriaFijaDetalle> cancelerias,
            List<PuertaAbatibleDetalle> puertas) throws SQLException {

        // Extraemos la conexion del DAO para manejar la transaccion
        Connection conn = this.cotizacionDAO.getConexion(); // Necesitaras un getter para la conexion en tu DAO

        try {
            // --- INICIO DE LA TRANSACCION ---
            conn.setAutoCommit(false);

            detalleDAO.eliminarDetallesVentanaPorCotizacionId(c.getIdCotizacion());
            detalleDAO.eliminarDetallesCanceleriaPorCotizacionId(c.getIdCotizacion());
            detalleDAO.eliminarDetallesPuertaPorCotizacionId(c.getIdCotizacion());

            // 2. Recalcular los montos 
            BigDecimal porcentajeManoObra = new BigDecimal("0.10");
            BigDecimal manoObra = c.getSubtotal().multiply(porcentajeManoObra);
            BigDecimal iva = (c.getSubtotal().add(manoObra)).multiply(new BigDecimal("0.16"));
            BigDecimal total = c.getSubtotal().add(manoObra).add(iva)
                    .subtract(c.getDescuentoMonto() != null ? c.getDescuentoMonto() : BigDecimal.ZERO);

            c.setManoObra(manoObra);
            c.setIva(iva);
            c.setTotal(total);

            // 3. Actualizar la cotizacion principal
            cotizacionDAO.actualizarCotizacion(c);

            // 4. Insertar los nuevos detalles (si existen)
            if (ventanas != null && !ventanas.isEmpty()) {
                detalleDAO.crearDetalleVentana(ventanas);
            }
            if (cancelerias != null && !cancelerias.isEmpty()) {
                detalleDAO.crearDetalleCanceleria(cancelerias);
            }
            if (puertas != null && !puertas.isEmpty()) {
                detalleDAO.crearDetallePuerta(puertas);
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error en la transaccion, revirtiendo cambios.");
            conn.rollback();
            e.printStackTrace();
            return false;
        } finally {
            // Devolver la conexion a su estado original
            conn.setAutoCommit(true);
        }
    }
}
