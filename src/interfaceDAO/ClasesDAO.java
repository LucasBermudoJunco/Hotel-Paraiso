package interfaceDAO;

public interface ClasesDAO {
	
	/*inicialmente, solo habrá consultas y altas, pero dejo la modificación preparada para futuras necesidades.*/
	public boolean read(String fichero);
	public boolean create(String fichero);
	public boolean update(String fichero);
	public boolean delete(String fichero);

}
