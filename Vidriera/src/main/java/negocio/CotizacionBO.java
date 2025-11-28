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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import modelo.CanceleriaFijaDetalle;
import modelo.Cotizacion;
import modelo.PuertaAbatibleDetalle;
import modelo.VentanaDetalle;
import utils.Conexion;

/**
 *
 * @author Vidrieria
 */
public class CotizacionBO {

    private CotizacionDAO cotizacionDAO;
    private DetalleCotizacionDAO detalleDAO;
    private MaterialDAO materialDetalleDAO;
    private Connection conexion;

    public Connection getConexion() {
        return this.conexion;
    }

    public CotizacionBO() {
        try {
            this.conexion = Conexion.getConnection();
            this.cotizacionDAO = new CotizacionDAO(conexion);
            this.detalleDAO = new DetalleCotizacionDAO(conexion);
            this.materialDetalleDAO = new MaterialDAO(conexion);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("No se pudo inicializar la conexión a la base de datos.");

        }

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

    try {
        // Calcular Mano de Obra (45%)
        BigDecimal porcentajeManoObra = new BigDecimal("0.45");
        BigDecimal manoObra = c.getSubtotal().multiply(porcentajeManoObra);

        // Calcular IVA (16%)
        BigDecimal iva = (c.getSubtotal().add(manoObra)).multiply(new BigDecimal("0.16"));

        // Calcular Total
        BigDecimal total = c.getSubtotal()
                .add(manoObra)
                .add(iva)
                .subtract(c.getDescuentoMonto() != null ? c.getDescuentoMonto() : BigDecimal.ZERO);

        // Asignar valores
        c.setManoObra(manoObra);
        c.setIva(iva);
        c.setTotal(total);

        // Guardar cotización
        boolean ok = cotizacionDAO.crearCotizacion(c);
        if (!ok) {
            return false;
        }

        // Asignar cotización a los detalles
        if (ventanas != null) {
            for (VentanaDetalle detalle : ventanas) {
                detalle.setCotizacion(c);
            }
        }

        if (cancelerias != null) {
            for (CanceleriaFijaDetalle detalle : cancelerias) {
                detalle.setCotizacion(c);
            }
        }

        if (puertas != null) {
            for (PuertaAbatibleDetalle detalle : puertas) {
                detalle.setCotizacion(c);
            }
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

    } catch (SQLException e) {
        System.out.println("Error al crear la cotización completa: " + e.getMessage());
        return false;
    }
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
        if (c.getEstado().equalsIgnoreCase("Aceptado") || c.getEstado().equalsIgnoreCase("Cancelada")) {
            System.out.println("No se puede modificar cotizacion aceptada o Cancelada");
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

        Connection conn = this.cotizacionDAO.getConexion();

        try {
            conn.setAutoCommit(false);

            // ⚠️ Validación: no eliminar si todas las listas son nulas o vacías
            boolean hayDetalles
                    = (ventanas != null && !ventanas.isEmpty())
                    || (cancelerias != null && !cancelerias.isEmpty())
                    || (puertas != null && !puertas.isEmpty());

            if (!hayDetalles) {
                throw new SQLException("No se proporcionaron detalles para actualizar la cotización.");
            }

            // 🔥 Eliminar los anteriores (solo si se van a reemplazar)
            detalleDAO.eliminarDetallesVentanaPorCotizacionId(c.getIdCotizacion());
            detalleDAO.eliminarDetallesCanceleriaPorCotizacionId(c.getIdCotizacion());
            detalleDAO.eliminarDetallesPuertaPorCotizacionId(c.getIdCotizacion());

            // Recalcular los montos
            BigDecimal porcentajeManoObra = new BigDecimal("0.45");
            BigDecimal manoObra = c.getSubtotal().multiply(porcentajeManoObra);
            BigDecimal iva = (c.getSubtotal().add(manoObra)).multiply(new BigDecimal("0.16"));
            BigDecimal total = c.getSubtotal().add(manoObra).add(iva)
                    .subtract(c.getDescuentoMonto() != null ? c.getDescuentoMonto() : BigDecimal.ZERO);

            c.setManoObra(manoObra);
            c.setIva(iva);
            c.setTotal(total);

            // Actualizar la cotización principal
            cotizacionDAO.actualizarCotizacion(c);

            // Asociar detalles con la cotización actual
            if (ventanas != null) {
                for (VentanaDetalle detalle : ventanas) {
                    detalle.setCotizacion(c);
                }
            }
            if (cancelerias != null) {
                for (CanceleriaFijaDetalle detalle : cancelerias) {
                    detalle.setCotizacion(c);
                }
            }
            if (puertas != null) {
                for (PuertaAbatibleDetalle detalle : puertas) {
                    detalle.setCotizacion(c);
                }
            }

            // Insertar los nuevos detalles
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
            System.err.println("Error en la transacción, revirtiendo cambios.");
            conn.rollback();
            e.printStackTrace();
            return false;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public List<Cotizacion> obtenerCotizacionesPorCliente(String nombre) {
        return cotizacionDAO.obtenerCotizacionesCliente(nombre);
    }

    public Cotizacion obtenerCotizacionesPorNumero(int numero) {
        return cotizacionDAO.obtenerCotizacionPorNumero(numero);
    }

    public List<Cotizacion> obtenerCotizacionesPorRangoFechas(Date fechaInicio, Date fechaFin) {
        return cotizacionDAO.obtenerCotizacionesPorRangoFechas(fechaInicio, fechaFin);
    }

    public List<Cotizacion> filtrarPorNumero(List<Cotizacion> lista, int numero) {
        return lista.stream()
                .filter(c -> c.getIdCotizacion() == numero)
                .collect(Collectors.toList());
    }

    public List<Cotizacion> filtrarPorCliente(List<Cotizacion> lista, String nombre) {
        return lista.stream()
                .filter(c -> c.getCliente().getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Cotizacion> filtrarPorFechas(List<Cotizacion> lista, Date inicio, Date fin) {
        return lista.stream()
                .filter(c -> !c.getFecha().before(inicio) && !c.getFecha().after(fin))
                .collect(Collectors.toList());
    }
}
