import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

	private static ArrayList<Persona> personas = new ArrayList<Persona>();

	private static void cargaInicial() {
		personas.add(new Persona("pepe", "perez", 20));
		personas.add(new Persona("ana", "lopez", 22));
		personas.add(new Persona("jose", "gomez", 15));
	}

	private synchronized static Persona getPersona(int id) {
		for(Persona p : personas) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	
	private synchronized static boolean eliminarPersona(int id) {
		return personas.removeIf(p -> p.getId() == id);	
	}
	
	private synchronized static ArrayList<Persona> mayoresEdad() {
		ArrayList<Persona> mayores = new ArrayList<Persona>();
		
		for(Persona p : personas) {
			if(p.getEdad() >= 18) {
				mayores.add(p);
			}
		}
		return mayores;
	}
	

	public static void main(String[] args) {
		cargaInicial();
		try {
			// Creamos objeto para el servidor Socket
			ServerSocket servidor;

			servidor = new ServerSocket();

			// Direccion + puerto
			InetSocketAddress direccion = new InetSocketAddress("localhost", 2000);
			// Vincular Direccion + puerto al servidor
			servidor.bind(direccion);

			System.out.println("SERVIDOR ARRANCADO.....");
			Socket socketCliente = servidor.accept();
			System.out.println("CLIENTE CONECTADO.....");
			
			ObjectInputStream inOb = new ObjectInputStream(socketCliente.getInputStream());
			ObjectOutputStream outOb = new ObjectOutputStream(socketCliente.getOutputStream());

			while(true) {
				// La peticion la recibimos en formato text
				String peticion = (String) inOb.readObject();
				String partes[] = peticion.split(",");
				
				System.out.println("[PETICION]"+partes[0]);
					
				if ( partes[0].equalsIgnoreCase("BUSCAR")) {  // BUSCAR,id
					int id = Integer.parseInt(partes[1]);
					outOb.writeObject(getPersona(id));
				} else if (partes[0].equalsIgnoreCase("MOSTRAR_TODOS")) { // MOSTRAR_TODOS
					outOb.writeObject(new ArrayList<>(personas));
				} else if(partes[0].equalsIgnoreCase("ADD")) { // ADD, nombre, apellido,edad
					String nombre = partes[1];
					String apellidos = partes[2];
					int edad = Integer.parseInt(partes[3]);
					personas.add(new Persona(nombre, apellidos, edad));
					outOb.writeObject("Persona añadida correctamente");
				}else if(partes[0].equalsIgnoreCase("ELIMINAR")) {
					int idEliminar = Integer.parseInt(partes[1]);
					System.out.println("[ELIMINAR]"+idEliminar);
					if(eliminarPersona(idEliminar)) {
						outOb.writeObject("Persona eliminada correctamente");
					}else {
						outOb.writeObject("Persona con id "+idEliminar+" no existe");
					}
				} else if(partes[0].equalsIgnoreCase("MAYORES_EDAD")) {
					outOb.writeObject(mayoresEdad());
				}else if(partes[0].equalsIgnoreCase("SALIR")) {
					break;
				}
			
			}
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
