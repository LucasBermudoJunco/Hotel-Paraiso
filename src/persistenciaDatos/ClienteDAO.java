package persistenciaDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import InterfaceDAO.ClasesDAO;
import clases.Cliente;


public class ClienteDAO implements ClasesDAO{
	/* Esta clase gestiona la tabla Cliente en la que se realizan el alta de cliente y la consulta del mismo*/
	
	public  Connection conectar() {
		Connection con = null;
		String[] usuContraseña;
		usuContraseña=leerContraseña();
		
		String url = "jdbc:mysql://localhost:3306/Hotel";
		try {
		
			con=DriverManager.getConnection(url,usuContraseña[0],usuContraseña[1]);
		
		} catch (SQLException ex) {
			if (ex.getErrorCode()==1045) {
					System.out.println("No tiene autorización a la base de datos.");
			}else {
				if (ex.getErrorCode()==1049) {
					System.out.println("No existe la base de datos.");
			}
			
		}
	}
	return con;
	}
	
	/**
	 * 
	 * @return
	 */
	public  String[] leerContraseña() {
		BufferedReader input=null;
		String[] registro = null;
		String registroFic="";
		
		try {
			input=new BufferedReader(new FileReader("usuarioContraseña.txt"));
			registroFic=input.readLine();
			registro = registroFic.split(",");			
			
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}finally {
			try {
				input.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return registro;
	}


	@Override
	public  String alta(String fichero) {
		
		int errorSql = 0;
		String nomFich = null;
		Cliente regCli=leerFichero(fichero);
		Connection conexion = conectar();
		if (conexion!=null) {
			String sql = "INSERT INTO cliente (doc_identidad, nombre, apellidos, telefono, email ) "
			
								+ "             VALUES ( ?,    ?,     ?,     ?,     ?  )";

			try {
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				sentencia.setString(1, regCli.getDni());
				sentencia.setString(2, regCli.getNombre());
				sentencia.setString(3, regCli.getApellidos());
				sentencia.setString(4, regCli.getTelefono());
				sentencia.setString(5, regCli.getEmail());
			

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
			nomFich=escribirFichSalida(errorSql);
	
		}
		return nomFich;
	}


	public String escribirFichSalida(int errorSql) {
		BufferedWriter escribir=null;
		String nomFich="salidaSql.txt";
		try {
			String sqlCode=Integer.toString(errorSql);/* lo paso a String para escribir en el fichero de salida*/
			escribir=new BufferedWriter(new FileWriter(nomFich));
			escribir.write(sqlCode);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				escribir.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return nomFich;
	}


	private Cliente leerFichero(String fichero) {
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
		Cliente clase=gson.fromJson(regFich, Cliente.class);
		
		
		return clase;
	}


	@Override
	public String modificacion(String fichero) {
		/* doc_identidad varchar(9),nombre varchar(20), apellidos varchar(50), telefono varchar(12), email varchar*/
		/* de momento no se necesita */
		return null;
	}


	@Override
	public String consulta(String fichero) {
		String regGson,	ficheroRetorno;
		int errorSql = 0;
		String nomFich = null;
		Cliente regCli=leerFichero(fichero);
		Connection conexion = conectar();
		
		if (conexion!=null) {
		
			String sql = "SELECT *" + "FROM cliente " + "WHERE doc_identidad = ?";
			try {
				PreparedStatement sentencia = conexion.prepareStatement(sql);

				sentencia.setString(1, regCli.getDni());

				ResultSet rs = sentencia.executeQuery();

				if (rs.next()|| rs.getString("nombre")!=null) {
					String dni = rs.getString("doc_identidad");
					String nombre = rs.getString("nombre");
					String apellidos = rs.getString("apellidos");
					String telefono = rs.getString("telefono");
					String email = rs.getString("email");
				

					Gson gson=new Gson();
					regCli=new Cliente (dni,nombre,apellidos,telefono,email);
					regGson = gson.toJson(regCli);
					
					BufferedWriter escribir=null;
					nomFich="salidaCliente.json";
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
