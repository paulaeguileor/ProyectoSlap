package utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class UIUtils {

    // colores
    public static final Color COLOR_PRINCIPAL = new Color(30, 30, 30);
    public static final Color COLOR_FONDO = new Color(250, 250, 250);
    public static final Color COLOR_BORDE = new Color(220, 220, 220);
    public static final Color COLOR_BOTON = new Color(245, 245, 245);
    public static final Color COLOR_BOTON_HOVER = new Color(230, 240, 255);
    public static final Color COLOR_PELIGRO = new Color(255, 235, 235);
    public static final Color COLOR_PELIGRO_BORDE = new Color(220, 120, 120);

    // fuentes
    public static Font fuenteTitulo() {
        return new Font("Segoe UI", Font.BOLD, 28);
    }

    public static Font fuenteSubtitulo() {
        return new Font("Segoe UI", Font.BOLD, 18);
    }

    public static Font fuenteNormal() {
        return new Font("Segoe UI", Font.PLAIN, 14);
    }

    public static Font fuentePrecio() {
        return new Font("Segoe UI", Font.BOLD, 16);
    }

    // bordes
    public static Border bordeSuave() {
        return BorderFactory.createLineBorder(COLOR_BORDE);
    }

    public static Border bordeTarjeta() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
    }

    public static Border bordeBoton() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );
    }

    // labels
    public static JLabel crearTitulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(fuenteTitulo());
        l.setForeground(COLOR_PRINCIPAL);
        return l;
    }

    public static JLabel crearTexto(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(fuenteNormal());
        l.setForeground(Color.BLACK);
        return l;
    }

    public static JLabel crearPrecio(double precio) {
        JLabel l = new JLabel(formatearPrecio(precio));
        l.setFont(fuentePrecio());
        l.setForeground(Color.BLACK);
        return l;
    }

    // tooltips
    public static void aplicarTooltip(JComponent c, String texto) {
        c.setToolTipText(texto);
    }

    public static void aplicarTooltipDoble(JComponent c, String linea1, String linea2) {
        c.setToolTipText("<html>" + linea1 + "<br>" + linea2 + "</html>");
    }

    // botones
    public static JButton crearBoton(String texto) {
        JButton b = new JButton(texto);
        b.setFocusPainted(false);
        b.setFont(fuenteNormal());
        b.setBackground(COLOR_BOTON);
        b.setBorder(bordeBoton());
        aplicarHover(b);
        return b;
    }

    public static JButton crearBotonPeligro(String texto) {
        JButton b = new JButton(texto);
        b.setFocusPainted(false);
        b.setFont(fuenteNormal());
        b.setBackground(COLOR_PELIGRO);
        b.setBorder(BorderFactory.createLineBorder(COLOR_PELIGRO_BORDE));
        aplicarHoverPeligro(b);
        return b;
    }

    private static void aplicarHover(JButton b) {
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setBackground(COLOR_BOTON_HOVER);
            }
            public void mouseExited(MouseEvent e) {
                b.setBackground(COLOR_BOTON);
            }
        });
    }

    private static void aplicarHoverPeligro(JButton b) {
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setBackground(new Color(255, 210, 210));
            }
            public void mouseExited(MouseEvent e) {
                b.setBackground(COLOR_PELIGRO);
            }
        });
    }

    // paneles
    public static JPanel crearPanelVertical() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        return p;
    }

    public static JPanel crearPanelHorizontal() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.setBackground(Color.WHITE);
        return p;
    }

    public static JPanel crearTarjeta() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(bordeTarjeta());
        return p;
    }

    // formato
    public static String formatearPrecio(double precio) {
        DecimalFormat df = new DecimalFormat("0.00 €");
        return df.format(precio);
    }

    // diálogos
    public static void info(Component parent, String texto) {
        JOptionPane.showMessageDialog(parent, texto, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void warn(Component parent, String texto) {
        JOptionPane.showMessageDialog(parent, texto, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public static void error(Component parent, String texto) {
        JOptionPane.showMessageDialog(parent, texto, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean confirm(Component parent, String texto) {
        int r = JOptionPane.showConfirmDialog(parent, texto, "Confirmar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        return r == JOptionPane.OK_OPTION;
    }

    // validaciones
    public static boolean emailValido(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean telefonoValido(String tel) {
        return tel != null && tel.matches("\\d+");
    }
}
