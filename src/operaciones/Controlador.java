package operaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import persistenciaDatos.ClienteDAO;

public class Controlador {
	
	private ClienteDAO clienteDAO;
	
	public boolean hayUnClienteConEsteDNI(String dni) {
		clienteDAO = new ClienteDAO();
		
		boolean hayUnClienteConEsteDNI = false;
		
		String rutaFicheroCliente = "files/cliente.txt";
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaFicheroCliente));
			
			escritor.write(dni);
			
			escritor.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(clienteDAO.hayUnClienteConEsteDNI(rutaFicheroCliente)) {
			hayUnClienteConEsteDNI = true;
		}
		
		return hayUnClienteConEsteDNI; 
	}
	
	public String obtenerInfoClienteConEsteDNI(String dni) {		
		String infoCliente = "";
		
		clienteDAO = new ClienteDAO();
		
		String rutaFicheroCliente = "files/cliente.txt";
		
		// Escritura del dni en el fichero
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaFicheroCliente));
			
			escritor.write(dni);
			
			escritor.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Llamada al método DAO que lea el fichero y realice la consulta
		clienteDAO.buscarClienteConEsteDNI(rutaFicheroCliente);
				
		// Lectura del fichero después de que el método DAO lo haya modificado		
		try {
			BufferedReader lector = new BufferedReader(new FileReader(rutaFicheroCliente));
			
			String lineaActualDelFichero = lector.readLine();
			
			if(!lineaActualDelFichero.equals("No hay ningún cliente con ese DNI")) {
				do {
					infoCliente += lineaActualDelFichero;
					
					lineaActualDelFichero = lector.readLine();
					
					if(lineaActualDelFichero != null) {
						infoCliente += ",";
					}
				} while(lineaActualDelFichero != null);
			}
			
			lector.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
				
		return infoCliente;
	}
	
	public void insertarCliente(String cliente) {
		String[] datosCliente = cliente.split(",");
		
		String rutaFicheroCliente = "files/cliente.txt";
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaFicheroCliente));
			
			escritor.write(datosCliente[0]);
			escritor.newLine();
			escritor.write(datosCliente[1]);
			escritor.newLine();
			escritor.write(datosCliente[2]);
			escritor.newLine();
			escritor.write(datosCliente[3]);
			escritor.newLine();
			escritor.write(datosCliente[4]);
			
			escritor.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		String texto = clienteDAO.darDeAltaAEsteCliente(rutaFicheroCliente);
		System.out.println(texto);
	}
	
}
