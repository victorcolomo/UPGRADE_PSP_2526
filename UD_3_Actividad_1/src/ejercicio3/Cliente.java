package ejercicio3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		

		try {
			DatagramSocket ds = new DatagramSocket();
			InetAddress destino = InetAddress.getByName("localhost");
			
			System.out.println("Opcion [1-HORA|2-Fecha]");
			String peticion = scan.nextLine();
			
			// Creamos el datagrama para enviar al servidor
			DatagramPacket dEnviado = new DatagramPacket(peticion.getBytes(), peticion.length(), destino, 2000);
			// Enviamos datagrama
			ds.send(dEnviado);
			
			// REcibimos datagrama del servidor
			byte mensaje[] = new byte[100];
			DatagramPacket dRecibido = new DatagramPacket(mensaje, 100);
			ds.receive(dRecibido);
			System.out.println(new String(mensaje).trim());
			
			ds.close();
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
