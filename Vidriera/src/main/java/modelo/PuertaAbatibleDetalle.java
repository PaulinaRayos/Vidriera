/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.math.BigDecimal;

/**
 *
 * @author Vidrieria
 */
public class PuertaAbatibleDetalle {
    private int idDetallePuerta;
    private int idTipoTrabajo;
    private Cotizacion cotizacion;
    private BigDecimal medidaHorizontal;
    private BigDecimal medidaVertical;
    private int cantidad;
    private String tipoCristal;
    private int noHojas;
    private BigDecimal precioSoloUnaUnidadCalculado;
    private BigDecimal subtotalLinea;
    private String descripcion;
    private TipoPuerta tipoPuerta; 
    private boolean mosquitero;
    private boolean duela;
    private String tipoDuela;
    private BigDecimal medidaDuela;
    private boolean adaptador;
    private String tipoAdaptador;
    private boolean junquillo;
    private String tipoJunquillo;
    private boolean canal;
    private String tipoCanal;
    private boolean pivote;
    private String tipoPivote;
    private int cantidadPivote;
    private boolean jaladera;
    private String tipoJaladera;
    private int cantidadJaladera;
    private boolean barra;
    private String tipoBarra;
    private boolean chapa;
    private String tipoChapa;
    private int cantidadChapa;
    private boolean bisagra;
    private String tipoBisagra;
    private int cantidadBisagra;
    private boolean cierraPuerta;
    private String tipoCierraPuerta;
    private int cantidadCierraPuerta;
    private boolean swiggle;
    private String tipoSwiggle;
    private int numFijosVerticales;
    private int numFijosHorizontales;
    private CatalogoTrabajo tipoTrabajo;

    
    //Constructor vacío
    public PuertaAbatibleDetalle() {    
    }

    //Constructor con id
    public PuertaAbatibleDetalle(int idDetallePuerta, int idTipoTrabajo, Cotizacion cotizacion, BigDecimal medidaHorizontal, BigDecimal medidaVertical, int cantidad, String tipoCristal, int noHojas, BigDecimal precioSoloUnaUnidadCalculado, BigDecimal subtotalLinea, String descripcion, TipoPuerta tipoPuerta, boolean mosquitero, boolean duela, String tipoDuela, BigDecimal medidaDuela, boolean adaptador, String tipoAdaptador, boolean junquillo, String tipoJunquillo, boolean canal, String tipoCanal, boolean pivote, String tipoPivote, int cantidadPivote, boolean jaladera, String tipoJaladera, int cantidadJaladera, boolean barra, String tipoBarra, boolean chapa, String tipoChapa, int cantidadChapa, boolean bisagra, String tipoBisagra, int cantidadBisagra, boolean cierraPuerta, String tipoCierraPuerta, int cantidadCierraPuerta, boolean swiggle, String tipoSwiggle, int numFijosVerticales, int numFijosHorizontales) {    
        this.idDetallePuerta = idDetallePuerta;
        this.idTipoTrabajo = idTipoTrabajo;
        this.cotizacion = cotizacion;
        this.medidaHorizontal = medidaHorizontal;
        this.medidaVertical = medidaVertical;
        this.cantidad = cantidad;
        this.tipoCristal = tipoCristal;
        this.noHojas = noHojas;
        this.precioSoloUnaUnidadCalculado = precioSoloUnaUnidadCalculado;
        this.subtotalLinea = subtotalLinea;
        this.descripcion = descripcion;
        this.tipoPuerta = tipoPuerta;
        this.mosquitero = mosquitero;
        this.duela = duela;
        this.tipoDuela = tipoDuela;
        this.medidaDuela = medidaDuela;
        this.adaptador = adaptador;
        this.tipoAdaptador = tipoAdaptador;
        this.junquillo = junquillo;
        this.tipoJunquillo = tipoJunquillo;
        this.canal = canal;
        this.tipoCanal = tipoCanal;
        this.pivote = pivote;
        this.tipoPivote = tipoPivote;
        this.cantidadPivote = cantidadPivote;
        this.jaladera = jaladera;
        this.tipoJaladera = tipoJaladera;
        this.cantidadJaladera = cantidadJaladera;
        this.barra = barra;
        this.tipoBarra = tipoBarra;
        this.chapa = chapa;
        this.tipoChapa = tipoChapa;
        this.cantidadChapa = cantidadChapa;
        this.bisagra = bisagra;
        this.tipoBisagra = tipoBisagra;
        this.cantidadBisagra = cantidadBisagra;
        this.cierraPuerta = cierraPuerta;
        this.tipoCierraPuerta = tipoCierraPuerta;
        this.cantidadCierraPuerta = cantidadCierraPuerta;
        this.swiggle = swiggle;
        this.tipoSwiggle = tipoSwiggle;
        this.numFijosVerticales = numFijosVerticales;
        this.numFijosHorizontales = numFijosHorizontales;
    }

