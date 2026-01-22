package ejercicio2;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		try {
			Socket socket = new Socket();
			InetSocketAddress direccion = new InetSocketAddress("localhost", 2000);
			socket.connect(direccion);
			System.out.println("CONECTADO AL SERVIDOR...");
			// Obtemos los streams del socket para poder enviar y recibir informacion con el
			// servidor
			InputStream entrada = socket.getInputStream();
			OutputStream salida = socket.getOutputStream();
			String peticion="";
			
			System.out.println("Opcion [1-HORA|2-Fecha]");
			peticion = scan.nextLine();
			
			salida.write(peticion.getBytes());
			
			// Declaramos variable donde guardamos la respuesta
			byte mensaje[] = new byte[100];
			entrada.read(mensaje);
			System.out.println(new String(mensaje));
			
			salida.close();
			entrada.close();
			socket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
