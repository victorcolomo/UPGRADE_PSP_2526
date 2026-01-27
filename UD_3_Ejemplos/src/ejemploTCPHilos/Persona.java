package ejemploTCPHilos;
import java.io.Serializable;

public class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nombre;
	private String apellido;
	private int edad;
	private static int nextId = 1;
	
	public Persona(String nombre, String apellido, int edad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.id = nextId++;
	}

	@Override
	public String toString() {
		return "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad;
	}

	public int getId() {
		return id;
	}

	public int getEdad() {
		return edad;
	}
	
	
	
}
