package Clases;

public class Habitacion {
	
	private String id;
	private String numero;
	private double precio;
	private String fechaSalida;
	
	public Habitacion() {
		super();
	}

	public Habitacion(String id, String numero, double precio, String fechaSalida) {
		super();
		this.id = id;
		this.numero = numero;
		this.precio = precio;
		this.fechaSalida = fechaSalida;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	@Override
	public String toString() {
		return "Habitacion [id=" + id + ", numero=" + numero + ", precio=" + precio + ", fechaSalida=" + fechaSalida
				+ "]";
	}

}
