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
import java.awt.TextField;

public class VistaLoginH
{
	Color colorFondo = new Color(244, 246, 247);
	Color colorBotonAceptar = new Color(41, 128, 185);
	Color colorBotonLimpiar = new Color(149, 165, 166);
	Color colorTextoBoton = Color.WHITE;
	Color colorTextoEtiqueta = new Color(44, 62, 80);
	
	Font fuenteEtiquetas = new Font("Segoe UI", Font.BOLD, 13);
	Font fuenteCampos = new Font("Segoe UI", Font.PLAIN, 12);
	Font fuenteBotones = new Font("Segoe UI", Font.BOLD, 12);

	Frame ventana = new Frame("Login");
	
	Label lblUsuario = new Label("Usuario");
	TextField txfUsuario = new TextField(20);
	
	Label lblClave = new Label("Clave");
	TextField txfClave = new TextField(20);
	
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");

	Dialog dlg = new Dialog(ventana, "Error de Login", true);
	Label lblDlg = new Label("");

	GridBagConstraints gbc = new GridBagConstraints();
	
	public VistaLoginH()
	{
		ventana.setLayout(new GridBagLayout());
		ventana.setBackground(colorFondo);

		lblUsuario.setFont(fuenteEtiquetas);
		lblUsuario.setForeground(colorTextoEtiqueta);
		lblClave.setFont(fuenteEtiquetas);
		lblClave.setForeground(colorTextoEtiqueta);
		
		txfUsuario.setFont(fuenteCampos);
		txfClave.setFont(fuenteCampos);
		
		btnAceptar.setFont(fuenteBotones);
		btnAceptar.setBackground(colorBotonAceptar);
		btnAceptar.setForeground(colorTextoBoton);
		
		btnLimpiar.setFont(fuenteBotones);
		btnLimpiar.setBackground(colorBotonLimpiar);
		btnLimpiar.setForeground(colorTextoBoton);

		gbc.insets = new Insets(12, 12, 12, 12);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		ventana.add(lblUsuario, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		ventana.add(txfUsuario, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		ventana.add(lblClave, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		ventana.add(txfClave, gbc);
		txfClave.setEchoChar('*');

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		ventana.add(btnAceptar, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		ventana.add(btnLimpiar, gbc);
		
		ventana.setSize(420, 210);
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		
		dlg.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
		dlg.setBackground(colorFondo);
		lblDlg.setFont(fuenteEtiquetas);
		lblDlg.setForeground(new Color(192, 57, 43));
		dlg.add(lblDlg);
		
		dlg.setSize(300, 120);
		dlg.setResizable(false);
		dlg.setLocationRelativeTo(null);
		dlg.setVisible(false);
	}
}