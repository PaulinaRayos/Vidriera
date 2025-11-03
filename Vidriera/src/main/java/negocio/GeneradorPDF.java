/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.io.ByteArrayOutputStream;
import java.util.List;
import modelo.CanceleriaFijaDetalle;
import modelo.Cotizacion;
import modelo.PuertaAbatibleDetalle;
import modelo.VentanaDetalle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author delll
 */
public class GeneradorPDF {
    
public static byte[] generarCotizacionPdf(Cotizacion cotizacion) {
        try {
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage(PDRectangle.LETTER);
            doc.addPage(page);
            PDPageContentStream content = new PDPageContentStream(doc, page);

            // Encabezado y datos cliente
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 18);
            content.newLineAtOffset(40, 730);
            content.showText("COTIZACIÓN");
            content.setFont(PDType1Font.HELVETICA, 12);
            content.newLineAtOffset(0, -30);
            content.showText("Cliente: " + cotizacion.getCliente().getNombre());
            content.newLineAtOffset(0, -20);
            content.showText("Fecha: " + cotizacion.getFecha());
            content.newLineAtOffset(0, -20);
            content.showText("Dirección: " + cotizacion.getCliente().getDireccion());
            content.newLineAtOffset(0, -20);
            content.showText("Teléfono: " + cotizacion.getCliente().getTelefono());
            content.endText();

            float y = 590;
            float startX = 40;

            // Encabezados de tabla
            content.setLineWidth(1f);
            content.moveTo(startX, y + 20);
            content.lineTo(startX + 510, y + 20);
            content.stroke();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 12);
            content.newLineAtOffset(startX, y);
            content.showText("Tipo");
            content.newLineAtOffset(80, 0);
            content.showText("Descripción");
            content.newLineAtOffset(180, 0);
            content.showText("Medidas");
            content.newLineAtOffset(100, 0);
            content.showText("Cantidad");
            content.newLineAtOffset(60, 0);
            content.showText("Subtotal");
            content.endText();

            y -= 20;

            // ||---------------------- DETALLES DE VENTANA ----------------------||
            List<VentanaDetalle> detallesVentana = cotizacion.getVentanaDetalles();
            for (VentanaDetalle v : detallesVentana) {
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 12);
                content.newLineAtOffset(startX, y);
                content.showText("Ventana");
                content.newLineAtOffset(80, 0);
                content.showText(v.getDescripcion());
                content.newLineAtOffset(180, 0);
                content.showText(""+v.getMedidaHorizontal()+"X"+v.getMedidaVertical()+""); // formatea "2.00 x 1.00"
                content.newLineAtOffset(100, 0);
                content.showText(String.valueOf(v.getCantidad()));
                content.newLineAtOffset(60, 0);
                content.showText("$" + v.getSubtotalLinea());
                content.endText();
                y -= 18;
            }
            // -- igual para puertas y cancelerías si existen:
            List<PuertaAbatibleDetalle> detallesPuerta = cotizacion.getPuertaAbatibleDetalles();
            for (PuertaAbatibleDetalle p : detallesPuerta) {
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 12);
                content.newLineAtOffset(startX, y);
                content.showText("Puerta");
                content.newLineAtOffset(80, 0);
                content.showText(p.getDescripcion());
                content.newLineAtOffset(180, 0);
                content.showText(""+p.getMedidaHorizontal()+"X"+p.getMedidaVertical()+"");
                content.newLineAtOffset(100, 0);
                content.showText(String.valueOf(p.getCantidad()));
                content.newLineAtOffset(60, 0);
                content.showText("$" + p.getSubtotalLinea());
                content.endText();
                y -= 18;
            }
            List<CanceleriaFijaDetalle> detallesCanceleria = cotizacion.getCanceleriaFijaDetalles();
            for (CanceleriaFijaDetalle c : detallesCanceleria) {
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 12);
                content.newLineAtOffset(startX, y);
                content.showText("Cancelería");
                content.newLineAtOffset(80, 0);
                content.showText(c.getDescripcion());
                content.newLineAtOffset(180, 0);
                content.showText(""+c.getMedidaHorizontal()+"X"+c.getMedidaVertical()+"");
                content.newLineAtOffset(100, 0);
                content.showText(String.valueOf(c.getCantidad()));
                content.newLineAtOffset(60, 0);
                content.showText("$" + c.getSubtotalLinea());
                content.endText();
                y -= 18;
            }

            // Línea separadora
            y -= 12;
            content.moveTo(startX, y);
            content.lineTo(startX + 510, y);
            content.stroke();

            // Totales y descuentos
            y -= 20;
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 13);
            content.newLineAtOffset(startX, y);
            content.showText("Subtotal: $" + cotizacion.getSubtotal());
            content.newLineAtOffset(200, 0);
            content.showText("Descuento: $" + cotizacion.getDescuentoMonto());
            content.newLineAtOffset(130, 0);
            content.showText("IVA: $" + cotizacion.getIva());
            content.endText();

            y -= 20;
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 15);
            content.newLineAtOffset(startX, y);
            content.showText("Total: $" + cotizacion.getTotal());
            content.endText();

            content.close();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            doc.save(baos);
            doc.close();
            return baos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
