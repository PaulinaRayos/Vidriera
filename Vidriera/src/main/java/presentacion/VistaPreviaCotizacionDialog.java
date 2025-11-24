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
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        setSize(720, 780);
        setLocationRelativeTo(parent);

    }

    private JPanel construirPanelCotizacion(Cotizacion c) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        // layout a BorderLayout para controlar el centrado
        panel.setLayout(new BorderLayout());

        // Contenedor principal que se alinea a la izquierda
        JPanel contentWrapper = new JPanel();
        contentWrapper.setBackground(Color.WHITE);
        contentWrapper.setLayout(new BoxLayout(contentWrapper, BoxLayout.Y_AXIS));
        contentWrapper.setBorder(BorderFactory.createEmptyBorder(24, 30, 24, 30)); // Margen de 30px a la izquierda

        // Encabezado 
        JLabel titulo = new JLabel("COTIZACIÓN");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- ALINEAR A LA IZQUIERDA
        contentWrapper.add(titulo);

        contentWrapper.add(Box.createVerticalStrut(14));

        // Datos cliente / fecha / estado
        // Alineamos los datos principales a la izquierda
        JPanel datos = new JPanel(new GridLayout(0, 4, 10, 4)); // Usamos 4 columnas para alinear
        datos.setBackground(Color.WHITE);
        datos.setAlignmentX(Component.LEFT_ALIGNMENT);

        datos.add(labelBold("Cliente:"));
        datos.add(labelNormal(safe(c.getCliente() != null ? c.getCliente().getNombre() : "N/A")));
        datos.add(labelBold("Fecha:"));
        datos.add(labelNormal(c.getFecha() != null ? new SimpleDateFormat("yyyy-MM-dd").format(c.getFecha()) : ""));

        datos.add(labelBold("Dirección:"));
        datos.add(labelNormal(safe(c.getCliente() != null ? c.getCliente().getDireccion() : "")));
        datos.add(labelBold("Estado:"));
        datos.add(labelNormal(safe(c.getEstado())));

        datos.add(labelBold("Teléfono:"));
        datos.add(labelNormal(safe(c.getCliente() != null ? c.getCliente().getTelefono() : "")));
        datos.add(labelBold("Vendedor:"));
        datos.add(labelNormal(safe(c.getVendedor() != null ? c.getVendedor().getNombre() : "N/A")));

        contentWrapper.add(datos);

        contentWrapper.add(Box.createVerticalStrut(14));
        contentWrapper.add(thickSeparator());
        contentWrapper.add(Box.createVerticalStrut(14));

        // bloques
        contentWrapper.add(titleLeft("DETALLES DE LA COTIZACIÓN"));
        contentWrapper.add(Box.createVerticalStrut(10));
        contentWrapper.add(crearBloquesDetalleFactura(c));

        contentWrapper.add(Box.createVerticalStrut(12));
        contentWrapper.add(thickSeparator());
        contentWrapper.add(Box.createVerticalStrut(12));

        // Totales
        JPanel resumen = new JPanel(new GridLayout(0, 2, 8, 8));
        resumen.setBackground(Color.WHITE);
        resumen.add(labelBold("Subtotal (Materiales):"));
        resumen.add(labelNormal(formatCurrency(c.getSubtotal())));
        resumen.add(labelBold("Mano de Obra (45%):"));
        resumen.add(labelNormal(formatCurrency(c.getManoObra())));
        resumen.add(labelBold("Descuento:"));
        resumen.add(labelNormal(formatCurrency(c.getDescuentoMonto())));
        resumen.add(labelBold("IVA (16%):"));
        resumen.add(labelNormal(formatCurrency(c.getIva())));

        contentWrapper.add(resumen);
        contentWrapper.add(Box.createVerticalStrut(10));

        JLabel lblTotal = new JLabel("TOTAL: " + formatCurrency(c.getTotal()));
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentWrapper.add(lblTotal);

        contentWrapper.add(Box.createVerticalStrut(24));

        // Añadir el wrapper al centro para que use el margen izquierdo
        panel.add(contentWrapper, BorderLayout.CENTER);
        contentWrapper.setMaximumSize(new Dimension(650, Integer.MAX_VALUE));
        return panel;
    }

    // Crea la colección de bloques
    // bloqure de totales
    private JPanel crearBloquesDetalleFactura(Cotizacion c) {
        JPanel cont = new JPanel();
        cont.setBackground(Color.WHITE);
        // Usamos BoxLayout para apilar los bloques
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
        // Aseguramos que el contenedor se alinee a la izquierda
        cont.setAlignmentX(Component.LEFT_ALIGNMENT);

        cont.add(Box.createVerticalStrut(10));

        // --- Tablas de Detalles ---
        List<Object> todosLosDetalles = new ArrayList<>();
        if (c.getVentanaDetalles() != null) {
            todosLosDetalles.addAll(c.getVentanaDetalles());
        }
        if (c.getPuertaAbatibleDetalles() != null) {
            todosLosDetalles.addAll(c.getPuertaAbatibleDetalles());
        }
        if (c.getCanceleriaFijaDetalles() != null) {
            todosLosDetalles.addAll(c.getCanceleriaFijaDetalles());
        }

        // Se mantiene la lógica de iteración
        for (Object detalle : todosLosDetalles) {
            if (detalle instanceof VentanaDetalle v) {
                cont.add(crearBloqueVentana(v));
            } else if (detalle instanceof PuertaAbatibleDetalle p) {
                cont.add(crearBloquePuerta(p));
            } else if (detalle instanceof CanceleriaFijaDetalle cf) {
                cont.add(crearBloqueCanceleria(cf));
            }
            cont.add(thinSeparator());
            cont.add(Box.createVerticalStrut(8));
        }

        if (todosLosDetalles.isEmpty()) {
            cont.add(labelNormal("No hay detalles agregados a esta cotización."));
        }

        return cont;
    }

