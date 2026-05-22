package es.studium;

import java.awt.Choice;
import java.awt.Desktop;
import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

public class ModeloH
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/practicaHoteles";
	String user = "userHoteles";
	String password = "1234";
	String sentenciaSQL = "";

	Connection connection = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	String[] txf = new String[4];
	int tipoAlta;
	int tipoChoice;
	int tipoConsulta;
	int[] fk = new int[2];
	LocalDate[] fecha = new LocalDate[2];
	boolean error;

	public ModeloH()
	{
		try
		{
			Class.forName(driver);
		}

		catch (ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}

	public int comprobarCredenciales(String nombreUsuario, String claveUsuario)
	{
		int tipoUsuario = -1;

		try
		{
			connection = DriverManager.getConnection(url, user, password);
			sentenciaSQL = "SELECT * FROM usuarios WHERE nombreUsuario = ? AND claveUsuario = SHA2(?, 256)";

			preparedStatement = connection.prepareStatement(sentenciaSQL);
			preparedStatement.setString(1, nombreUsuario);
			preparedStatement.setString(2, claveUsuario);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				tipoUsuario = resultSet.getInt("tipoUsuario");
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return tipoUsuario;
	}

	public void realizarAlta()
	{
		try
		{
			connection = DriverManager.getConnection(url, user, password);

			if (tipoAlta == 1)
			{
				sentenciaSQL = "insert into hoteles values(null, ? , ? )";
				preparedStatement = connection.prepareStatement(sentenciaSQL);
				preparedStatement.setString(1, txf[0]);
				preparedStatement.setString(2, txf[1]);
				preparedStatement.executeUpdate();
			}

			else if (tipoAlta == 2)
			{
				try
				{
					sentenciaSQL = "insert into habitaciones values(null, ? , ? , ?)";
					preparedStatement = connection.prepareStatement(sentenciaSQL);
					preparedStatement.setInt(1, Integer.parseInt(txf[0]));
					preparedStatement.setDouble(2, Double.parseDouble(txf[1]));
					preparedStatement.setInt(3, fk[0]);
					preparedStatement.executeUpdate();
				}

				catch (NumberFormatException nfe)
				{
					nfe.printStackTrace();
					error = true;
				}
			}

			else if (tipoAlta == 3)
			{
				sentenciaSQL = "insert into huespedes values(null, ? , ?)";
				preparedStatement = connection.prepareStatement(sentenciaSQL);
				preparedStatement.setString(1, txf[0]);
				preparedStatement.setString(2, txf[1]);
				preparedStatement.executeUpdate();
			}

			else if (tipoAlta == 4)
			{
				sentenciaSQL = "insert into ocupar values(null, ? , ?, ?, ?)";
				preparedStatement = connection.prepareStatement(sentenciaSQL);
				preparedStatement.setObject(1, fecha[0]);
				preparedStatement.setObject(2, fecha[1]);
				preparedStatement.setInt(3, fk[0]);
				preparedStatement.setInt(4, fk[1]);
				preparedStatement.executeUpdate();
			}

			if (preparedStatement != null)
			{
				preparedStatement.close();
			}
			connection.close();
		} catch (SQLException se)
		{
			se.printStackTrace();
			error = true;
		}
	}

	public void realizarBaja(Choice chcBajas)
	{
		try
		{
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();

			if (tipoChoice == 1)
			{
				sentenciaSQL = "delete from hoteles where idHotel = " + chcBajas.getSelectedItem().split(" - ")[0];
			}

			else if (tipoChoice == 2)
			{
				sentenciaSQL = "delete from habitaciones where idHabitacion = "
						+ chcBajas.getSelectedItem().split(" - ")[0];
			}

			else if (tipoChoice == 3)
			{
				sentenciaSQL = "delete from huespedes where idHuesped = " + chcBajas.getSelectedItem().split(" - ")[0];
			}

			else if (tipoChoice == 4)
			{
				sentenciaSQL = "delete from ocupar where idOcupar = " + chcBajas.getSelectedItem().split(" - ")[0];
			}

			statement.executeUpdate(sentenciaSQL);
			statement.close();
			connection.close();
		} catch (SQLException se)
		{
			se.printStackTrace();
			error = true;
		}
	}

	public void realizarModificacion(VistaModificacionH vmodh)
	{
		try
		{
			connection = DriverManager.getConnection(url, user, password);

			if (tipoChoice == 1)
			{
				sentenciaSQL = "update hoteles set nombreHotel = ?, direccionHotel = ? where idHotel = ?";
				preparedStatement = connection.prepareStatement(sentenciaSQL);
				preparedStatement.setString(1, vmodh.txf1.getText());
				preparedStatement.setString(2, vmodh.txf2.getText());
				preparedStatement.setInt(3, Integer.parseInt(vmodh.chcModificacion.getSelectedItem().split(" - ")[0]));
				preparedStatement.executeUpdate();
			}

			else if (tipoChoice == 2)
			{
				sentenciaSQL = "update habitaciones set numeroHabitacion = ?, precioHabitacion = ?, idHotelFK = ? where idHabitacion = ?";
				preparedStatement = connection.prepareStatement(sentenciaSQL);
				preparedStatement.setInt(1, Integer.parseInt(vmodh.txf1.getText()));
				preparedStatement.setDouble(2, Double.parseDouble(vmodh.txf2.getText()));
				preparedStatement.setDouble(3, fk[0]);
				preparedStatement.setInt(4, Integer.parseInt(vmodh.chcModificacion.getSelectedItem().split(" - ")[0]));
				preparedStatement.executeUpdate();
			}

			else if (tipoChoice == 3)
			{
				sentenciaSQL = "update huespedes set nombreHuesped = ?, telefonoHuesped = ? where idHuesped = ?";
				preparedStatement = connection.prepareStatement(sentenciaSQL);
				preparedStatement.setString(1, vmodh.txf1.getText());
				preparedStatement.setString(2, vmodh.txf2.getText());
				preparedStatement.setInt(3, Integer.parseInt(vmodh.chcModificacion.getSelectedItem().split(" - ")[0]));
				preparedStatement.executeUpdate();
			}

			else if (tipoChoice == 4)
			{
				sentenciaSQL = "update ocupar set fechaEntrada = ?, fechaSalida = ?, idHabitacionFK = ?, idHuespedFK = ? where idOcupar = ?";
				preparedStatement = connection.prepareStatement(sentenciaSQL);
				preparedStatement.setObject(1, fecha[0]);
				preparedStatement.setObject(2, fecha[1]);
				preparedStatement.setInt(3, fk[0]);
				preparedStatement.setInt(4, fk[1]);
				preparedStatement.setInt(5, Integer.parseInt(vmodh.chcModificacion.getSelectedItem().split(" - ")[0]));
				preparedStatement.executeUpdate();
			}

			if (preparedStatement != null)
			{
				preparedStatement.close();
			}

			connection.close();
		} catch (SQLException se)
		{
			se.printStackTrace();
			error = true;
		}
	}

	public void realizarConsulta(TextArea txa)
	{
		try
		{
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();

			if (tipoConsulta == 1)
			{
				sentenciaSQL = "select * from hoteles";
				resultSet = statement.executeQuery(sentenciaSQL);

				txa.setText("");
				txa.append("		LISTA DE HOTELES" + "\n\n");

				while (resultSet.next())
				{
					txa.append("	[" + resultSet.getInt("idHotel") + "]	|	" + resultSet.getString("nombreHotel")
							+ "	|	" + resultSet.getString("direccionHotel") + "\n\n");
				}
			} else if (tipoConsulta == 2)
			{
				sentenciaSQL = "select idHabitacion, numeroHabitacion, precioHabitacion, nombreHotel from habitaciones join hoteles on habitaciones.idHotelFK = hoteles.idHotel";
				resultSet = statement.executeQuery(sentenciaSQL);

				txa.setText("");
				txa.append("		LISTA DE HABITACIONES" + "\n\n");

				while (resultSet.next())
				{
					txa.append("	[" + resultSet.getInt("idHabitacion") + "]	|	"
							+ resultSet.getInt("numeroHabitacion") + "	|	" + resultSet.getDouble("precioHabitacion")
							+ "	|	(" + resultSet.getString("nombreHotel") + ")\n\n");
				}
			} else if (tipoConsulta == 3)
			{
				sentenciaSQL = "select * from huespedes";
				resultSet = statement.executeQuery(sentenciaSQL);

				txa.setText("");
				txa.append("		LISTA DE HUESPEDES" + "\n\n");

				while (resultSet.next())
				{
					txa.append("	[" + resultSet.getInt("idHuesped") + "]	|	" + resultSet.getString("nombreHuesped")
							+ "	|	" + resultSet.getString("telefonoHuesped") + "\n\n");
				}
			}

			else if (tipoConsulta == 4)
			{
				sentenciaSQL = "select idOcupar, date_format(fechaEntrada, '%d/%m/%Y') as 'fechaEntrada', date_format(fechaSalida, '%d/%m/%Y') as 'fechaSalida', numeroHabitacion, nombreHuesped from habitaciones join ocupar on  ocupar.idHabitacionFK = habitaciones.idHabitacion join huespedes on huespedes.idHuesped = ocupar.idHuespedFK order by idOcupar";

				resultSet = statement.executeQuery(sentenciaSQL);

				txa.append("Selecciona un Historial \n\n");
				while (resultSet.next())
				{
					if (resultSet.getString("fechaSalida") != null)
					{
						txa.append(resultSet.getInt("idOcupar") + " - " + resultSet.getString("fechaEntrada") + " - "
								+ resultSet.getString("fechaSalida") + " - numero: "
								+ resultSet.getInt("numeroHabitacion") + " - " + resultSet.getString("nombreHuesped") + "\n\n");
					}

					else
					{
						txa.append(resultSet.getInt("idOcupar") + " - " + resultSet.getString("fechaEntrada") + " - Sin fecha de salida"
								+ " - numero: " + resultSet.getInt("numeroHabitacion") + " - "
								+ resultSet.getString("nombreHuesped") + "\n\n");
					}
				}
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException se)
		{
			se.printStackTrace();
			error = true;
		}
	}

	public Choice rellenarChc(Choice chc)
	{
		chc.removeAll();
		try
		{
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();

			if (tipoChoice == 1)
			{
				sentenciaSQL = "select * from hoteles";
				resultSet = statement.executeQuery(sentenciaSQL);

				chc.add("Selecciona un hotel");
				while (resultSet.next())
				{
					chc.add(resultSet.getInt("idHotel") + " - " + resultSet.getString("nombreHotel") + " - "
							+ resultSet.getString("direccionHotel"));
				}
			}

			else if (tipoChoice == 2)
			{
				sentenciaSQL = "select idHabitacion, numeroHabitacion, precioHabitacion, nombreHotel from habitaciones join hoteles on habitaciones.idHotelFK = hoteles.idHotel order by idHabitacion asc";
				resultSet = statement.executeQuery(sentenciaSQL);

				chc.add("Selecciona una Habitacion");
				while (resultSet.next())
				{
					chc.add(resultSet.getInt("idHabitacion") + " - " + resultSet.getString("numeroHabitacion") + " - "
							+ resultSet.getDouble("precioHabitacion") + " - " + resultSet.getString("nombreHotel")
							+ "");
				}
			}

			else if (tipoChoice == 3)
			{
				sentenciaSQL = "select * from huespedes";
				resultSet = statement.executeQuery(sentenciaSQL);

				chc.add("Selecciona un Huesped");
				while (resultSet.next())
				{
					chc.add(resultSet.getInt("idHuesped") + " - " + resultSet.getString("nombreHuesped") + " - "
							+ resultSet.getString("telefonoHuesped"));
				}
			}

			else if (tipoChoice == 4)
			{
				sentenciaSQL = "select idOcupar, date_format(fechaEntrada, '%d/%m/%Y') as 'fechaEntrada', date_format(fechaSalida, '%d/%m/%Y') as 'fechaSalida', numeroHabitacion, nombreHuesped from habitaciones join ocupar on  ocupar.idHabitacionFK = habitaciones.idHabitacion join huespedes on huespedes.idHuesped = ocupar.idHuespedFK order by idOcupar";

				resultSet = statement.executeQuery(sentenciaSQL);

				chc.add("Selecciona una Reserva");
				while (resultSet.next())
				{
					if (resultSet.getString("fechaSalida") != null)
					{
						chc.add(resultSet.getInt("idOcupar") + " - " + resultSet.getString("fechaEntrada") + " - "
								+ resultSet.getString("fechaSalida") + " - numero: "
								+ resultSet.getInt("numeroHabitacion") + " - " + resultSet.getString("nombreHuesped"));
					}

					else
					{
						chc.add(resultSet.getInt("idOcupar") + " - " + resultSet.getString("fechaEntrada") + " - Sin fecha de salida"
								+ " - numero: " + resultSet.getInt("numeroHabitacion") + " - "
								+ resultSet.getString("nombreHuesped"));
					}
				}
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException se)
		{
			se.printStackTrace();
		}

		return chc;
	}

	public LocalDate fechaSQL(String fecha)
	{
		LocalDate fechaSQL = null;

		try
		{
			DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			fechaSQL = LocalDate.parse(fecha, formateador);
		}

		catch (DateTimeParseException dtpe)
		{
			dtpe.printStackTrace();
		}

		return fechaSQL;
	}
	
	public void exportarPDF(int tipoConsulta)
	{
		String ruta = "Consulta.pdf";
		try
		{
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			
			
			PdfWriter pdfW = new PdfWriter(ruta);
			PdfDocument pdfD = new PdfDocument(pdfW);
			Document document = new Document(pdfD, PageSize.A4.rotate());
			
			PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
			PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
			Table table = null;
			
			if(tipoConsulta == 1)
			{
				sentenciaSQL = "select * from hoteles";
				resultSet = statement.executeQuery(sentenciaSQL);
				
				table = new Table(UnitValue.createPercentArray(new float[] {2, 2, 2})).useAllAvailableWidth();
				String[] cabecera = {"ID", "Nombre del Hotel", "Direccion del Hotel"};
				for(String linea : cabecera)
				{
					table.addHeaderCell(new Cell().add(new Paragraph(linea).setFont(bold)));
				}
				
				while(resultSet.next())
				{
					for(int i = 1; i <= 3; i++)
					{
						String valorCelda = resultSet.getString(i);
						
						if(valorCelda == null)
						{
							valorCelda = "";
						}
						
						table.addCell(new Cell().add(new Paragraph(valorCelda).setFont(font)));
					}
				}
			}
			
			else if(tipoConsulta == 2)
			{
				sentenciaSQL = "select idHabitacion, numeroHabitacion, concat(precioHabitacion, ' €') as 'pecioHabitacion', nombreHotel from habitaciones join hoteles on habitaciones.idHotelFK = hoteles.idHotel order by idHabitacion asc";
				resultSet = statement.executeQuery(sentenciaSQL);
				
				table = new Table(UnitValue.createPercentArray(new float[] {2, 2, 2, 2})).useAllAvailableWidth();
				String[] cabecera = {"ID", "Numero de Habitacion", "Precio de Habitacion", "Nombre del Hotel"};
				for(String linea : cabecera)
				{
					table.addHeaderCell(new Cell().add(new Paragraph(linea).setFont(bold)));
				}
				
				while(resultSet.next())
				{
					for(int i = 1; i <= 4; i++)
					{
						String valorCelda = resultSet.getString(i);
						
						if(valorCelda == null)
						{
							valorCelda = "";
						}
						
						table.addCell(new Cell().add(new Paragraph(valorCelda).setFont(font)));
					}
				}
				
				
			}
			
			else if(tipoConsulta == 3)
			{
				sentenciaSQL = "select * from huespedes";
				resultSet = statement.executeQuery(sentenciaSQL);
				
				table = new Table(UnitValue.createPercentArray(new float[] {2, 2, 2})).useAllAvailableWidth();
				String[] cabecera = {"ID", "Nombre del Huesped", "Telefono del huesped"};
				for(String linea : cabecera)
				{
					table.addHeaderCell(new Cell().add(new Paragraph(linea).setFont(bold)));
				}
				
				while(resultSet.next())
				{
					for(int i = 1; i <= 3; i++)
					{
						String valorCelda = resultSet.getString(i);
						
						if(valorCelda == null)
						{
							valorCelda = "";
						}
						
						table.addCell(new Cell().add(new Paragraph(valorCelda).setFont(font)));
					}
				}
			}
			
			else if(tipoConsulta == 4)
			{
				sentenciaSQL = "select idOcupar, date_format(fechaEntrada, '%d/%m/%Y') as 'fechaEntrada', date_format(fechaSalida, '%d/%m/%Y') as 'fechaSalida', numeroHabitacion, nombreHuesped from habitaciones join ocupar on  ocupar.idHabitacionFK = habitaciones.idHabitacion join huespedes on huespedes.idHuesped = ocupar.idHuespedFK order by idOcupar";
				resultSet = statement.executeQuery(sentenciaSQL);
				
				table = new Table(UnitValue.createPercentArray(new float[] {2, 2, 2, 2, 2})).useAllAvailableWidth();
				String[] cabecera = {"ID", "Fecha de entrada", "Fecha de salida", "Numero de la Habitacion", "Nombre del Huesped"};
				for(String linea : cabecera)
				{
					table.addHeaderCell(new Cell().add(new Paragraph(linea).setFont(bold)));
				}
				
				while(resultSet.next())
				{
					for(int i = 1; i <= 5; i++)
					{
						String valorCelda = resultSet.getString(i);
						
						if(valorCelda == null)
						{
							valorCelda = "";
						}
						
						table.addCell(new Cell().add(new Paragraph(valorCelda).setFont(font)));
					}
				}
			}
			
			document.setMargins(20, 20, 20, 20);
			document.add(table);
			document.close();
			pdfD.close();
			pdfW.close();
			resultSet.close();
			statement.close();
			connection.close();
			Desktop.getDesktop().open(new File(ruta));
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
}