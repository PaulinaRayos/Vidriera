/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Vidrieria
 */
public enum TipoCanceleria {
    TIPO_2("2\""),
    TIPO_3("3\"");

    private final String descripcion;

    TipoCanceleria(String descripcion) {
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
    public static TipoCanceleria fromDescripcion(String dbValue) {
        if (dbValue == null) {
            return null;
        }

        // busca por la descripción
        for (TipoCanceleria tipo : values()) {
            if (tipo.getDescripcion().equals(dbValue)) {
                return tipo;
            }
        }

        // si falla buscar por el nombre
        try {
            return TipoCanceleria.valueOf(dbValue);
        } catch (IllegalArgumentException e) {
        }

        return null;
    }
}
