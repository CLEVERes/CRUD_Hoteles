package es.studium;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

public class VistaMenuH
{
	Color colorFondo = new Color(244, 246, 247);
	Font fuenteMenu = new Font("Segoe UI", Font.PLAIN, 12);

	public static int tipoUsuario;

	Frame ventana = new Frame("Menú Principal");

	MenuBar barraMenu = new MenuBar();
	Menu mnHotel = new Menu("Hoteles");
	Menu mnHabitacion = new Menu("Habitaciones");
	Menu mnHuesped = new Menu("Huéspedes");
	Menu mnHistorial = new Menu("Historial");

	MenuItem altaHotel = new MenuItem("Altas");
	MenuItem bajaHotel = new MenuItem("Bajas");
	MenuItem modificacionHotel = new MenuItem("Modificaciones");
	MenuItem consultaHotel = new MenuItem("Consultas");

	MenuItem altaHabitacion = new MenuItem("Altas");
	MenuItem bajaHabitacion = new MenuItem("Bajas");
	MenuItem modificacionHabitacion = new MenuItem("Modificaciones");
	MenuItem consultaHabitacion = new MenuItem("Consultas");

	MenuItem altaHuesped = new MenuItem("Altas");
	MenuItem bajaHuesped = new MenuItem("Bajas");
	MenuItem modificacionHuesped = new MenuItem("Modificaciones");
	MenuItem consultaHuesped = new MenuItem("Consultas");

	MenuItem altaHistorial = new MenuItem("Altas");
	MenuItem bajaHistorial = new MenuItem("Bajas");
	MenuItem modificacionHistorial = new MenuItem("Modificaciones");
	MenuItem consultaHistorial = new MenuItem("Consultas");

	public VistaMenuH()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setBackground(colorFondo);

		ventana.setMenuBar(barraMenu);

		mnHotel.setFont(fuenteMenu);
		mnHabitacion.setFont(fuenteMenu);
		mnHuesped.setFont(fuenteMenu);
		mnHistorial.setFont(fuenteMenu);

		mnHotel.add(altaHotel);
		mnHabitacion.add(altaHabitacion);
		mnHuesped.add(altaHuesped);
		mnHistorial.add(altaHistorial);

		if (tipoUsuario == 1)
		{
			mnHotel.add(bajaHotel);
			mnHotel.add(modificacionHotel);
			mnHotel.add(consultaHotel);

			mnHabitacion.add(bajaHabitacion);
			mnHabitacion.add(modificacionHabitacion);
			mnHabitacion.add(consultaHabitacion);

			mnHuesped.add(bajaHuesped);
			mnHuesped.add(modificacionHuesped);
			mnHuesped.add(consultaHuesped);

			mnHistorial.add(bajaHistorial);
			mnHistorial.add(modificacionHistorial);
			mnHistorial.add(consultaHistorial);
		}

		barraMenu.add(mnHotel);
		barraMenu.add(mnHabitacion);
		barraMenu.add(mnHuesped);
		barraMenu.add(mnHistorial);

		ventana.setSize(400, 250);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
}