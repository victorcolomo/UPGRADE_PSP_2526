package servidor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import modelo.EmpleadoDAO;
import modelo.IEmpleadoDAO;
import utils.Utilidades;

public class Servidor {

	public static void main(String[] args) {
		System.out.println("SERVIDOR ARRANCADO....");
		ServerSocket servidor;
		IEmpleadoDAO empleadoDAO = new EmpleadoDAO();
		try {
			servidor = new ServerSocket();
			// Direccion + puerto
			InetSocketAddress direccion = new InetSocketAddress(Utilidades.DIRECCION, Utilidades.PUERTO);
			// Vincular Direccion + puerto al servidor
			servidor.bind(direccion);
			
			while (true) {
				// Por cada cliente obtenemos el socket
				Socket socketCliente = servidor.accept();
				System.out.println("CLIENTE CONECTADO.....");
				// Para que se puedan ejecutar simultaneamente, creamos el hilo
				new ClienteHandler(socketCliente, empleadoDAO);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
