
package modelo;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Vidrieria
 */
public class CanceleriaFijaDetalle extends DetalleCotizacion {

    private int idCanceleriaDetalle;
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
    private TipoCanceleria tipoCanceleria;
    private boolean bolsa;
    private int numFijosVerticales;
    private int numFijosHorizontales;
    private String tipoTapa;
    private int cantidadTapa;
    private boolean zoclo;
    private String tipoZoclo;
    private boolean junquillo;
    private String tipoJunquillo;
    private boolean arco;
    private String tipoArco;
    private BigDecimal medidaArco;
    private boolean canalillo;
    private String tipoCanalillo;
    private BigDecimal medidaCanalillo;
    private CatalogoTrabajo tipoTrabajo;
    private List<MaterialDetalle> materiales;

    // Constructor vacío
    public CanceleriaFijaDetalle() {
    }

    // Constructor con id
    public CanceleriaFijaDetalle(int idCanceleriaDetalle, int idTipoTrabajo, Cotizacion cotizacion, BigDecimal medidaHorizontal, BigDecimal medidaVertical, int cantidad, String tipoCristal, int noHojas, BigDecimal precioSoloUnaUnidadCalculado, BigDecimal subtotalLinea, String descripcion, TipoCanceleria tipoCanceleria, boolean bolsa, int numFijosVerticales, int numFijosHorizontales, String tipoTapa, int cantidadTapa, boolean zoclo, String tipoZoclo, boolean junquillo, String tipoJunquillo, boolean arco, String tipoArco, BigDecimal medidaArco, boolean canalillo, String tipoCanalillo, BigDecimal medidaCanalillo) {
        this.idCanceleriaDetalle = idCanceleriaDetalle;
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
        this.tipoCanceleria = tipoCanceleria;
        this.bolsa = bolsa;
        this.numFijosVerticales = numFijosVerticales;
        this.numFijosHorizontales = numFijosHorizontales;
        this.tipoTapa = tipoTapa;
        this.cantidadTapa = cantidadTapa;
        this.zoclo = zoclo;
        this.tipoZoclo = tipoZoclo;
        this.junquillo = junquillo;
        this.tipoJunquillo = tipoJunquillo;
        this.arco = arco;
        this.tipoArco = tipoArco;
        this.medidaArco = medidaArco;
        this.canalillo = canalillo;
        this.tipoCanalillo = tipoCanalillo;
        this.medidaCanalillo = medidaCanalillo;
    }

    // Constructor sin id
    public CanceleriaFijaDetalle(int idTipoTrabajo, Cotizacion cotizacion, BigDecimal medidaHorizontal, BigDecimal medidaVertical, int cantidad, String tipoCristal, int noHojas, BigDecimal precioSoloUnaUnidadCalculado, BigDecimal subtotalLinea, String descripcion, TipoCanceleria tipoCanceleria, boolean bolsa, int numFijosVerticales, int numFijosHorizontales, String tipoTapa, int cantidadTapa, boolean zoclo, String tipoZoclo, boolean junquillo, String tipoJunquillo, boolean arco, String tipoArco, BigDecimal medidaArco, boolean canalillo, String tipoCanalillo, BigDecimal medidaCanalillo) {
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
        this.tipoCanceleria = tipoCanceleria;
        this.bolsa = bolsa;
        this.numFijosVerticales = numFijosVerticales;
        this.numFijosHorizontales = numFijosHorizontales;
        this.tipoTapa = tipoTapa;
        this.cantidadTapa = cantidadTapa;
        this.zoclo = zoclo;
        this.tipoZoclo = tipoZoclo;
        this.junquillo = junquillo;
        this.tipoJunquillo = tipoJunquillo;
        this.arco = arco;
        this.tipoArco = tipoArco;
        this.medidaArco = medidaArco;
        this.canalillo = canalillo;
        this.tipoCanalillo = tipoCanalillo;
        this.medidaCanalillo = medidaCanalillo;
    }

    // Getters y setters  
    public int getIdCanceleriaDetalle() {
        return idCanceleriaDetalle;
    }

    public void setIdCanceleriaDetalle(int idCanceleriaDetalle) {
        this.idCanceleriaDetalle = idCanceleriaDetalle;
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

    public TipoCanceleria getTipoCanceleria() {
        return tipoCanceleria;
    }

    public void setTipoCanceleria(TipoCanceleria tipoCanceleria) {
        this.tipoCanceleria = tipoCanceleria;
    }

    public boolean isBolsa() {
        return bolsa;
    }

    public void setBolsa(boolean bolsa) {
        this.bolsa = bolsa;
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

    public String getTipoTapa() {
        return tipoTapa;
    }

    public void setTipoTapa(String tipoTapa) {
        this.tipoTapa = tipoTapa;
    }

    public int getCantidadTapa() {
        return cantidadTapa;
    }

    public void setCantidadTapa(int cantidadTapa) {
        this.cantidadTapa = cantidadTapa;
    }

    public boolean isZoclo() {
        return zoclo;
    }

    public void setZoclo(boolean zoclo) {
        this.zoclo = zoclo;
    }

    public String getTipoZoclo() {
        return tipoZoclo;
    }

    public void setTipoZoclo(String tipoZoclo) {
        this.tipoZoclo = tipoZoclo;
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

    public boolean isCanalillo() {
        return canalillo;
    }

    public void setCanalillo(boolean canalillo) {
        this.canalillo = canalillo;
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
        for (MaterialDetalle md : materiales) {
            if (md.getMaterial() != null && md.getCantidad() != null) {
                subtotal = subtotal.add(md.getMaterial().getPrecio().multiply(md.getCantidad()));
            }
        }
        this.subtotalLinea = subtotal;

        if (this.cantidad > 0) {
            this.precioSoloUnaUnidadCalculado = subtotal.divide(new BigDecimal(this.cantidad), BigDecimal.ROUND_HALF_UP);
        } else {
            this.precioSoloUnaUnidadCalculado = BigDecimal.ZERO;
        }
    }

    @Override
    public String toString() {
        return "CanceleriaFijaDetalle{" + "idCanceleriaDetalle=" + idCanceleriaDetalle + ", idTipoTrabajo=" + idTipoTrabajo + ", cotizacion=" + cotizacion + ", medidaHorizontal=" + medidaHorizontal + ", medidaVertical=" + medidaVertical + ", cantidad=" + cantidad + ", tipoCristal=" + tipoCristal + ", noHojas=" + noHojas + ", precioSoloUnaUnidadCalculado=" + precioSoloUnaUnidadCalculado + ", subtotalLinea=" + subtotalLinea + ", descripcion=" + descripcion + ", tipoCanceleria=" + tipoCanceleria + ", bolsa=" + bolsa + ", numFijosVerticales=" + numFijosVerticales + ", numFijosHorizontales=" + numFijosHorizontales + ", tipoTapa=" + tipoTapa + ", cantidadTapa=" + cantidadTapa + ", zoclo=" + zoclo + ", tipoZoclo=" + tipoZoclo + ", junquillo=" + junquillo + ", tipoJunquillo=" + tipoJunquillo + ", arco=" + arco + ", tipoArco=" + tipoArco + ", medidaArco=" + medidaArco + ", canalillo=" + canalillo + ", tipoCanalillo=" + tipoCanalillo + ", medidaCanalillo=" + medidaCanalillo + '}';
    }

}
