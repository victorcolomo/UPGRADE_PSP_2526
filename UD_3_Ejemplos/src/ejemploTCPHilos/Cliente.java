package ejemploTCPHilos;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		try {
			// Conectamos al servidor
			Socket socket = new Socket();
			InetSocketAddress direccion = new InetSocketAddress("localhost", 2000);
			int opcion;
			String peticion = "";
			
			socket.connect(direccion);

			System.out.println("CONECTADO AL SERVIDOR...");

			// Obtemos los streams del socket para poder enviar y recibir informacion con el
			// servidor
			ObjectOutputStream outOb = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inOb = new ObjectInputStream(socket.getInputStream());
			
			do {
			// MENU
			System.out.println("== MENU ===");
			System.out.println("1 - Buscar Por id"); // BUSCAR,id
			System.out.println("2 - Mostrar todos"); // MOSTRAR
			System.out.println("3 - Añadir persona"); // ADD, nombre, apellido,edad
			System.out.println("4 - Eliminar persona");
			System.out.println("5 - Mayores de edad");
			System.out.println("6 - Salir");
			opcion = Integer.parseInt(scan.nextLine());
			
			switch(opcion) {
				case 1:
					System.out.println("Id de la persona: ");
					int id = Integer.parseInt(scan.nextLine());
					// Enviamos
					// BUSCAR,id
					outOb.writeObject("BUSCAR,"+id);
					// Respuesta del servidor sera un objeto de tipo Persona
					Persona persona = (Persona) inOb.readObject();
					if(persona == null) {
						System.out.println("No existe la persona con el ID: "+peticion);
					}else {
						System.out.println(persona);
					}
					break;
				case 2:
					// Enviamos
					// MOSTRAR_TODOS
					outOb.writeObject("MOSTRAR_TODOS");
					// Recibimos del servidor el arraylist
					ArrayList<Persona> personas = (ArrayList<Persona>) inOb.readObject();
					for(Persona p : personas) {
						System.out.println(p);
					}
					break;
				case 3:
					System.out.print("Nombre:");
					String nombre = scan.nextLine();
					System.out.print("Apellido:");
					String apellido = scan.nextLine();
					System.out.print("Edad:");
					int edad = Integer.parseInt(scan.nextLine());
					// ADD, nombre, apellido,edad
					outOb.writeObject("ADD,"+nombre+","+apellido+","+edad);
					System.out.println(inOb.readObject());
					break;
				case 4:
					System.out.println("Id de la persona: ");
					int idEliminar = Integer.parseInt(scan.nextLine());
					// Enviamos
					// ELIMINAR,id
					outOb.writeObject("ELIMINAR,"+idEliminar);
					System.out.println(inOb.readObject());
					break;
				case 5:
					outOb.writeObject("MAYORES_EDAD");
					// Recibimos del servidor el arraylist
					ArrayList<Persona> mayores = (ArrayList<Persona>) inOb.readObject();
					if(mayores.isEmpty()) {
						System.out.println("No hay personas mayores de edad");
					}
					for(Persona p : mayores) {
						System.out.println(p);
					}
					break;
				case 6:
					System.out.println("Salimos..");
					outOb.writeObject("SALIR");
					break;
				default:
					System.out.println("[ERROR] opcion incorrecta");
				}
			
			} while(opcion != 6);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
