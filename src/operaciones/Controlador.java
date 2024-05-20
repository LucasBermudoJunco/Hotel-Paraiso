package operaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
	
	public String hayUnClienteConEsteDNI(String dni) {
		clienteDAO = new ClienteDAO();
		
		String resultadoConsulta = "No hay ningún cliente con ese DNI";
		
		// Escritura en el fichero
		String rutaFicheroCliente = "files/salidaCliente.json";
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaFicheroCliente));
			
			escritor.write(dni);
			
			escritor.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Comprobación de si hay un cliente con ese DNI
		if(clienteDAO.hayUnClienteConEsteDNI(rutaFicheroCliente)) {
			resultadoConsulta = "Sí hay cliente con ese DNI";
		} else {
			try {
				BufferedReader lector = new BufferedReader(new FileReader(rutaFicheroCliente));
				
				String contenido = lector.readLine();
				
				if(contenido.equals("error de conexión")) {
					resultadoConsulta = "error de conexión";
				}
				
				lector.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return resultadoConsulta; 
	}
	
	public String obtenerInfoClienteConEsteDNI(String dni) {		
		String infoCliente = "error de conexión";
		
		clienteDAO = new ClienteDAO();
		gson = new Gson();
		
		// Escritura del dni en el fichero
		String rutaFicheroCliente = "files/salidaCliente.json";
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaFicheroCliente));
			
			escritor.write(dni);
			
			escritor.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Llamada al método DAO que lea el fichero y realice la consulta
		if(clienteDAO.read(rutaFicheroCliente)) {	
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
					infoCliente = cliente.getDni() + ",";
					infoCliente += cliente.getNombre() + ",";
					infoCliente += cliente.getApellidos() + ",";
					infoCliente += cliente.getTelefono() + ",";
					infoCliente += cliente.getEmail();
				} else {
					infoCliente = "No hay ningún cliente con ese DNI";
				}
				
				lector.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return infoCliente;
	}
	
	public String insertarCliente(String infoCliente) {
		String resultadoInsercion = "";
		
		String[] datosCliente = infoCliente.split(",");
		
		clienteDAO = new ClienteDAO();
		gson = new Gson();
		
		String rutaFicheroCliente = "files/salidaCliente.json";
		
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
		
		if(!clienteDAO.create(rutaFicheroCliente)){
			resultadoInsercion = "conexión fallida";
		}
		
		return resultadoInsercion;
	}
	
	
	
	
	/************************ RESERVA *************************/
	
	public String obtenerInfoReservaConEsteCodigo(String codigoRes) {
		String infoReserva = "error de conexión";
		
		reservaDAO = new ReservaDAO();
		gson = new Gson();
		
		// Escritura del codigo en el fichero
		String rutaFicheroReserva = "files/salidaReserva.json";
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaFicheroReserva));
			
			escritor.write(codigoRes);
			
			escritor.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Llamada al método DAO que lea el fichero y realice la consulta
		if(reservaDAO.read(rutaFicheroReserva)) {
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
					infoReserva = reserva.getId_reserva() + ",";
					infoReserva += reserva.getHabitacion() + ",";
					infoReserva += reserva.getFecha_entrada() + ",";
					infoReserva += reserva.getFecha_salida() + ",";
					infoReserva += reserva.getDoc_identidad();
				} else {
					infoReserva = "No hay ninguna reserva con ese código";
				}
				
				lector.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		} else {
			
		}
				
		return infoReserva;
	}
	
	public String insertarReserva(String infoReserva) {
		String resultadoInsercion = "";
		
		String[] datosReserva = infoReserva.split(",");
		
		reservaDAO = new ReservaDAO();
		gson = new Gson();
		
		String rutaFicheroReserva = "files/salidaReserva.json";
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaFicheroReserva));
			
			Reserva reserva = new Reserva(
					"101",datosReserva[0],datosReserva[1],datosReserva[2]
			);
			
			String reservaParaJSON = gson.toJson(reserva);
			
			escritor.write(reservaParaJSON);
			
			escritor.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(!reservaDAO.create(rutaFicheroReserva)){
			resultadoInsercion = "conexión fallida";
		}
		
		return resultadoInsercion;
	}
	
	public double precioFinal(String datosReservaFactura) {
		double precioFinal = 0;
		
		String[] datosFactura = datosReservaFactura.split(",");
		
		// tipoHabitacion
		if(datosFactura[3].equals("Económica")) {
			precioFinal += 40.00;
		} else if((datosFactura[3].equals("Estándar"))) {
			precioFinal += 80.00;
		} else {
			precioFinal += 1000.00;
		}
		
		// dias
		String[] valoresFechaEntrada = datosFactura[0].split("-");
		LocalDate fechaEntrada = LocalDate.of(Integer.valueOf(valoresFechaEntrada[0]),
				Integer.valueOf(valoresFechaEntrada[1]),
				Integer.valueOf(valoresFechaEntrada[2]));

		String[] valoresFechaSalida = datosFactura[1].split("-");
		LocalDate fechaSalida = LocalDate.of(Integer.valueOf(valoresFechaSalida[0]),
				Integer.valueOf(valoresFechaSalida[1]),
				Integer.valueOf(valoresFechaSalida[2]));
		
		int dias = (int)ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
		
		// extra
		if(datosFactura[4].equals("Desayuno +10€")) {
			precioFinal += 10.00;
		} else if(datosFactura[4].equals("Media pensión +30€")) {
			precioFinal += 30.00;
		} else {
			precioFinal += 60.00;
		}
		
		// Total
		precioFinal *= dias;
		
		return precioFinal;
	}
	
}
