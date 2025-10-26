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
public class MaterialDetalle {
 
    private Material material;
    private BigDecimal cantidad;

    public MaterialDetalle() {}

    public MaterialDetalle(Material material, BigDecimal cantidad) {
        this.material = material;
        this.cantidad = cantidad;
    }

    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }

    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; 
    
    }

}