    //Constructor sin id
    public PuertaAbatibleDetalle(int idTipoTrabajo, Cotizacion cotizacion, BigDecimal medidaHorizontal, BigDecimal medidaVertical, int cantidad, String tipoCristal, int noHojas, BigDecimal precioSoloUnaUnidadCalculado, BigDecimal subtotalLinea, String descripcion, TipoPuerta tipoPuerta, boolean mosquitero, boolean duela, String tipoDuela, BigDecimal medidaDuela, boolean adaptador, String tipoAdaptador, boolean junquillo, String tipoJunquillo, boolean canal, String tipoCanal, boolean pivote, String tipoPivote, int cantidadPivote, boolean jaladera, String tipoJaladera, int cantidadJaladera, boolean barra, String tipoBarra, boolean chapa, String tipoChapa, int cantidadChapa, boolean bisagra, String tipoBisagra, int cantidadBisagra, boolean cierraPuerta, String tipoCierraPuerta, int cantidadCierraPuerta, boolean swiggle, String tipoSwiggle, int numFijosVerticales, int numFijosHorizontales) {    
        this.idTipoTrabajo = idTipoTrabajo;
        this.cotizacion = cotizacion;
        this.medidaHorizontal = medidaHorizontal;
        this.medidaVertical = medidaVertical;
        this.cantidad = cantidad;
        this.tipoCristal = tipoCristal;
        this.noHojas = noHojas;
        this.precioSoloUnaUnidadCalculado = precioSoloUnaUnidadCalculado;
        this.subtotalLinea = subtotalLinea;
        this.descripcion = descripcion;
        this.tipoPuerta = tipoPuerta;
        this.mosquitero = mosquitero;
        this.duela = duela;
        this.tipoDuela = tipoDuela;
        this.medidaDuela = medidaDuela;
        this.adaptador = adaptador;
        this.tipoAdaptador = tipoAdaptador;
        this.junquillo = junquillo;
        this.tipoJunquillo = tipoJunquillo;
        this.canal = canal;
        this.tipoCanal = tipoCanal;
        this.pivote = pivote;
        this.tipoPivote = tipoPivote;
        this.cantidadPivote = cantidadPivote;
        this.jaladera = jaladera;
        this.tipoJaladera = tipoJaladera;
        this.cantidadJaladera = cantidadJaladera;
        this.barra = barra;
        this.tipoBarra = tipoBarra;
        this.chapa = chapa;
        this.tipoChapa = tipoChapa;
        this.cantidadChapa = cantidadChapa;
        this.bisagra = bisagra;
        this.tipoBisagra = tipoBisagra;
        this.cantidadBisagra = cantidadBisagra;
        this.cierraPuerta = cierraPuerta;
        this.tipoCierraPuerta = tipoCierraPuerta;
        this.cantidadCierraPuerta = cantidadCierraPuerta;
        this.swiggle = swiggle;
        this.tipoSwiggle = tipoSwiggle;
        this.numFijosVerticales = numFijosVerticales;
        this.numFijosHorizontales = numFijosHorizontales;
    }

    //Getters y setters
    public int getIdDetallePuerta() {
        return idDetallePuerta;
    }

    public void setIdDetallePuerta(int idDetallePuerta) {
        this.idDetallePuerta = idDetallePuerta;
    }

