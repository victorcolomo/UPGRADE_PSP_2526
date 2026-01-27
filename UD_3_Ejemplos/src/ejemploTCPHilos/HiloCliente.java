package ejemploTCPHilos;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloCliente extends Thread {

	private Socket socketCliente;
	private GestionPersonas personas;

	public HiloCliente(Socket socketCliente, GestionPersonas personas) {
		this.socketCliente = socketCliente;
		this.personas = personas;
		this.start();  // Iniciamos el hilo
	}

	@Override
	public void run() {

		try {
		// Obtemos el flujo de stream 
		ObjectInputStream inOb = new ObjectInputStream(socketCliente.getInputStream());
		ObjectOutputStream outOb = new ObjectOutputStream(socketCliente.getOutputStream());

		while (true) {
			// La peticion la recibimos en formato text
			String peticion = (String) inOb.readObject();
			String partes[] = peticion.split(",");

			System.out.println("[PETICION]" + partes[0]);

			if (partes[0].equalsIgnoreCase("BUSCAR")) { // BUSCAR,id
				int id = Integer.parseInt(partes[1]);
				outOb.writeObject(personas.getPersona(id));
			} else if (partes[0].equalsIgnoreCase("MOSTRAR_TODOS")) { // MOSTRAR_TODOS
				outOb.writeObject(personas.getTodos());
			} else if (partes[0].equalsIgnoreCase("ADD")) { // ADD, nombre, apellido,edad
				String nombre = partes[1];
				String apellidos = partes[2];
				int edad = Integer.parseInt(partes[3]);
				personas.addPersona(nombre, apellidos, edad);
				outOb.writeObject("Persona añadida correctamente");
			} else if (partes[0].equalsIgnoreCase("ELIMINAR")) {
				int idEliminar = Integer.parseInt(partes[1]);
				System.out.println("[ELIMINAR]" + idEliminar);
				if (personas.eliminarPersona(idEliminar)) {
					outOb.writeObject("Persona eliminada correctamente");
				} else {
					outOb.writeObject("Persona con id " + idEliminar + " no existe");
				}
			} else if (partes[0].equalsIgnoreCase("MAYORES_EDAD")) {
				outOb.writeObject(personas.mayoresEdad());
			} else if (partes[0].equalsIgnoreCase("SALIR")) {
				break;
			}
		}
		
		}catch(Exception e) {
			
		}
	}

}
