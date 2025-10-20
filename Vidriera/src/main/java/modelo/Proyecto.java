/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Vidrieria
 */
public class Proyecto {
    private int idProyecto;
    private String estado;
    private Date fechaInicio;
    private Date fechaEntregaEstimada;
    private Cliente cliente;
 
    //Constructor vacío
    public Proyecto() {
    }

    //Constructor con id
    public Proyecto(int idProyecto, String estado, Date fechaInicio, Date fechaEntregaEstimada, Cliente cliente) {
        this.idProyecto = idProyecto;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.cliente = cliente;
    }

    //Constructor sin id
    public Proyecto(String estado, Date fechaInicio, Date fechaEntregaEstimada, Cliente cliente) {
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.cliente = cliente;
    }
    
    // Getters y setters
    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(Date fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "idProyecto=" + idProyecto + ", estado=" + estado + ", fechaInicio=" + fechaInicio + ", fechaEntregaEstimada=" + fechaEntregaEstimada + ", cliente=" + cliente + '}';
    }
    
    
    
}
