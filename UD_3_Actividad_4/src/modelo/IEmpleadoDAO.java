package modelo;

import java.util.List;

public interface IEmpleadoDAO {
	List<Empleado> obtenerTodos();
	Empleado buscarPorId(int idEmpleado);
	List<Empleado> buscarPorDepartamento(String departamento);
	boolean eliminarEmpleado(int idEmpleado);
	boolean insertaEmpleado(Empleado nuevoEmpleado);
	
}
