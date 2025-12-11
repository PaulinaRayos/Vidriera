package modelo;

import java.math.BigDecimal;

/**
 *
 * @author vidrieria
 */
public class Material {

    private int idMaterial;
    private String descripcion;
    private BigDecimal precio;
    private int stockActual;
    private TipoMaterial tipo;
    private String unidadMedida;
    private Boolean estadoActivo;

    public enum TipoMaterial {
        VIDRIO, ALUMINIO, JUNQUILLO, ZOCLO, TAPA, CANALILLO, DUELA,
        ADAPTADOR, MOSQUITERO, PIVOTE, JALADERA, BARRA, BISAGRA, MALLA,
        TORNILLERIA, EMPAQUE, SELLADOR, HERRAJE, ACCESORIO, PERFIL, ARCO, BOLSA, OTRO, TELA
    }

    public Material() {
        this.stockActual = 0;
        this.estadoActivo = true;
    }

    public Material(int idMaterial, String descripcion, BigDecimal precio,
            int stockActual, TipoMaterial tipo, String unidadMedida, Boolean estadoActivo) {
        this.idMaterial = idMaterial;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stockActual = stockActual;
        this.tipo = tipo;
        this.unidadMedida = unidadMedida;
        this.estadoActivo = estadoActivo;
    }

    // Getters y Setters
    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public TipoMaterial getTipo() {
        return tipo;
    }

    public void setTipo(TipoMaterial tipo) {
        this.tipo = tipo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Boolean getEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(Boolean estadoActivo) {
        this.estadoActivo = estadoActivo;
    }

    public String getDisplayName() {
        return idMaterial + " - " + descripcion;
    }

}
