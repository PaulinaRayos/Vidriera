package presentacion;

import dao.CatalogoTrabajoDAO;
import dao.ClienteDAO;
import dao.MaterialDAO;
import dao.VendedorDAO;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelo.CanceleriaFijaDetalle;
import modelo.CatalogoTrabajo;
import modelo.Cliente;
import modelo.Cotizacion;
import modelo.Material;
import modelo.PuertaAbatibleDetalle;
import modelo.Vendedor;
import modelo.VentanaDetalle;
import negocio.CotizacionBO;
import utils.Conexion;
import org.apache.pdfbox.pdmodel.*;

/**
 *
 * @author User
 */
public class frmCrearCotizacion extends javax.swing.JFrame {

    private List<CatalogoTrabajo> catalogoTrabajos;
    private List<Cliente> clientes;
    private Vendedor vendedorPorDefecto;
    private JPanel panelDetallesDinamicos;
    private List<VentanaDetalle> detallesVentana;
    private List<CanceleriaFijaDetalle> detallesCancelaria;
    private List<PuertaAbatibleDetalle> detallesPuerta;
    private List<Material> materialesDisponibles;
    private Cotizacion cotizacionSeleccionada;
    private DefaultTableModel modeloTablaDetalles;
    private List<Object> detallesEnMemoria = new ArrayList<>();

