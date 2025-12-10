/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Vidrieria
 */
public enum TipoPuerta {
    SERIE_1751("Serie 1.751"),
    LINEA_PESADA("Línea Pesada");

    private final String descripcion;

    TipoPuerta(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    /**
     * Busca una instancia del Enum basado en su descripción o nombre.
     *
     * @param dbValue El detalle de la base de datos
     * @return El Enum correspondiente
     */
    public static TipoPuerta fromDescripcion(String dbValue) {
        if (dbValue == null) {
            return null;
        }

        // buscar por la descripción 
        for (TipoPuerta tipo : values()) {
            if (tipo.getDescripcion().equals(dbValue)) {
                return tipo;
            }
        }

        // si falla busca por el nombre 
        try {
            return TipoPuerta.valueOf(dbValue);
        } catch (IllegalArgumentException e) {
        }

        return null;
    }
}
