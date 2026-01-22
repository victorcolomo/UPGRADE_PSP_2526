package ejemploUD1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(System.in);
			
			DatagramSocket datagramS = new DatagramSocket();
			
			InetAddress direccionServidor = InetAddress.getByName("localhost");
			System.out.print("Dime una frase:");
			String textoEnviar = scan.nextLine();
			
			// Enviamos datagrama
			DatagramPacket datagramaEnviar = new DatagramPacket(textoEnviar.getBytes(),textoEnviar.length(),direccionServidor, 2000);
			datagramS.send(datagramaEnviar);
			
			// Recibimos datagrama
			byte[] mensajeRecibido = new byte[100];
			DatagramPacket datagramaRecibo = new DatagramPacket(mensajeRecibido, 100);
			datagramS.receive(datagramaRecibo);
			String textoRecibido = new String(mensajeRecibido).trim();
			
			System.out.println(textoRecibido);
			
			
		} catch (IOException e) {
			System.out.println("[ERROR] Al arrancar el servidor");
			e.printStackTrace();
		}
	}
	
}
