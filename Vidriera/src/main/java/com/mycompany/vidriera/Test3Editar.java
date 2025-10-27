/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vidriera;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import modelo.Cotizacion;
import modelo.TipoVentana;
import modelo.VentanaDetalle;
import negocio.CotizacionBO;
import utils.Conexion;

/**
 *
 * @author Vidrieria
 */
public class Test3Editar {
//
//    public static void main(String[] args) {
//        // ID de la cotizacion que quieres editar
//        int idCotizacionAEditar = 1;
//
//        Connection conn = null;
//        try {
//            conn = Conexion.getConnection();
//            CotizacionBO bo = new CotizacionBO(conn);
//
//            // 1. OBTENER LA COTIZACION COMPLETA DE LA BD
//            Cotizacion cotizacion = bo.obtenerCotizacionPorId(idCotizacionAEditar);
//            if (cotizacion == null) {
//                System.out.println("No se encontro la cotizacion con ID: " + idCotizacionAEditar);
//                return;
//            }
//            
//            System.out.println("Cotizacion encontrada. Subtotal original: " + cotizacion.getSubtotal());
//            System.out.println("Cliente original: " + cotizacion.getCliente().getNombre());
//
//            // 2. MODIFICAR LOS DATOS PRINCIPALES DE LA COTIZACION
//            cotizacion.setFecha(new Date()); // Actualizamos la fecha de modificacion
//            cotizacion.setEstado("Aprobada");
//            cotizacion.setSubtotal(new BigDecimal("1800.00"));
//            cotizacion.setDescuentoMonto(new BigDecimal("200.00"));
//
//            // 3. CREAR LA NUEVA LISTA DE DETALLES QUE REEMPLAZARA A LA ANTIGUA
//            VentanaDetalle vd1 = new VentanaDetalle();
//            vd1.setIdTipoTrabajo(1);
//            vd1.setMedidaHorizontal(new BigDecimal("1.2"));
//            vd1.setMedidaVertical(new BigDecimal("1.0"));
//            vd1.setCantidad(1);
//            vd1.setTipoCristal("Claro 6mm");
//            vd1.setNoHojas(2);
//            vd1.setPrecioSoloUnaUnidadCalculado(new BigDecimal("800"));
//            vd1.setSubtotalLinea(new BigDecimal("800"));
//            vd1.setDescripcion("Ventana de cocina editada");
//            vd1.setTipoVentana(TipoVentana.TIPO_1_5);
//            vd1.setCotizacion(cotizacion); // Asignar la cotizacion al detalle
//
//            VentanaDetalle vd2 = new VentanaDetalle();
//            vd2.setIdTipoTrabajo(1);
//            vd2.setMedidaHorizontal(new BigDecimal("2.0"));
//            vd2.setMedidaVertical(new BigDecimal("1.5"));
//            vd2.setCantidad(1);
//            vd2.setTipoCristal("Tintex 6mm");
//            vd2.setNoHojas(3);
//            vd2.setPrecioSoloUnaUnidadCalculado(new BigDecimal("1000"));
//            vd2.setSubtotalLinea(new BigDecimal("1000"));
//            vd2.setDescripcion("Ventana de sala NUEVA");
//            vd2.setTipoVentana(TipoVentana.TIPO_2); // Asegurate que TIPO_2_0 exista en tu enum
//            vd2.setCotizacion(cotizacion); 
//
//            // Crear la lista de detalles actualizada
//            ArrayList<VentanaDetalle> nuevosDetalles = new ArrayList<>();
//            nuevosDetalles.add(vd1);
//            nuevosDetalles.add(vd2);
//
//            // 4. LLAMAR AL METODO DE ACTUALIZACION COMPLETA
//            System.out.println("\nActualizando cotizacion y sus detalles...");
//            boolean exito = bo.actualizarCotizacionCompleta(cotizacion, nuevosDetalles, new ArrayList<>(), new ArrayList<>());
//
//            if (exito) {
//                System.out.println("Cotizacion actualizada con exito");
//                System.out.println("Nuevo subtotal: " + cotizacion.getSubtotal());
//                System.out.println("Nuevo total calculado: " + cotizacion.getTotal());
//            } else {
//                System.out.println("Error al actualizar la cotizacion.");
//            }
//
//        } catch (SQLException e) {
//            System.err.println("Ocurrio un error de SQL durante la prueba.");
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    
}