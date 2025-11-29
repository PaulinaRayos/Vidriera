package presentacion;

import modelo.Cotizacion;
import modelo.VentanaDetalle;
import modelo.PuertaAbatibleDetalle;
import modelo.CanceleriaFijaDetalle;
import modelo.MaterialDetalle;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Dialog que muestra la vista previa tipo documento de una cotización. Estilo:
 * C2-B (títulos gruesos alineados a la izquierda, líneas separadoras fuertes).
 */
public class VistaPreviaCotizacionDialog extends JDialog {

    public VistaPreviaCotizacionDialog(Frame parent, Cotizacion cotizacion) {
        super(parent, "Vista previa - Cotización", true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel content = construirPanelCotizacion(cotizacion);
        JScrollPane scroll = new JScrollPane(content);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        add(scroll, BorderLayout.CENTER);

        setSize(520, 650);
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private JPanel construirPanelCotizacion(Cotizacion c) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        JPanel contentWrapper = new JPanel();
        contentWrapper.setBackground(Color.WHITE);
        contentWrapper.setLayout(new BoxLayout(contentWrapper, BoxLayout.Y_AXIS));
        contentWrapper.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25)); // ✅ Menos margen

        // ===== LOGO =====
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/LogoVidrieria.png"));
            Image img = icon.getImage();

            int anchoDeseado = 180; // <- aquí haces el logo más larguito
            int altoAuto = (int) ((double) icon.getIconHeight() / icon.getIconWidth() * anchoDeseado);

            Image imgEscalada = img.getScaledInstance(anchoDeseado, altoAuto, Image.SCALE_SMOOTH);

            JLabel lblLogo = new JLabel(new ImageIcon(imgEscalada));
            lblLogo.setAlignmentX(Component.LEFT_ALIGNMENT);

            contentWrapper.add(lblLogo);
            contentWrapper.add(Box.createVerticalStrut(6)); // separación mínima real

        } catch (Exception e) {
            System.out.println("No se pudo cargar el logo: " + e.getMessage());
        }

        // ===== TÍTULO CENTRADO =====
        JLabel titulo = new JLabel("COTIZACIÓN");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentWrapper.add(titulo);

        contentWrapper.add(Box.createVerticalStrut(8));

        // ===== DATOS DEL CLIENTE (COMO PDF, COMPACTOS) =====
        JPanel datos = new JPanel();
        datos.setLayout(new BoxLayout(datos, BoxLayout.Y_AXIS));
        datos.setBackground(Color.WHITE);
        datos.setAlignmentX(Component.LEFT_ALIGNMENT);

        datos.add(labelNormal("Cliente: " + safe(c.getCliente() != null ? c.getCliente().getNombre() : "")));
        datos.add(labelNormal("Teléfono: " + safe(c.getCliente() != null ? c.getCliente().getTelefono() : "")));
        datos.add(labelNormal("Dirección: " + safe(c.getCliente() != null ? c.getCliente().getDireccion() : "")));
        datos.add(labelNormal("Fecha: " + (c.getFecha() != null
                ? new SimpleDateFormat("yyyy-MM-dd").format(c.getFecha())
                : "")));

        contentWrapper.add(datos);

// Separación REAL mínima como PDF
        contentWrapper.add(Box.createVerticalStrut(8));
        contentWrapper.add(thickSeparator());
        contentWrapper.add(Box.createVerticalStrut(8));

        // ===== DESCRIPCIÓN =====
        contentWrapper.add(titleLeft("DESCRIPCIÓN DE LOS TRABAJOS:"));
        contentWrapper.add(Box.createVerticalStrut(6));

        if (c.getVentanaDetalles() != null) {
            for (VentanaDetalle d : c.getVentanaDetalles()) {
                contentWrapper.add(labelNormal("• " + safe(d.getDescripcion())));
            }
        }

        if (c.getPuertaAbatibleDetalles() != null) {
            for (PuertaAbatibleDetalle d : c.getPuertaAbatibleDetalles()) {
                contentWrapper.add(labelNormal("• " + safe(d.getDescripcion())));
            }
        }

        if (c.getCanceleriaFijaDetalles() != null) {
            for (CanceleriaFijaDetalle d : c.getCanceleriaFijaDetalles()) {
                contentWrapper.add(labelNormal("• " + safe(d.getDescripcion())));
            }
        }

        contentWrapper.add(Box.createVerticalStrut(12));
        contentWrapper.add(thickSeparator());
        contentWrapper.add(Box.createVerticalStrut(10));

        // ===== TOTAL FINAL =====
        JLabel lblTotal = new JLabel("TOTAL FINAL: " + formatCurrency(c.getTotal()));
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentWrapper.add(lblTotal);

        contentWrapper.add(Box.createVerticalStrut(15));

        panel.add(contentWrapper, BorderLayout.CENTER);
        contentWrapper.setMaximumSize(new Dimension(480, Integer.MAX_VALUE));

        return panel;
    }

    // --------- UI helper components ----------
    private JLabel titleLeft(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.BOLD, 16));
        l.setForeground(Color.BLACK);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JLabel labelNormal(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.PLAIN, 13));
        l.setForeground(Color.DARK_GRAY);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JLabel labelBold(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.BOLD, 13));
        l.setForeground(Color.BLACK);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JComponent thickSeparator() {
        JSeparator s = new JSeparator(SwingConstants.HORIZONTAL);
        s.setPreferredSize(new Dimension(1, 4));
        s.setMaximumSize(new Dimension(Integer.MAX_VALUE, 4));
        return s;
    }

    private String formatCurrency(BigDecimal amount) {
        if (amount == null) {
            amount = BigDecimal.ZERO;
        }
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
        return nf.format(amount);
    }

    private String safe(Object o) {
        return o != null ? o.toString() : "";
    }

    public static void mostrarPrevia(Frame parent, Cotizacion cotizacion) {
        VistaPreviaCotizacionDialog dialog = new VistaPreviaCotizacionDialog(parent, cotizacion);
        dialog.setVisible(true);
    }
}
