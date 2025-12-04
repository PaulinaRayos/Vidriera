/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package presentacion.Cotizacion;

import presentacion.Ventana.PanelDetalleVentana;
import presentacion.PuertaAbatible.PanelDetallePuertaAbatible;
import presentacion.Canceleria.PanelDetalleCanceleria;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Frame;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.CanceleriaFijaDetalle;
import modelo.Material;
import modelo.PuertaAbatibleDetalle;
import modelo.VentanaDetalle;

/**
 *
 * @author User
 */
public class DetalleEditorDialog extends javax.swing.JDialog {

    private PanelDetalleVentana panelVentana;
    private PanelDetallePuertaAbatible panelPuerta;
    private PanelDetalleCanceleria panelCanceleria;
    private CardLayout cardLayout;

    private Object detalleResultado = null;
    private boolean esModoEdicion = false;
    private List<Material> materialesDisponibles;

    /**
     * constructor para hacer una nueva cotizacion
     */
    public DetalleEditorDialog(java.awt.Frame parent, boolean modal, List<Material> materiales) {
        super(parent, modal);
        this.materialesDisponibles = materiales;

        initComponents();

        configurarCardLayout();
        configurarRadioBotones();

        this.esModoEdicion = false;
        
         // Selecciona Ventana por defecto
        radVentana.setSelected(true);
        mostrarPanelSeleccionado();
    
        this.setTitle("Añadir Detalle");
        this.pack();
        this.setLocationRelativeTo(parent);
    }

    /**
     * construcor para editar una cotizacion.
     */
    public DetalleEditorDialog(java.awt.Frame parent, boolean modal, List<Material> materiales, Object detalleAEditar) {
        super(parent, modal);
        this.materialesDisponibles = materiales;

        initComponents();
        configurarCardLayout();
        configurarRadioBotones();

        this.detalleResultado = detalleAEditar;
        this.esModoEdicion = true;

        radVentana.setEnabled(true);
        radPuerta.setEnabled(false);
        radCanceleria.setEnabled(false);
        if (detalleAEditar instanceof VentanaDetalle vd) {
            radVentana.setSelected(true);
            cardLayout.show(panelContenedor, "VENTANA");
            panelVentana.setDetalle(vd);
            this.setTitle("Editar Detalle - Ventana");

        } else if (detalleAEditar instanceof PuertaAbatibleDetalle pd) {
            radPuerta.setSelected(true);
            cardLayout.show(panelContenedor, "PUERTA");
            panelPuerta.setDetalle(pd);
            this.setTitle("Editar Detalle - Puerta");

        } else if (detalleAEditar instanceof CanceleriaFijaDetalle cd) {
            radCanceleria.setSelected(true);
            cardLayout.show(panelContenedor, "CANCELERIA");
            panelCanceleria.setDetalle(cd);
            this.setTitle("Editar Detalle - Cancelería");
        }

        this.pack();
        this.setLocationRelativeTo(parent);
    }

    /**
     * Inicializa los paneles de detalle y los añade al CardLayout.
     */
    private void configurarCardLayout() {
        try {
            cardLayout = (CardLayout) panelContenedor.getLayout();
        } catch (ClassCastException e) {
            System.err.println("¡Advertencia! panelContenedor no tenía CardLayout. Asignando uno nuevo.");
            cardLayout = new CardLayout();
            panelContenedor.setLayout(cardLayout);
        }

        List<Material> vidrios = new ArrayList<>();
        if (materialesDisponibles != null) {
            for (Material m : materialesDisponibles) {
                if (m.getTipo() == Material.TipoMaterial.VIDRIO) {
                    vidrios.add(m);
                }
            }
        }

        panelVentana = new PanelDetalleVentana();
        panelVentana.inicializarDatos(materialesDisponibles);

        panelPuerta = new PanelDetallePuertaAbatible();
        panelPuerta.inicializarDatos(materialesDisponibles);

        panelCanceleria = new PanelDetalleCanceleria();
        panelCanceleria.inicializarDatos(materialesDisponibles);

        panelContenedor.add(new javax.swing.JPanel(), "VACIO");
        panelContenedor.add(panelVentana, "VENTANA");
        panelContenedor.add(panelPuerta, "PUERTA");
        panelContenedor.add(panelCanceleria, "CANCELERIA");

        cardLayout.show(panelContenedor, "VACIO");
    }

