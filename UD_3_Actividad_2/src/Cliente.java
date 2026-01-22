import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		try {
			// Conectamos al servidor
			Socket socket = new Socket();
			InetSocketAddress direccion = new InetSocketAddress("localhost", 2000);

			socket.connect(direccion);

			System.out.println("CONECTADO AL SERVIDOR...");

			// Obtemos los streams del socket para poder enviar y recibir informacion con el
			// servidor
			OutputStream salida = socket.getOutputStream();
			ObjectInputStream inOb = new ObjectInputStream(socket.getInputStream());

			String peticion = "";

			System.out.println("Id de la persona: ");
			peticion = scan.nextLine();

			salida.write(peticion.getBytes());

			// Enviar id de la pesona
			Persona persona = (Persona) inOb.readObject();
			
			if(persona == null) {
				System.out.println("No existe la persona con el ID: "+peticion);
			}else {
				System.out.println(persona);
			}

			// Recibir objeto del servidor

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
