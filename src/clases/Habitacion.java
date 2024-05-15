package clases;

import java.util.ArrayList;
import java.util.List;

public class Habitacion {
	private String habitacion;
	private String id_habitacion;
	private double precio;
    private String fecha_salida;
    
    /**
	 * @return the listHab
	 */
	

	
	/**
	 * @param habitacion
	 * @param id_habitacion
	 * @param precio
	 * @param fecha_salida
	 * @param listHab
	 */
	public Habitacion(String habitacion, String id_habitacion, double precio, String fecha_salida)
			{
	
		this.habitacion = habitacion;
		this.id_habitacion = id_habitacion;
		this.precio = precio;
		this.fecha_salida = fecha_salida;
		
	}

	/**
	 * @return the habitacion
	 */
	public String getHabitacion() {
		return habitacion;
	}

	/**
	 * @param habitacion the habitacion to set
	 */
	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}

	/**
	 * @return the id_habitacion
	 */
	public String getId_habitacion() {
		return id_habitacion;
	}

	/**
	 * @param id_habitacion the id_habitacion to set
	 */
	public void setId_habitacion(String id_habitacion) {
		this.id_habitacion = id_habitacion;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return the fecha_salida
	 */
	public String getFecha_salida() {
		return fecha_salida;
	}

	/**
	 * @param fecha_salida the fecha_salida to set
	 */
	public void setFecha_salida(String fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
    
	
	@Override
	public String toString() {
		return "Habitacion [habitacion=" + habitacion + ", id_habitacion=" + id_habitacion + ", precio=" + precio
				+ ", fecha_salida=" + fecha_salida + "]";
	}
    

}