    public frmCrearCotizacion() {
        initComponents();
        txtDescuento.setTransferHandler(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cargarVendedorPorDefecto();
        panelDetallesDinamicos = new JPanel();
        panelDetallesDinamicos.setLayout(new BoxLayout(panelDetallesDinamicos, BoxLayout.Y_AXIS));
        cargarClientes();
        cargarCatalogo();
        cargarMaterialesDisponibles();
        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarClientes(txtBuscarCliente.getText());
            }
        });

        inicializarLogica();

        ButtonGroup grupoDescuento = new ButtonGroup();
        grupoDescuento.add(ckbxDescuentoSi);
        grupoDescuento.add(ckbxDescuentoNo);

        //poner "No" seleccionado por defecto
        ckbxDescuentoNo.setSelected(true);
        // Bloquear el campo de texto
        txtDescuento.setText("0");
        txtDescuento.setEnabled(false);

        ckbxDescuentoSi.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ckbxDescuentoNo.setSelected(false);
                txtDescuento.setEnabled(true);
                txtDescuento.setText(""); // limpiar
                recalcularTotales();
            }
        });

        ckbxDescuentoNo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ckbxDescuentoSi.setSelected(false);
                txtDescuento.setText("0");
                txtDescuento.setEnabled(false);
                recalcularTotales();
            }
        });

        txtDescuento.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                recalcularTotales();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                recalcularTotales();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

    }

    private void cargarCatalogo() {
        this.catalogoTrabajos = new ArrayList<>();
        try (Connection con = Conexion.getConnection()) {
            CatalogoTrabajoDAO dao = new CatalogoTrabajoDAO(con);
            this.catalogoTrabajos = dao.obtenerTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el catálogo de trabajos.");
        }
    }

    private void cargarVendedorPorDefecto() {
        try (Connection conexion = Conexion.getConnection()) {
            VendedorDAO vendedorDAO = new VendedorDAO(conexion);
            List<Vendedor> todos = vendedorDAO.obtenerTodos();
            if (todos != null && !todos.isEmpty()) {
                vendedorPorDefecto = todos.get(0);
            } else {
                JOptionPane.showMessageDialog(this, "Error crítico: No hay vendedores registrados en la base de datos.", "Error de Configuración", JOptionPane.ERROR_MESSAGE);
                btnGuardar.setEnabled(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el vendedor por defecto: " + ex.getMessage());
            btnGuardar.setEnabled(false);
        }
    }

    private BufferedImage panelToImage(JPanel panel) {
        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        panel.paint(g2);
        g2.dispose();
        return image;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        iconoTitulo = new javax.swing.JLabel();
        panelSubtitulo = new javax.swing.JPanel();
        ConsultarCotizacion = new javax.swing.JLabel();
        iconoCrear = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        panelBuscarCliente = new javax.swing.JPanel();
        Buscar = new javax.swing.JLabel();
        txtBuscarCliente = new javax.swing.JTextField();
        btnCrearCliente = new javax.swing.JButton();
        cbxSeleccionarCliente = new javax.swing.JComboBox<>();
        btnNuevaCotizacion = new javax.swing.JButton();
        btnEditarCotizacion = new javax.swing.JButton();
        btnEliminarCotizacion = new javax.swing.JButton();
        tituloNuevaCotizacion = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetallesAgregados = new javax.swing.JTable();
        pnlTotales = new javax.swing.JPanel();
        ckbxDescuentoSi = new javax.swing.JCheckBox();
        Descuento = new javax.swing.JLabel();
        ckbxDescuentoNo = new javax.swing.JCheckBox();
        txtDescuento = new javax.swing.JTextField();
        labelPorsentaje = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblManoObra = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblIVA = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMaterialUtilizar = new javax.swing.JTextArea();
        Descuento1 = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        pnlNuevaCotizacion = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crear Cotización");

        PanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        panelTitulo.setBackground(new java.awt.Color(0, 19, 90));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Administrar cotizaciones");

        iconoTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clipboard-48.png"))); // NOI18N
        iconoTitulo.setText("jLabel2");

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iconoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1112, Short.MAX_VALUE))
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addGroup(panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTituloLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                    .addGroup(panelTituloLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(iconoTitulo)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addGap(9, 9, 9))
        );

        panelSubtitulo.setBackground(new java.awt.Color(0, 81, 168));

        ConsultarCotizacion.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        ConsultarCotizacion.setForeground(new java.awt.Color(255, 255, 255));
        ConsultarCotizacion.setText("Crear cotización");

        iconoCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-24.png"))); // NOI18N

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSubtituloLayout = new javax.swing.GroupLayout(panelSubtitulo);
        panelSubtitulo.setLayout(panelSubtituloLayout);
        panelSubtituloLayout.setHorizontalGroup(
            panelSubtituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSubtituloLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(iconoCrear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConsultarCotizacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVolver)
                .addGap(17, 17, 17))
        );
        panelSubtituloLayout.setVerticalGroup(
            panelSubtituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSubtituloLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelSubtituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconoCrear)
                    .addGroup(panelSubtituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVolver)
                        .addComponent(ConsultarCotizacion)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        panelBuscarCliente.setBackground(new java.awt.Color(255, 255, 255));

        Buscar.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        Buscar.setForeground(new java.awt.Color(15, 105, 196));
        Buscar.setText("Cliente");

        txtBuscarCliente.setToolTipText("");

        btnCrearCliente.setBackground(new java.awt.Color(0, 81, 168));
        btnCrearCliente.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCrearCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-20.png"))); // NOI18N
        btnCrearCliente.setText("Cliente nuevo");
        btnCrearCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCrearCliente.setBorderPainted(false);
        btnCrearCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCrearCliente.setDefaultCapable(false);
        btnCrearCliente.setFocusPainted(false);
        btnCrearCliente.setRequestFocusEnabled(false);
        btnCrearCliente.setRolloverEnabled(false);

        cbxSeleccionarCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxSeleccionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSeleccionarClienteActionPerformed(evt);
            }
        });

        btnNuevaCotizacion.setText("Agregar");
        btnNuevaCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaCotizacionActionPerformed(evt);
            }
        });

        btnEditarCotizacion.setText("Editar");
        btnEditarCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCotizacionActionPerformed(evt);
            }
        });

        btnEliminarCotizacion.setText("Eliminar");
        btnEliminarCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCotizacionActionPerformed(evt);
            }
        });

        tituloNuevaCotizacion.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        tituloNuevaCotizacion.setForeground(new java.awt.Color(15, 105, 196));
        tituloNuevaCotizacion.setText("Nueva Cotización");

        tblDetallesAgregados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tipo de trabajo", "Cantidad", "Descripción", "Medidas", "Subtotal"
            }
        ));
        jScrollPane3.setViewportView(tblDetallesAgregados);

        pnlTotales.setBackground(new java.awt.Color(255, 255, 255));

        ckbxDescuentoSi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ckbxDescuentoSi.setSelected(true);
        ckbxDescuentoSi.setText("Si");

        Descuento.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        Descuento.setForeground(new java.awt.Color(15, 105, 196));
        Descuento.setText("Descuento");

        ckbxDescuentoNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ckbxDescuentoNo.setText("No");
        ckbxDescuentoNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbxDescuentoNoActionPerformed(evt);
            }
        });

        txtDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoActionPerformed(evt);
            }
        });
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyTyped(evt);
            }
        });

        labelPorsentaje.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        labelPorsentaje.setForeground(new java.awt.Color(0, 38, 115));
        labelPorsentaje.setText("%");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255)));
        jPanel2.setForeground(new java.awt.Color(153, 204, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Subtotal");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mano de Obra");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("TOTAL");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("IVA");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Descuento");

        lblManoObra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblManoObra.setText("$0.00");

        lblSubtotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSubtotal.setText("$0.00");

        lblDescuento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescuento.setText("-$0.00");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotal.setText("$0.00");

        lblIVA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblIVA.setText("$0.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblManoObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblIVA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblSubtotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblManoObra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblIVA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblDescuento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblTotal)))
        );

        txtMaterialUtilizar.setEditable(false);
        txtMaterialUtilizar.setColumns(20);
        txtMaterialUtilizar.setRows(5);
        jScrollPane2.setViewportView(txtMaterialUtilizar);

        Descuento1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        Descuento1.setForeground(new java.awt.Color(15, 105, 196));
        Descuento1.setText("Resumen Cotización");

        javax.swing.GroupLayout pnlTotalesLayout = new javax.swing.GroupLayout(pnlTotales);
        pnlTotales.setLayout(pnlTotalesLayout);
        pnlTotalesLayout.setHorizontalGroup(
            pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalesLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTotalesLayout.createSequentialGroup()
                        .addComponent(ckbxDescuentoSi)
                        .addGap(18, 18, 18)
                        .addComponent(ckbxDescuentoNo)
                        .addGap(18, 18, 18)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelPorsentaje))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Descuento))
                .addGap(46, 46, 46)
                .addGroup(pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Descuento1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(824, Short.MAX_VALUE))
        );
        pnlTotalesLayout.setVerticalGroup(
            pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTotalesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Descuento)
                    .addComponent(Descuento1))
                .addGap(18, 18, 18)
                .addGroup(pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlTotalesLayout.createSequentialGroup()
                        .addGroup(pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckbxDescuentoSi)
                            .addComponent(ckbxDescuentoNo)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPorsentaje))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout panelBuscarClienteLayout = new javax.swing.GroupLayout(panelBuscarCliente);
        panelBuscarCliente.setLayout(panelBuscarClienteLayout);
        panelBuscarClienteLayout.setHorizontalGroup(
            panelBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarClienteLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTotales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBuscarClienteLayout.createSequentialGroup()
                        .addGroup(panelBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBuscarClienteLayout.createSequentialGroup()
                                .addComponent(cbxSeleccionarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCrearCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Buscar)
                            .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(713, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarClienteLayout.createSequentialGroup()
                        .addGroup(panelBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1585, Short.MAX_VALUE)
                            .addGroup(panelBuscarClienteLayout.createSequentialGroup()
                                .addComponent(tituloNuevaCotizacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNuevaCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))))
        );
        panelBuscarClienteLayout.setVerticalGroup(
            panelBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxSeleccionarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(btnCrearCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(panelBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEliminarCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNuevaCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditarCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tituloNuevaCotizacion, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelBotones.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1467, Short.MAX_VALUE)
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        pnlNuevaCotizacion.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setBackground(new java.awt.Color(4, 210, 65));
        btnGuardar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save-20.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar.setDefaultCapable(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setRequestFocusEnabled(false);
        btnGuardar.setRolloverEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnDescartar.setBackground(new java.awt.Color(255, 0, 51));
        btnDescartar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnDescartar.setForeground(new java.awt.Color(255, 255, 255));
        btnDescartar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancelar-20.png"))); // NOI18N
        btnDescartar.setText("Descartar");
        btnDescartar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDescartar.setBorderPainted(false);
        btnDescartar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDescartar.setDefaultCapable(false);
        btnDescartar.setFocusPainted(false);
        btnDescartar.setRequestFocusEnabled(false);
        btnDescartar.setRolloverEnabled(false);
        btnDescartar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescartarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNuevaCotizacionLayout = new javax.swing.GroupLayout(pnlNuevaCotizacion);
        pnlNuevaCotizacion.setLayout(pnlNuevaCotizacionLayout);
        pnlNuevaCotizacionLayout.setHorizontalGroup(
            pnlNuevaCotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevaCotizacionLayout.createSequentialGroup()
                .addGap(883, 883, 883)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnDescartar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(464, Short.MAX_VALUE))
        );
        pnlNuevaCotizacionLayout.setVerticalGroup(
            pnlNuevaCotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNuevaCotizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNuevaCotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlNuevaCotizacionLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDescartar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNuevaCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBuscarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelSubtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(panelSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlNuevaCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(194, 194, 194)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelPrincipalLayout.createSequentialGroup()
                    .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 968, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(PanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCotizacionActionPerformed
        int filaSeleccionada = tblDetallesAgregados.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cotización de la tabla para editar.");
            return;
        }

        Object detalleAEditar = detallesEnMemoria.get(filaSeleccionada);
        List<Material> materiales = materialesDisponibles;

        DetalleEditorDialog dialog = null;

        if (detalleAEditar instanceof VentanaDetalle vd) {
            dialog = new DetalleEditorDialog(this, true, materiales, vd);

        } else if (detalleAEditar instanceof PuertaAbatibleDetalle pd) {
            dialog = new DetalleEditorDialog(this, true, materiales, pd);

        } else if (detalleAEditar instanceof CanceleriaFijaDetalle cd) {
            dialog = new DetalleEditorDialog(this, true, materiales, cd);

        } else {
            JOptionPane.showMessageDialog(this, "Error desconocido en la corización.");
            return;
        }

        dialog.setVisible(true);
        Object detalleResultado = dialog.getDetalleResultado();

        if (detalleResultado != null) {
            detallesEnMemoria.set(filaSeleccionada, detalleResultado);
            actualizarTablaDetalles();
            recalcularTotales();
            actualizarTextAreaMateriales();
        }
    }//GEN-LAST:event_btnEditarCotizacionActionPerformed

    private void btnNuevaCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaCotizacionActionPerformed
        // VALIDACIÓN: Debe seleccionar un cliente antes de continuar
        if (cbxSeleccionarCliente.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debes seleccionar un cliente antes de agregar una cotización.",
                    "Cliente requerido",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        List<Material> materiales = this.materialesDisponibles;

        if (materiales == null || materiales.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error: No se pudieron cargar los materiales. Revisa la base de datos."
            );
            return;
        }

        DetalleEditorDialog dialog = new DetalleEditorDialog(this, true, materiales);
        dialog.setVisible(true);

        Object nuevoDetalle = dialog.getDetalleResultado();

        if (nuevoDetalle != null) {
            detallesEnMemoria.add(nuevoDetalle);
            actualizarTablaDetalles();
            actualizarTextAreaMateriales();
            recalcularTotales();
        }
    }//GEN-LAST:event_btnNuevaCotizacionActionPerformed

    private void btnEliminarCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCotizacionActionPerformed
        int filaSeleccionada = tblDetallesAgregados.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una corización para eliminarla.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas eliminar esta cotización de la lista?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            detallesEnMemoria.remove(filaSeleccionada);

            actualizarTablaDetalles();
            actualizarTextAreaMateriales();
            recalcularTotales();
        }
    }//GEN-LAST:event_btnEliminarCotizacionActionPerformed

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de descartar la cotización?",
                "Confirmar Cancelación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            InicioAdministrarCotizaciones inicio = new InicioAdministrarCotizaciones();
            inicio.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        // ===============================
        // VALIDACIONES INICIALES
        // ===============================
        // Validar cliente seleccionado
        if (cbxSeleccionarCliente.getSelectedIndex() <= 0
                || cbxSeleccionarCliente.getSelectedItem().toString().equals("Seleccione un cliente...")
                || cbxSeleccionarCliente.getSelectedItem().toString().equals("No hay clientes")) {

            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un cliente.",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar vendedor por defecto
        if (vendedorPorDefecto == null) {
            JOptionPane.showMessageDialog(this,
                    "No se puede guardar porque no se cargó un vendedor por defecto.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que existan detalles
        if (detallesEnMemoria.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe agregar al menos un detalle a la cotización.",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int indexCliente = cbxSeleccionarCliente.getSelectedIndex() - 1;

        if (indexCliente < 0 || indexCliente >= clientes.size()) {
            JOptionPane.showMessageDialog(this,
                    "Error interno: índice de cliente inválido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente clienteSeleccionado = clientes.get(indexCliente);

        try {
            ArrayList<VentanaDetalle> detallesVentana = new ArrayList<>();
            ArrayList<PuertaAbatibleDetalle> detallesPuerta = new ArrayList<>();
            ArrayList<CanceleriaFijaDetalle> detallesCanceleria = new ArrayList<>();

            BigDecimal subtotal = BigDecimal.ZERO;

            // ===============================
            // VALIDACIONES DE CADA DETALLE (CORRECTAS)
            // ===============================
            for (Object detalleObj : detallesEnMemoria) {

                // ----------- VALIDAR VENTANA -----------
                if (detalleObj instanceof VentanaDetalle d) {

                    if (d.getMedidaVertical() == null
                            || d.getMedidaVertical().compareTo(BigDecimal.ZERO) <= 0
                            || d.getMedidaVertical().compareTo(new BigDecimal("10")) > 0) {

                        JOptionPane.showMessageDialog(this,
                                "Error en ventana: El alto debe ser entre 0 y 10 metros.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (d.getMedidaHorizontal() == null
                            || d.getMedidaHorizontal().compareTo(BigDecimal.ZERO) <= 0
                            || d.getMedidaHorizontal().compareTo(new BigDecimal("15")) > 0) {

                        JOptionPane.showMessageDialog(this,
                                "Error en ventana: El ancho debe ser entre 0 y 15 metros.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (d.getCantidad() <= 0) {
                        JOptionPane.showMessageDialog(this,
                                "Error en ventana: La cantidad debe ser mayor a 0.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } // ----------- VALIDAR PUERTA -----------
                else if (detalleObj instanceof PuertaAbatibleDetalle d) {

                    if (d.getMedidaVertical() == null
                            || d.getMedidaVertical().compareTo(BigDecimal.ZERO) <= 0
                            || d.getMedidaVertical().compareTo(new BigDecimal("10")) > 0) {

                        JOptionPane.showMessageDialog(this,
                                "Error en puerta: El alto debe ser entre 0 y 10 metros.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (d.getMedidaHorizontal() == null
                            || d.getMedidaHorizontal().compareTo(BigDecimal.ZERO) <= 0
                            || d.getMedidaHorizontal().compareTo(new BigDecimal("15")) > 0) {

                        JOptionPane.showMessageDialog(this,
                                "Error en puerta: El ancho debe ser entre 0 y 15 metros.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (d.getCantidad() <= 0) {
                        JOptionPane.showMessageDialog(this,
                                "Error en puerta: La cantidad debe ser mayor a 0.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } // ----------- VALIDAR CANCELERÍA -----------
                else if (detalleObj instanceof CanceleriaFijaDetalle d) {

                    if (d.getMedidaVertical() == null
                            || d.getMedidaVertical().compareTo(BigDecimal.ZERO) <= 0
                            || d.getMedidaVertical().compareTo(new BigDecimal("10")) > 0) {

                        JOptionPane.showMessageDialog(this,
                                "Error en cancelería: El alto debe ser entre 0 y 10 metros.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (d.getMedidaHorizontal() == null
                            || d.getMedidaHorizontal().compareTo(BigDecimal.ZERO) <= 0
                            || d.getMedidaHorizontal().compareTo(new BigDecimal("15")) > 0) {

                        JOptionPane.showMessageDialog(this,
                                "Error en cancelería: El ancho debe ser entre 0 y 15 metros.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (d.getCantidad() <= 0) {
                        JOptionPane.showMessageDialog(this,
                                "Error en cancelería: La cantidad debe ser mayor a 0.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            // ===============================
            // CLASIFICAR DETALLES Y CALCULAR SUBTOTAL
            // ===============================
            for (Object detalleObj : detallesEnMemoria) {
                if (detalleObj instanceof VentanaDetalle vd) {
                    detallesVentana.add(vd);
                    subtotal = subtotal.add(vd.getSubtotalLinea());
                } else if (detalleObj instanceof PuertaAbatibleDetalle pd) {
                    detallesPuerta.add(pd);
                    subtotal = subtotal.add(pd.getSubtotalLinea());
                } else if (detalleObj instanceof CanceleriaFijaDetalle cd) {
                    detallesCanceleria.add(cd);
                    subtotal = subtotal.add(cd.getSubtotalLinea());
                }
            }

            // ===============================
            // CALCULAR DESCUENTO
            // ===============================
            BigDecimal descuentoMonto = BigDecimal.ZERO;

            if (ckbxDescuentoSi.isSelected()) {
                String textoDescuento = txtDescuento.getText().trim();

                if (!textoDescuento.isEmpty()) {
                    try {
                        BigDecimal porcentaje = new BigDecimal(textoDescuento);

                        if (porcentaje.compareTo(BigDecimal.ZERO) < 0
                                || porcentaje.compareTo(new BigDecimal("100")) > 0) {

                            JOptionPane.showMessageDialog(this,
                                    "El descuento debe estar entre 0% y 100%.",
                                    "Descuento inválido",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        descuentoMonto = subtotal.multiply(porcentaje.divide(new BigDecimal("100")));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this,
                                "El descuento debe ser numérico.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            // ===============================
            // CONFIRMAR GUARDADO
            // ===============================
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea guardar la cotización para el cliente:\n"
                    + clienteSeleccionado.getNombre() + "?",
                    "Confirmar guardado",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // ===============================
            // GUARDAR COTIZACIÓN
            // ===============================
            Cotizacion cotizacion = new Cotizacion();
            cotizacion.setCliente(clienteSeleccionado);
            cotizacion.setVendedor(vendedorPorDefecto);
            cotizacion.setProyecto(null);
            cotizacion.setFecha(new Date());
            cotizacion.setEstado("Pendiente");
            cotizacion.setSubtotal(subtotal);
            cotizacion.setDescuentoMonto(descuentoMonto);

            CotizacionBO bo = new CotizacionBO();
            boolean exito = bo.crearCotizacionCompleta(
                    cotizacion,
                    detallesVentana,
                    detallesCanceleria,
                    detallesPuerta
            );

            if (exito) {
                JOptionPane.showMessageDialog(this,
                        "Cotización guardada con éxito.\nNúmero: "
                        + cotizacion.getIdCotizacion(),
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

                this.dispose();
                new InicioAdministrarCotizaciones().setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this,
                        "Ocurrió un error al guardar.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error inesperado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.dispose();
        InicioAdministrarCotizaciones ini = new InicioAdministrarCotizaciones();

        ini.setVisible(true);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void txtDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescuentoActionPerformed
        if (ckbxDescuentoSi.isSelected()) {
            ckbxDescuentoNo.setSelected(false); // deseleccionar "No"
            txtDescuento.setEnabled(true);      // habilitar para escribir
            recalcularTotales();                // recalcular totales
        }
    }//GEN-LAST:event_txtDescuentoActionPerformed

    private void ckbxDescuentoNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbxDescuentoNoActionPerformed
        if (ckbxDescuentoNo.isSelected()) {
            ckbxDescuentoSi.setSelected(false);
            txtDescuento.setText("0");
            txtDescuento.setEnabled(false);
            recalcularTotales();
        }

    }//GEN-LAST:event_ckbxDescuentoNoActionPerformed

    private void cbxSeleccionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSeleccionarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSeleccionarClienteActionPerformed

    private void txtDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyReleased
        if (ckbxDescuentoSi.isSelected()) {
            recalcularTotales();
        }

    }//GEN-LAST:event_txtDescuentoKeyReleased

    private void txtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) && c != '.') {
            evt.consume(); // Bloquea letras y símbolos
        }

        if (c == '.' && txtDescuento.getText().contains(".")) {
            evt.consume();
        }    }//GEN-LAST:event_txtDescuentoKeyTyped

    /**
     * Configura la tabla, los listeners y el estado inicial de los botones.
     */
    private void inicializarLogica() {
        this.modeloTablaDetalles = (DefaultTableModel) tblDetallesAgregados.getModel();

        modeloTablaDetalles.setRowCount(0);

        tblDetallesAgregados.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    actualizarEstadoBotones();
                }
            }
        });
        actualizarEstadoBotones();
    }

    /**
     * Genera un resumen de texto con los detalles específicos (accesorios,
     * hojas, etc.) de los elementos en la cotización actual.
     */
    private void actualizarTextAreaMateriales() {
        StringBuilder sb = new StringBuilder();

        if (detallesEnMemoria.isEmpty()) {
            txtMaterialUtilizar.setText("");
            return;
        }

        sb.append("=== RESUMEN DE COTIZACIÓN ===\n\n");

        for (Object obj : detallesEnMemoria) {

            // ------------------------------
            // VENTANA
            // ------------------------------
            if (obj instanceof VentanaDetalle v) {
                sb.append(v.getDescripcion()).append("\n");
            } // ------------------------------
            // PUERTA
            // ------------------------------
            else if (obj instanceof PuertaAbatibleDetalle p) {
                sb.append(p.getDescripcion()).append("\n");
            } // ------------------------------
            // CANCELERÍA
            // ------------------------------
            else if (obj instanceof CanceleriaFijaDetalle c) {
                sb.append(c.getDescripcion()).append("\n");
            }

            sb.append("\n"); // salto entre items
        }

        txtMaterialUtilizar.setText(sb.toString());
        txtMaterialUtilizar.setCaretPosition(0);
    }

    /**
     * Habilita o deshabilita los botones editar y eliminar basado en si la
     * tabla tiene filas.
     */
    private void actualizarEstadoBotones() {
        boolean filaSeleccionada = tblDetallesAgregados.getSelectedRow() != -1;

        btnEditarCotizacion.setEnabled(filaSeleccionada);
        btnEliminarCotizacion.setEnabled(filaSeleccionada);
    }

    private void actualizarTablaDetalles() {
        modeloTablaDetalles.setRowCount(0);

        for (Object detalle : detallesEnMemoria) {

            if (detalle instanceof VentanaDetalle vd) {
                modeloTablaDetalles.addRow(new Object[]{
                    "Ventana",
                    vd.getCantidad(),
                    vd.getDescripcion(),
                    vd.getMedidaHorizontal() + " x " + vd.getMedidaVertical(),
                    vd.getSubtotalLinea()
                });
            } else if (detalle instanceof PuertaAbatibleDetalle pd) {
                modeloTablaDetalles.addRow(new Object[]{
                    "Puerta",
                    pd.getCantidad(),
                    pd.getDescripcion(),
                    pd.getMedidaHorizontal() + " x " + pd.getMedidaVertical(),
                    pd.getSubtotalLinea()
                });
            } else if (detalle instanceof CanceleriaFijaDetalle cd) {
                modeloTablaDetalles.addRow(new Object[]{
                    "Cancelería",
                    cd.getCantidad(),
                    cd.getDescripcion(),
                    cd.getMedidaHorizontal() + " x " + cd.getMedidaVertical(),
                    cd.getSubtotalLinea()
                });
            }

        }

        actualizarEstadoBotones();
    }

    /**
     * (PENDIENTE) Recorre la lista 'detallesEnMemoria' y actualiza los JLabels
     * de totales.
     */
    private void recalcularTotales() {
        BigDecimal subtotal = BigDecimal.ZERO;

        // 1. Sumar los subtotales de todos los detalles (null-safe)
        for (Object detalle : detallesEnMemoria) {
            if (detalle instanceof VentanaDetalle vd) {
                subtotal = subtotal.add(nullSafe(vd.getSubtotalLinea()));
            } else if (detalle instanceof PuertaAbatibleDetalle pd) {
                subtotal = subtotal.add(nullSafe(pd.getSubtotalLinea()));
            } else if (detalle instanceof CanceleriaFijaDetalle cd) {
                subtotal = subtotal.add(nullSafe(cd.getSubtotalLinea()));
            }
        }

        // 2. Calcular mano de obra: 45% del subtotal
        BigDecimal porcentajeManoObra = new BigDecimal("0.45");
        BigDecimal manoObra = subtotal.multiply(porcentajeManoObra);

        // 3. Calcular descuento (si aplica)
        BigDecimal descuento = BigDecimal.ZERO;
        if (ckbxDescuentoSi.isSelected() && txtDescuento.getText() != null && !txtDescuento.getText().trim().isEmpty()) {
            try {
                BigDecimal porcentaje = new BigDecimal(txtDescuento.getText().trim());
                if (porcentaje.compareTo(BigDecimal.ZERO) < 0) {
                    porcentaje = BigDecimal.ZERO;
                }
                descuento = subtotal.multiply(porcentaje.divide(new BigDecimal("100")));
            } catch (NumberFormatException e) {
                descuento = BigDecimal.ZERO; // Si hay error en el número, no aplicar descuento
            }
        } else {
            descuento = BigDecimal.ZERO;
        }

        // 4. Calcular IVA: 16% sobre (subtotal + manoObra - descuento)
        BigDecimal baseImponible = subtotal.add(manoObra).subtract(descuento);
        if (baseImponible.compareTo(BigDecimal.ZERO) < 0) {
            baseImponible = BigDecimal.ZERO;
        }
        BigDecimal iva = baseImponible.multiply(new BigDecimal("0.16"));

        // 5. Calcular total
        BigDecimal total = baseImponible.add(iva);

        // 6. Actualizar los JLabels (formateo simple)
        lblSubtotal.setText("$ " + subtotal.setScale(2, BigDecimal.ROUND_HALF_UP));
        lblManoObra.setText("$ " + manoObra.setScale(2, BigDecimal.ROUND_HALF_UP));
        lblDescuento.setText("$ " + descuento.setScale(2, BigDecimal.ROUND_HALF_UP));
        lblIVA.setText("$ " + iva.setScale(2, BigDecimal.ROUND_HALF_UP));
        lblTotal.setText("$ " + total.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private void cargarMaterialesDisponibles() {
        try {
            MaterialDAO materialDAO = new MaterialDAO(utils.Conexion.getConnection());
            materialesDisponibles = materialDAO.obtenerTodos(); // desde BD

            if (materialesDisponibles == null || materialesDisponibles.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No se encontraron materiales en la base de datos.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al conectar con la base de datos: " + e.getMessage(),
                    "Error de conexion",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmCrearCotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCrearCotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCrearCotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCrearCotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCrearCotizacion().setVisible(true);
            }
        });
    }

    private void cargarClientes() {
        try (Connection conexion = Conexion.getConnection()) {
            ClienteDAO clienteDAO = new ClienteDAO(conexion);
            clientes = clienteDAO.obtenerTodos();

            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

            // 🔹 Opción vacía por defecto (evita cliente precargado)
            modelo.addElement("Seleccione un cliente...");

            if (clientes != null && !clientes.isEmpty()) {
                for (Cliente c : clientes) {
                    modelo.addElement(c.getNombre());
                }
            } else {
                modelo.addElement("No hay clientes");
            }

            cbxSeleccionarCliente.setModel(modelo);
            cbxSeleccionarCliente.setSelectedIndex(0); // Asegura que inicie vacío

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes: " + ex.getMessage());
        }
    }

    private void filtrarClientes(String texto) {
        cbxSeleccionarCliente.removeAllItems();

        // 🔹 Siempre mantener la primera opción
        //cbxSeleccionarCliente.addItem("Seleccione un cliente...");
        String textoNormalizado = normalizarTexto(texto);

        for (Cliente c : clientes) {
            String nombreNormalizado = normalizarTexto(c.getNombre());
            if (nombreNormalizado.contains(textoNormalizado)) {
                cbxSeleccionarCliente.addItem(c.getNombre());
            }
        }

        // Si no hay coincidencias, mostrar mensaje
        if (cbxSeleccionarCliente.getItemCount() == 1) {
            cbxSeleccionarCliente.addItem("No hay coincidencias");
        }

        cbxSeleccionarCliente.setSelectedIndex(0);
    }

    /**
     * Quita acentos y pasa a minúsculas.
     */
    private String normalizarTexto(String texto) {
        if (texto == null) {
            return "";
        }
        String sinAcentos = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return sinAcentos.toLowerCase();
    }

    private BigDecimal nullSafe(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Buscar;
    private javax.swing.JLabel ConsultarCotizacion;
    private javax.swing.JLabel Descuento;
    private javax.swing.JLabel Descuento1;
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JButton btnCrearCliente;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnEditarCotizacion;
    private javax.swing.JButton btnEliminarCotizacion;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevaCotizacion;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cbxSeleccionarCliente;
    private javax.swing.JCheckBox ckbxDescuentoNo;
    private javax.swing.JCheckBox ckbxDescuentoSi;
    private javax.swing.JLabel iconoCrear;
    private javax.swing.JLabel iconoTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelPorsentaje;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblManoObra;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelBuscarCliente;
    private javax.swing.JPanel panelSubtitulo;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JPanel pnlNuevaCotizacion;
    private javax.swing.JPanel pnlTotales;
    private javax.swing.JTable tblDetallesAgregados;
    private javax.swing.JLabel tituloNuevaCotizacion;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextArea txtMaterialUtilizar;
    // End of variables declaration//GEN-END:variables
}