//bloque ventana
    private JPanel crearBloqueVentana(VentanaDetalle v) {
        JPanel bloque = new JPanel();
        bloque.setLayout(new BoxLayout(bloque, BoxLayout.Y_AXIS));
        bloque.setBackground(Color.WHITE);
        bloque.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        bloque.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Título principal del detalle
        JLabel titulo = new JLabel("VENTANA — (" + safe(v.getTipoVentana() != null ? v.getTipoVentana().getDescripcion() : "N/A") + ")");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        titulo.setForeground(new Color(0, 51, 153));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        bloque.add(titulo);
        bloque.add(Box.createVerticalStrut(4));

        // Contenedor de datos (4 Columnas - Parte superior)
        JPanel datos = new JPanel(new GridLayout(0, 4, 10, 4));
        datos.setBackground(Color.WHITE);
        datos.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Fila 1
        datos.add(labelBold("Descripción:"));
        datos.add(labelNormal(safe(v.getDescripcion())));
        datos.add(labelBold("Cristal:"));
        datos.add(labelNormal(safe(v.getTipoCristal())));

        // Fila 2
        datos.add(labelBold("Medidas (H x V):"));
        datos.add(labelNormal(safe(v.getMedidaHorizontal()) + " x " + safe(v.getMedidaVertical()) + "m"));
        datos.add(labelBold("No. Hojas:"));
        datos.add(labelNormal(String.valueOf(v.getNoHojas())));

        // Fila 3
        datos.add(labelBold("Cantidad:"));
        datos.add(labelNormal(String.valueOf(v.getCantidad()) + " pza(s)"));
        datos.add(labelBold("Mosquitero:"));
        datos.add(labelNormal(v.isMosquitero() ? "Sí" : "No"));

        bloque.add(datos);
        bloque.add(Box.createVerticalStrut(8));

        // TABLA
        bloque.add(titleLeft("ACCESORIOS:"));
        bloque.add(Box.createVerticalStrut(4));

        JPanel matPanel = new JPanel();
        matPanel.setLayout(new GridLayout(0, 3, 10, 2));
        matPanel.setBackground(new Color(230, 230, 230));
        matPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        matPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Encabezados de Materiales (3 Columnas)
        matPanel.add(labelBold("DESCRIPCIÓN"));
        matPanel.add(labelBold("TIPO"));
        matPanel.add(labelBold("TOTAL")); // <-- SOLO TOTAL

        boolean materialesListados = false;
        if (v.getMateriales() != null && !v.getMateriales().isEmpty()) {
            for (MaterialDetalle md : v.getMateriales()) {
                String tipo = safe(md.getMaterial().getTipo());

                // SOLO listar materiales que NO son el vidrio principal
                if (!tipo.equals("VIDRIO")) {
                    matPanel.add(labelNormal("• " + safe(md.getMaterial().getDescripcion())));
                    matPanel.add(labelNormal(tipo));

                    matPanel.add(labelNormal(formatCurrency(md.getPrecioTotal()))); // <-- SOLO PRECIO TOTAL
                    materialesListados = true;
                }
            }
        }

        if (!materialesListados) {
            matPanel.add(labelNormal("No se requieren accesorios o perfiles adicionales."));
            matPanel.add(new JPanel()); // Gap
            matPanel.add(new JPanel()); // Gap
        }

        bloque.add(matPanel);

        bloque.add(Box.createVerticalStrut(8));
        bloque.add(labelBold("Subtotal de la Línea: " + formatCurrency(v.getSubtotalLinea())));
        bloque.add(bigGap());
        bloque.setMaximumSize(new Dimension(650, bloque.getPreferredSize().height));
        return bloque;
    }

