
package modelo;

import java.math.BigDecimal;

/**
 *
 * @author vidrieria
 */
public class MaterialDetalle {

    private Material material;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal precioTotal;

    public MaterialDetalle() {
    }

    public MaterialDetalle(Material material, BigDecimal cantidad, BigDecimal precioUnitario) {
        this.material = material;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        // Calcula automáticamente el total
        if (cantidad != null && precioUnitario != null) {
            this.precioTotal = cantidad.multiply(precioUnitario);
        } else {
            this.precioTotal = BigDecimal.ZERO;
        }
    }


    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
        // Recalcula el total si precioUnitario ya está asignado
        if (this.precioUnitario != null && cantidad != null) {
            this.precioTotal = this.precioUnitario.multiply(cantidad);
        }
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
        // Recalcula el total si cantidad ya está asignada
        if (this.cantidad != null && precioUnitario != null) {
            this.precioTotal = precioUnitario.multiply(this.cantidad);
        }
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    private void recalcularTotal() {
        if (cantidad != null && precioUnitario != null) {
            this.precioTotal = cantidad.multiply(precioUnitario);
        }
    }

}
