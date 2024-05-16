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

import clases.Cliente;
import interfaceDAO.ClasesDAO;


/* Esta clase gestiona la tabla Cliente en la que se realizan el alta de cliente y la consulta del mismo*/
public class ClienteDAO implements ClasesDAO{
	
/*************************** ATRIBUTOS *********************************/
	
	private ConexionABaseDeDatos conexion;
	private Gson gson;
	
	
	
	
	
	
/*************************** MÉTODOS *********************************/
	
// MÉTODOS DE LA INTERFAZ ClasesDAO
	
	
	
	@Override
	public void read(String fichero) {
		conexion = new ConexionABaseDeDatos();
		gson = new Gson();
		
		Connection con = conexion.conectar();
		
		if(conexion != null) {
			// Lectura del fichero
			String documento = "";
				
			try {
				BufferedReader lector = new BufferedReader(new FileReader(fichero));
				
				String lineaActual = lector.readLine();
				
				while(lineaActual != null) {
					documento += lineaActual;
					
					lineaActual = lector.readLine();
				}
				
				lector.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			// Consulta a la base de datos
			try {
				String accionSQL = "select * from cliente where doc_identidad = ?";
				PreparedStatement sentPrep = con.prepareStatement(accionSQL);
				
				sentPrep.setString(1, documento);
				
				ResultSet rs = sentPrep.executeQuery();
				
				// Escritura en el fichero
				try {
					BufferedWriter escritor = new BufferedWriter(new FileWriter(fichero));
					
					if(rs.next()) {
						String dni = rs.getString("doc_identidad");
						String nombre = rs.getString("nombre");
						String apellidos = rs.getString("apellidos");
						String telefono = rs.getString("telefono");
						String email = rs.getString("email");
						
						Cliente cliente = new Cliente(dni,nombre,apellidos,telefono,email);
						
						String datosCliente = gson.toJson(cliente);
						
						escritor.write(datosCliente);
					} else {
						escritor.write("No hay ningún cliente con ese DNI");
					}
					
					escritor.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void create(String fichero) {
		
		int errorSql = 0;
		
		conexion = new ConexionABaseDeDatos();
		
		Cliente regCli = leerFichero(fichero);
		Connection con = conexion.conectar();
		
		if (conexion!=null) {
			String sql = "INSERT INTO cliente VALUES (?,?,?,?,?)";

			try {
				PreparedStatement sentencia = con.prepareStatement(sql);
				sentencia.setString(1, regCli.getDni());
				sentencia.setString(2, regCli.getNombre());
				sentencia.setString(3, regCli.getApellidos());
				sentencia.setString(4, regCli.getTelefono());
				sentencia.setString(5, regCli.getEmail());
				
				sentencia.executeUpdate();

				con.close();
				
				errorSql=0;
			} catch (SQLException ex) {
				if (ex.getErrorCode()==1062) {
					errorSql=1;
				
				} else if (ex.getErrorCode()==1045){
					errorSql=2;
				
				} else {
					System.out.println("Error SQL inesperado :"+ex.getMessage());
					int errorCode = ex.getErrorCode();
					System.out.println("Código de Error: " + errorCode);
					String sqlMessage = ex.getSQLState();
				    System.out.println("Mensaje SQL: " + sqlMessage);
				}
			}
			
			conexion.escribirFichSalida(errorSql);
		}
	}
	
	@Override
	public void update(String fichero) {
		
	}
	
	@Override
	public void delete(String fichero) {
		
	}
	
	
	
// OTROS MÉTODOS
	
	
	
	private Cliente leerFichero(String fichero) {
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
		
		Cliente clase=gson.fromJson(regFich, Cliente.class);
		
		return clase;
	}
	
	public boolean hayUnClienteConEsteDNI(String rutaFicheroCliente) {
		boolean hayUnClienteConEsteDNI = false;

		conexion = new ConexionABaseDeDatos();
		
		Connection con = conexion.conectar();
		
		if(conexion != null) {
			// Lectura del fichero
			String documento = "";
				
			try {
				BufferedReader lector = new BufferedReader(new FileReader(rutaFicheroCliente));
				
				String lineaActual = lector.readLine();
				
				while(lineaActual != null) {
					documento += lineaActual;
					
					lineaActual = lector.readLine();
				}
				
				lector.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			// Consulta a la base de datos
			try {
				String accionSQL = "select nombre from cliente where doc_identidad = ?";
				PreparedStatement sentPrep = con.prepareStatement(accionSQL);
				
				sentPrep.setString(1, documento);
				
				ResultSet rs = sentPrep.executeQuery();
				
				if(rs.next()) {
					hayUnClienteConEsteDNI = true;
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return hayUnClienteConEsteDNI;
	}

}
