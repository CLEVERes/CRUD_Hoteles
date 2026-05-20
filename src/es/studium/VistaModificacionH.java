package es.studium;

import java.awt.*;

public class VistaModificacionH {
    Color colorFondo = new Color(244, 246, 247);
    Color colorBotonAceptar = new Color(41, 128, 185);
    Color colorBotonEditar = new Color(39, 174, 96);
    Color colorBotonLimpiar = new Color(149, 165, 166);
    Color colorTextoBoton = Color.WHITE;
    Color colorTextoEtiqueta = new Color(44, 62, 80);

    Font fuenteEtiquetas = new Font("Segoe UI", Font.BOLD, 13);
    Font fuenteCampos = new Font("Segoe UI", Font.PLAIN, 12);
    Font fuenteBotones = new Font("Segoe UI", Font.BOLD, 12);

    public Frame ventana = new Frame("Modificación");
    public Choice chcModificacion = new Choice();
    public Choice chcHoteles = new Choice();
    public Choice chcHabitaciones = new Choice();
    public Choice chcHuespedes = new Choice();
    public Button btnEditar = new Button("Editar");

    public Dialog dlgEdicion = new Dialog(ventana, "Edición", true);
    public Label lblFecha = new Label("Formato: DD/MM/AAAA", Label.CENTER);
    public Label lbl1 = new Label("");
    public TextField txf1 = new TextField(20);
    public Label lbl2 = new Label("");
    public TextField txf2 = new TextField(20);
    public Button btnAceptar = new Button("Aceptar");
    public Button btnLimpiar = new Button("Limpiar");

    public Dialog dlgConfirmacion = new Dialog(ventana, "Confirmación", true);
    Label lblConfirmacion = new Label("¿Deseas continuar con la modificación?");
    public Button btnSi = new Button("Sí");
    public Button btnNo = new Button("No");

    public Dialog dlg = new Dialog(ventana, "Notificación", true);
    public Label lblDlg = new Label("");

    GridBagConstraints gbc = new GridBagConstraints();

    public VistaModificacionH(int tipoModificacion) {
        ventana.setLayout(new GridBagLayout());
        ventana.setBackground(colorFondo);

        chcModificacion.setFont(fuenteCampos);
        btnEditar.setFont(fuenteBotones);
        btnEditar.setBackground(colorBotonEditar);
        btnEditar.setForeground(colorTextoBoton);

        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0;
        ventana.add(chcModificacion, gbc);

        gbc.gridy = 1;
        btnEditar.setPreferredSize(new Dimension(120, 32));
        ventana.add(btnEditar, gbc);

        dlgEdicion.setLayout(new GridBagLayout());
        dlgEdicion.setBackground(colorFondo);
        
        lbl1.setFont(fuenteEtiquetas);
        lbl1.setForeground(colorTextoEtiqueta);
        lbl2.setFont(fuenteEtiquetas);
        lbl2.setForeground(colorTextoEtiqueta);
        txf1.setFont(fuenteCampos);
        txf2.setFont(fuenteCampos);
        lblFecha.setFont(fuenteEtiquetas);
        lblFecha.setForeground(colorTextoEtiqueta);
        
        btnAceptar.setFont(fuenteBotones);
        btnAceptar.setBackground(colorBotonAceptar);
        btnAceptar.setForeground(colorTextoBoton);
        btnLimpiar.setFont(fuenteBotones);
        btnLimpiar.setBackground(colorBotonLimpiar);
        btnLimpiar.setForeground(colorTextoBoton);

        gbc.insets = new Insets(10, 12, 10, 12);
        int filaBotones = 3;

        if (tipoModificacion == 1) {
            lbl1.setText("Nombre del Hotel:");
            lbl2.setText("Dirección del Hotel:");
            agregarComponentesBase();
            filaBotones = 3;
            dlgEdicion.setSize(450, 220);
        } 
        else if (tipoModificacion == 2) {
            lbl1.setText("Número Habitación:");
            lbl2.setText("Precio:");
            agregarComponentesBase();
            gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
            dlgEdicion.add(chcHoteles, gbc);
            filaBotones = 4;
            dlgEdicion.setSize(450, 280);
        } 
        else if (tipoModificacion == 3) {
            lbl1.setText("Nombre Huésped:");
            lbl2.setText("Teléfono:");
            agregarComponentesBase();
            filaBotones = 3;
            dlgEdicion.setSize(450, 220);
        } 
        else if (tipoModificacion == 4) {
            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
            dlgEdicion.add(lblFecha, gbc);
            lbl1.setText("Fecha Entrada:");
            lbl2.setText("Fecha Salida:");
            agregarComponentesBase();
            gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
            dlgEdicion.add(chcHabitaciones, gbc);
            gbc.gridy = 4;
            dlgEdicion.add(chcHuespedes, gbc);
            filaBotones = 5;
            dlgEdicion.setSize(450, 320);
        }

        gbc.gridwidth = 1;
        gbc.gridy = filaBotones;
        gbc.gridx = 0;
        dlgEdicion.add(btnAceptar, gbc);
        gbc.gridx = 1;
        dlgEdicion.add(btnLimpiar, gbc);

        dlgConfirmacion.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
        dlgConfirmacion.setBackground(colorFondo);
        lblConfirmacion.setFont(fuenteEtiquetas);
        lblConfirmacion.setForeground(colorTextoEtiqueta);
        dlgConfirmacion.add(lblConfirmacion);
        
        btnSi.setFont(fuenteBotones);
        btnSi.setBackground(colorBotonAceptar);
        btnSi.setForeground(colorTextoBoton);
        btnSi.setPreferredSize(new Dimension(80, 28));
        dlgConfirmacion.add(btnSi);
        
        btnNo.setFont(fuenteBotones);
        btnNo.setBackground(colorBotonLimpiar);
        btnNo.setForeground(colorTextoBoton);
        btnNo.setPreferredSize(new Dimension(80, 28));
        dlgConfirmacion.add(btnNo);

        dlg.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        dlg.setBackground(colorFondo);
        lblDlg.setFont(fuenteEtiquetas);
        lblDlg.setForeground(colorTextoEtiqueta);
        dlg.add(lblDlg);

        ventana.setVisible(true);
        ventana.setSize(450, 180);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        
        dlgEdicion.setResizable(false);
        dlgEdicion.setLocationRelativeTo(ventana);
        dlgConfirmacion.setSize(380, 160);
        dlgConfirmacion.setResizable(false);
        dlgConfirmacion.setLocationRelativeTo(ventana);
        dlg.setSize(300, 120);
        dlg.setResizable(false);
        dlg.setLocationRelativeTo(ventana);
    }

    private void agregarComponentesBase() {
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        dlgEdicion.add(lbl1, gbc);
        gbc.gridx = 1;
        dlgEdicion.add(txf1, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        dlgEdicion.add(lbl2, gbc);
        gbc.gridx = 1;
        dlgEdicion.add(txf2, gbc);
    }
}