// bloque de puerta
    private JPanel crearBloquePuerta(PuertaAbatibleDetalle p) {
        JPanel bloque = new JPanel();
        bloque.setLayout(new BoxLayout(bloque, BoxLayout.Y_AXIS));
        bloque.setBackground(Color.WHITE);
        bloque.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        bloque.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel titulo = new JLabel("PUERTA ABATIBLE — (" + safe(p.getTipoPuerta() != null ? p.getTipoPuerta().getDescripcion() : "N/A") + ")");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        titulo.setForeground(new Color(0, 51, 153));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        bloque.add(titulo);
        bloque.add(Box.createVerticalStrut(4));

        // Contenedor de datos: ALINEACIÓN ESTRICTA (4 Columnas)
        JPanel datos = new JPanel(new GridLayout(0, 4, 10, 4));
        datos.setBackground(Color.WHITE);
        datos.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Fila 1
        datos.add(labelBold("Descripción:"));
        datos.add(labelNormal(safe(p.getDescripcion())));
        datos.add(labelBold("Cristal:"));
        datos.add(labelNormal(safe(p.getTipoCristal())));

        // Fila 2
        datos.add(labelBold("Medidas (H x V):"));
        datos.add(labelNormal(safe(p.getMedidaHorizontal()) + " x " + safe(p.getMedidaVertical()) + "m"));
        datos.add(labelBold("No. Hojas:"));
        datos.add(labelNormal(String.valueOf(p.getNoHojas())));

        // Fila 3
        datos.add(labelBold("Cantidad:"));
        datos.add(labelNormal(String.valueOf(p.getCantidad()) + " pza(s)"));

        bloque.add(datos);
        bloque.add(Box.createVerticalStrut(8));

        // tabla de accesorios
        bloque.add(titleLeft("ACCESORIOS:"));
        bloque.add(Box.createVerticalStrut(4));

        JPanel matPanel = new JPanel();
        matPanel.setLayout(new GridLayout(0, 3, 10, 2));
        matPanel.setBackground(new Color(230, 230, 230));
        matPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        matPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Encabezados
        matPanel.add(labelBold("DESCRIPCIÓN"));
        matPanel.add(labelBold("TIPO"));
        matPanel.add(labelBold("TOTAL")); // <-- SOLO TOTAL

        boolean materialesListados = false;
        if (p.getMateriales() != null && !p.getMateriales().isEmpty()) {
            for (MaterialDetalle md : p.getMateriales()) {
                String tipo = safe(md.getMaterial().getTipo());

                if (!tipo.equals("VIDRIO")) {
                    matPanel.add(labelNormal("• " + safe(md.getMaterial().getDescripcion())));
                    matPanel.add(labelNormal(tipo));
                    matPanel.add(labelNormal(formatCurrency(md.getPrecioTotal()))); // <-- SOLO PRECIO TOTAL
                    materialesListados = true;
                }
            }
        }

        if (!materialesListados) {
            matPanel.add(labelNormal("No se requieren accesorios o perfiles adicionales."));
            matPanel.add(new JPanel());
            matPanel.add(new JPanel());
        }

        bloque.add(matPanel);

        bloque.add(Box.createVerticalStrut(8));
        bloque.add(labelBold("Subtotal de la Línea: " + formatCurrency(p.getSubtotalLinea())));
        bloque.add(bigGap());
        bloque.setMaximumSize(new Dimension(650, bloque.getPreferredSize().height));

        return bloque;
    }

