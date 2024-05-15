package clases;

public class Reserva {
	 private int id_reserva;
	 private String habitacion;
	 private String fecha_entrada;
	 private String fecha_salida;
	 private String doc_identidad;
	 
	 
	 
	 
	 
	/**
	 * @param id_reserva
	 * @param habitacion
	 * @param fecha_entrada
	 * @param fecha_salida
	 * @param doc_identidad
	 */
	public Reserva(int id_reserva, String habitacion, String fecha_entrada, String fecha_salida, String doc_identidad) {
		super();
		this.id_reserva = id_reserva;
		this.habitacion = habitacion;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.doc_identidad = doc_identidad;
	}





	/**
	 * @return the id_reserva
	 */
	public int getId_reserva() {
		return id_reserva;
	}





	/**
	 * @param id_reserva the id_reserva to set
	 */
	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
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
	 * @return the fecha_entrada
	 */
	public String getFecha_entrada() {
		return fecha_entrada;
	}





	/**
	 * @param fecha_entrada the fecha_entrada to set
	 */
	public void setFecha_entrada(String fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
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





	/**
	 * @return the doc_identidad
	 */
	public String getDoc_identidad() {
		return doc_identidad;
	}





	/**
	 * @param doc_identidad the doc_identidad to set
	 */
	public void setDoc_identidad(String doc_identidad) {
		this.doc_identidad = doc_identidad;
	}





	@Override
	public String toString() {
		return "Reserva [id_reserva=" + id_reserva + ", habitacion=" + habitacion + ", fecha_entrada=" + fecha_entrada
				+ ", fecha_salida=" + fecha_salida + ", doc_identidad=" + doc_identidad + "]";
	}
	 
	 
	   
}
