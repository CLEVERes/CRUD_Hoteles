package es.studium;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;

public class VistaAltaH
{
	Color colorFondo = new Color(244, 246, 247);
	Color colorBotonAceptar = new Color(41, 128, 185);
	Color colorBotonLimpiar = new Color(149, 165, 166);
	Color colorTextoBoton = Color.WHITE;
	Color colorTextoEtiqueta = new Color(44, 62, 80);

	Font fuenteEtiquetas = new Font("Segoe UI", Font.BOLD, 13);
	Font fuenteCampos = new Font("Segoe UI", Font.PLAIN, 12);
	Font fuenteBotones = new Font("Segoe UI", Font.BOLD, 12);

	public Frame ventana = new Frame("Altas");

	public Label lbl1 = new Label("");
	public TextField txf1 = new TextField(20);

	public Label lbl2 = new Label("");
	public TextField txf2 = new TextField(20);

	public Label lblFecha = new Label("Formato: Dia/Mes/Año", Label.CENTER);

	public Button btnAceptar = new Button("Aceptar");
	public Button btnLimpiar = new Button("Limpiar");

	public Choice chcHoteles = new Choice();
	public Choice chcHabitaciones = new Choice();
	public Choice chcHuespedes = new Choice();

	public Dialog dlg = new Dialog(ventana, "Notificación", true);
	public Label lblDlg = new Label("");

	GridBagConstraints gbc = new GridBagConstraints();

	public VistaAltaH(int tipoAlta)
	{
		ventana.setLayout(new GridBagLayout());
		ventana.setBackground(colorFondo);

		lbl1.setFont(fuenteEtiquetas);
		lbl1.setForeground(colorTextoEtiqueta);
		lbl2.setFont(fuenteEtiquetas);
		lbl2.setForeground(colorTextoEtiqueta);
		lblFecha.setFont(fuenteEtiquetas);
		lblFecha.setForeground(colorTextoEtiqueta);

		txf1.setFont(fuenteCampos);
		txf2.setFont(fuenteCampos);

		btnAceptar.setFont(fuenteBotones);
		btnAceptar.setBackground(colorBotonAceptar);
		btnAceptar.setForeground(colorTextoBoton);

		btnLimpiar.setFont(fuenteBotones);
		btnLimpiar.setBackground(colorBotonLimpiar);
		btnLimpiar.setForeground(colorTextoBoton);

		chcHoteles.setFont(fuenteCampos);
		chcHabitaciones.setFont(fuenteCampos);
		chcHuespedes.setFont(fuenteCampos);

		gbc.insets = new Insets(12, 12, 12, 12);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;

		if (tipoAlta == 1)
		{
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			lbl1.setText("Nombre del hotel:");
			ventana.add(lbl1, gbc);

			gbc.gridx = 1;
			gbc.gridy = 0;
			ventana.add(txf1, gbc);

			gbc.gridx = 0;
			gbc.gridy = 1;
			lbl2.setText("Dirección del hotel:");
			ventana.add(lbl2, gbc);

			gbc.gridx = 1;
			gbc.gridy = 1;
			ventana.add(txf2, gbc);

			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 1;
			ventana.add(btnAceptar, gbc);

			gbc.gridx = 1;
			gbc.gridy = 2;
			ventana.add(btnLimpiar, gbc);

			ventana.setSize(450, 220);
		}

		else if (tipoAlta == 2)
		{
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			lbl1.setText("Número de habitación:");
			ventana.add(lbl1, gbc);

			gbc.gridx = 1;
			gbc.gridy = 0;
			ventana.add(txf1, gbc);

			gbc.gridx = 0;
			gbc.gridy = 1;
			lbl2.setText("Precio de habitación (€):");
			ventana.add(lbl2, gbc);

			gbc.gridx = 1;
			gbc.gridy = 1;
			ventana.add(txf2, gbc);

			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 2;
			ventana.add(chcHoteles, gbc);

			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 1;
			ventana.add(btnAceptar, gbc);

			gbc.gridx = 1;
			gbc.gridy = 3;
			ventana.add(btnLimpiar, gbc);

			ventana.setSize(450, 270);
		}

		else if (tipoAlta == 3)
		{
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			lbl1.setText("Nombre del huésped:");
			ventana.add(lbl1, gbc);

			gbc.gridx = 1;
			gbc.gridy = 0;
			ventana.add(txf1, gbc);

			gbc.gridx = 0;
			gbc.gridy = 1;
			lbl2.setText("Teléfono del huésped:");
			ventana.add(lbl2, gbc);

			gbc.gridx = 1;
			gbc.gridy = 1;
			ventana.add(txf2, gbc);

			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 1;
			ventana.add(btnAceptar, gbc);

			gbc.gridx = 1;
			gbc.gridy = 2;
			ventana.add(btnLimpiar, gbc);

			ventana.setSize(450, 220);
		}

		else if (tipoAlta == 4)
		{
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 2; 
			ventana.add(lblFecha, gbc);

			gbc.gridwidth = 1;

			gbc.gridx = 0;
			gbc.gridy = 1;
			lbl1.setText("Fecha de Entrada:");
			ventana.add(lbl1, gbc);

			gbc.gridx = 1;
			gbc.gridy = 1;
			ventana.add(txf1, gbc);

			gbc.gridx = 0;
			gbc.gridy = 2;
			lbl2.setText("Fecha de Salida:");
			ventana.add(lbl2, gbc);

			gbc.gridx = 1;
			gbc.gridy = 2;
			ventana.add(txf2, gbc);

			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 2;
			ventana.add(chcHabitaciones, gbc);

			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.gridwidth = 2;
			ventana.add(chcHuespedes, gbc);

			gbc.gridwidth = 1;
			gbc.gridx = 0;
			gbc.gridy = 5;
			ventana.add(btnAceptar, gbc);

			gbc.gridx = 1;
			gbc.gridy = 5;
			ventana.add(btnLimpiar, gbc);

			ventana.setSize(450, 330);
		}

		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);

		dlg.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		dlg.setBackground(colorFondo);
		lblDlg.setFont(fuenteEtiquetas);
		lblDlg.setForeground(colorTextoEtiqueta);
		dlg.add(lblDlg);

		dlg.setSize(280, 110);
		dlg.setVisible(false);
		dlg.setResizable(false);
		dlg.setLocationRelativeTo(null);
	}
}