    /**
     * Añade los ActionListeners a los Radio Buttons
     */
    private void configurarRadioBotones() {
        radVentana.addActionListener(e -> mostrarPanelSeleccionado());
        radPuerta.addActionListener(e -> mostrarPanelSeleccionado());
        radCanceleria.addActionListener(e -> mostrarPanelSeleccionado());

        javax.swing.ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup1.add(radVentana);
        buttonGroup1.add(radPuerta);
        buttonGroup1.add(radCanceleria);
    }

    /**
     * Comprueba que tipo de tarbajo esta seleccionado y muestra el panel .
     */
    private void mostrarPanelSeleccionado() {
        if (radVentana.isSelected()) {
            cardLayout.show(panelContenedor, "VENTANA");
            this.setTitle("Añadir Detalle - Ventana");
        } else if (radPuerta.isSelected()) {
            cardLayout.show(panelContenedor, "PUERTA");
            this.setTitle("Añadir Detalle - Puerta");
        } else if (radCanceleria.isSelected()) {
            cardLayout.show(panelContenedor, "CANCELERIA");
            this.setTitle("Añadir Detalle - Cancelería");
        } else {
            cardLayout.show(panelContenedor, "VACIO");
            this.setTitle("Añadir Detalle");
        }
        this.pack();
    }

