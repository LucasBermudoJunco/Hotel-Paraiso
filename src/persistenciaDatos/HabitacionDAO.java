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
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import clases.Habitacion;
import interfaceDAO.ClasesDAO;

/* en esta clase se realizarán las consultas de las habitaciones libres y se actualizará 
 * la fecha_salida cuando se realice una reserva */
/* En la tabla se ha realizado una carga inicial para tener todas las habitaciones libres.
 * Una habitación está libre cuando la fecha_salida es menor o igual a hoy */
public class HabitacionDAO implements ClasesDAO {
	
/*************************** ATRIBUTOS *********************************/
	
	private ConexionABaseDeDatos conexion;
	private Gson gson;
	

	public String alta(String fichero) {
		/* El alta se ha realizado con carga inicial*/
		return null;
	}

	public String modificacion(String fichero) {
		conexion = new ConexionABaseDeDatos();
		
		/*select min(habitacion) from habitacion where fecha_salida <= now() and id_habitacion= ?; */
	
		String nomFich = null;
		Habitacion regHab=leerFichero(fichero);
		ClienteDAO cli=new ClienteDAO();
		Connection con = conexion.conectar();
		
		if (conexion!=null) {
		
			String sql = 
				"SELECT  min(habitacion)as habitacion"+" FROM habitacion "+" WHERE fecha_salida <= now() AND id_habitacion = ? ";
			  
			try {
				PreparedStatement sentencia = con.prepareStatement(sql);
				
				//id_habitacion se corresponde con el tipo de habitación
				
				sentencia.setString(1, regHab.getId_habitacion());
				
				ResultSet rs = sentencia.executeQuery();

				if (rs.next()|| rs.getString("habitacion")!=null) {
					String habitacion = rs.getString("habitacion");
									
					nomFich=actualizaTabla(habitacion,regHab.getFecha_salida(), con);

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
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nomFich;
	}
		
		
	
	/**
	 * 
	 * @param hab
	 * @param fecsalida
	 * @param conexion
	 * @return
	 */
	private String actualizaTabla(String hab, String fecsalida,Connection conexion2) {
		conexion = new ConexionABaseDeDatos();
		
		Connection con = conexion.conectar();
		ClienteDAO cli=new ClienteDAO();
		int errorSql = -1;
		String nomFich = null;
		String sql = 
				"UPDATE habitacion "+"SET fecha_salida = ? "+"WHERE habitacion = ? ";
		try {
			PreparedStatement sentencia = con.prepareStatement(sql);
			sentencia.setString(1, fecsalida);
			sentencia.setString(2, hab);
			sentencia.executeUpdate();
			errorSql=0;
			
		} catch (SQLException ex) {
				System.out.println("Error SQL inesperado :"+ex.getMessage());
				int errorCode = ex.getErrorCode();
				System.out.println("Código de Error: " + errorCode);
				errorSql=errorCode;
			   
		}
		//escribirFichSalida escribe sqlcode y por tanto es independiente de la clase que lo trate
		conexion.escribirFichSalida(errorSql);
		
		return nomFich;
	}
	
	
	public String consulta(String fichero) {
		conexion = new ConexionABaseDeDatos();
	
		List<String> listHab=new ArrayList<>();

		/*select id_habitacion , precio from habitacion where fecha_salida <= now() 
		 * group by id_habitacion, precio order by id_habitacion; */
		String regGson,	ficheroRetorno = null;
	
		ClienteDAO cli=new ClienteDAO();
		Connection con = conexion.conectar();
		
		if (conexion!=null) {
		
			String sql = 
			"SELECT id_habitacion , precio " +"FROM habitacion "+"WHERE fecha_salida <= curdate() "+"GROUP BY id_habitacion, precio "+"order by id_habitacion"; 
			try {
				PreparedStatement sentencia = con.prepareStatement(sql);
				
				ResultSet rs = sentencia.executeQuery();
				
			
                while (rs.next()) {
                	String habitacion=null;
                    String tipHab = rs.getString("id_habitacion");
                    double precio = rs.getDouble("precio");
					String fecha_salida=null;
					String claseHab=(habitacion+tipHab+precio+fecha_salida);
					                   
                    listHab.add(claseHab);
                    
                   
                }

				Gson gson=new Gson();
					
				regGson = gson.toJson(listHab);
					
				BufferedWriter escribir=null;
				ficheroRetorno="src/files/salidaHabitacion.json";
				try {
					escribir=new BufferedWriter(new FileWriter(ficheroRetorno));
						
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
		
			} catch (SQLException ex) {
				String sqlMessage = ex.getSQLState();
				if ( sqlMessage=="S1000") {
					System.out.println("No se ha encontrado habitación");
				}else {
					System.out.println("Error SQL inesperado :"+ex.getMessage());
					int errorCode = ex.getErrorCode();
					System.out.println("Código de Error: " + errorCode);
				    System.out.println("Mensaje SQL: " + sqlMessage);
				}
				
			
			}
		}
		return ficheroRetorno;
	}
	/**
	 * 
	 * @param fichero
	 * @return
	 */
	private Habitacion leerFichero(String fichero) {
		Gson gson=new Gson();
			
		String regFich="";
		
		try (BufferedReader leer = new BufferedReader(new FileReader(fichero))) {
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
		Habitacion clase=gson.fromJson(regFich, Habitacion.class);
		
		
		return clase;
	}

	@Override
	public boolean read(String fichero) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(String fichero) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String fichero) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String fichero) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
