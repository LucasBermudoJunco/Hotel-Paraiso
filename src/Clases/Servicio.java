package Clases;

public class Servicio {
	
	private int id;
	private double precio;
	private String tipo;
	
	public Servicio() {
		super();
	}

	public Servicio(int id, double precio, String tipo) {
		super();
		this.id = id;
		this.precio = precio;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Servicio [id=" + id + ", precio=" + precio + ", tipo=" + tipo + "]";
	}

}
