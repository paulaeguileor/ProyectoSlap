package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BD.BD;
import clases.PedidoInfo;
import clases.Sesion;
import clases.SolicitudInfo;

import ventanas.UI;

public class AdminPanel extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JFrame vAnterior;
    private final BD bd;

    // --- PEDIDOS (arriba) ---
    private JTable tablaPedidos;
    private DefaultTableModel modeloPedidos;
    private JComboBox<String> cmbEstadoPedido;
    private JButton btnActualizarPedido;
    private JButton btnRefrescarPedidos;

    // --- SOLICITUDES (abajo) ---
    private JTable tablaSolicitudes;
    private DefaultTableModel modeloSolicitudes;
    private JComboBox<String> cmbEstadoSolicitud;
    private JButton btnActualizarSolicitud;
    private JButton btnRefrescarSolicitudes;

    private JButton btnVolver;

    public AdminPanel(JFrame vAnterior, BD bd) {
        super("ADMIN - Pedidos y Cambios/Devoluciones");
        this.vAnterior = vAnterior;
        this.bd = bd;

        if (!Sesion.esAdmin) {
            JOptionPane.showMessageDialog(
                    this,
                    "Acceso denegado. Esta sección es solo para ADMIN.",
                    "No autorizado",
                    JOptionPane.ERROR_MESSAGE
            );
            dispose();
            return;
        }

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Panel de Administración");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 22f));
        titulo.setBorder(new EmptyBorder(12, 12, 12, 12));
        getContentPane().add(titulo, BorderLayout.NORTH);

        JPanel panelPedidos = construirPanelPedidos();

        JPanel panelSolicitudes = construirPanelSolicitudes();

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelPedidos, panelSolicitudes);
        split.setResizeWeight(0.60);
        split.setOneTouchExpandable(true);

        getContentPane().add(split, BorderLayout.CENTER);

        JPanel pSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnVolver = new JButton("Volver");
        pSur.add(btnVolver);
        getContentPane().add(pSur, BorderLayout.SOUTH);

        UI.estiloBotonSecundario(btnVolver);
        btnVolver.addActionListener(e -> {
            if (vAnterior != null) vAnterior.setVisible(true);
            dispose();
        });

        cargarPedidos();
        cargarSolicitudes();

        setVisible(true);
    }

    // =========================
    //  PANEL PEDIDOS
    // =========================
    private JPanel construirPanelPedidos() {
        JPanel cont = new JPanel(new BorderLayout());
        cont.setBorder(new EmptyBorder(10, 12, 10, 12));

        JLabel lbl = new JLabel("Pedidos");
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 18f));
        lbl.setBorder(new EmptyBorder(0, 0, 8, 0));
        cont.add(lbl, BorderLayout.NORTH);

        modeloPedidos = new DefaultTableModel(
                new Object[]{"ID", "Usuario", "Fecha", "Estado", "Importe"}, 0
        ) {
            private static final long serialVersionUID = 1L;
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaPedidos = new JTable(modeloPedidos);
        tablaPedidos.setRowHeight(26);
        tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        UI.aplicarEstiloTabla(tablaPedidos);
        tablaPedidos.getColumnModel().getColumn(3).setCellRenderer(UI.rendererEstadoPedidos());

        tablaPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && tablaPedidos.getSelectedRow() >= 0) {
                    actualizarPedidoSeleccionado();
                }
            }
        });

        tablaPedidos.getInputMap(JComponent.WHEN_FOCUSED).put(
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, 0),
                "actualizarPedido"
        );
        tablaPedidos.getActionMap().put("actualizarPedido", new AbstractAction() {
            private static final long serialVersionUID = 1L;
            @Override public void actionPerformed(java.awt.event.ActionEvent e) {
                if (tablaPedidos.getSelectedRow() >= 0) actualizarPedidoSeleccionado();
            }
        });

        JScrollPane scroll = new JScrollPane(tablaPedidos);
        cont.add(scroll, BorderLayout.CENTER);

        JPanel pControles = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        pControles.add(new JLabel("Cambiar estado a:"));

        cmbEstadoPedido = new JComboBox<>(new String[]{
                "PENDIENTE", "ENVIADO", "ENTREGADO", "CANCELADO", "DEVUELTO", "CAMBIADO"
        });
        pControles.add(cmbEstadoPedido);

        btnActualizarPedido = new JButton("Actualizar pedido");
        btnRefrescarPedidos = new JButton("Refrescar");
        
        UI.estiloBotonPrimario(btnActualizarPedido);
        UI.estiloBotonSecundario(btnRefrescarPedidos);

        pControles.add(btnActualizarPedido);
        pControles.add(btnRefrescarPedidos);

        btnActualizarPedido.addActionListener(e -> actualizarPedidoSeleccionado());
        btnRefrescarPedidos.addActionListener(e -> cargarPedidos());

        cont.add(pControles, BorderLayout.SOUTH);

        return cont;
    }

    private void cargarPedidos() {
        modeloPedidos.setRowCount(0);
        List<PedidoInfo> pedidos = bd.cargarTodosPedidos();
        if (pedidos.isEmpty()) {
            return;
        }
        for (PedidoInfo p : pedidos) {
            modeloPedidos.addRow(new Object[]{
                    p.getId(), p.getUsuario(), p.getFecha(), p.getEstado(), p.getImporte()
            });
        }
    }

    private void actualizarPedidoSeleccionado() {
        int fila = tablaPedidos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona un pedido primero.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPedido = (int) modeloPedidos.getValueAt(fila, 0);
        String estadoActual = String.valueOf(modeloPedidos.getValueAt(fila, 3));
        String nuevoEstado = String.valueOf(cmbEstadoPedido.getSelectedItem());

        if (nuevoEstado.equalsIgnoreCase(estadoActual)) {
            JOptionPane.showMessageDialog(this,
                    "El pedido ya está en ese estado.",
                    "Sin cambios",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int res = JOptionPane.showConfirmDialog(
                this,
                "¿Cambiar pedido " + idPedido + " de " + estadoActual + " a " + nuevoEstado + "?",
                "Confirmar",
                JOptionPane.OK_CANCEL_OPTION
        );
        if (res != JOptionPane.OK_OPTION) return;

        boolean ok = bd.actualizarEstadoPedido(idPedido, nuevoEstado);
        if (ok) {
            JOptionPane.showMessageDialog(this,
                    "Pedido actualizado correctamente.",
                    "OK",
                    JOptionPane.INFORMATION_MESSAGE);
            cargarPedidos();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo actualizar el pedido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================
    //  PANEL SOLICITUDES
    // =========================
    private JPanel construirPanelSolicitudes() {
        JPanel cont = new JPanel(new BorderLayout());
        cont.setBorder(new EmptyBorder(10, 12, 10, 12));

        JLabel lbl = new JLabel("Cambios y devoluciones");
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 18f));
        lbl.setBorder(new EmptyBorder(0, 0, 8, 0));
        cont.add(lbl, BorderLayout.NORTH);

        modeloSolicitudes = new DefaultTableModel(
                new Object[]{"ID Solicitud", "ID Pedido", "Usuario", "Fecha", "Tipo", "Estado"}, 0
        ) {
            private static final long serialVersionUID = 1L;
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaSolicitudes = new JTable(modeloSolicitudes);
        tablaSolicitudes.setRowHeight(26);
        tablaSolicitudes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        UI.aplicarEstiloTabla(tablaSolicitudes);
        tablaSolicitudes.getColumnModel().getColumn(5).setCellRenderer(UI.rendererEstadoSolicitudes());

        JScrollPane scroll = new JScrollPane(tablaSolicitudes);
        cont.add(scroll, BorderLayout.CENTER);

        JPanel pControles = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        pControles.add(new JLabel("Cambiar estado a:"));

        cmbEstadoSolicitud = new JComboBox<>(new String[]{
                "PENDIENTE", "PROCESANDO", "ACEPTADO", "RECHAZADO", "COMPLETADO"
        });
        pControles.add(cmbEstadoSolicitud);

        btnActualizarSolicitud = new JButton("Actualizar solicitud");
        btnRefrescarSolicitudes = new JButton("Refrescar");
        
        UI.estiloBotonPrimario(btnActualizarSolicitud);
        UI.estiloBotonSecundario(btnRefrescarSolicitudes);

        pControles.add(btnActualizarSolicitud);
        pControles.add(btnRefrescarSolicitudes);

        btnActualizarSolicitud.addActionListener(e -> actualizarSolicitudSeleccionada());
        btnRefrescarSolicitudes.addActionListener(e -> cargarSolicitudes());

        cont.add(pControles, BorderLayout.SOUTH);

        return cont;
    }

    private void cargarSolicitudes() {
        modeloSolicitudes.setRowCount(0);
        List<SolicitudInfo> solicitudes = bd.cargarTodasSolicitudes();
        for (SolicitudInfo s : solicitudes) {
            modeloSolicitudes.addRow(new Object[]{
                    s.getId(), s.getIdPedido(), s.getUsuario(), s.getFecha(), s.getTipo(), s.getEstado()
            });
        }
    }

    private void actualizarSolicitudSeleccionada() {
        int fila = tablaSolicitudes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona una solicitud primero.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idSolicitud = (int) modeloSolicitudes.getValueAt(fila, 0);
        int idPedido = (int) modeloSolicitudes.getValueAt(fila, 1);
        String tipo = String.valueOf(modeloSolicitudes.getValueAt(fila, 4)); // CAMBIO / DEVOLUCION
        String estadoActual = String.valueOf(modeloSolicitudes.getValueAt(fila, 5));
        String nuevoEstado = String.valueOf(cmbEstadoSolicitud.getSelectedItem());

        if (nuevoEstado.equalsIgnoreCase(estadoActual)) {
            JOptionPane.showMessageDialog(this,
                    "La solicitud ya está en ese estado.",
                    "Sin cambios",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int res = JOptionPane.showConfirmDialog(
                this,
                "¿Cambiar solicitud " + idSolicitud + " de " + estadoActual + " a " + nuevoEstado + "?",
                "Confirmar",
                JOptionPane.OK_CANCEL_OPTION
        );
        if (res != JOptionPane.OK_OPTION) return;

        boolean ok = bd.actualizarEstadoSolicitud(idSolicitud, nuevoEstado);
        if (!ok) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo actualizar la solicitud.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if ("COMPLETADO".equalsIgnoreCase(nuevoEstado)) {
            if ("DEVOLUCION".equalsIgnoreCase(tipo)) {
                bd.actualizarEstadoPedido(idPedido, "DEVUELTO");
            } else if ("CAMBIO".equalsIgnoreCase(tipo)) {
                bd.actualizarEstadoPedido(idPedido, "CAMBIADO");
            }
            cargarPedidos();
        }

        JOptionPane.showMessageDialog(this,
                "Solicitud actualizada correctamente.",
                "OK",
                JOptionPane.INFORMATION_MESSAGE);

        cargarSolicitudes();
    }
}