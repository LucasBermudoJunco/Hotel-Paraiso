package Clases;

public class Servicio {
	
	private int id;
	private String tipo;
	private double precio;
	
	public Servicio() {
		super();
	}

	public Servicio(int id, String tipo, double precio) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Servicio [id=" + id + ", tipo=" + tipo + ", precio=" + precio + "]";
	}
}
