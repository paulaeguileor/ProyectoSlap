package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BD.BD;
import clases.UsuariosInfo;

public class Usuario extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JFrame vAnterior;
    private BD bd;
    
    // datos del usuario que mostraremos
    private String nombreMostrado;
    private String direccionMostrada;
    private String emailMostrado;
    private String telefonoMostrado;

    //Contenido Central
    private JPanel pCentro;
    
    //Navegador
    private JPanel pNav;
    private JButton btnPerfil, btnCDs, btnCompras;
    
    //Sur
    private JPanel pSur;
    private JButton btnVolver;
    
    private JTable tablaDirecciones;
    private DefaultTableModel modeloTablaDirecciones;
    
    private JTable tablaPedidos;
    private DefaultTableModel modeloTablaPedidos;
    
    public Usuario(JFrame va, BD bd, String nombreUsuario) {
        super();
        this.vAnterior = va;
        this.bd = bd;
        
        setTitle("Mi cuenta");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        
        // Navegador
        pNav = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pNav.setBorder(new EmptyBorder(8, 12, 8, 12));
        btnPerfil = new JButton("Perfil");
        btnCDs = new JButton("Cambios y Devoluciones");
        btnCompras = new JButton("Compras");
        pNav.add(btnPerfil);
        pNav.add(btnCDs);
        pNav.add(btnCompras);
        getContentPane().add(pNav, BorderLayout.NORTH);
        
        // Contenido central
        pCentro = new JPanel(new BorderLayout());
        pCentro.setBorder(new EmptyBorder(10, 12, 10, 12));
        getContentPane().add(pCentro, BorderLayout.CENTER);
        
        // Sur
        pSur = new JPanel();
        btnVolver = new JButton("Volver");
        pSur.add(btnVolver);
        getContentPane().add(pSur, BorderLayout.SOUTH);
        
        // Cargar datos desde BD
        UsuariosInfo info = bd.cargarUsuario(nombreUsuario);

        // valores por defecto
        nombreMostrado = nombreUsuario;
        direccionMostrada = "No disponible";
        emailMostrado = "No disponible";
        telefonoMostrado = "No disponible";

        if (info != null) {
            if (info.getNombre() != null)    nombreMostrado   = info.getNombre();
            if (info.getDireccion() != null) direccionMostrada = info.getDireccion();
            if (info.getEmail() != null)     emailMostrado    = info.getEmail();
            if (info.getTelefono() != null)  telefonoMostrado = info.getTelefono();
        }

        // LISTENERS
        btnPerfil.addActionListener(e -> mostrarPerfilCentrado(
                nombreMostrado,
                direccionMostrada,
                emailMostrado,
                telefonoMostrado
        ));

        btnCompras.addActionListener(e -> mostrarCompras());
        btnCDs.addActionListener(e -> mostrarCambiosDevoluciones());
        btnVolver.addActionListener(e -> {
            if (vAnterior != null) vAnterior.setVisible(true);
            dispose();
        });
        
        // Vista por defecto: perfil
        mostrarPerfilCentrado(
                nombreMostrado,
                direccionMostrada,
                emailMostrado,
                telefonoMostrado
        );
        
        setVisible(true);
    }
    
    // PERFIL
    private void mostrarPerfilCentrado(String nombreCompleto, String direccion, String email, String telefono) {
        JPanel panelPerfilBis = new JPanel(new BorderLayout());
        panelPerfilBis.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel columna = new JPanel();
        columna.setLayout(new BoxLayout(columna, BoxLayout.Y_AXIS));
        columna.setMaximumSize(new Dimension(520, Integer.MAX_VALUE));
        columna.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nombre
        JLabel lblNombre = etiquetaSeccion(nombreCompleto);
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        columna.add(lblNombre);
        columna.add(Box.createVerticalStrut(20));

        // Bloques de info
        columna.add(bloqueInfo("DIRECCIÓN", direccion));
        columna.add(Box.createVerticalStrut(12));
        columna.add(bloqueInfo("E-MAIL", email));
        columna.add(Box.createVerticalStrut(12));
        columna.add(bloqueInfo("TELÉFONO", telefono));
        columna.add(Box.createVerticalStrut(24));

        // Botón EDITAR PERFIL
        JButton btnEditarPerfil = new JButton("EDITAR PERFIL");
        btnEditarPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEditarPerfil.setFocusPainted(false);
        btnEditarPerfil.addActionListener(ev -> {
            // Panel del diálogo de edición
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

            if (result == javax.swing.JOptionPane.OK_OPTION) {
                // Actualizamos campos en memoria
                direccionMostrada = txtDir.getText().trim();
                emailMostrado = txtEmail.getText().trim();
                telefonoMostrado = txtTel.getText().trim();

                // Guardamos en BD
                if (!emailMostrado.isEmpty() && (!emailMostrado.contains("@") || !emailMostrado.contains("."))) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "Email no válido.",
                            "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!telefonoMostrado.isEmpty() && !telefonoMostrado.matches("\\d+")) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "El teléfono solo puede contener números.",
                            "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean ok = bd.actualizarUsuarioContacto(
                        nombreMostrado,
                        direccionMostrada,
                        emailMostrado,
                        telefonoMostrado
                );

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
        });
        columna.add(btnEditarPerfil);
        columna.add(Box.createVerticalStrut(12));

        // Botón CERRAR SESIÓN
        JButton btnCerrarSesion = new JButton("CERRAR SESIÓN");
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrarSesion.setFocusPainted(false);
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

        panelPerfilBis.add(new JPanel(), BorderLayout.WEST);
        panelPerfilBis.add(new JPanel(), BorderLayout.EAST);
        panelPerfilBis.add(centro, BorderLayout.CENTER);

        setCentro(panelPerfilBis);
    }
    
    private JPanel bloqueInfo(String titulo, String valor) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(Color.BLACK);

        JLabel lblValor = new JLabel(valor);
        lblValor.setForeground(Color.BLACK);

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

    private void setCentro(JComponent comp) {
        pCentro.removeAll();
        pCentro.add(comp, BorderLayout.CENTER);
        pCentro.revalidate();
        pCentro.repaint();
    }
    
    //COMPRAS
    private void mostrarCompras() {
        JPanel pC = new JPanel(new BorderLayout());
        JLabel titulo = crearTitulo("Compras");
        pC.add(titulo, BorderLayout.NORTH);

        modeloTablaPedidos = new DefaultTableModel(new Object[]{"Nº Pedido", "Fecha", "Estado", "Importe"}, 0);
        tablaPedidos = new JTable(modeloTablaPedidos);
        tablaPedidos.setRowHeight(24);

        // Cargar pedidos desde BD
        java.util.List<clases.PedidoInfo> pedidos = bd.cargarPedidosUsuario(nombreMostrado);
        for (clases.PedidoInfo p : pedidos) {
            modeloTablaPedidos.addRow(new Object[]{
                    p.getId(),
                    p.getFecha(),
                    p.getEstado(),
                    p.getImporte()
            });
        }

        // Botón para ver detalle
        JButton btnDetalle = new JButton("Ver detalle pedido");
        btnDetalle.addActionListener(e -> {
            int fila = tablaPedidos.getSelectedRow();
            if (fila >= 0) {
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
        });

        JPanel pSurTabla = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pSurTabla.add(btnDetalle);

        JScrollPane scroll = new JScrollPane(tablaPedidos);
        pC.add(scroll, BorderLayout.CENTER);
        pC.add(pSurTabla, BorderLayout.SOUTH);

        setCentro(pC);
    }
    
    //CAMBIOS Y DEVOLUCIONES
    private void mostrarCambiosDevoluciones() {
        JPanel pCD = new JPanel(new BorderLayout());
        JLabel titulo = crearTitulo("Cambios y Devoluciones");
        pCD.add(titulo, BorderLayout.NORTH);

        modeloTablaDirecciones = new DefaultTableModel(
                new Object[]{"Nº Solicitud", "Nº Pedido", "Fecha", "Tipo", "Estado"}, 0);
        tablaDirecciones = new JTable(modeloTablaDirecciones);
        tablaDirecciones.setRowHeight(24);

        java.util.List<clases.SolicitudInfo> solicitudes = bd.cargarSolicitudesUsuario(nombreMostrado);
        for (clases.SolicitudInfo s : solicitudes) {
            modeloTablaDirecciones.addRow(new Object[]{
                    s.getId(),
                    s.getIdPedido(),
                    s.getFecha(),
                    s.getTipo(),
                    s.getEstado()
            });
        }

        JScrollPane scroll = new JScrollPane(tablaDirecciones);
        pCD.add(scroll, BorderLayout.CENTER);

        setCentro(pCD);
    }
    
    private JLabel crearTitulo(String txt) {
        JLabel l = new JLabel(txt);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 20f));
        l.setBorder(new EmptyBorder(0, 0, 10, 0));
        return l;
    }
}