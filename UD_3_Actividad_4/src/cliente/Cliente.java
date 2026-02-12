package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import modelo.Empleado;
import utils.Utilidades;

public class Cliente {

	public static void mostrarMenu() {
		System.out.println("1. Listar todos los empleados.");
		System.out.println("2. Buscar un empleado por ID.");
		System.out.println("3. Buscar empleados por departamento.");
		System.out.println("4. Insertar empleado");
		System.out.println("5. Eliminar empleado");
		System.out.println("6. Salir.");
	}

	public static void main(String[] args) {

		// Conectarse al servidor
		Socket socket = new Socket();
		InetSocketAddress direccion = new InetSocketAddress(Utilidades.DIRECCION, Utilidades.PUERTO);
		int opcion = 0;

		try {
			socket.connect(direccion);
			System.out.println("CONECTADO AL SERVIDOR...");
			Utilidades utils = new Utilidades();
			// Obtener streams de los sockets
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			do {
				mostrarMenu();
				opcion = utils.leerEnteroRango("Indica una opcion[1-6]:",1,6);
				
				// Enviar la peticion
				out.writeInt(opcion);
				// Forzamos el envío de datos
				out.flush();
				
				switch(opcion) {
				case 1:
					// 1. Listar todos los empleados.
					List<Empleado> empleados = (List<Empleado>) in.readObject();
					utils.mostrarListaEmpleados(empleados);
					break;
				case 2:
					// 2. Buscar un empleado por ID.
					// Pedimos id a buscar
					int idEmpleado = utils.leerEntero("ID del empleado: ");
					out.writeInt(idEmpleado);
					out.flush();
					Empleado e = (Empleado) in.readObject();
					if(e != null) {
						System.out.println("DATOS DEL EMPLEADO");
						System.out.println(e);
					}else {
						System.out.println("[ERROR] No existe el empleado con ID: "+idEmpleado);
					}
					break;
				case 3:
					// 3. Buscar empleados por departamento.
					// Pedimos el departamento
					String departamento = utils.leerTexto("Nombre del departamento: ");
					out.writeUTF(departamento);
					out.flush();
					List<Empleado> empleadosDep = (List<Empleado>) in.readObject();
					utils.mostrarListaEmpleados(empleadosDep);
					break;
				case 4:
					// 4. Insertar empleado
					String nombre = utils.leerTexto("Nombre: ");
					String apellido = utils.leerTexto("Apellido: ");
					String dep = utils.leerTexto("Departamento: ");
					String puesto = utils.leerTexto("Puesto: ");
					double salario = utils.leerDouble("Salario: ");
					// Creamos el objeto
					Empleado nuevoEmpleado = new Empleado(nombre, apellido, dep, puesto, salario);
					// Enviamos el objeto empleado
					out.writeObject(nuevoEmpleado);
					// Recibimos respuesta
					if(in.readBoolean()) {
						System.out.println("[INFO] Empleado creado");
					}else {
						System.out.println("[INFO] No se ha podido crear el empleado");
					}
					
					break;
				case 5:
					// 5. Eliminar empleado
					int idDelete = utils.leerEntero("ID del empleado: ");
					out.writeInt(idDelete);
					out.flush();
					if(in.readBoolean()) {
						System.out.println("[INFO] Empleado eliminado");
					}else {
						System.out.println("[INFO] No se ha podido eliminar el empleado");
					}
					break;
				case 6: 
					break;
				}


			} while (opcion != 6);

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
