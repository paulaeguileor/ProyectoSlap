package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BD.BD;
import clases.UsuariosInfo;

public class Usuario extends JFrame {
    private static final long serialVersionUID = 1L;

    private JFrame vAnterior;
    private BD bd;

    private String nombreMostrado;
    private String direccionMostrada;
    private String emailMostrado;
    private String telefonoMostrado;

    private JPanel pCentro;

    private JPanel pNav;
    private JButton btnPerfil, btnCDs, btnCompras;

    private JPanel pSur;
    private JButton btnVolver;

    private JTable tablaDirecciones;
    private DefaultTableModel modeloTablaDirecciones;

    private JTable tablaPedidos;
    private DefaultTableModel modeloTablaPedidos;

    // ====== COLORES / ESTILO ======
    private static final Color BG_APP = new Color(250, 250, 250);
    private static final Color BG_HEADER = Color.WHITE;
    private static final Color BORDER_SOFT = new Color(230, 230, 230);

    private static final Color TAB_BG = new Color(245, 245, 245);
    private static final Color TAB_ACTIVE_BG = new Color(230, 240, 255);
    private static final Color TAB_BORDER = new Color(210, 210, 210);
    private static final Color TAB_ACTIVE_BORDER = new Color(120, 160, 230);

    private static final Color BTN_PRIMARY_BG = new Color(230, 240, 255);
    private static final Color BTN_PRIMARY_BORDER = new Color(120, 160, 230);

    private static final Color BTN_DANGER_BG = new Color(255, 235, 235);
    private static final Color BTN_DANGER_BORDER = new Color(220, 120, 120);

    public Usuario(JFrame va, BD bd, String nombreUsuario) {
        super();
        this.vAnterior = va;
        this.bd = bd;

        setTitle("Mi cuenta");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(BG_APP);

        // ===== HEADER (título + tabs) =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(12, 16, 12, 16));
        header.setBackground(BG_HEADER);

        JPanel pTitulo = new JPanel();
        pTitulo.setBackground(BG_HEADER);
        pTitulo.setLayout(new BoxLayout(pTitulo, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("Mi cuenta");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 20f));

        JLabel lblSub = new JLabel("Gestiona tu perfil, pedidos y solicitudes");
        lblSub.setFont(lblSub.getFont().deriveFont(Font.PLAIN, 12f));
        lblSub.setForeground(new Color(120, 120, 120));

        pTitulo.add(lblTitulo);
        pTitulo.add(Box.createVerticalStrut(2));
        pTitulo.add(lblSub);

        header.add(pTitulo, BorderLayout.WEST);

        pNav = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pNav.setBackground(BG_HEADER);

        btnPerfil = crearTab("Perfil");
        btnCDs = crearTab("Cambios y devoluciones");
        btnCompras = crearTab("Compras");

        pNav.add(btnPerfil);
        if (!clases.Sesion.esAdmin) {
            pNav.add(btnCDs);
            pNav.add(btnCompras);
        }
        header.add(pNav, BorderLayout.CENTER);

        getContentPane().add(header, BorderLayout.NORTH);

        // ===== CENTRO =====
        pCentro = new JPanel(new BorderLayout());
        pCentro.setBorder(new EmptyBorder(10, 12, 10, 12));
        pCentro.setBackground(BG_APP);
        getContentPane().add(pCentro, BorderLayout.CENTER);

        // ===== SUR =====
        pSur = new JPanel(new BorderLayout());
        pSur.setBackground(BG_APP);
        pSur.setBorder(new EmptyBorder(8, 16, 16, 16));

        JPanel pSurRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        pSurRight.setBackground(BG_APP);

        btnVolver = crearBotonNeutro("Volver");
        pSurRight.add(btnVolver);

        pSur.add(pSurRight, BorderLayout.EAST);
        getContentPane().add(pSur, BorderLayout.SOUTH);

        // ===== Cargar datos =====
        UsuariosInfo info = bd.cargarUsuario(nombreUsuario);

        nombreMostrado = nombreUsuario;
        direccionMostrada = "No disponible";
        emailMostrado = "No disponible";
        telefonoMostrado = "No disponible";

        if (info != null) {
            if (info.getNombre() != null) nombreMostrado = info.getNombre();
            if (info.getDireccion() != null) direccionMostrada = info.getDireccion();
            if (info.getEmail() != null) emailMostrado = info.getEmail();
            if (info.getTelefono() != null) telefonoMostrado = info.getTelefono();
        }

        // ===== LISTENERS =====
        btnPerfil.addActionListener(e -> {
            setTabActivo(btnPerfil);
            mostrarPerfilCentrado(nombreMostrado, direccionMostrada, emailMostrado, telefonoMostrado);
        });

        if (!clases.Sesion.esAdmin) {
            btnCompras.addActionListener(e -> {
                setTabActivo(btnCompras);
                mostrarCompras();
            });

            btnCDs.addActionListener(e -> {
                setTabActivo(btnCDs);
                mostrarCambiosDevoluciones();
            });
        }

        btnVolver.addActionListener(e -> {
            if (vAnterior != null) vAnterior.setVisible(true);
            dispose();
        });

        // Vista inicial
        mostrarPerfilCentrado(nombreMostrado, direccionMostrada, emailMostrado, telefonoMostrado);
        setTabActivo(btnPerfil);

        setVisible(true);
    }

