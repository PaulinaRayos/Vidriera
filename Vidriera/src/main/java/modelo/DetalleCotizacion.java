/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.math.BigDecimal;

/**
 * Representa el detalle de una cotización, incluyendo medidas,
 * cantidad, tipo de cristal, subtotal y descripción del trabajo.
 * Cada detalle pertenece a una cotización.
 * 
 * @author Vidrieria
 */
public class DetalleCotizacion {

    private int idDetalle;
    private BigDecimal medidaHorizontal;
    private BigDecimal medidaVertical;
    private int cantidad;
    private String tipoCristal;
    private int noHojas;
    private BigDecimal precioSoloUnaUnidadCalculado;
    private BigDecimal subtotalLinea;
    private String descripcion;
    
    // Relación con la clase Cotizacion
    private Cotizacion cotizacion;

    // Constructor vacío
    public DetalleCotizacion() {
    }

    // Constructor con id
    public DetalleCotizacion(int idDetalle, BigDecimal medidaHorizontal, BigDecimal medidaVertical,
                             int cantidad, String tipoCristal, int noHojas,
                             BigDecimal precioSoloUnaUnidadCalculado, BigDecimal subtotalLinea,
                             String descripcion, Cotizacion cotizacion) {
        this.idDetalle = idDetalle;
        this.medidaHorizontal = medidaHorizontal;
        this.medidaVertical = medidaVertical;
        this.cantidad = cantidad;
        this.tipoCristal = tipoCristal;
        this.noHojas = noHojas;
        this.precioSoloUnaUnidadCalculado = precioSoloUnaUnidadCalculado;
        this.subtotalLinea = subtotalLinea;
        this.descripcion = descripcion;
        this.cotizacion = cotizacion;
    }

   
    //Constructor sin id
    public DetalleCotizacion(BigDecimal medidaHorizontal, BigDecimal medidaVertical, int cantidad, String tipoCristal, int noHojas, BigDecimal precioSoloUnaUnidadCalculado, BigDecimal subtotalLinea, String descripcion, Cotizacion cotizacion) {    
        this.medidaHorizontal = medidaHorizontal;
        this.medidaVertical = medidaVertical;
        this.cantidad = cantidad;
        this.tipoCristal = tipoCristal;
        this.noHojas = noHojas;
        this.precioSoloUnaUnidadCalculado = precioSoloUnaUnidadCalculado;
        this.subtotalLinea = subtotalLinea;
        this.descripcion = descripcion;
        this.cotizacion = cotizacion;
    }

    // Getters y Setters (forma larga)
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public BigDecimal getMedidaHorizontal() {
        return medidaHorizontal;
    }

    public void setMedidaHorizontal(BigDecimal medidaHorizontal) {
        this.medidaHorizontal = medidaHorizontal;
    }

    public BigDecimal getMedidaVertical() {
        return medidaVertical;
    }

    public void setMedidaVertical(BigDecimal medidaVertical) {
        this.medidaVertical = medidaVertical;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoCristal() {
        return tipoCristal;
    }

    public void setTipoCristal(String tipoCristal) {
        this.tipoCristal = tipoCristal;
    }

    public int getNoHojas() {
        return noHojas;
    }

    public void setNoHojas(int noHojas) {
        this.noHojas = noHojas;
    }

    public BigDecimal getPrecioSoloUnaUnidadCalculado() {
        return precioSoloUnaUnidadCalculado;
    }

    public void setPrecioSoloUnaUnidadCalculado(BigDecimal precioSoloUnaUnidadCalculado) {
        this.precioSoloUnaUnidadCalculado = precioSoloUnaUnidadCalculado;
    }

    public BigDecimal getSubtotalLinea() {
        return subtotalLinea;
    }

    public void setSubtotalLinea(BigDecimal subtotalLinea) {
        this.subtotalLinea = subtotalLinea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    @Override
    public String toString() {
        return "DetalleCotizacion{" +
                "idDetalle=" + idDetalle +
                ", medidaHorizontal=" + medidaHorizontal +
                ", medidaVertical=" + medidaVertical +
                ", cantidad=" + cantidad +
                ", tipoCristal='" + tipoCristal + '\'' +
                ", noHojas=" + noHojas +
                ", precioSoloUnaUnidadCalculado=" + precioSoloUnaUnidadCalculado +
                ", subtotalLinea=" + subtotalLinea +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}