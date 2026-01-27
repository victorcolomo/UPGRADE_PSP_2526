package ejemploTCPHilos;

import java.util.ArrayList;

public class GestionPersonas {

	private ArrayList<Persona> personas = new ArrayList<Persona>();
	
	public GestionPersonas() {
		cargaInicial();
	}
	private void cargaInicial() {
		personas.add(new Persona("pepe", "perez", 20));
		personas.add(new Persona("ana", "lopez", 22));
		personas.add(new Persona("jose", "gomez", 15));
	}

	public synchronized Persona getPersona(int id) {
		for(Persona p : personas) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	
	public ArrayList<Persona> getTodos(){
		return new ArrayList<>(personas);
	}
	
	public synchronized void addPersona(String nombre, String apellido, int edad) {
		personas.add(new Persona(nombre, apellido, edad));
	}
	
	public synchronized boolean eliminarPersona(int id) {
		return personas.removeIf(p -> p.getId() == id);	
	}
	
	public synchronized ArrayList<Persona> mayoresEdad() {
		ArrayList<Persona> mayores = new ArrayList<Persona>();
		
		for(Persona p : personas) {
			if(p.getEdad() >= 18) {
				mayores.add(p);
			}
		}
		return mayores;
	}
	
}
