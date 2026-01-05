package ventanas;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class UI {

    private UI() {}

    public static final Font FONT_BASE = new Font("SansSerif", Font.PLAIN, 14);
    public static final Color BG = Color.WHITE;
    public static final Color LINE = new Color(235, 235, 235);

    public static void aplicarEstiloTabla(JTable tabla) {
        tabla.setFont(FONT_BASE);
        tabla.setRowHeight(28);
        tabla.setShowVerticalLines(false);
        tabla.setShowHorizontalLines(true);
        tabla.setGridColor(LINE);
        tabla.setSelectionBackground(new Color(220, 235, 255));
        tabla.setSelectionForeground(Color.BLACK);

        JTableHeader header = tabla.getTableHeader();
        header.setFont(FONT_BASE.deriveFont(Font.BOLD, 14f));
        header.setBackground(new Color(245, 245, 245));
        header.setForeground(Color.BLACK);
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer zebra = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(250, 250, 250));
                }
                setBorder(noFocusBorder);
                return c;
            }
        };
        zebra.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(zebra);
        }
    }

    public static TableCellRenderer rendererEstadoPedidos() {
        return new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setFont(FONT_BASE.deriveFont(Font.BOLD));

                String estado = value == null ? "" : value.toString().toUpperCase();

                if (!isSelected) {
                    lbl.setOpaque(true);
                    lbl.setBackground(Color.WHITE);
                    lbl.setForeground(Color.DARK_GRAY);

                    switch (estado) {
                        case "PENDIENTE":  lbl.setForeground(new Color(160, 120, 0)); break;
                        case "ENVIADO":    lbl.setForeground(new Color(0, 90, 160)); break;
                        case "ENTREGADO":  lbl.setForeground(new Color(0, 130, 70)); break;
                        case "DEVUELTO":   lbl.setForeground(new Color(120, 0, 160)); break;
                        case "CAMBIADO":   lbl.setForeground(new Color(0, 120, 120)); break;
                        case "CANCELADO":  lbl.setForeground(new Color(160, 0, 0)); break;
                        default: break;
                    }
                }
                return lbl;
            }
        };
    }

    public static TableCellRenderer rendererEstadoSolicitudes() {
        return new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setFont(FONT_BASE.deriveFont(Font.BOLD));

                String estado = value == null ? "" : value.toString().toUpperCase();

                if (!isSelected) {
                    lbl.setOpaque(true);
                    lbl.setBackground(Color.WHITE);
                    lbl.setForeground(Color.DARK_GRAY);

                    switch (estado) {
                        case "PENDIENTE":   lbl.setForeground(new Color(160, 120, 0)); break;
                        case "PROCESANDO":  lbl.setForeground(new Color(0, 90, 160)); break;
                        case "ACEPTADO":    lbl.setForeground(new Color(0, 130, 70)); break;
                        case "RECHAZADO":   lbl.setForeground(new Color(160, 0, 0)); break;
                        case "COMPLETADO":  lbl.setForeground(new Color(120, 0, 160)); break;
                        default: break;
                    }
                }
                return lbl;
            }
        };
    }

    public static void estiloBotonPrimario(JButton b) {
        b.setFocusPainted(false);
        b.setFont(FONT_BASE.deriveFont(Font.BOLD));
        b.setBackground(new Color(20, 20, 20));
        b.setForeground(Color.WHITE);
        b.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void estiloBotonSecundario(JButton b) {
        b.setFocusPainted(false);
        b.setFont(FONT_BASE);
        b.setBackground(new Color(240, 240, 240));
        b.setForeground(Color.BLACK);
        b.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static JComponent panelVacio(String titulo, String subtitulo) {
        JPanel p = new JPanel();
        p.setBackground(BG);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));

        JLabel t = new JLabel(titulo);
        t.setFont(FONT_BASE.deriveFont(Font.BOLD, 18f));
        t.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel s = new JLabel(subtitulo);
        s.setFont(FONT_BASE);
        s.setForeground(Color.DARK_GRAY);
        s.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.add(Box.createVerticalGlue());
        p.add(t);
        p.add(Box.createVerticalStrut(10));
        p.add(s);
        p.add(Box.createVerticalGlue());

        return p;
    }
}
