package Operaciones;

import java.util.ArrayList;
import java.util.List;

import Clases.Cliente;

public class Controlador {
	
	public static String listarClientes() {

		Cliente cli1 = new Cliente("1","Paco","Mer","pacomer@hambre.es","123456789");
		Cliente cli2 = new Cliente("2","Andrés","Mer","pacomer@hambre.es","123456789");
		Cliente cli3 = new Cliente("3","María","Mer","pacomer@hambre.es","123456789");
		
		List<Cliente> listaClientes = new ArrayList<>();
		
		listaClientes.add(cli1);	
		listaClientes.add(cli2);	
		listaClientes.add(cli3);
		
		return listaClientes.toString();
		
	}
	
}