    public int getIdTipoTrabajo() {
        return idTipoTrabajo;
    }

    public void setIdTipoTrabajo(int idTipoTrabajo) {
        this.idTipoTrabajo = idTipoTrabajo;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
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

    public TipoPuerta getTipoPuerta() {
        return tipoPuerta;
    }

    public void setTipoPuerta(TipoPuerta tipoPuerta) {
        this.tipoPuerta = tipoPuerta;
    }

    public boolean isMosquitero() {
        return mosquitero;
    }

    public void setMosquitero(boolean mosquitero) {
        this.mosquitero = mosquitero;
    }

    public boolean isDuela() {
        return duela;
    }

    public void setDuela(boolean duela) {
        this.duela = duela;
    }

    public String getTipoDuela() {
        return tipoDuela;
    }

    public void setTipoDuela(String tipoDuela) {
        this.tipoDuela = tipoDuela;
    }

    public BigDecimal getMedidaDuela() {
        return medidaDuela;
    }

    public void setMedidaDuela(BigDecimal medidaDuela) {
        this.medidaDuela = medidaDuela;
    }

    public boolean isAdaptador() {
        return adaptador;
    }

    public void setAdaptador(boolean adaptador) {
        this.adaptador = adaptador;
    }

    public String getTipoAdaptador() {
        return tipoAdaptador;
    }

    public void setTipoAdaptador(String tipoAdaptador) {
        this.tipoAdaptador = tipoAdaptador;
    }

    public boolean isJunquillo() {
        return junquillo;
    }

    public void setJunquillo(boolean junquillo) {
        this.junquillo = junquillo;
    }

    public String getTipoJunquillo() {
        return tipoJunquillo;
    }

    public void setTipoJunquillo(String tipoJunquillo) {
        this.tipoJunquillo = tipoJunquillo;
    }

    public boolean isCanal() {
        return canal;
    }

    public void setCanal(boolean canal) {
        this.canal = canal;
    }

    public String getTipoCanal() {
        return tipoCanal;
    }

    public void setTipoCanal(String tipoCanal) {
        this.tipoCanal = tipoCanal;
    }

    public boolean isPivote() {
        return pivote;
    }

    public void setPivote(boolean pivote) {
        this.pivote = pivote;
    }

    public String getTipoPivote() {
        return tipoPivote;
    }

    public void setTipoPivote(String tipoPivote) {
        this.tipoPivote = tipoPivote;
    }

    public int getCantidadPivote() {
        return cantidadPivote;
    }

    public void setCantidadPivote(int cantidadPivote) {
        this.cantidadPivote = cantidadPivote;
    }

    public boolean isJaladera() {
        return jaladera;
    }

    public void setJaladera(boolean jaladera) {
        this.jaladera = jaladera;
    }

    public String getTipoJaladera() {
        return tipoJaladera;
    }

    public void setTipoJaladera(String tipoJaladera) {
        this.tipoJaladera = tipoJaladera;
    }

    public int getCantidadJaladera() {
        return cantidadJaladera;
    }

    public void setCantidadJaladera(int cantidadJaladera) {
        this.cantidadJaladera = cantidadJaladera;
    }

    public boolean isBarra() {
        return barra;
    }

    public void setBarra(boolean barra) {
        this.barra = barra;
    }

    public String getTipoBarra() {
        return tipoBarra;
    }

    public void setTipoBarra(String tipoBarra) {
        this.tipoBarra = tipoBarra;
    }

    public boolean isChapa() {
        return chapa;
    }

    public void setChapa(boolean chapa) {
        this.chapa = chapa;
    }

    public String getTipoChapa() {
        return tipoChapa;
    }

    public void setTipoChapa(String tipoChapa) {
        this.tipoChapa = tipoChapa;
    }

    public int getCantidadChapa() {
        return cantidadChapa;
    }

    public void setCantidadChapa(int cantidadChapa) {
        this.cantidadChapa = cantidadChapa;
    }

    public boolean isBisagra() {
        return bisagra;
    }

    public void setBisagra(boolean bisagra) {
        this.bisagra = bisagra;
    }

    public String getTipoBisagra() {
        return tipoBisagra;
    }

    public void setTipoBisagra(String tipoBisagra) {
        this.tipoBisagra = tipoBisagra;
    }

    public int getCantidadBisagra() {
        return cantidadBisagra;
    }

    public void setCantidadBisagra(int cantidadBisagra) {
        this.cantidadBisagra = cantidadBisagra;
    }

    public boolean isCierraPuerta() {
        return cierraPuerta;
    }

    public void setCierraPuerta(boolean cierraPuerta) {
        this.cierraPuerta = cierraPuerta;
    }

    public String getTipoCierraPuerta() {
        return tipoCierraPuerta;
    }

    public void setTipoCierraPuerta(String tipoCierraPuerta) {
        this.tipoCierraPuerta = tipoCierraPuerta;
    }

    public int getCantidadCierraPuerta() {
        return cantidadCierraPuerta;
    }

    public void setCantidadCierraPuerta(int cantidadCierraPuerta) {
        this.cantidadCierraPuerta = cantidadCierraPuerta;
    }

    public boolean isSwiggle() {
        return swiggle;
    }

    public void setSwiggle(boolean swiggle) {
        this.swiggle = swiggle;
    }

    public String getTipoSwiggle() {
        return tipoSwiggle;
    }

    public void setTipoSwiggle(String tipoSwiggle) {
        this.tipoSwiggle = tipoSwiggle;
    }

    public int getNumFijosVerticales() {
        return numFijosVerticales;
    }

    public void setNumFijosVerticales(int numFijosVerticales) {
        this.numFijosVerticales = numFijosVerticales;
    }

    public int getNumFijosHorizontales() {
        return numFijosHorizontales;
    }

    public void setNumFijosHorizontales(int numFijosHorizontales) {
        this.numFijosHorizontales = numFijosHorizontales;
    }
    public CatalogoTrabajo getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(CatalogoTrabajo tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    @Override
    public String toString() {
        return "PuertaAbatibleDetalle{" + "idDetallePuerta=" + idDetallePuerta + ", idTipoTrabajo=" + idTipoTrabajo + ", cotizacion=" + cotizacion + ", medidaHorizontal=" + medidaHorizontal + ", medidaVertical=" + medidaVertical + ", cantidad=" + cantidad + ", tipoCristal=" + tipoCristal + ", noHojas=" + noHojas + ", precioSoloUnaUnidadCalculado=" + precioSoloUnaUnidadCalculado + ", subtotalLinea=" + subtotalLinea + ", descripcion=" + descripcion + ", tipoPuerta=" + tipoPuerta + ", mosquitero=" + mosquitero + ", duela=" + duela + ", tipoDuela=" + tipoDuela + ", medidaDuela=" + medidaDuela + ", adaptador=" + adaptador + ", tipoAdaptador=" + tipoAdaptador + ", junquillo=" + junquillo + ", tipoJunquillo=" + tipoJunquillo + ", canal=" + canal + ", tipoCanal=" + tipoCanal + ", pivote=" + pivote + ", tipoPivote=" + tipoPivote + ", cantidadPivote=" + cantidadPivote + ", jaladera=" + jaladera + ", tipoJaladera=" + tipoJaladera + ", cantidadJaladera=" + cantidadJaladera + ", barra=" + barra + ", tipoBarra=" + tipoBarra + ", chapa=" + chapa + ", tipoChapa=" + tipoChapa + ", cantidadChapa=" + cantidadChapa + ", bisagra=" + bisagra + ", tipoBisagra=" + tipoBisagra + ", cantidadBisagra=" + cantidadBisagra + ", cierraPuerta=" + cierraPuerta + ", tipoCierraPuerta=" + tipoCierraPuerta + ", cantidadCierraPuerta=" + cantidadCierraPuerta + ", swiggle=" + swiggle + ", tipoSwiggle=" + tipoSwiggle + ", numFijosVerticales=" + numFijosVerticales + ", numFijosHorizontales=" + numFijosHorizontales + '}';
    }
    
    
    
    
}