    /**
     * Copia los valores del panel al objeto existente
     */
    private void actualizarDetalleExistente(Object datosNuevos) {
        if (detalleResultado instanceof VentanaDetalle detalleOriginal && datosNuevos instanceof VentanaDetalle vd) {

            detalleOriginal.setMedidaHorizontal(vd.getMedidaHorizontal());
            detalleOriginal.setMedidaVertical(vd.getMedidaVertical());
            detalleOriginal.setCantidad(vd.getCantidad());
            detalleOriginal.setTipoCristal(vd.getTipoCristal());
            detalleOriginal.setNoHojas(vd.getNoHojas());
            //detalleOriginal.setPrecioSoloUnaUnidadCalculado(vd.getPrecioSoloUnaUnidadCalculado());
            detalleOriginal.setSubtotalLinea(vd.getSubtotalLinea());
            detalleOriginal.setDescripcion(vd.getDescripcion());
            detalleOriginal.setTipoVentana(vd.getTipoVentana());
            detalleOriginal.setMosquitero(vd.isMosquitero());
            detalleOriginal.setArco(vd.isArco());
            detalleOriginal.setTipoArco(vd.getTipoArco());
            detalleOriginal.setMedidaArco(vd.getMedidaArco());
            detalleOriginal.setTipoCanalillo(vd.getTipoCanalillo());
            detalleOriginal.setMedidaCanalillo(vd.getMedidaCanalillo());
        } else if (detalleResultado instanceof PuertaAbatibleDetalle detalleOriginal && datosNuevos instanceof PuertaAbatibleDetalle pd) {

            detalleOriginal.setMedidaHorizontal(pd.getMedidaHorizontal());
            detalleOriginal.setMedidaVertical(pd.getMedidaVertical());
            detalleOriginal.setCantidad(pd.getCantidad());
            detalleOriginal.setTipoCristal(pd.getTipoCristal());
            detalleOriginal.setNoHojas(pd.getNoHojas());
            //detalleOriginal.setPrecioSoloUnaUnidadCalculado(pd.getPrecioSoloUnaUnidadCalculado());
            detalleOriginal.setSubtotalLinea(pd.getSubtotalLinea());
            detalleOriginal.setDescripcion(pd.getDescripcion());
            detalleOriginal.setTipoPuerta(pd.getTipoPuerta());
            detalleOriginal.setMosquitero(pd.isMosquitero());
            detalleOriginal.setDuela(pd.isDuela());
            detalleOriginal.setTipoDuela(pd.getTipoDuela());
            detalleOriginal.setMedidaDuela(pd.getMedidaDuela());
            detalleOriginal.setAdaptador(pd.isAdaptador());
            detalleOriginal.setTipoAdaptador(pd.getTipoAdaptador());
            detalleOriginal.setJunquillo(pd.isJunquillo());
            detalleOriginal.setTipoJunquillo(pd.getTipoJunquillo());
            detalleOriginal.setCanal(pd.isCanal());
            detalleOriginal.setTipoCanal(pd.getTipoCanal());
            detalleOriginal.setPivote(pd.isPivote());
            detalleOriginal.setTipoPivote(pd.getTipoPivote());
            detalleOriginal.setCantidadPivote(pd.getCantidadPivote());
            detalleOriginal.setJaladera(pd.isJaladera());
            detalleOriginal.setTipoJaladera(pd.getTipoJaladera());
            detalleOriginal.setCantidadJaladera(pd.getCantidadJaladera());
            detalleOriginal.setBarra(pd.isBarra());
            detalleOriginal.setTipoBarra(pd.getTipoBarra());
        } else if (detalleResultado instanceof CanceleriaFijaDetalle detalleOriginal && datosNuevos instanceof CanceleriaFijaDetalle cd) {

            detalleOriginal.setMedidaHorizontal(cd.getMedidaHorizontal());
            detalleOriginal.setMedidaVertical(cd.getMedidaVertical());
            detalleOriginal.setCantidad(cd.getCantidad());
            detalleOriginal.setTipoCristal(cd.getTipoCristal());
            detalleOriginal.setNoHojas(cd.getNoHojas());
            detalleOriginal.setPrecioSoloUnaUnidadCalculado(cd.getPrecioSoloUnaUnidadCalculado());
            detalleOriginal.setSubtotalLinea(cd.getSubtotalLinea());
            detalleOriginal.setDescripcion(cd.getDescripcion());
            detalleOriginal.setTipoCanceleria(cd.getTipoCanceleria());
            detalleOriginal.setBolsa(cd.isBolsa());
            detalleOriginal.setNumFijosVerticales(cd.getNumFijosVerticales());
            detalleOriginal.setNumFijosHorizontales(cd.getNumFijosHorizontales());
            detalleOriginal.setTipoTapa(cd.getTipoTapa());
            detalleOriginal.setCantidadTapa(cd.getCantidadTapa());
            detalleOriginal.setZoclo(cd.isZoclo());
            detalleOriginal.setTipoZoclo(cd.getTipoZoclo());
            detalleOriginal.setJunquillo(cd.isJunquillo());
            detalleOriginal.setTipoJunquillo(cd.getTipoJunquillo());
            detalleOriginal.setArco(cd.isArco());
            detalleOriginal.setTipoArco(cd.getTipoArco());
            detalleOriginal.setMedidaArco(cd.getMedidaArco());
            detalleOriginal.setCanalillo(cd.isCanalillo());
            detalleOriginal.setTipoCanalillo(cd.getTipoCanalillo());
            detalleOriginal.setMedidaCanalillo(cd.getMedidaCanalillo());
        }
    }

