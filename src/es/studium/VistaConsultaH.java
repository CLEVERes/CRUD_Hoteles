package es.studium;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextArea;

public class VistaConsultaH
{
	Color colorFondo = new Color(244, 246, 247);
	Color colorBotonActualizar = new Color(41, 128, 185);
	Color colorBotonExportar = new Color(39, 174, 96);
	Color colorTextoBoton = Color.WHITE;
	Color colorTextoEtiqueta = new Color(44, 62, 80);
	Color colorFondoTextArea = Color.WHITE;
	Color colorTextoTextArea = new Color(52, 73, 94);
	
	Font fuenteEtiquetas = new Font("Segoe UI", Font.BOLD, 13);
	Font fuenteBotones = new Font("Segoe UI", Font.BOLD, 12);
	Font fuenteConsola = new Font("Consolas", Font.PLAIN, 12);

	Frame ventana = new Frame("Consultas");
	TextArea txa = new TextArea(10, 10);
	Button btnActualizar = new Button("Actualizar");
	Button btnExportar = new Button("Exportar PDF");

	Dialog dlg = new Dialog(ventana, "Notificación", true);
	Label lblDlg = new Label("");

	GridBagConstraints gbc = new GridBagConstraints();

	public VistaConsultaH()
	{
		ventana.setLayout(new GridBagLayout());
		ventana.setBackground(colorFondo);

		txa.setFont(fuenteConsola);
		txa.setBackground(colorFondoTextArea);
		txa.setForeground(colorTextoTextArea);
		txa.setEditable(false);

		btnActualizar.setFont(fuenteBotones);
		btnActualizar.setBackground(colorBotonActualizar);
		btnActualizar.setForeground(colorTextoBoton);
		btnActualizar.setPreferredSize(new java.awt.Dimension(120, 32));

		btnExportar.setFont(fuenteBotones);
		btnExportar.setBackground(colorBotonExportar);
		btnExportar.setForeground(colorTextoBoton);
		btnExportar.setPreferredSize(new java.awt.Dimension(120, 32));

		gbc.insets = new Insets(15, 15, 15, 15);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		ventana.add(txa, gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		ventana.add(btnActualizar, gbc);

		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		ventana.add(btnExportar, gbc);

		ventana.setSize(650, 450);
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);

		dlg.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		dlg.setBackground(colorFondo);
		lblDlg.setFont(fuenteEtiquetas);
		lblDlg.setForeground(colorTextoEtiqueta);
		dlg.add(lblDlg);

		dlg.setSize(300, 120);
		dlg.setVisible(false);
		dlg.setResizable(false);
		dlg.setLocationRelativeTo(null);
	}
}