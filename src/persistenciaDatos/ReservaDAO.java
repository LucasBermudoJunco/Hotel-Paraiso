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
	
/*************************** ATRIBUTOS *********************************/
	
	private ConexionABaseDeDatos conexion;
	private Gson gson;

	
	
	
	
	
	
/*************************** MÉTODOS *********************************/
	
// MÉTODOS DE LA INTERFAZ ClasesDAO
	
	
	
	@Override
	public boolean read(String fichero) {
		boolean conexionCorrecta = false;
		
		conexion = new ConexionABaseDeDatos();
		gson = new Gson();
		
		// Conexión a la base de datos
		try {
			Connection con = conexion.conectar();
			
			if(con != null) {
				conexionCorrecta = true;
			
				// Lectura del fichero 		
				String codigo = "";
				
				try {
					BufferedReader lector = new BufferedReader(new FileReader(fichero));
					
					codigo = lector.readLine();
					
					lector.close();
					
					// Consulta a la Base de datos				
					if(con != null) {
						try {
							String accionSQL = "select * from reserva where id_reserva = ?";
							PreparedStatement sentPrep = con.prepareStatement(accionSQL);
							
							sentPrep.setString(1, codigo);
							
							ResultSet rs = sentPrep.executeQuery();
							
							// Escritura del resultado de la consulta en el fichero 				
							try {					
		
								BufferedWriter escritor = new BufferedWriter(new FileWriter(fichero));
								
								if(rs.next()) {
									String id_reserva_String = rs.getString("id_reserva");
									int id_reserva_int = Integer.valueOf(id_reserva_String);
									String habitacion = rs.getString("habitacion");
									String fecha_entrada = rs.getString("fecha_entrada");
									String fecha_salida = rs.getString("fecha_salida");
									String doc_identidad = rs.getString("doc_identidad");
									
									Reserva reserva = new Reserva(id_reserva_int,habitacion,fecha_entrada,fecha_salida,doc_identidad);
									
									String datosReserva = gson.toJson(reserva);
									
									escritor.write(datosReserva);
								} else {
									escritor.write("No hay ninguna reserva con ese código");
								}
								
								escritor.close();
							} catch(IOException e) {
								e.printStackTrace();
							}
						} catch (SQLException e) {
							conexionCorrecta = false;
							
							e.printStackTrace();
						}
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			
			try {
				BufferedWriter escritor = new BufferedWriter(new FileWriter(fichero));
				
				escritor.write("error de conexión");
				
				escritor.close();
			} catch(IOException ex) {
				e.printStackTrace();
			}
		}
		
		return conexionCorrecta;
	}

	@Override
	public boolean create(String fichero) {
		boolean conexionCorrecta = false;
		
	 	int errorSql = 0;
		
		conexion = new ConexionABaseDeDatos();
	 	
		Reserva regRes = leerFichero(fichero);
		
		try {
			Connection con = conexion.conectar();
			
			if (con!=null) {
				String sql = "INSERT INTO reserva (id_reserva,habitacion, fecha_entrada, fecha_salida, doc_identidad ) "

									+ "             VALUES (null,?,?,?,?)";
				
				try {
					conexionCorrecta = true;
					
					PreparedStatement sentencia = con.prepareStatement(sql);
					
					int random = (int)(Math.random()*15+1);
					String habitacion = "";
					if(random == 1) {
						habitacion = "101";
					} else if(random == 2) {
						habitacion = "102";
					} else if(random == 3) {
						habitacion = "103";
					} else if(random == 4) {
						habitacion = "104";
					} else if(random == 5) {
						habitacion = "105";
					} else if(random == 6) {
						habitacion = "106";
					} else if(random == 7) {
						habitacion = "201";
					} else if(random == 8) {
						habitacion = "202";
					} else if(random == 9) {
						habitacion = "203";
					} else if(random == 10) {
						habitacion = "204";
					} else if(random == 11) {
						habitacion = "205";
					} else if(random == 12) {
						habitacion = "301";
					} else if(random == 13) {
						habitacion = "302";
					} else if(random == 14) {
						habitacion = "303";
					} else if(random == 15) {
						habitacion = "304";
					}
					
					sentencia.setString(1, habitacion);
					sentencia.setString(2, regRes.getFecha_entrada());
					sentencia.setString(3, regRes.getFecha_salida());
					sentencia.setString(4, regRes.getDoc_identidad());
	
					sentencia.executeUpdate();
	
					con.close();
					
					errorSql=0;
				} catch (SQLException ex) {
					conexionCorrecta = false;
					
					ex.printStackTrace();
					
//					if (ex.getErrorCode()==1062) {
//						errorSql=1;
//					
//					} else if (ex.getErrorCode()==1045){
//						errorSql=2;
//					
//					} else {
//						System.out.println("Error SQL inesperado :"+ex.getMessage());
//						int errorCode = ex.getErrorCode();
//						System.out.println("Código de Error: " + errorCode);
//						String sqlMessage = ex.getSQLState();
//					    System.out.println("Mensaje SQL: " + sqlMessage);
//					}
				}
				
				// escribirFichSalida escribe sqlcode y por tanto es independiente de la clase que lo trate
				conexion.escribirFichSalida(errorSql);
		
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conexionCorrecta;
	}
	
	@Override
	public boolean update(String fichero) {
		return false;
	}
	
	@Override
	public boolean delete(String fichero) {
		return false;
	}
	
	
	
// OTROS MÉTODOS
	
	/**
	 * 
	 * @param fichero
	 * @return
	 */
	private Reserva leerFichero(String fichero) {
		Gson gson=new Gson();
			
		String regFich="";
		
		try {
			BufferedReader leer = new BufferedReader(new FileReader(fichero));
			String fila;
			
			/* solo lee un registro pero mantengo el while para no preguntar por fichero vacío*/
			
			while((fila=leer.readLine())!=null) {
				regFich
				+=fila;
			}
			
			leer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		
		
		}
		
		Reserva clase=gson.fromJson(regFich, Reserva.class);
		
		
		return clase;
	}
	
	
	public String modificacion(String fichero) {
		// no se puede modificar la reserva
		return null;
	}
	
	public String consulta(String fichero) {
		// Solo se necesita el id_reserva, todo el resto de campos a null
		@SuppressWarnings("unused")
		String regGson,	ficheroRetorno;
		@SuppressWarnings("unused")
		int errorSql = 0;
		String nomFich = null;
		Reserva regRes=leerFichero(fichero);
		Connection con = conexion.conectar();
		
		if (con!=null) {
		
			String sql = "SELECT *" + "FROM reserva " + "WHERE id_reserva = ?";
			try {
				PreparedStatement sentencia = con.prepareStatement(sql);
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
					nomFich="src/files/salidaReserva.json";
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
					
					con.close();
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


