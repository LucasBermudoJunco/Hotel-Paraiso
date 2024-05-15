package InterfaceDAO;

public interface ClasesDAO {
	/*inicialmente, solo habrá altas y consultas, pero dejo la modificación preparada para futuras necesidades.*/
		
	public String alta(String fichero);
	public String modificacion(String fichero);
	public String consulta(String fichero);

}
