/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pauli
 */
public class Cotizacion {

    private int idCotizacion;
    private Date fecha;
    private BigDecimal subtotal;
    private BigDecimal manoObra;
    private BigDecimal iva;
    private BigDecimal descuentoMonto;
    private BigDecimal total;
    private String estado;
    private Cliente cliente;
    private Proyecto proyecto;
    private Vendedor vendedor;

    // Relación con los detalles de trabajo
    private List<DetalleCotizacion> detalles = new ArrayList<>();
    private List<VentanaDetalle> ventanaDetalles;
    private List<PuertaAbatibleDetalle> puertaAbatibleDetalles;
    private List<CanceleriaFijaDetalle> canceleriaFijaDetalles;

    //Constructor vacío
    public Cotizacion() {
    }

    //Constructor con id
    public Cotizacion(int idCotizacion, Date fecha, BigDecimal subtotal, BigDecimal manoObra, BigDecimal iva, BigDecimal descuentoMonto, BigDecimal total, String estado, Cliente cliente, Proyecto proyecto, Vendedor vendedor, List<VentanaDetalle> ventanaDetalles, List<PuertaAbatibleDetalle> puertaAbatibleDetalles, List<CanceleriaFijaDetalle> canceleriaFijaDetalles) {    
        this.idCotizacion = idCotizacion;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.manoObra = manoObra;
        this.iva = iva;
        this.descuentoMonto = descuentoMonto;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.proyecto = proyecto;
        this.vendedor = vendedor;
        this.ventanaDetalles = ventanaDetalles;
        this.puertaAbatibleDetalles = puertaAbatibleDetalles;
        this.canceleriaFijaDetalles = canceleriaFijaDetalles;
    }

    //Constructor sin id
    public Cotizacion(Date fecha, BigDecimal subtotal, BigDecimal manoObra, BigDecimal iva, BigDecimal descuentoMonto, BigDecimal total, String estado, Cliente cliente, Proyecto proyecto, Vendedor vendedor, List<VentanaDetalle> ventanaDetalles, List<PuertaAbatibleDetalle> puertaAbatibleDetalles, List<CanceleriaFijaDetalle> canceleriaFijaDetalles) {    
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.manoObra = manoObra;
        this.iva = iva;
        this.descuentoMonto = descuentoMonto;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.proyecto = proyecto;
        this.vendedor = vendedor;
        this.ventanaDetalles = ventanaDetalles;
        this.puertaAbatibleDetalles = puertaAbatibleDetalles;
        this.canceleriaFijaDetalles = canceleriaFijaDetalles;
    }

    // Getters y Setters
    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getManoObra() {
        return manoObra;
    }

    public void setManoObra(BigDecimal manoObra) {
        this.manoObra = manoObra;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getDescuentoMonto() {
        return descuentoMonto;
    }

    public void setDescuentoMonto(BigDecimal descuentoMonto) {
        this.descuentoMonto = descuentoMonto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<VentanaDetalle> getVentanaDetalles() {
        return ventanaDetalles;
    }

    public void setVentanaDetalles(List<VentanaDetalle> ventanaDetalles) {
        this.ventanaDetalles = ventanaDetalles;
    }

    public List<PuertaAbatibleDetalle> getPuertaAbatibleDetalles() {
        return puertaAbatibleDetalles;
    }

    public void setPuertaAbatibleDetalles(List<PuertaAbatibleDetalle> puertaAbatibleDetalles) {
        this.puertaAbatibleDetalles = puertaAbatibleDetalles;
    }

    public List<CanceleriaFijaDetalle> getCanceleriaFijaDetalles() {
        return canceleriaFijaDetalles;
    }

    public void setCanceleriaFijaDetalles(List<CanceleriaFijaDetalle> canceleriaFijaDetalles) {
        this.canceleriaFijaDetalles = canceleriaFijaDetalles;
    }

    public List<DetalleCotizacion> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCotizacion> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Cotizacion{" + "idCotizacion=" + idCotizacion + ", fecha=" + fecha + ", subtotal=" + subtotal + ", manoObra=" + manoObra + ", iva=" + iva + ", descuentoMonto=" + descuentoMonto + ", total=" + total + ", estado=" + estado + ", cliente=" + cliente + ", proyecto=" + proyecto + ", vendedor=" + vendedor + ", ventanaDetalles=" + ventanaDetalles + ", puertaAbatibleDetalles=" + puertaAbatibleDetalles + ", canceleriaFijaDetalles=" + canceleriaFijaDetalles + '}';
    }
    
}
