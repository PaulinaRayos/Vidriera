/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.vidriera;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Cliente;
import modelo.Cotizacion;
import modelo.TipoVentana;
import modelo.Vendedor;
import modelo.VentanaDetalle;
import negocio.CotizacionBO;
import utils.Conexion;

/**
 *
 * @author Vidrieria
 */
public class Test2Crear {

    
public static void main(String[] args) {
        try {
            // Crear Cliente y Vendedor existentes en BD 
            Cliente cliente = new Cliente();
            cliente.setIdCliente(1); 

            Vendedor vendedor = new Vendedor();
            vendedor.setIdVendedor(1); 

            // --- Crear Cotización ---
            Cotizacion cotizacion = new Cotizacion();
            cotizacion.setCliente(cliente);
            cotizacion.setVendedor(vendedor);
            cotizacion.setFecha(new Date());
            cotizacion.setEstado("Pendiente");
            cotizacion.setSubtotal(new BigDecimal("1000"));
            cotizacion.setDescuentoMonto(new BigDecimal("100"));
            cotizacion.setTotal(cotizacion.getSubtotal().subtract(cotizacion.getDescuentoMonto())); 

            //Crear detalle de ventana
            VentanaDetalle vd = new VentanaDetalle();
            vd.setIdTipoTrabajo(1); // id de CatalogoTrabajo de prueba
            vd.setMedidaHorizontal(new BigDecimal("1.5"));
            vd.setMedidaVertical(new BigDecimal("2.0"));
            vd.setCantidad(2);
            vd.setTipoCristal("Transparente");
            vd.setNoHojas(2);
            vd.setPrecioSoloUnaUnidadCalculado(new BigDecimal("500"));
            vd.setSubtotalLinea(new BigDecimal("1000"));
            vd.setDescripcion("Ventana de prueba");
            vd.setTipoVentana(TipoVentana.TIPO_1_5);
            vd.setMosquitero(false);
            vd.setArco(false);
            vd.setCotizacion(cotizacion); // asignar la cotización al detalle

            // Lista de detalles
            ArrayList<VentanaDetalle> detalles = new ArrayList<>();
            detalles.add(vd);

            //conexion y BO
            Conexion con = new Conexion();
            CotizacionBO bo = new CotizacionBO(con.getConnection());

            //Crear cotizacion completa
            boolean ok = bo.crearCotizacionCompleta(cotizacion, detalles, new ArrayList<>(), new ArrayList<>());
            if(ok) {
                System.out.println("Cotizacion creada con exito. ID: " + cotizacion.getIdCotizacion());
            } else {
                System.out.println("Error al crear la cotizacion.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}