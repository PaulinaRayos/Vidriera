/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author pauli
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

}
