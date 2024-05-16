package interfaceDAO;

public interface ClasesDAO {
	
	/*inicialmente, solo habrá consultas y altas, pero dejo la modificación preparada para futuras necesidades.*/
	public void read(String fichero);
	public void create(String fichero);
	public void update(String fichero);
	public void delete(String fichero);

}
