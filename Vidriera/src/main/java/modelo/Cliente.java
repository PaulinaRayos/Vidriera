/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Vidrieria
 */
public class Cliente {

    private int idCliente;
    private String nombre;
    private String telefono;
    private String direccion;
    private String email;
    private String RFC;
    private String estado;
    private String ciudad;
    private String colonia;
    

    //Constructor vacío
    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String telefono, String direccion, String email, String RFC, String estado, String ciudad, String colonia) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.RFC = RFC;
        this.estado = estado;
        this.ciudad = ciudad;
        this.colonia = colonia;
    }

   
    

    //Constructor sin id
    public Cliente(String nombre, String telefono, String direccion, String email, String RFC, String estado, String ciudad, String colonia) {    
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.RFC = RFC;
        this.estado = estado;
        this.ciudad = ciudad;
        this.colonia = colonia;
    }

    // Getters y setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

}