    // =========================
    // PERFIL
    // =========================
    private void mostrarPerfilCentrado(String nombreCompleto, String direccion, String email, String telefono) {
        JPanel panelPerfilBis = new JPanel(new BorderLayout());
        panelPerfilBis.setOpaque(false);
        panelPerfilBis.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel columna = new JPanel();
        columna.setBackground(Color.WHITE);
        columna.setLayout(new BoxLayout(columna, BoxLayout.Y_AXIS));
        columna.setMaximumSize(new Dimension(520, Integer.MAX_VALUE));
        columna.setAlignmentX(Component.CENTER_ALIGNMENT);
        columna.setBorder(new EmptyBorder(18, 18, 18, 18));

        // Nombre
        JLabel lblNombre = etiquetaSeccion(nombreCompleto);
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        columna.add(lblNombre);
        columna.add(Box.createVerticalStrut(14));

        // Bloques
        columna.add(bloqueInfo("DIRECCIÓN", direccion));
        columna.add(Box.createVerticalStrut(12));
        columna.add(bloqueInfo("E-MAIL", email));
        columna.add(Box.createVerticalStrut(12));
        columna.add(bloqueInfo("TELÉFONO", telefono));
        columna.add(Box.createVerticalStrut(18));

        // ADMIN
        if (clases.Sesion.esAdmin) {
            JButton btnAdminPanel = crearBotonPrimario("ADMIN: Procesar pedidos");
            btnAdminPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnAdminPanel.addActionListener(e -> {
                setVisible(false);
                new ventanas.AdminPanel(this, bd);
            });
            columna.add(btnAdminPanel);
            columna.add(Box.createVerticalStrut(12));
        }

        // Editar
        JButton btnEditarPerfil = crearBotonNeutro("EDITAR PERFIL");
        btnEditarPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEditarPerfil.addActionListener(ev -> abrirDialogoEditarPerfil());
        columna.add(btnEditarPerfil);
        columna.add(Box.createVerticalStrut(10));

        // Cerrar sesión
        JButton btnCerrarSesion = crearBotonPeligro("CERRAR SESIÓN");
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrarSesion.addActionListener(e -> {
            clases.Sesion.cerrarSesion();
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Has cerrado sesión correctamente.",
                    "Sesión cerrada",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);

            if (vAnterior != null) vAnterior.setVisible(true);
            dispose();
        });
        columna.add(btnCerrarSesion);

        centro.add(Box.createVerticalGlue());
        centro.add(columna);
        centro.add(Box.createVerticalGlue());

        panelPerfilBis.add(centro, BorderLayout.CENTER);

