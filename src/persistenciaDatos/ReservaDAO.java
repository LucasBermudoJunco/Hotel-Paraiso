package persistenciaDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import clases.Reserva;
import interfaceDAO.ClasesDAO;

public class ReservaDAO implements ClasesDAO {

	@Override
	public String alta(String fichero) {
		
		 	int errorSql = 0;
			String nomFich = null;
			Reserva regRes=leerFichero(fichero);
			ClienteDAO cli=new ClienteDAO();
			Connection conexion = cli.conectar();
			
			if (conexion!=null) {
				String sql = "INSERT INTO reserva (id_reserva,habitacion, fecha_entrada, fecha_salida, doc_identidad ) "
				
									+ "             VALUES (null, ?,    ?,     ?,     ? )";
				 

				try {
					PreparedStatement sentencia = conexion.prepareStatement(sql);
					
					sentencia.setString(1, regRes.getHabitacion());
					sentencia.setString(2, regRes.getFecha_entrada());
					sentencia.setString(3, regRes.getFecha_salida());
					sentencia.setString(4, regRes.getDoc_identidad());
				

					sentencia.executeUpdate();

					conexion.close();
					errorSql=0;
				} catch (SQLException ex) {
					if (ex.getErrorCode()==1062) {
						errorSql=1;
					
					}else if (ex.getErrorCode()==1045){
						errorSql=2;
					
					}else {
						System.out.println("Error SQL inesperado :"+ex.getMessage());
						int errorCode = ex.getErrorCode();
						System.out.println("Código de Error: " + errorCode);
						String sqlMessage = ex.getSQLState();
					    System.out.println("Mensaje SQL: " + sqlMessage);
					}
				}
				//escribirFichSalida escribe sqlcode y por tanto es independiente de la clase que lo trate
				nomFich=cli.escribirFichSalida(errorSql);
		
			}
			return nomFich;
		}
	
	/**
	 * 
	 * @param fichero
	 * @return
	 */
	private Reserva leerFichero(String fichero) {
		Gson gson=new Gson();
			
		String regFich="";
		
		try {
			BufferedReader leer=new BufferedReader(new FileReader(fichero));
			String fila;
			
			/* solo lee un registro pero mantengo el while para no preguntar por fichero vacío*/
			
			while((fila=leer.readLine())!=null) {
				regFich
				+=fila;
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		
		
		}
		Reserva clase=gson.fromJson(regFich, Reserva.class);
		
		
		return clase;
	}
	
	
		@Override
		public String modificacion(String fichero) {
			// no se puede modificar la reserva
			return null;
		}

		@Override
		public String consulta(String fichero) {
			// Solo se necesita el id_reserva, todo el resto de campos a null
			String regGson,	ficheroRetorno;
			int errorSql = 0;
			String nomFich = null;
			Reserva regRes=leerFichero(fichero);
			ClienteDAO cli=new ClienteDAO();
			Connection conexion = cli.conectar();
			
			if (conexion!=null) {
			
				String sql = "SELECT *" + "FROM reserva " + "WHERE id_reserva = ?";
				try {
					PreparedStatement sentencia = conexion.prepareStatement(sql);
					//muevo el campo recuperado del fichero y lo muevo a la ?
					sentencia.setInt(1, regRes.getId_reserva());

					ResultSet rs = sentencia.executeQuery();

					if (rs.next()|| rs.getString("habitacion")!=null) {
						String habitacion = rs.getString("habitacion");
						String fecEntrada = rs.getString("fecha_entrada");
						String fecSalida = rs.getString("fecha_salida");
						String dni = rs.getString("doc_identidad");
						int reserva = rs.getInt("id_reserva");
											

						Gson gson=new Gson();
						regRes=new Reserva (reserva, habitacion, fecEntrada, fecSalida, dni);
						regGson = gson.toJson(regRes);
						
						BufferedWriter escribir=null;
						nomFich="salidaReserva.json";
						try {
							escribir=new BufferedWriter(new FileWriter(nomFich));
							
							escribir.write(regGson);
							
						} catch (IOException e) {
							e.printStackTrace();
						}finally {
							try {
								escribir.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						
						conexion.close();
					}
				} catch (SQLException ex) {
					String sqlMessage = ex.getSQLState();
					if ( sqlMessage=="S1000") {
						System.out.println("No se ha encontrado el cliente");
					}else {
						System.out.println("Error SQL inesperado :"+ex.getMessage());
						int errorCode = ex.getErrorCode();
						System.out.println("Código de Error: " + errorCode);
					    System.out.println("Mensaje SQL: " + sqlMessage);
					}
					
				}
		
		}
			return nomFich;
		}
}


