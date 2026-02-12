package modelo;

import java.io.Serializable;

// Clase POJO
public class Empleado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1050366744287801879L;
	
	private int id;	// Autoincremental
	private String nombre;
	private String apellido;
	private String departamento;
	private String puesto;
	private double salario;
	
	public Empleado(String nombre, String apellido, String departamento, String puesto, double salario) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.departamento = departamento;
		this.puesto = puesto;
		this.salario = salario;
	}
	
	public Empleado(int id, String nombre, String apellido, String departamento, String puesto, double salario) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.departamento = departamento;
		this.puesto = puesto;
		this.salario = salario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", departamento=" + departamento
				+ ", puesto=" + puesto + ", salario=" + salario;
	}
}