package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import modelo.Empleado;
import modelo.IEmpleadoDAO;

public class ClienteHandler extends Thread {

	private Socket socketCliente;
	private IEmpleadoDAO empleadoDAO;

	public ClienteHandler(Socket socketCliente, IEmpleadoDAO empleadoDAO) {
		super();
		this.socketCliente = socketCliente;
		this.empleadoDAO = empleadoDAO;
		this.start();
	}

	@Override
	public void run() {
		// Obtener streams de entrada y salida
		try {
			ObjectOutputStream out = new ObjectOutputStream(socketCliente.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socketCliente.getInputStream());
			boolean conectado = true;

			while (conectado) {
				// 1º Recibimos del cliente el tipo peticion
				int opcion = in.readInt();
				System.out.println("[INFO] Peticion: " + opcion);

				// 2º Dependiendo de la peticion se realiza una consulta u otra
				switch (opcion) {
				case 1:
					// 1. Listar todos los empleados.
					out.writeObject(empleadoDAO.obtenerTodos());
					out.flush();
					break;
				case 2:
					// 2. Buscar un empleado por ID.
					// Leemos el id del empleado
					int idEmpleado = in.readInt();
					out.writeObject(empleadoDAO.buscarPorId(idEmpleado));
					out.flush();
					break;
				case 3:
					// 3. Buscar empleados por departamento.
					// Leemos el departamento
					String departamento = in.readUTF();
					out.writeObject(empleadoDAO.buscarPorDepartamento(departamento));
					out.flush();
					break;
				case 4:
					// 4. Insertar nuevo empleado
					// Recibe el empleado
					Empleado e = (Empleado) in.readObject();
					// Realiza consulta a la BBDD y enviamos respuesta
					out.writeBoolean(empleadoDAO.insertaEmpleado(e));
					out.flush();
					break;
				case 5:
					// 5. Eliminar
					int idDelete = in.readInt();
					System.out.println("[INFO] Eliminar " + idDelete);
					out.writeBoolean(empleadoDAO.eliminarEmpleado(idDelete));
					out.flush();
					break;
				case 6:
					// Si salimos cerramos el socket
					conectado = false;
					out.close();
					in.close();
					socketCliente.close();
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ver peticion de cliente

		// Enviar respuesta
	}

}
