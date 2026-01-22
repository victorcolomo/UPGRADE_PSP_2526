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
			
			InputStream entrada = socketCliente.getInputStream();
			
			ObjectOutputStream outOb = new ObjectOutputStream(socketCliente.getOutputStream());

			byte mensaje[] = new byte[100];
			entrada.read(mensaje);
			
			String textoId = new String(mensaje).trim();
			int id = Integer.parseInt(textoId);
			
			Persona persona = getPersona(id);
			
			outOb.writeObject(persona);
			
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