// Bloque de canceleria
    private JPanel crearBloqueCanceleria(CanceleriaFijaDetalle cf) {
        JPanel bloque = new JPanel();
        bloque.setLayout(new BoxLayout(bloque, BoxLayout.Y_AXIS));
        bloque.setBackground(Color.WHITE);
        bloque.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        bloque.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel titulo = new JLabel("CANCELERÍA FIJA (" + safe(cf.getTipoCanceleria() != null ? cf.getTipoCanceleria().getDescripcion() : "N/A") + ")");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        titulo.setForeground(new Color(0, 51, 153));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        bloque.add(titulo);
        bloque.add(Box.createVerticalStrut(4));

        // Contenedor de datos: ALINEACIÓN ESTRICTA (4 Columnas)
        JPanel datos = new JPanel(new GridLayout(0, 4, 10, 4));
        datos.setBackground(Color.WHITE);
        datos.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Fila 1
        datos.add(labelBold("Descripción:"));
        datos.add(labelNormal(safe(cf.getDescripcion())));
        datos.add(labelBold("Cristal:"));
        datos.add(labelNormal(safe(cf.getTipoCristal())));

        // Fila 2
        datos.add(labelBold("Medidas (H x V):"));
        datos.add(labelNormal(safe(cf.getMedidaHorizontal()) + " x " + safe(cf.getMedidaVertical()) + "m"));
        datos.add(labelBold("Cantidad:"));
        datos.add(labelNormal(String.valueOf(cf.getCantidad()) + " pza(s)"));

        // Fila 3
        datos.add(labelBold("Fijos (V x H):"));
        datos.add(labelNormal(cf.getNumFijosVerticales() + " x " + cf.getNumFijosHorizontales()));
        datos.add(labelBold("Tapa:"));
        datos.add(labelNormal(safe(cf.getTipoTapa()) + " x" + cf.getCantidadTapa()));

        bloque.add(datos);
        bloque.add(Box.createVerticalStrut(8));

        // TABLA ACCESORIOS
        bloque.add(titleLeft("ACCESORIOS:"));
        bloque.add(Box.createVerticalStrut(4));

        JPanel matPanel = new JPanel();
        matPanel.setLayout(new GridLayout(0, 3, 10, 2));
        matPanel.setBackground(new Color(230, 230, 230));
        matPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        matPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Encabezados
        matPanel.add(labelBold("DESCRIPCIÓN"));
        matPanel.add(labelBold("TIPO"));
        matPanel.add(labelBold("TOTAL")); // <-- SOLO TOTAL

        boolean materialesListados = false;
        if (cf.getMateriales() != null && !cf.getMateriales().isEmpty()) {
            for (MaterialDetalle md : cf.getMateriales()) {
                String tipo = safe(md.getMaterial().getTipo());

                if (!tipo.equals("VIDRIO")) {
                    matPanel.add(labelNormal("• " + safe(md.getMaterial().getDescripcion())));
                    matPanel.add(labelNormal(tipo));
                    matPanel.add(labelNormal(formatCurrency(md.getPrecioTotal()))); // <-- SOLO PRECIO TOTAL
                    materialesListados = true;
                }
            }
        }

        if (!materialesListados) {
            matPanel.add(labelNormal("No se requieren accesorios o perfiles adicionales."));
            matPanel.add(new JPanel());
            matPanel.add(new JPanel());
        }

        bloque.add(matPanel);

        bloque.add(Box.createVerticalStrut(8));
        bloque.add(labelBold("Subtotal de la Línea: " + formatCurrency(cf.getSubtotalLinea())));
        bloque.add(bigGap());
        bloque.setMaximumSize(new Dimension(650, bloque.getPreferredSize().height));

        return bloque;
    }

    // --------- UI helper components ----------
    // título alineado a la izquierda (no demasiado grande)
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

    // separador grueso (usado entre grandes secciones)
    private JComponent thickSeparator() {
        JSeparator s = new JSeparator(SwingConstants.HORIZONTAL);
        s.setForeground(Color.BLACK);
        s.setBackground(Color.BLACK);
        s.setPreferredSize(new Dimension(1, 6)); // altura gruesa
        s.setMaximumSize(new Dimension(Integer.MAX_VALUE, 6));
        return s;
    }

    // separador fino entre items
    private JComponent thinSeparator() {
        JSeparator s = new JSeparator(SwingConstants.HORIZONTAL);
        s.setForeground(Color.LIGHT_GRAY);
        s.setBackground(Color.LIGHT_GRAY);
        s.setPreferredSize(new Dimension(1, 2));
        s.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        return s;
    }

    // "gap" visual grande (línea negra fina y espacio)
    private JComponent bigGap() {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setLayout(new BorderLayout());
        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setForeground(Color.BLACK);
        sep.setBackground(Color.BLACK);
        sep.setPreferredSize(new Dimension(1, 4));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 4));
        p.add(sep, BorderLayout.CENTER);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 8));
        return p;
    }

    // ------------ utilitarios -------------
    private String formatCurrency(BigDecimal amount) {
        if (amount == null) {
            amount = BigDecimal.ZERO;
        }
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
        return nf.format(amount);
    }

    // overload para doubles si en otro lado se usa
    private String formatCurrency(double amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
        return nf.format(amount);
    }

    private String materialesResumen(List<MaterialDetalle> lista) {
        if (lista == null || lista.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int maxItems = 3;
        int count = 0;
        for (MaterialDetalle md : lista) {
            if (count > 0) {
                sb.append(", ");
            }
            sb.append(md.getMaterial().getDescripcion()).append(" x").append(md.getCantidad());
            count++;
            if (count >= maxItems) {
                break;
            }
        }
        if (lista.size() > maxItems) {
            sb.append(" ...");
        }
        return sb.toString();
    }

    private String safe(Object o) {
        return o != null ? o.toString() : "";
    }

    // Método estático simple para abrir:
    public static void mostrarPrevia(Frame parent, Cotizacion cotizacion) {
        VistaPreviaCotizacionDialog dialog = new VistaPreviaCotizacionDialog(parent, cotizacion);
        dialog.setVisible(true);
    }
}
