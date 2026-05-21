package es.studium;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControladorH extends WindowAdapter implements ActionListener, KeyListener
{
	private VistaLoginH vlh;
	private VistaMenuH vmph;
	private VistaAltaH vah;
	private VistaBajaH vbh;
	private VistaModificacionH vmodh;
	private VistaConsultaH vch;
	private ModeloH mh;

	private int ubicacion = 0;
	private int accion = 0;

	private String[] ubicacionLog =
	{ "Hotel", "Habitacion", "Huesped", "Historial" };
	private String[] accionLog =
	{ "Alta realizada", "Baja realizada", "Modificacion realizada", "Consulta realizada" };

	public ControladorH(ModeloH mh, VistaLoginH vlh)
	{
		this.mh = mh;
		this.vlh = vlh;

		this.vlh.ventana.addWindowListener(this);
		this.vlh.dlg.addWindowListener(this);

		this.vlh.btnAceptar.addActionListener(this);
		this.vlh.btnLimpiar.addActionListener(this);
		this.vlh.txfUsuario.addKeyListener(this);
		this.vlh.txfClave.addKeyListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{

		if (vlh != null)
		{
			if (e.getSource().equals(vlh.btnLimpiar))
			{
				vlh.txfUsuario.setText("");
				vlh.txfClave.setText("");
				return;
			}

			else if (e.getSource().equals(vlh.btnAceptar))
			{
				entrarLogin();
				ubicacion = 5;
				escribirLog(VistaMenuH.tipoUsuario, ubicacion, ubicacionLog, accion, accionLog);
			}
		}

		if (vmph != null)
		{
			if (e.getSource().equals(vmph.altaHotel))
			{
				accion = 1;
				ubicacion = 1;
				mh.tipoAlta = 1;
				crearVistaAlta();
				return;
			}

			else if (e.getSource().equals(vmph.altaHabitacion))
			{
				accion = 1;
				ubicacion = 2;
				mh.tipoAlta = 2;
				crearVistaAlta();
				this.vah.chcHoteles.addKeyListener(this);
				mh.tipoChoice = 1;
				Choice chcHoteles = vah.chcHoteles;
				vah.chcHoteles = mh.rellenarChc(chcHoteles);
				return;
			}

			else if (e.getSource().equals(vmph.altaHuesped))
			{
				accion = 1;
				ubicacion = 3;
				mh.tipoAlta = 3;
				crearVistaAlta();
				return;
			}

			else if (e.getSource().equals(vmph.altaHistorial))
			{
				accion = 1;
				ubicacion = 4;
				mh.tipoAlta = 4;
				crearVistaAlta();
				mh.tipoChoice = 2;
				vah.chcHabitaciones = mh.rellenarChc(vah.chcHabitaciones);
				mh.tipoChoice = 3;
				vah.chcHuespedes = mh.rellenarChc(vah.chcHuespedes);
				return;
			}

			else if (e.getSource().equals(vmph.bajaHotel))
			{
				accion = 2;
				ubicacion = 1;
				mh.tipoChoice = 1;
				crearVistaBaja();
			}

			else if (e.getSource().equals(vmph.bajaHabitacion))
			{
				accion = 2;
				ubicacion = 2;
				mh.tipoChoice = 2;
				crearVistaBaja();
			}

			else if (e.getSource().equals(vmph.bajaHuesped))
			{
				accion = 2;
				ubicacion = 3;
				mh.tipoChoice = 3;
				crearVistaBaja();
			}

			else if (e.getSource().equals(vmph.bajaHistorial))
			{
				accion = 2;
				ubicacion = 4;
				mh.tipoChoice = 4;
				crearVistaBaja();
			}

			else if (e.getSource().equals(vmph.modificacionHotel))
			{
				accion = 3;
				ubicacion = 1;
				mh.tipoChoice = 1;
				crearVistaModificacion();
				return;
			}

			else if (e.getSource().equals(vmph.modificacionHabitacion))
			{
				accion = 3;
				ubicacion = 2;
				mh.tipoChoice = 2;
				crearVistaModificacion();
				mh.tipoChoice = 1;
				vmodh.chcHoteles.addKeyListener(this);
				vmodh.chcHoteles = mh.rellenarChc(vmodh.chcHoteles);
				mh.tipoChoice = 2;
				return;
			}

			else if (e.getSource().equals(vmph.modificacionHuesped))
			{
				accion = 3;
				ubicacion = 3;
				mh.tipoChoice = 3;
				crearVistaModificacion();
				return;
			}

			else if (e.getSource().equals(vmph.modificacionHistorial))
			{
				accion = 3;
				ubicacion = 4;
				mh.tipoChoice = 4;
				crearVistaModificacion();
				mh.tipoChoice = 2;
				vmodh.chcHabitaciones.addKeyListener(this);
				vmodh.chcHabitaciones = mh.rellenarChc(vmodh.chcHabitaciones);
				mh.tipoChoice = 3;
				vmodh.chcHuespedes.addKeyListener(this);
				vmodh.chcHuespedes = mh.rellenarChc(vmodh.chcHuespedes);
				mh.tipoChoice = 4;
				return;
			}

			else if (e.getSource().equals(vmph.consultaHotel))
			{
				accion = 4;
				ubicacion = 1;
				mh.tipoConsulta = 1;
				crearVistaConsulta();
				
			}

			else if (e.getSource().equals(vmph.consultaHabitacion))
			{
				accion = 4;
				ubicacion = 2;
				mh.tipoConsulta = 2;
				crearVistaConsulta();
			}

			else if (e.getSource().equals(vmph.consultaHuesped))
			{
				accion = 4;
				ubicacion = 3;
				mh.tipoConsulta = 3;
				crearVistaConsulta();
			}

			else if (e.getSource().equals(vmph.consultaHistorial))
			{
				accion = 4;
				ubicacion = 4;
				mh.tipoConsulta = 4;
				crearVistaConsulta();
			}

		}

		if (vah != null)
		{
			if (e.getSource().equals(vah.btnLimpiar))
			{
				vah.txf1.setText("");
				vah.txf2.setText("");
				return;
			}

			else if (e.getSource().equals(vah.btnAceptar))
			{

				aceptarAlta();
			}
		}

		if (vbh != null)
		{
			if (e.getSource().equals(vbh.btnEliminar))
			{
				if (vbh.chcBajas.getSelectedIndex() > 0)
				{
					vbh.dlgConfirmacion.setVisible(true);
				}
				return;
			}

			else if (e.getSource().equals(vbh.btnSi))
			{
				mh.realizarBaja(vbh.chcBajas);
				vbh.dlgConfirmacion.dispose();

				if (mh.error == true)
				{
					vbh.lblDlg.setText("Error al realizar baja");
					vbh.dlg.setVisible(true);
					mh.error = false;
				}

				else
				{
					vbh.lblDlg.setText("Baja completada");
					vbh.dlg.setVisible(true);
					vbh.chcBajas = mh.rellenarChc(vbh.chcBajas);
					escribirLog(VistaMenuH.tipoUsuario, ubicacion, ubicacionLog, accion, accionLog);
				}

				return;
			}

			else if (e.getSource().equals(vbh.btnNo))
			{
				vbh.dlgConfirmacion.dispose();
				return;
			}
		}

		if (vmodh != null)
		{
			if (e.getSource().equals(vmodh.btnEditar))
			{
				if (!vmodh.chcModificacion.getSelectedItem().split(" - ")[2].equals("Sin fecha de salida"))
				{
					vmodh.txf1.setText(vmodh.chcModificacion.getSelectedItem().split(" - ")[1]);
					vmodh.txf2.setText(vmodh.chcModificacion.getSelectedItem().split(" - ")[2]);

				} else
				{
					vmodh.txf1.setText(vmodh.chcModificacion.getSelectedItem().split(" - ")[1]);
					vmodh.txf2.setText("");
				}
				vmodh.dlgEdicion.setVisible(true);
				return;
			}

			else if (e.getSource().equals(vmodh.btnLimpiar))
			{
				vmodh.txf1.setText("");
				vmodh.txf2.setText("");
				return;
			}

			else if (e.getSource().equals(vmodh.btnAceptar))
			{
				if (mh.tipoChoice == 1)
				{
					mh.fk[0] = Integer.parseInt(vmodh.chcHoteles.getSelectedItem().split(" - ")[0]);
				}

				else if (mh.tipoChoice == 4)
				{
					mh.fecha[0] = mh.fechaSQL(vmodh.txf1.getText());
					mh.fecha[1] = mh.fechaSQL(vmodh.txf2.getText());
					mh.fk[0] = Integer.parseInt(vmodh.chcHabitaciones.getSelectedItem().split(" - ")[0]);
					mh.fk[1] = Integer.parseInt(vmodh.chcHuespedes.getSelectedItem().split(" - ")[0]);
				}

				aceptarModificacion();
			}

			else if (e.getSource().equals(vmodh.btnSi))
			{
				mh.realizarModificacion(vmodh);
				vmodh.dlgConfirmacion.dispose();

				if (mh.error == true)
				{
					vmodh.lblDlg.setText("Error al realizar Modificacion");
					vmodh.dlg.setVisible(true);
					mh.error = false;
				}

				else
				{
					vmodh.lblDlg.setText("Modificacion completada");
					vmodh.dlg.setVisible(true);
					vmodh.chcModificacion = mh.rellenarChc(vmodh.chcModificacion);
					escribirLog(VistaMenuH.tipoUsuario, ubicacion, ubicacionLog, accion, accionLog);
				}
				return;
			}

			else if (e.getSource().equals(vmodh.btnNo))
			{
				vmodh.dlgConfirmacion.dispose();
				return;
			}
		}

		if (vch != null)
		{
			escribirLog(VistaMenuH.tipoUsuario, ubicacion, ubicacionLog, accion, accionLog);
			
			if (e.getSource().equals(vch.btnActualizar))
			{
				mh.realizarConsulta(vch.txa);
				if (mh.error == true)
				{
					vch.lblDlg.setText("Se ha producido un error");
					vch.dlg.setVisible(true);
					mh.error = false;
				}
				else
				{
					escribirLog(VistaMenuH.tipoUsuario, ubicacion, ubicacionLog, accion, accionLog);
				}
				return;
			}
			
			else if (e.getSource().equals(vch.btnExportar))
			{
				mh.exportarPDF(mh.tipoConsulta);
			}
		}
	}

	public void windowClosing(WindowEvent e)
	{
		if (vlh != null && e.getSource().equals(vlh.ventana))
		{
			ubicacion = 6;
			escribirLog(VistaMenuH.tipoUsuario, ubicacion, ubicacionLog, accion, accionLog);
			System.exit(0);
		}

		else if (vlh != null && e.getSource().equals(vlh.dlg))
		{
			vlh.dlg.dispose();
		}

		else if (vmph != null && e.getSource().equals(vmph.ventana))
		{
			vlh.ventana.setVisible(true);
			vmph.ventana.dispose();
			this.vmph = null;
		}

		else if (vah != null && e.getSource().equals(vah.ventana))
		{
			vmph.ventana.setVisible(true);
			vah.ventana.dispose();
			this.vah = null;
		}

		else if (vah != null && e.getSource().equals(vah.dlg))
		{
			vah.dlg.dispose();
		}

		else if (vbh != null && e.getSource().equals(vbh.ventana))
		{
			vmph.ventana.setVisible(true);
			vbh.ventana.dispose();
			this.vbh = null;
		}

		else if (vbh != null && e.getSource().equals(vbh.dlgConfirmacion))
		{
			vbh.dlgConfirmacion.dispose();
		}

		else if (vbh != null && e.getSource().equals(vbh.dlg))
		{
			vbh.dlg.dispose();
		}

		else if (vmodh != null && e.getSource().equals(vmodh.ventana))
		{
			vmph.ventana.setVisible(true);
			vmodh.ventana.dispose();
			this.vmodh = null;
		}

		else if (vmodh != null && e.getSource().equals(vmodh.dlgEdicion))
		{
			vmodh.dlgEdicion.dispose();
		}

		else if (vmodh != null && e.getSource().equals(vmodh.dlgConfirmacion))
		{
			vmodh.dlgConfirmacion.dispose();
		}

		else if (vmodh != null && e.getSource().equals(vmodh.dlg))
		{
			vmodh.dlg.dispose();
		}

		else if (vch != null && e.getSource().equals(vch.ventana))
		{
			vmph.ventana.setVisible(true);
			vch.ventana.dispose();
			this.vch = null;
		}

		else if (vch != null && e.getSource().equals(vch.dlg))
		{
			vch.dlg.dispose();
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if (e.getSource() == vlh.txfUsuario || e.getSource() == vlh.txfClave)
			{
				entrarLogin();
				escribirLog(VistaMenuH.tipoUsuario, ubicacion, ubicacionLog, accion, accionLog);
			}

			else if (vah != null
					&& (e.getSource() == vah.txf1 || e.getSource() == vah.txf2 || e.getSource() == vah.chcHoteles))
			{
				mh.txf[0] = vah.txf1.getText();
				mh.txf[1] = vah.txf2.getText();

				if (mh.tipoAlta == 2)
				{
					if (vah.chcHoteles.getSelectedIndex() > 0)
					{
						mh.fk[0] = Integer.parseInt(vah.chcHoteles.getSelectedItem().split(" - ")[0]);
					}
				}

				mh.realizarAlta();

				if (mh.error == true)
				{
					vah.lblDlg.setText("Error al insertar datos");
					vah.dlg.setVisible(true);
					mh.error = false;
				} else
				{
					vah.lblDlg.setText("Alta correcta");
					vah.dlg.setVisible(true);
					escribirLog(VistaMenuH.tipoUsuario, ubicacion, ubicacionLog, accion, accionLog);
				}
				return;
			}

			else if (vmodh != null && (e.getSource() == vmodh.txf1 || e.getSource() == vmodh.txf2))
			{
				vmodh.dlgEdicion.dispose();
				vmodh.dlgConfirmacion.setVisible(true);
				return;
			}
		}
	}

	public void keyReleased(KeyEvent e)
	{
	}

	public void entrarLogin()
	{
		String nombreUsuario = vlh.txfUsuario.getText();
		String claveUsuario = vlh.txfClave.getText();

		int tipoUsuario = mh.comprobarCredenciales(nombreUsuario, claveUsuario);

		if (tipoUsuario == -1)
		{
			vlh.dlg.setTitle("ERROR");
			vlh.lblDlg.setText("CREDENCIALES INCORRECTAS");
			vlh.dlg.setVisible(true);
		}

		else if (tipoUsuario >= 0)
		{
			vlh.txfUsuario.setText("");
			vlh.txfClave.setText("");
			vlh.ventana.setVisible(false);

			VistaMenuH.tipoUsuario = tipoUsuario;
			this.vmph = new VistaMenuH();
			this.vmph.ventana.addWindowListener(this);

			this.vmph.altaHotel.addActionListener(this);
			this.vmph.altaHabitacion.addActionListener(this);
			this.vmph.altaHuesped.addActionListener(this);
			this.vmph.altaHistorial.addActionListener(this);

			this.vmph.bajaHotel.addActionListener(this);
			this.vmph.bajaHabitacion.addActionListener(this);
			this.vmph.bajaHuesped.addActionListener(this);
			this.vmph.bajaHistorial.addActionListener(this);

			this.vmph.modificacionHotel.addActionListener(this);
			this.vmph.modificacionHabitacion.addActionListener(this);
			this.vmph.modificacionHuesped.addActionListener(this);
			this.vmph.modificacionHistorial.addActionListener(this);

			this.vmph.consultaHotel.addActionListener(this);
			this.vmph.consultaHabitacion.addActionListener(this);
			this.vmph.consultaHuesped.addActionListener(this);
			this.vmph.consultaHistorial.addActionListener(this);
		}
		return;
	}

	public void aceptarAlta()
	{
		mh.txf[0] = vah.txf1.getText();
		mh.txf[1] = vah.txf2.getText();

		if (mh.tipoAlta == 2)
		{
			if (vah.chcHoteles.getSelectedIndex() > 0)
			{
				mh.fk[0] = Integer.parseInt(vah.chcHoteles.getSelectedItem().split(" - ")[0]);
			}
		}

		else if (mh.tipoAlta == 4)
		{
			mh.fecha[0] = mh.fechaSQL(mh.txf[0]);
			mh.fecha[1] = mh.fechaSQL(mh.txf[1]);

			if (vah.chcHabitaciones.getSelectedIndex() > 0)
			{
				mh.fk[0] = Integer.parseInt(vah.chcHabitaciones.getSelectedItem().split(" - ")[0]);
			}

			if (vah.chcHuespedes.getSelectedIndex() > 0)
			{
				mh.fk[1] = Integer.parseInt(vah.chcHuespedes.getSelectedItem().split(" - ")[0]);
			}
		}

		mh.realizarAlta();

		if (mh.error == true)
		{
			vah.lblDlg.setText("Error al insertar datos");
			vah.dlg.setVisible(true);
			mh.error = false;
		} else
		{
			vah.lblDlg.setText("Alta correcta");
			vah.dlg.setVisible(true);
			escribirLog(VistaMenuH.tipoUsuario, ubicacion, ubicacionLog, accion, accionLog);
		}
		return;
	}

	public void aceptarModificacion()
	{
		vmodh.dlgEdicion.dispose();
		vmodh.dlgConfirmacion.setVisible(true);
		return;
	}

	public void crearVistaAlta()
	{
		vmph.ventana.setVisible(false);
		this.vah = new VistaAltaH(mh.tipoAlta);
		this.vah.ventana.addWindowListener(this);
		this.vah.txf1.addKeyListener(this);
		this.vah.txf2.addKeyListener(this);
		this.vah.dlg.addWindowListener(this);
		this.vah.btnAceptar.addActionListener(this);
		this.vah.btnLimpiar.addActionListener(this);
	}

	public void crearVistaBaja()
	{
		vmph.ventana.setVisible(false);
		this.vbh = new VistaBajaH();
		this.vbh.ventana.addWindowListener(this);
		this.vbh.dlgConfirmacion.addWindowListener(this);
		this.vbh.dlg.addWindowListener(this);
		this.vbh.btnEliminar.addActionListener(this);
		this.vbh.btnSi.addActionListener(this);
		this.vbh.btnNo.addActionListener(this);
		Choice chcBajas = vbh.chcBajas;
		vbh.chcBajas = mh.rellenarChc(chcBajas);
		return;
	}

	public void crearVistaModificacion()
	{
		vmph.ventana.setVisible(false);
		this.vmodh = new VistaModificacionH(mh.tipoChoice);
		this.vmodh.ventana.addWindowListener(this);
		this.vmodh.dlgEdicion.addWindowListener(this);
		this.vmodh.txf1.addKeyListener(this);
		this.vmodh.txf2.addKeyListener(this);
		this.vmodh.dlgConfirmacion.addWindowListener(this);
		this.vmodh.dlg.addWindowListener(this);
		this.vmodh.btnEditar.addActionListener(this);
		this.vmodh.btnAceptar.addActionListener(this);
		this.vmodh.btnLimpiar.addActionListener(this);
		this.vmodh.btnSi.addActionListener(this);
		this.vmodh.btnNo.addActionListener(this);
		Choice chcModificacion = vmodh.chcModificacion;
		vmodh.chcModificacion = mh.rellenarChc(chcModificacion);

	}

	public void crearVistaConsulta()
	{
		vmph.ventana.setVisible(false);
		this.vch = new VistaConsultaH();
		this.vch.ventana.addWindowListener(this);
		this.vch.dlg.addWindowListener(this);
		this.vch.btnActualizar.addActionListener(this);
		this.vch.btnExportar.addActionListener(this);
		mh.realizarConsulta(vch.txa);
		if (mh.error == true)
		{
			vch.lblDlg.setText("Se ha producido un error");
			vch.dlg.setVisible(true);
			mh.error = false;
		}
		return;
	}

	public void escribirLog(int Usuario, int ubi, String[] ubiLog, int acc, String[] accLog)
	{
		SimpleDateFormat formateadorLog = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String ahora = formateadorLog.format(new Date());
		String usuario = "";
		String informacion = "";

		if (Usuario == 0)
		{
			usuario = "basico";
		}

		else if (Usuario == 1)
		{
			usuario = "admin";
		}

		if (ubi == 5)
		{
			informacion = "Inicio de sesion";
		}

		else if (ubi == 6)
		{
			informacion = "Fin de sesion";
		}

		else
		{
			for (int i = 0; i < accLog.length; i++)
			{
				for (int j = 0; j < ubiLog.length; j++)
				{
					if (acc == (i+1) && ubi == (j+1))
					{
						informacion = accLog[i] + " en: " + ubiLog[j];
					}
				}

			}

		}

		try
		{
			FileWriter fileW = new FileWriter("FicheroLog.txt", true);
			BufferedWriter bufferedW = new BufferedWriter(fileW);
			PrintWriter printW = new PrintWriter(bufferedW);

			printW.println("[" + ahora + "]" + "[" + usuario + "]" + "[" + informacion + "]");

			printW.close();
			bufferedW.close();
			fileW.close();
		}

		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}