package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO implements IEmpleadoDAO{

	// 1. Listar todos los empleados.
	public synchronized List<Empleado> obtenerTodos() {
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		try {
			// Obtenemos la conexion a la BBDD
			Connection con = DBConnection.getConnection();
			// String con la consulta a realizar
			String sql = "SELECT * FROM empleados";
			// Statement: objeto que nos permitira realizar consultas SQL
			Statement st = con.createStatement();
			// Ejecutamos la consulta select y nos devuelve un ResulSet
			// Result set es un cursos o iterados
			ResultSet rs = st.executeQuery(sql);

			// Recorremos todos los registros de la consulta
			while (rs.next()) {
				// Creo un empleado y lo añado al arraylist
				Empleado e = mapearEmpleado(rs);
				listaEmpleados.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEmpleados;
	}

	// 2. Buscar un empleado por ID.
	public synchronized Empleado buscarPorId(int idEmpleado) {
		
		try {
			// Obtenemos la conexion a la BBDD
			Connection con = DBConnection.getConnection();
			// Por cada parametro de la consulta ponemos un ?
			String sql = "SELECT * FROM empleados WHERE id=?";
			// Creamos consulta preparada
			PreparedStatement ps = con.prepareStatement(sql);
			// Reemplazamos cada ? por su valor
			ps.setInt(1, idEmpleado);
			
			// Ejecutamos la consulta preparada y nos devuelve un ResulSet
			// Result set es un cursos o iterados
			ResultSet rs = ps.executeQuery();

			// Comprobamos si la consulta devuelve un registro
			if (rs.next()) {
				// De cada registro obtenemos los campos
				// Creo un empleado y lo añado al arraylist
				Empleado e = mapearEmpleado(rs);;
				return e;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 3. Buscar empleados por departamento.
	public synchronized List<Empleado> buscarPorDepartamento(String departamento) {
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		try {
			// Obtenemos la conexion a la BBDD
			Connection con = DBConnection.getConnection();
			// Por cada parametro de la consulta ponemos un ?
			String sql = "SELECT * FROM empleados WHERE departamento=?";
			// Creamos consulta preparada
			PreparedStatement ps = con.prepareStatement(sql);
			// Reemplazamos cada ? por su valor
			ps.setString(1, departamento);
			
			// Ejecutamos la consulta preparada y nos devuelve un ResulSet
			// Result set es un cursos o iterados
			ResultSet rs = ps.executeQuery();

			// Recorremos todos los registros de la consulta
			while (rs.next()) {
				// De cada registro obtenemos los campos
				Empleado e = mapearEmpleado(rs);
				listaEmpleados.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEmpleados;
	}
	
	private Empleado mapearEmpleado(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String nombre = rs.getString("nombre");
		String apellido = rs.getString("apellido");
		String departamento1 = rs.getString("departamento");
		String puesto = rs.getString("puesto");
		double salario = rs.getDouble("salario");
		// Creo un empleado y lo añado al arraylist
		Empleado e = new Empleado(id, nombre, apellido, departamento1, puesto, salario);
		return e;
	}

	@Override
	public synchronized boolean eliminarEmpleado(int idEmpleado) {
		try {
			// Obtenemos la conexion a la BBDD
			Connection con = DBConnection.getConnection();
			// Por cada parametro de la consulta ponemos un ?
			String sql = "DELETE FROM empleados WHERE id=?";
			// Creamos consulta preparada
			PreparedStatement ps = con.prepareStatement(sql);
			// Reemplazamos cada ? por su valor
			ps.setInt(1, idEmpleado);
			
			// Ejecutamos la consulta preparada y nos devuelve un entero
			// que nos indica los registros afectados
			int filas = ps.executeUpdate();
			
			return filas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public synchronized boolean insertaEmpleado(Empleado nuevoEmpleado) {
		try {
			// Obtenemos la conexion a la BBDD
			Connection con = DBConnection.getConnection();
			// Por cada parametro de la consulta ponemos un ?
			String sql = "INSERT INTO empleados (nombre, apellido, departamento, puesto, salario) VALUES (?,?,?,?,?)";
			// Creamos consulta preparada
			PreparedStatement ps = con.prepareStatement(sql);
			// Reemplazamos cada ? por su valor
			ps.setString(1, nuevoEmpleado.getNombre());
			ps.setString(2, nuevoEmpleado.getApellido());
			ps.setString(3, nuevoEmpleado.getDepartamento());
			ps.setString(4, nuevoEmpleado.getPuesto());
			ps.setDouble(5, nuevoEmpleado.getSalario());
			
			// Ejecutamos la consulta preparada y nos devuelve un entero
			// que nos indica los registros afectados
			int filas = ps.executeUpdate();
			
			return filas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
