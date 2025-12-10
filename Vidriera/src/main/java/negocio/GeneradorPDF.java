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
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author delll
 */
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class GeneradorPDF {

    private static String limpiarTexto(String txt) {
        if (txt == null) {
            return "";
        }
        return txt.replace("\n", " ").replace("\r", " ");
    }

    public static byte[] generarCotizacionPdf(Cotizacion cotizacion) {
        try {
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage(PDRectangle.LETTER);
            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);

            // ================= LOGO =================
            try {
                InputStream is = GeneradorPDF.class
                        .getClassLoader()
                        .getResourceAsStream("LogoVidrieria.jpg");

                if (is != null) {
                    PDImageXObject logo = PDImageXObject.createFromByteArray(
                            doc,
                            is.readAllBytes(),
                            "logo"
                    );

                    float logoWidth = 120;
                    float logoHeight = 90;

                    float logoX = 40;
                    float logoY = page.getMediaBox().getHeight() - 110;

                    content.drawImage(logo, logoX, logoY, logoWidth, logoHeight);
                } else {
                    System.out.println("NO SE ENCONTRÓ EL LOGO EN RESOURCES");
                }

            } catch (Exception e) {
                System.out.println("Error al cargar logo: " + e.getMessage());
            }

            // ================= TÍTULO =================
            String titulo = "COTIZACIÓN";
            float fontSize = 18;
            float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(titulo) / 1000 * fontSize;
            float pageWidth = page.getMediaBox().getWidth();
            float centerX = (pageWidth - titleWidth) / 2;

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
            content.newLineAtOffset(centerX, 700);
            content.showText(titulo);
            content.endText();

            // ================= DATOS DEL CLIENTE =================
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 12);
            content.newLineAtOffset(40, 640);
            content.showText("Cliente: " + limpiarTexto(cotizacion.getCliente().getNombre()));
            content.newLineAtOffset(0, -18);
            content.showText("Teléfono: " + limpiarTexto(cotizacion.getCliente().getTelefono()));
            content.newLineAtOffset(0, -18);
            content.showText("Dirección: " + limpiarTexto(cotizacion.getCliente().getDireccion()));
            content.newLineAtOffset(0, -18);
            content.showText("Fecha: " + limpiarTexto(String.valueOf(cotizacion.getFecha())));
            content.endText();

            float y = 540;
            float startX = 40;

            // ================= TÍTULO DESCRIPCIONES =================
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 13);
            content.newLineAtOffset(startX, y);
            content.showText("DESCRIPCIÓN DE LOS TRABAJOS:");
            content.endText();

            y -= 25;

            content.setFont(PDType1Font.HELVETICA, 12);

            // ================= VENTANAS =================
            List<VentanaDetalle> v = cotizacion.getVentanaDetalles();
            if (v != null) {
                for (VentanaDetalle d : v) {
                    content.beginText();
                    content.newLineAtOffset(startX, y);
                    content.showText("- " + limpiarTexto(d.getDescripcion()));
                    content.endText();
                    y -= 18;
                }
            }

            // ================= PUERTAS =================
            List<PuertaAbatibleDetalle> p = cotizacion.getPuertaAbatibleDetalles();
            if (p != null) {
                for (PuertaAbatibleDetalle d : p) {
                    content.beginText();
                    content.newLineAtOffset(startX, y);
                    content.showText("- " + limpiarTexto(d.getDescripcion()));
                    content.endText();
                    y -= 18;
                }
            }

            // ================= CANCELERÍAS =================
            List<CanceleriaFijaDetalle> c = cotizacion.getCanceleriaFijaDetalles();
            if (c != null) {
                for (CanceleriaFijaDetalle d : c) {
                    content.beginText();
                    content.newLineAtOffset(startX, y);
                    content.showText("- " + limpiarTexto(d.getDescripcion()));
                    content.endText();
                    y -= 18;
                }
            }

            // ================= TOTAL =================
            y -= 30;
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.newLineAtOffset(startX, y);
            content.showText("TOTAL FINAL: $" + cotizacion.getTotal());
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
