package Clases;

import java.time.LocalDate;
import java.util.List;

public class Reserva {
	
	private String id;
	private Cliente cliente;
	private Habitacion habitacion;
	private List<Servicio> servicios;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private double precioFinal;
	
	public Reserva() {
		super();
	}
	public Reserva(String id, Cliente cliente, Habitacion habitacion, List<Servicio> servicios, LocalDate fechaEntrada,
			LocalDate fechaSalida, double precioFinal) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.habitacion = habitacion;
		this.servicios = servicios;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.precioFinal = precioFinal;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Habitacion getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public double getPrecioFinal() {
		return precioFinal;
	}
	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	}
	@Override
	public String toString() {
		return "Reserva [id=" + id + ", cliente=" + cliente + ", habitacion=" + habitacion + ", servicios=" + servicios
				+ ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", precioFinal=" + precioFinal
				+ "]";
	}
	
	// Métodos
	

}