        setCentro(panelPerfilBis);
    }

    private void abrirDialogoEditarPerfil() {
        JPanel panelEdit = new JPanel();
        panelEdit.setLayout(new BoxLayout(panelEdit, BoxLayout.Y_AXIS));
        panelEdit.setBorder(new EmptyBorder(10, 10, 10, 10));

        javax.swing.JTextField txtDir = new javax.swing.JTextField(direccionMostrada, 20);
        javax.swing.JTextField txtEmail = new javax.swing.JTextField(emailMostrado, 20);
        javax.swing.JTextField txtTel = new javax.swing.JTextField(telefonoMostrado, 20);

        panelEdit.add(new JLabel("Dirección:"));
        panelEdit.add(txtDir);
        panelEdit.add(Box.createVerticalStrut(8));
        panelEdit.add(new JLabel("E-mail:"));
        panelEdit.add(txtEmail);
        panelEdit.add(Box.createVerticalStrut(8));
        panelEdit.add(new JLabel("Teléfono:"));
        panelEdit.add(txtTel);

        int result = javax.swing.JOptionPane.showConfirmDialog(
                this,
                panelEdit,
                "Editar perfil",
                javax.swing.JOptionPane.OK_CANCEL_OPTION,
                javax.swing.JOptionPane.PLAIN_MESSAGE
        );
        if (result != javax.swing.JOptionPane.OK_OPTION) return;

        String nuevaDir = txtDir.getText().trim();
        String nuevoEmail = txtEmail.getText().trim();
        String nuevoTel = txtTel.getText().trim();

        if (!nuevoEmail.isEmpty() && (!nuevoEmail.contains("@") || !nuevoEmail.contains("."))) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Email no válido.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!nuevoTel.isEmpty() && !nuevoTel.matches("\\d+")) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "El teléfono solo puede contener números.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        direccionMostrada = nuevaDir;
        emailMostrado = nuevoEmail;
        telefonoMostrado = nuevoTel;

        // Si tu método es void, cambia esto (te lo explico abajo)
        boolean ok = bd.actualizarUsuarioContacto(nombreMostrado, direccionMostrada, emailMostrado, telefonoMostrado);

        if (ok) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Datos actualizados correctamente.",
                    "Guardado",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);

            mostrarPerfilCentrado(nombreMostrado, direccionMostrada, emailMostrado, telefonoMostrado);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No se han podido guardar los cambios. Inténtalo de nuevo.",
                    "Error al guardar",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel bloqueInfo(String titulo, String valor) {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(new Color(110, 110, 110));
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 12f));

        JLabel lblValor = new JLabel(valor);
        lblValor.setForeground(Color.BLACK);
        lblValor.setFont(lblValor.getFont().deriveFont(Font.PLAIN, 14f));

        p.add(lblTitulo);
        p.add(Box.createVerticalStrut(4));
        p.add(lblValor);
        return p;
    }

    private JLabel etiquetaSeccion(String txt) {
        JLabel l = new JLabel(txt);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 18f));
        l.setForeground(Color.BLACK);
        return l;
    }

    // =========================
    // CENTRO - Card
    // =========================
    private void setCentro(JComponent comp) {
        pCentro.removeAll();
        pCentro.setBackground(BG_APP);
        pCentro.add(envolverEnCard(comp), BorderLayout.CENTER);
        pCentro.revalidate();
        pCentro.repaint();
    }

    private JPanel envolverEnCard(JComponent contenido) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_SOFT),
                new EmptyBorder(16, 16, 16, 16)
        ));

        card.add(contenido, BorderLayout.CENTER);

        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBackground(BG_APP);
        wrap.setBorder(new EmptyBorder(16, 16, 16, 16));
        wrap.add(card, BorderLayout.CENTER);

        return wrap;
    }

    // =========================
    // COMPRAS
    // =========================
    private void mostrarCompras() {
        JPanel pC = new JPanel(new BorderLayout());
        pC.setOpaque(false);

        JLabel titulo = crearTitulo("Compras");
        pC.add(titulo, BorderLayout.NORTH);

        modeloTablaPedidos = new DefaultTableModel(new Object[]{"Nº Pedido", "Fecha", "Estado", "Importe"}, 0) {
            private static final long serialVersionUID = 1L;
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaPedidos = new JTable(modeloTablaPedidos);
        estilizarTabla(tablaPedidos);

        java.util.List<clases.PedidoInfo> pedidos = bd.cargarPedidosUsuario(nombreMostrado);
        for (clases.PedidoInfo p : pedidos) {
            modeloTablaPedidos.addRow(new Object[]{p.getId(), p.getFecha(), p.getEstado(), p.getImporte()});
        }

        JScrollPane scroll = new JScrollPane(tablaPedidos);
        scroll.setBorder(BorderFactory.createLineBorder(BORDER_SOFT));
        pC.add(scroll, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        acciones.setBackground(Color.WHITE);

        JButton btnDetalle = crearBotonNeutro("Ver detalle");
        btnDetalle.addActionListener(e -> verDetallePedido());

        JButton btnCambio = crearBotonNeutro("Solicitar cambio");
        btnCambio.addActionListener(e -> solicitarCambioDevolucion("CAMBIO"));

        JButton btnDevolucion = crearBotonNeutro("Solicitar devolución");
        btnDevolucion.addActionListener(e -> solicitarCambioDevolucion("DEVOLUCION"));

        acciones.add(btnDetalle);
        acciones.add(btnCambio);
        acciones.add(btnDevolucion);

        if (modeloTablaPedidos.getRowCount() == 0) {
            pC.add(mensajeVacio("Aún no tienes pedidos.", "Cuando compres algo, aparecerá aquí."), BorderLayout.SOUTH);
        } else {
            pC.add(acciones, BorderLayout.SOUTH);
        }

        setCentro(pC);
    }

    private void verDetallePedido() {
        int fila = tablaPedidos.getSelectedRow();
        if (fila < 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Selecciona un pedido primero.",
                    "Aviso",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPedido = (int) modeloTablaPedidos.getValueAt(fila, 0);
        java.util.List<clases.LineaPedidoInfo> lineas = bd.cargarLineasPedido(idPedido);

        StringBuilder sb = new StringBuilder();
        for (clases.LineaPedidoInfo l : lineas) {
            sb.append(l.getTipoArticulo())
              .append(" - ")
              .append(l.getDescripcion())
              .append(" x")
              .append(l.getCantidad())
              .append("  = ")
              .append(l.getSubtotal())
              .append("\n");
        }

        javax.swing.JOptionPane.showMessageDialog(this, sb.toString(),
                "Detalle pedido " + idPedido,
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    private void solicitarCambioDevolucion(String tipo) {
        int fila = tablaPedidos.getSelectedRow();
        if (fila < 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Selecciona un pedido primero.",
                    "Aviso",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPedido = (int) modeloTablaPedidos.getValueAt(fila, 0);
        String estadoPedido = String.valueOf(modeloTablaPedidos.getValueAt(fila, 2));

        if (!"ENTREGADO".equalsIgnoreCase(estadoPedido)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Solo puedes solicitar " + tipo.toLowerCase() + " si el pedido está ENTREGADO.",
                    "No permitido",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        int res = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "¿Confirmas la solicitud de " + tipo + " para el pedido " + idPedido + "?",
                "Confirmar",
                javax.swing.JOptionPane.OK_CANCEL_OPTION
        );
        if (res != javax.swing.JOptionPane.OK_OPTION) return;
        
        if (bd.existeSolicitudActiva(idPedido)) {
            JOptionPane.showMessageDialog(this,
                    "Ya existe una solicitud activa para este pedido.",
                    "No permitido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        bd.crearSolicitud(nombreMostrado, idPedido, tipo);

        javax.swing.JOptionPane.showMessageDialog(this,
                "Solicitud creada correctamente.\nEstado: PENDIENTE",
                "OK",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);

        setTabActivo(btnCDs);
        mostrarCambiosDevoluciones();
    }

    // =========================
    // CAMBIOS Y DEVOLUCIONES
    // =========================
    private void mostrarCambiosDevoluciones() {
        JPanel pCD = new JPanel(new BorderLayout());
        pCD.setOpaque(false);

        JLabel titulo = crearTitulo("Cambios y devoluciones");
        pCD.add(titulo, BorderLayout.NORTH);

        modeloTablaDirecciones = new DefaultTableModel(
                new Object[]{"Nº Solicitud", "Nº Pedido", "Fecha", "Tipo", "Estado"}, 0
        ) {
            private static final long serialVersionUID = 1L;
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaDirecciones = new JTable(modeloTablaDirecciones);
        estilizarTabla(tablaDirecciones);

        java.util.List<clases.SolicitudInfo> solicitudes = bd.cargarSolicitudesUsuario(nombreMostrado);
        for (clases.SolicitudInfo s : solicitudes) {
            modeloTablaDirecciones.addRow(new Object[]{
                    s.getId(), s.getIdPedido(), s.getFecha(), s.getTipo(), s.getEstado()
            });
        }

        JScrollPane scroll = new JScrollPane(tablaDirecciones);
        scroll.setBorder(BorderFactory.createLineBorder(BORDER_SOFT));
        pCD.add(scroll, BorderLayout.CENTER);

        if (modeloTablaDirecciones.getRowCount() == 0) {
            pCD.add(mensajeVacio("No hay solicitudes todavía.", "Cuando pidas un cambio/devolución, aparecerá aquí."), BorderLayout.SOUTH);
        }

        setCentro(pCD);
    }

    // =========================
    // UI Helpers
    // =========================
    private JLabel crearTitulo(String txt) {
        JLabel l = new JLabel(txt);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 20f));
        l.setBorder(new EmptyBorder(0, 0, 10, 0));
        return l;
    }

    private JButton crearTab(String texto) {
        JButton b = new JButton(texto);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(TAB_BORDER),
                new EmptyBorder(6, 12, 6, 12)
        ));
        b.setBackground(TAB_BG);
        b.setFont(b.getFont().deriveFont(Font.PLAIN, 13f));
        return b;
    }

    private void setTabActivo(JButton activo) {
        JButton[] tabs = new JButton[]{btnPerfil, btnCDs, btnCompras};
        for (JButton b : tabs) {
            if (b == null) continue;
            boolean esActivo = (b == activo);

            b.setBackground(esActivo ? TAB_ACTIVE_BG : TAB_BG);
            b.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(esActivo ? TAB_ACTIVE_BORDER : TAB_BORDER),
                    new EmptyBorder(6, 12, 6, 12)
            ));
            b.setFont(b.getFont().deriveFont(esActivo ? Font.BOLD : Font.PLAIN, 13f));
        }
    }

    private JButton crearBotonNeutro(String texto) {
        JButton b = new JButton(texto);
        b.setFocusPainted(false);
        b.setBackground(new Color(245, 245, 245));
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(8, 12, 8, 12)
        ));
        return b;
    }

    private JButton crearBotonPrimario(String texto) {
        JButton b = new JButton(texto);
        b.setFocusPainted(false);
        b.setBackground(BTN_PRIMARY_BG);
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BTN_PRIMARY_BORDER),
                new EmptyBorder(8, 12, 8, 12)
        ));
        b.setFont(b.getFont().deriveFont(Font.BOLD, 13f));
        return b;
    }

    private JButton crearBotonPeligro(String texto) {
        JButton b = new JButton(texto);
        b.setFocusPainted(false);
        b.setBackground(BTN_DANGER_BG);
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BTN_DANGER_BORDER),
                new EmptyBorder(8, 12, 8, 12)
        ));
        b.setFont(b.getFont().deriveFont(Font.BOLD, 13f));
        return b;
    }

    private JPanel mensajeVacio(String titulo, String sub) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(10, 10, 0, 10));

        JLabel t = new JLabel(titulo, SwingConstants.LEFT);
        t.setFont(t.getFont().deriveFont(Font.BOLD, 13f));

        JLabel s = new JLabel(sub, SwingConstants.LEFT);
        s.setForeground(new Color(120, 120, 120));
        s.setFont(s.getFont().deriveFont(Font.PLAIN, 12f));

        p.add(t, BorderLayout.NORTH);
        p.add(s, BorderLayout.CENTER);
        return p;
    }

    private void estilizarTabla(JTable tabla) {
        tabla.setRowHeight(26);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));

        JTableHeader th = tabla.getTableHeader();
        th.setFont(th.getFont().deriveFont(Font.BOLD, 12f));
        th.setBackground(new Color(245, 245, 245));
        th.setForeground(Color.BLACK);
        th.setBorder(BorderFactory.createLineBorder(BORDER_SOFT));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(252, 252, 252));
                }
                return c;
            }
        };

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
}