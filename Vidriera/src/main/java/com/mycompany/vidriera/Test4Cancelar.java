/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vidriera;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import modelo.Cotizacion;
import negocio.CotizacionBO;
import utils.Conexion;
/**
 *
 * @author Vidrieria
 */
public class Test4Cancelar {
  




    public static void main(String[] args) {
        //id de la cotizacion a modificar
        int idCotizacionACancelar = 3;

        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            CotizacionBO bo = new CotizacionBO(conn);

            // obtenemos la cotizacion de la base
            System.out.println("Buscando cotizacion con ID: " + idCotizacionACancelar);
            Cotizacion cotizacion = bo.obtenerCotizacionPorId(idCotizacionACancelar);

            // verificacion cotizacion
            if (cotizacion == null) {
                System.out.println("No se encontro una cotizacion con el ID: " + idCotizacionACancelar);
                return; // Termina el programa si no existe
            }

            System.out.println("Cotizacion encontrada. Estado actual: '" + cotizacion.getEstado() + "'");

            // modificar el estado
            cotizacion.setEstado("Cancelado");
            cotizacion.setFecha(new Date()); // Opcional: actualiza la fecha a la de cancelacion

            // actualizar 
            System.out.println("\nCambiando estado a Cancelado...");
            boolean exito = bo.actualizarCotizacion(cotizacion);

            // verificar
            if (exito) {
                System.out.println("Cotizacion cancelada con exito");
                System.out.println("Nuevo estado: '" + cotizacion.getEstado() + "'");
            } else {
                System.out.println("Error: No se pudo cancelar la cotizacion.");
            }

        } catch (SQLException e) {
            System.err.println("Ocurrio un error de SQL durante la prueba.");
            e.printStackTrace();
        } finally {
            //cerrar conxion
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

