/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.math.BigDecimal;

/**
 *
 * @author delll
 */
public class Material {

    private int idMaterial;
    private String descripcion;
    private BigDecimal precio;
    private int stockActual;
    private TipoMaterial tipo;

    public enum TipoMaterial {
        VIDRIO, ALUMINIO, JUNQUILLO, ZOCLO, TAPA, CANALILLO, DUELA,
        ADAPTADOR, MOSQUITERO, PIVOTE, JALADERA, BARRA, BISAGRA, MALLA,
        TORNILLERIA, EMPAQUE, SELLADOR, HERRAJE, ACCESORIO, PERFIL, ARCO, BOLSA, OTRO, TELA
    }

    public Material() {
        this.stockActual = 0;
    }

    public Material(int idMaterial, String descripcion, BigDecimal precio,
            int stockActual, TipoMaterial tipo) {
        this.idMaterial = idMaterial;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stockActual = stockActual;
        this.tipo = tipo;
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

    public String getDisplayName() {
        return idMaterial + " - " + descripcion;
    }

}