    /**
     * metodo para que frmCrearCotizacion recoja el resultado.
     */
    public Object getDetalleResultado() {
        return this.detalleResultado;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTipoTrabajo = new javax.swing.JPanel();
        radVentana = new javax.swing.JRadioButton();
        tituloTipoTrabajo = new javax.swing.JLabel();
        radPuerta = new javax.swing.JRadioButton();
        radCanceleria = new javax.swing.JRadioButton();
        panelBotonesCyA = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        panelContenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelTipoTrabajo.setBackground(new java.awt.Color(255, 255, 255));

        radVentana.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        radVentana.setForeground(new java.awt.Color(0, 38, 115));
        radVentana.setText("Ventana");

        tituloTipoTrabajo.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        tituloTipoTrabajo.setForeground(new java.awt.Color(15, 105, 196));
        tituloTipoTrabajo.setText("Tipo de Trabajo");

        radPuerta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        radPuerta.setForeground(new java.awt.Color(0, 38, 115));
        radPuerta.setText("Puerta Abatible");
        radPuerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radPuertaActionPerformed(evt);
            }
        });

        radCanceleria.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        radCanceleria.setForeground(new java.awt.Color(0, 38, 115));
        radCanceleria.setText("Cancelería");

        javax.swing.GroupLayout panelTipoTrabajoLayout = new javax.swing.GroupLayout(panelTipoTrabajo);
        panelTipoTrabajo.setLayout(panelTipoTrabajoLayout);
        panelTipoTrabajoLayout.setHorizontalGroup(
            panelTipoTrabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipoTrabajoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelTipoTrabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTipoTrabajoLayout.createSequentialGroup()
                        .addComponent(radVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(radPuerta)
                        .addGap(80, 80, 80)
                        .addComponent(radCanceleria))
                    .addComponent(tituloTipoTrabajo))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        panelTipoTrabajoLayout.setVerticalGroup(
            panelTipoTrabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipoTrabajoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tituloTipoTrabajo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTipoTrabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radVentana)
                    .addComponent(radPuerta)
                    .addComponent(radCanceleria))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        getContentPane().add(panelTipoTrabajo, java.awt.BorderLayout.PAGE_START);

        panelBotonesCyA.setBackground(new java.awt.Color(255, 255, 255));

        btnAceptar.setBackground(new java.awt.Color(71, 27, 252));
        btnAceptar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save-20.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnAceptar.setBorderPainted(false);
        btnAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAceptar.setDefaultCapable(false);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setRequestFocusEnabled(false);
        btnAceptar.setRolloverEnabled(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(202, 50, 0));
        btnCancelar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancelar-20.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCancelar.setBorderPainted(false);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancelar.setDefaultCapable(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setRequestFocusEnabled(false);
        btnCancelar.setRolloverEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesCyALayout = new javax.swing.GroupLayout(panelBotonesCyA);
        panelBotonesCyA.setLayout(panelBotonesCyALayout);
        panelBotonesCyALayout.setHorizontalGroup(
            panelBotonesCyALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesCyALayout.createSequentialGroup()
                .addContainerGap(372, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBotonesCyALayout.setVerticalGroup(
            panelBotonesCyALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesCyALayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(panelBotonesCyALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        getContentPane().add(panelBotonesCyA, java.awt.BorderLayout.PAGE_END);

        panelContenedor.setLayout(new java.awt.CardLayout());
        getContentPane().add(panelContenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        Object detalleNuevo = null;

        if (radVentana.isSelected()) {

            // VALIDAR
            if (!panelVentana.validar()) {
                return; // si falla → no continua
            }

            // OBTENER DETALLE
            detalleNuevo = panelVentana.getDetalle();
            if (detalleNuevo == null) {
                JOptionPane.showMessageDialog(this,
                        "No se pudo obtener el detalle de la ventana.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } else if (radPuerta.isSelected()) {

            // VALIDAR
            if (!panelPuerta.validar()) {
                return;
            }

            // OBTENER DETALLE
            detalleNuevo = panelPuerta.getDetalle();
            if (detalleNuevo == null) {
                JOptionPane.showMessageDialog(this,
                        "No se pudo obtener el detalle de la puerta.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } else if (radCanceleria.isSelected()) {

            // VALIDAR
            if (!panelCanceleria.validar()) {
                return;
            }

            // OBTENER DETALLE
            detalleNuevo = panelCanceleria.getDetalle();
            if (detalleNuevo == null) {
                JOptionPane.showMessageDialog(this,
                        "No se pudo obtener el detalle de la cancelería.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } else {

            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un tipo de trabajo (Ventana, Puerta o Cancelería).",
                    "Seleccione una opción",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (esModoEdicion) {
            actualizarDetalleExistente(detalleNuevo);
            this.detalleResultado = detalleResultado;  // mantiene el mismo objeto
        } else {
            this.detalleResultado = detalleNuevo;  // asigna el nuevo objeto
        }

        // CERRAR
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (esModoEdicion) {

        } else {
            this.detalleResultado = null;
        }
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void radPuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radPuertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radPuertaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JPanel panelBotonesCyA;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JPanel panelTipoTrabajo;
    private javax.swing.JRadioButton radCanceleria;
    private javax.swing.JRadioButton radPuerta;
    private javax.swing.JRadioButton radVentana;
    private javax.swing.JLabel tituloTipoTrabajo;
    // End of variables declaration//GEN-END:variables
}
