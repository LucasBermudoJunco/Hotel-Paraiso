package operaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import clases.Cliente;
import clases.Reserva;
import persistenciaDatos.ClienteDAO;
import persistenciaDatos.ReservaDAO;

public class Controlador {
	
	private ClienteDAO clienteDAO;
	private ReservaDAO reservaDAO;
	private Gson gson;
	
	
	
	
	/************************ CLIENTE *************************/
	
	public boolean hayUnClienteConEsteDNI(String dni) {
		clienteDAO = new ClienteDAO();
		
		boolean hayUnClienteConEsteDNI = false;
		
		// Escritura en el fichero
		String rutaFicheroCliente = "files/cliente.txt";
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaFicheroCliente));
			
			escritor.write(dni);
			
			escritor.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Comprobación de si hay un cliente con ese DNI
		if(clienteDAO.hayUnClienteConEsteDNI(rutaFicheroCliente)) {
			hayUnClienteConEsteDNI = true;
		}
		
		return hayUnClienteConEsteDNI; 
	}
	
	public String obtenerInfoClienteConEsteDNI(String dni) {		
		String infoCliente = "";
		
		clienteDAO = new ClienteDAO();
		gson = new Gson();
		
		// Escritura del dni en el fichero
		String rutaFicheroCliente = "files/cliente.json";
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaFicheroCliente));
			
			escritor.write(dni);
			
			escritor.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Llamada al método DAO que lea el fichero y realice la consulta
		clienteDAO.read(rutaFicheroCliente);
				
		// Lectura del fichero después de que el método DAO lo haya modificado		
		try {
			BufferedReader lector = new BufferedReader(new FileReader(rutaFicheroCliente));
			
			String contenidoDelFichero = "";
			String lineaActualDelFichero = lector.readLine();
			
			if(!lineaActualDelFichero.equals("No hay ningún cliente con ese DNI")) {
				do {
					contenidoDelFichero += lineaActualDelFichero;
					
					lineaActualDelFichero = lector.readLine();
				} while(lineaActualDelFichero != null);
					
				Cliente cliente = gson.fromJson(contenidoDelFichero, Cliente.class);
				
				// Incorporación de los datos del cliente al String que va a ser devuelto
				infoCliente += cliente.getDni() + ",";
				infoCliente += cliente.getNombre() + ",";
				infoCliente += cliente.getApellidos() + ",";
				infoCliente += cliente.getTelefono() + ",";
				infoCliente += cliente.getEmail();
			}
			
			lector.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return infoCliente;
	}
	
	public void insertarCliente(String infoCliente) {
		String[] datosCliente = infoCliente.split(",");
		
		clienteDAO = new ClienteDAO();
		gson = new Gson();
		
		String rutaFicheroCliente = "files/cliente.json";
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaFicheroCliente));
			
			Cliente cliente = new Cliente(
					datosCliente[0],datosCliente[1],datosCliente[2],datosCliente[3],datosCliente[4]
			);
			
			String clienteParaJSON = gson.toJson(cliente);
			
			escritor.write(clienteParaJSON);
			
			escritor.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		clienteDAO.create(rutaFicheroCliente);
	}
	
	
	
	
	/************************ RESERVA *************************/
	
	public String obtenerInfoReservaConEsteCodigo(String documento) {
		String infoReserva = "";
		
		reservaDAO = new ReservaDAO();
		gson = new Gson();
		
		// Escritura del codigo en el fichero
		String rutaFicheroReserva = "files/reserva.json";
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaFicheroReserva));
			
			escritor.write(documento);
			
			escritor.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Llamada al método DAO que lea el fichero y realice la consulta
		reservaDAO.read(rutaFicheroReserva);
				
		// Lectura del fichero después de que el método DAO lo haya modificado		
		try {

			BufferedReader lector = new BufferedReader(new FileReader(rutaFicheroReserva));
			
			String contenidoDelFichero = "";
			String lineaActualDelFichero = lector.readLine();
			
			if(!lineaActualDelFichero.equals("No hay ninguna reserva con ese código")) {
				do {
					contenidoDelFichero += lineaActualDelFichero;
					
					lineaActualDelFichero = lector.readLine();
				} while(lineaActualDelFichero != null);
					
				Reserva reserva = gson.fromJson(contenidoDelFichero, Reserva.class);
				
				// Incorporación de los datos de la reserva al String que va a ser devuelto
				infoReserva += reserva.getId_reserva() + ",";
				infoReserva += reserva.getHabitacion() + ",";
				infoReserva += reserva.getFecha_entrada() + ",";
				infoReserva += reserva.getFecha_salida() + ",";
				infoReserva += reserva.getDoc_identidad();
			}
			
			lector.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
				
		return infoReserva;
	}
	
}
