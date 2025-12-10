/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Vidrieria
 */
public enum TipoVentana {
    TIPO_1_5("1 ½\""),
    TIPO_2("2\""),
    TIPO_3_7_8("3 7/8\"");

    private final String descripcion;

    TipoVentana(String descripcion) {
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
    public static TipoVentana fromDescripcion(String dbValue) {
        if (dbValue == null) {
            return null;
        }

        // buscar por la descripción
        for (TipoVentana tipo : values()) {
            if (tipo.getDescripcion().equals(dbValue)) {
                return tipo;
            }
        }

        // si falla busca por el nombre
        try {
            return TipoVentana.valueOf(dbValue);
        } catch (IllegalArgumentException e) {
        }

        return null;
    }
}
