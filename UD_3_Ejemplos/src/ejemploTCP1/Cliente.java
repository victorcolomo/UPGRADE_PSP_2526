package ejemploTCP1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {

		try {
			Scanner scan = new Scanner(System.in);
			
			// Creamos socket para conectarnos con el servidor
			Socket cliente = new Socket();
			// Direccion + Puerto del servidor
			InetSocketAddress direccionervidor = new InetSocketAddress("localhost", 2000);

			cliente.connect(direccionervidor);
			System.out.println("CONECTADOS AL SERVIDOR");
			
			// Obtemos los streams del socket para poder enviar y recibir informacion con el servidor
			InputStream entrada = cliente.getInputStream();
			OutputStream salida = cliente.getOutputStream();
			
			String texto = "";
			
			while( !texto.equalsIgnoreCase("FIN") ) {
				System.out.println("Dime una frase");
				texto = scan.nextLine();
				
				// Enviamos mensaje
				salida.write(texto.getBytes());
				
				// Recibe mensaje del servidor
				byte[] respuesta = new byte[100];
				entrada.read(respuesta);
				
				System.out.println(new String(respuesta));
			}
			
			// Cuando terminamos la comunicacion cerramos todo
			salida.close();
			entrada.close();
			cliente.close();
			
			
			
		} catch (IOException e) {
			System.out.println("ERROR Al conectarse con el servidor");
			e.printStackTrace();
		}

	}
}
