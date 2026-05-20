package es.studium;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Label;

public class VistaBajaH
{
	Color colorFondo = new Color(244, 246, 247);
	Color colorBotonPeligro = new Color(192, 57, 43);
	Color colorBotonNeutro = new Color(149, 165, 166);
	Color colorBotonAceptar = new Color(41, 128, 185);
	Color colorTextoBoton = Color.WHITE;
	Color colorTextoEtiqueta = new Color(44, 62, 80);
	
	Font fuenteEtiquetas = new Font("Segoe UI", Font.BOLD, 13);
	Font fuenteCampos = new Font("Segoe UI", Font.PLAIN, 12);
	Font fuenteBotones = new Font("Segoe UI", Font.BOLD, 12);

	Frame ventana = new Frame("Bajas");

	Choice chcBajas = new Choice();
	Button btnEliminar = new Button("Eliminar");

	Dialog dlgConfirmacion = new Dialog(ventana, "Confirmación", true);
	Label lblConfirmacion = new Label("¿Deseas continuar con la eliminación?");
	Button btnSi = new Button("Sí");
	Button btnNo = new Button("No");

	Dialog dlg = new Dialog(ventana, "Notificación", true);
	Label lblDlg = new Label("");

	GridBagConstraints gbc = new GridBagConstraints();

	public VistaBajaH()
	{
		ventana.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 35));
		ventana.setBackground(colorFondo);

		chcBajas.setFont(fuenteCampos);
		ventana.add(chcBajas);
		
		btnEliminar.setFont(fuenteBotones);
		btnEliminar.setBackground(colorBotonPeligro);
		btnEliminar.setForeground(colorTextoBoton);
		btnEliminar.setPreferredSize(new java.awt.Dimension(100, 30));
		ventana.add(btnEliminar);

		ventana.setSize(450, 170);
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);

		dlgConfirmacion.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
		dlgConfirmacion.setBackground(colorFondo);

		lblConfirmacion.setFont(fuenteEtiquetas);
		lblConfirmacion.setForeground(colorTextoEtiqueta);
		dlgConfirmacion.add(lblConfirmacion);
		
		btnSi.setFont(fuenteBotones);
		btnSi.setBackground(colorBotonAceptar);
		btnSi.setForeground(colorTextoBoton);
		btnSi.setPreferredSize(new java.awt.Dimension(80, 28));
		dlgConfirmacion.add(btnSi);
		
		btnNo.setFont(fuenteBotones);
		btnNo.setBackground(colorBotonNeutro);
		btnNo.setForeground(colorTextoBoton);
		btnNo.setPreferredSize(new java.awt.Dimension(80, 28));
		dlgConfirmacion.add(btnNo);

		dlgConfirmacion.setSize(350, 160);
		dlgConfirmacion.setVisible(false);
		dlgConfirmacion.setResizable(false);
		dlgConfirmacion.setLocationRelativeTo(null);

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