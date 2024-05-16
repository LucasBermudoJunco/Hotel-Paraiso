package persistenciaDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionABaseDeDatos {
	
	public Connection conectar() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/Hotel";
		// Lectura del fichero que contiene Usuario y Contraseña
		String[] usuarioContrasenya = leerContrasenya();
		String usuario = usuarioContrasenya[0];
		String contrasenya = usuarioContrasenya[1];
		
		// Conexión a la Base de datos
		try {
			con = DriverManager.getConnection(url,usuario,contrasenya);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public String[] leerContrasenya() {
		String[] datosFichero = new String[0];
		
		// Lectura del fichero que contiene Usuario y Contraseña
		String rutaFicheroUsuarioContrasenya = "files/usuarioContrasenya.txt";
		
		try {
			BufferedReader lector = new BufferedReader(new FileReader(rutaFicheroUsuarioContrasenya));
			
			String contenidoFichero = lector.readLine();
			datosFichero = contenidoFichero.split(",");
			
			lector.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return datosFichero;
	}

	public void escribirFichSalida(int errorSql) {
		BufferedWriter escribir=null;
		
		String nomFich="salidaSql.txt";
		
		try {
			String sqlCode = Integer.toString(errorSql);/* lo paso a String para escribir en el fichero de salida*/
			escribir=new BufferedWriter(new FileWriter(nomFich));
			escribir.write(sqlCode);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				escribir.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
