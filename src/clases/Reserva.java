package clases;

public class Reserva {
	
	private int id_reserva;
	private String habitacion;
	private String fecha_entrada;
	private String fecha_salida;
	private String doc_identidad;
	 
	public Reserva(int id_reserva, String habitacion, String fecha_entrada, String fecha_salida, String doc_identidad) {
		super();
		this.id_reserva = id_reserva;
		this.habitacion = habitacion;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.doc_identidad = doc_identidad;
	}
	
	// Constructor sin el id_reserva para poder usarlo para inserciones en BBDD
	// (ya que el id_reserva es autoincrementado)
	public Reserva(String habitacion, String fecha_entrada, String fecha_salida, String doc_identidad) {
		super();
		this.habitacion = habitacion;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.doc_identidad = doc_identidad;
	}
	
	public int getId_reserva() {
		return id_reserva;
	}
	
	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}
	
	public String getHabitacion() {
		return habitacion;
	}
	
	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}
	
	public String getFecha_entrada() {
		return fecha_entrada;
	}
	
	public void setFecha_entrada(String fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}
	
	public String getFecha_salida() {
		return fecha_salida;
	}
	
	public void setFecha_salida(String fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	
	public String getDoc_identidad() {
		return doc_identidad;
	}
	
	public void setDoc_identidad(String doc_identidad) {
		this.doc_identidad = doc_identidad;
	}
	
	public String toString() {
		return "Reserva [id_reserva=" + id_reserva + ", habitacion=" + habitacion + ", fecha_entrada=" + fecha_entrada
				+ ", fecha_salida=" + fecha_salida + ", doc_identidad=" + doc_identidad + "]";
	}
	   
}
