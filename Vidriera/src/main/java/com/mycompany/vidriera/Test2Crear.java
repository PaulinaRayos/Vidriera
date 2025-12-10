/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.vidriera;

import dao.MaterialDetalleDAO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
//        try {
//            // Crear Cliente y Vendedor existentes en BD 
//            Cliente cliente = new Cliente();
//            cliente.setIdCliente(1); 
//
//            Vendedor vendedor = new Vendedor();
//            vendedor.setIdVendedor(1); 
//
//            // --- Crear Cotización ---
//            Cotizacion cotizacion = new Cotizacion();
//            cotizacion.setCliente(cliente);
//            cotizacion.setVendedor(vendedor);
//            cotizacion.setFecha(new Date());
//            cotizacion.setEstado("Pendiente");
//            cotizacion.setSubtotal(new BigDecimal("1000"));
//            cotizacion.setDescuentoMonto(new BigDecimal("100"));
//            cotizacion.setTotal(cotizacion.getSubtotal().subtract(cotizacion.getDescuentoMonto())); 
//
//            //Crear detalle de ventana
//            VentanaDetalle vd = new VentanaDetalle();
//            vd.setIdTipoTrabajo(1); // id de CatalogoTrabajo de prueba
//            vd.setMedidaHorizontal(new BigDecimal("1.5"));
//            vd.setMedidaVertical(new BigDecimal("2.0"));
//            vd.setCantidad(2);
//            vd.setTipoCristal("Transparente");
//            vd.setNoHojas(2);
//            vd.setPrecioSoloUnaUnidadCalculado(new BigDecimal("500"));
//            vd.setSubtotalLinea(new BigDecimal("1000"));
//            vd.setDescripcion("Ventana de prueba");
//            vd.setTipoVentana(TipoVentana.TIPO_1_5);
//            vd.setMosquitero(false);
//            vd.setArco(false);
//            vd.setCotizacion(cotizacion); // asignar la cotización al detalle
//
//            // Lista de detalles
//            ArrayList<VentanaDetalle> detalles = new ArrayList<>();
//            detalles.add(vd);
//
//            //conexion y BO
//            Conexion con = new Conexion();
//            CotizacionBO bo = new CotizacionBO(con.getConnection());
//
//            //Crear cotizacion completa
//            boolean ok = bo.crearCotizacionCompleta(cotizacion, detalles, new ArrayList<>(), new ArrayList<>());
//            if(ok) {
//                System.out.println("Cotizacion creada con exito. ID: " + cotizacion.getIdCotizacion());
//               
//                                if (vd.getIdVentanaDetalle() > 0) {
//                    System.out.println("\nAsociando materiales...");
//                    
//                    // Preparar materiales a asociar
//                    Map<Integer, BigDecimal> materiales = new HashMap<>();
//                    materiales.put(1, new BigDecimal("3.5"));  // Material 1: 3.5 m²
//                    materiales.put(2, new BigDecimal("8.0"));  // Material 2: 8.0 m
//                    materiales.put(3, new BigDecimal("4"));    // Material 3: 4 pzas
//                    
//                    MaterialDetalleDAO materialDAO = new MaterialDetalleDAO(con.getConnection());
//                    boolean materialesOk = materialDAO.asociarMaterialesVentana(
//                        vd.getIdVentanaDetalle(), 
//                        materiales
//                    );
//                    
//                    if (materialesOk) {
//                        System.out.println("Materiales asociados correctamente:");
//                        System.out.println("  - Material 1: 3.5 unidades");
//                        System.out.println("  - Material 2: 8.0 unidades");
//                        System.out.println("  - Material 3: 4 unidades");
//                    } else {
//                        System.out.println("Error al asociar materiales.");
//                    }
//                } else {
//                    System.out.println("Advertencia: El detalle no tiene ID asignado.");
//                }
//
//                    
//            } else {
//                System.out.println("Error al crear la cotizacion.");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}}