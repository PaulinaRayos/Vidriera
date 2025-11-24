
package modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author Vidrieria
 */
public class VentanaDetalle extends DetalleCotizacion {

    private int idVentanaDetalle;
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
    private TipoVentana tipoVentana;
    private boolean mosquitero;
    private boolean arco;
    private String tipoArco;
    private BigDecimal medidaArco;
    private String tipoCanalillo;
    private BigDecimal medidaCanalillo;
    private CatalogoTrabajo tipoTrabajo;
    private List<MaterialDetalle> materiales;

    public VentanaDetalle() {
        super();
    }

    //Constructor con id
    public VentanaDetalle(int idVentanaDetalle, int idTipoTrabajo, Cotizacion cotizacion, BigDecimal medidaHorizontal, BigDecimal medidaVertical, int cantidad, String tipoCristal, int noHojas, BigDecimal precioSoloUnaUnidadCalculado, BigDecimal subtotalLinea, String descripcion, TipoVentana tipoVentana, boolean mosquitero, boolean arco, String tipoArco, BigDecimal medidaArco, String tipoCanalillo, BigDecimal medidaCanalillo) {
        this.idVentanaDetalle = idVentanaDetalle;
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
        this.tipoVentana = tipoVentana;
        this.mosquitero = mosquitero;
        this.arco = arco;
        this.tipoArco = tipoArco;
        this.medidaArco = medidaArco;
        this.tipoCanalillo = tipoCanalillo;
        this.medidaCanalillo = medidaCanalillo;
    }

    //Constructor sin id
    public VentanaDetalle(int idTipoTrabajo, Cotizacion cotizacion, BigDecimal medidaHorizontal, BigDecimal medidaVertical, int cantidad, String tipoCristal, int noHojas, BigDecimal precioSoloUnaUnidadCalculado, BigDecimal subtotalLinea, String descripcion, TipoVentana tipoVentana, boolean mosquitero, boolean arco, String tipoArco, BigDecimal medidaArco, String tipoCanalillo, BigDecimal medidaCanalillo) {
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
        this.tipoVentana = tipoVentana;
        this.mosquitero = mosquitero;
        this.arco = arco;
        this.tipoArco = tipoArco;
        this.medidaArco = medidaArco;
        this.tipoCanalillo = tipoCanalillo;
        this.medidaCanalillo = medidaCanalillo;
    }

    //Getters y setters
    public int getIdVentanaDetalle() {
        return idVentanaDetalle;
    }

    public void setIdVentanaDetalle(int idVentanaDetalle) {
        this.idVentanaDetalle = idVentanaDetalle;
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

    public TipoVentana getTipoVentana() {
        return tipoVentana;
    }

    public void setTipoVentana(TipoVentana tipoVentana) {
        this.tipoVentana = tipoVentana;
    }

    public boolean isMosquitero() {
        return mosquitero;
    }

    public void setMosquitero(boolean mosquitero) {
        this.mosquitero = mosquitero;
    }

    public boolean isArco() {
        return arco;
    }

    public void setArco(boolean arco) {
        this.arco = arco;
    }

    public String getTipoArco() {
        return tipoArco;
    }

    public void setTipoArco(String tipoArco) {
        this.tipoArco = tipoArco;
    }

    public BigDecimal getMedidaArco() {
        return medidaArco;
    }

    public void setMedidaArco(BigDecimal medidaArco) {
        this.medidaArco = medidaArco;
    }

    public String getTipoCanalillo() {
        return tipoCanalillo;
    }

    public void setTipoCanalillo(String tipoCanalillo) {
        this.tipoCanalillo = tipoCanalillo;
    }

    public BigDecimal getMedidaCanalillo() {
        return medidaCanalillo;
    }

    public void setMedidaCanalillo(BigDecimal medidaCanalillo) {
        this.medidaCanalillo = medidaCanalillo;
    }

    public CatalogoTrabajo getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(CatalogoTrabajo tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    public List<MaterialDetalle> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<MaterialDetalle> materiales) {
        this.materiales = materiales;
    }

    public void calcularSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;

        if (materiales != null) {
            for (MaterialDetalle md : materiales) {
                if (md.getMaterial() != null && md.getCantidad() != null) {
                    subtotal = subtotal.add(md.getMaterial().getPrecio().multiply(md.getCantidad()));
                }
            }
        }

        this.subtotalLinea = subtotal;

        if (this.cantidad > 0) {
            this.precioSoloUnaUnidadCalculado
                    = subtotal.divide(new BigDecimal(this.cantidad), BigDecimal.ROUND_HALF_UP);
        } else {
            this.precioSoloUnaUnidadCalculado = BigDecimal.ZERO;
        }
    }
}
