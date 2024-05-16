package clases;

public class Habitacion {
	private String habitacion;
	private String id_habitacion;
	private double precio;
    private String fecha_salida;
	
	public Habitacion(String habitacion, String id_habitacion, double precio, String fecha_salida){
		this.habitacion = habitacion;
		this.id_habitacion = id_habitacion;
		this.precio = precio;
		this.fecha_salida = fecha_salida;
		
	}
	
	public String getHabitacion() {
		return habitacion;
	}
	
	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}

	public String getId_habitacion() {
		return id_habitacion;
	}
	
	public void setId_habitacion(String id_habitacion) {
		this.id_habitacion = id_habitacion;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getFecha_salida() {
		return fecha_salida;
	}
	
	public void setFecha_salida(String fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	
	@Override
	public String toString() {
		return "Habitacion [habitacion=" + habitacion + ", id_habitacion=" + id_habitacion + ", precio=" + precio
				+ ", fecha_salida=" + fecha_salida + "]";
	}
    

}
