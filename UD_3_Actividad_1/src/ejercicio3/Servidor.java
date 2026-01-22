package ejercicio3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.time.LocalDateTime;

public class Servidor {

	/**
	 * Método privado para devolver la fecha actual
	 * @return fecha
	 */
	private static String generarFecha() {
		LocalDateTime actual = LocalDateTime.now();
		int dia = actual.getDayOfMonth();
		int mes = actual.getMonthValue();
		int year = actual.getYear();
		String respuesta=String.format("DIA ACTUAL: %02d-%02d-%04d", dia,mes,year);
		return respuesta;
	}
	
	/**
	 * Método privado para devolver la hora actual
	 * @return hora
	 */
	private static String generarHora() {
		LocalDateTime actual = LocalDateTime.now();
		int hora = actual.getHour();
		int minutos = actual.getMinute();
		int segundos = actual.getSecond();
		String respuesta = String.format("HORA ACTUAL: %02d:%02d:%02d", hora,minutos,segundos);
		return respuesta;
	}

	
	public static void main(String[] args) {
		try {
			InetSocketAddress direccion = new InetSocketAddress("localhost",2000);
			DatagramSocket ds = new DatagramSocket(direccion);
			
			// 2º Recibimos datagrama del cliente
			
			// Creamos datagrama donde recibismos la peticion
			byte mensajeRecibido[] = new byte[100];
			DatagramPacket recibido = new DatagramPacket(mensajeRecibido, 100);
			// El servidor se queda a la espera de recibir el datagrama del cliente
			ds.receive(recibido);
			
			// Dependiendo del valor del texto, enviamos un dato u otro
			String textoRecibido = new String(mensajeRecibido).trim();
			
			// Tenemos que crear el datagrama que se va a enviar al cliente
			
			// 3º Enviar datagrama al cliente
			if(textoRecibido.equals("1")) {
				String respuesta = generarHora();
				DatagramPacket enviado = new DatagramPacket(respuesta.getBytes(), respuesta.length(), recibido.getAddress(), recibido.getPort());
				ds.send(enviado);
			}else if(textoRecibido.equals("2")) {
				String respuesta = generarFecha();
				DatagramPacket enviado = new DatagramPacket(respuesta.getBytes(), respuesta.length(), recibido.getAddress(), recibido.getPort());
				ds.send(enviado);
			}else {
				String respuesta = "PETICION INCORRECTA";
				DatagramPacket enviado = new DatagramPacket(respuesta.getBytes(), respuesta.length(), recibido.getAddress(), recibido.getPort());
				ds.send(enviado);
			}
			
			ds.close();
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
