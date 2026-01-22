package ejemploUD1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Servidor {

	
	public static void main(String[] args) {
		
		try {
			InetSocketAddress direccionervidor = new InetSocketAddress("localhost", 2000);
			DatagramSocket datagramS = new DatagramSocket(direccionervidor);
			
			// Recibimos datagrama
			byte[] mensajeRecibido = new byte[100];
			DatagramPacket datagramaRecibo = new DatagramPacket(mensajeRecibido, 100);
			datagramS.receive(datagramaRecibo);
			String textoRecibo = new String(mensajeRecibido).trim();
			System.out.println("MENSAJE RECIBIDO: "+textoRecibo);
			
			// Para enviar respuesta al clinte, necesito la direccion IP y el puerto
			// por el que el cliente ha enviado el mensaje
			InetAddress direccionCliente = datagramaRecibo.getAddress();
			int puertoCliente = datagramaRecibo.getPort();
			
			// Enviar datagrama
			String textoEnviar = "Tamaño:"+textoRecibo.length();
			System.out.println(textoEnviar);
			DatagramPacket datagramaEnviar = new DatagramPacket(textoEnviar.getBytes(), textoEnviar.length()+1 ,direccionCliente, puertoCliente);
			datagramS.send(datagramaEnviar);
			
			
		} catch (IOException e) {
			System.out.println("[ERROR] Al arrancar el servidor");
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
