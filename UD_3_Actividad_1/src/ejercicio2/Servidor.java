package ejercicio2;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * Servidor para procesar peticiones de fecha y hora
 * @author Victor Colomo
 * @version 1.0
 */
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

	
	/**
	 * Metodo principal del servidor
	 * @param args argumentos del programa
	 */
	public static void main(String[] args) {

		try {
			// Creamos objeto para el servidor Socket
			ServerSocket servidor = new ServerSocket();
			// Direccion + puerto
			InetSocketAddress direccion = new InetSocketAddress("localhost", 2000);
			// Vincular Direccion + puerto al servidor
			servidor.bind(direccion);

			System.out.println("SERVIDOR ARRANCADO.....");

			Socket socketCliente = servidor.accept();
			System.out.println("CLIENTE CONECTADO.....");

			// Obtemos los streams del socket para poder enviar y recibir informacion con el
			// servidor
			InputStream entrada = socketCliente.getInputStream();
			OutputStream salida = socketCliente.getOutputStream();

			String texto = "";

			// Leemos la peticion que nos envia el cliente
			// Declara una variable donde guardamos el mensaje del cliente
			byte[] mensaje = new byte[100];
			// Leemos la peticion que nos envia el cliente y lo guardamos en mensaje
			entrada.read(mensaje);

			texto = new String(mensaje).trim();
			System.out.println("PETICION RECIBIDA: " + texto);
			
			// Si es "1" -> Hora
			if(texto.equals("1")) {
				salida.write(generarHora().getBytes());
			}else if(texto.equals("2")) {
				// Si es "2" -> Fecha
				salida.write(generarFecha().getBytes());
			}else {
				salida.write("PETICION INCORRECTA".getBytes());
			}
			
			// Cuando terminamos la comunicacion cerramos todo
			salida.close();
			entrada.close();
			socketCliente.close();
			servidor.close();

		} catch (IOException e) {
			System.out.println("[ERROR] Al iniciar servidor");
			e.printStackTrace();
		}

	}

}
