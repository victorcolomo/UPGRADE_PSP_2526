package utils;

import java.util.List;
import java.util.Scanner;

import modelo.Empleado;

public class Utilidades {
	public static final int PUERTO = 5000;
	public static final String DIRECCION = "localhost";
	
	
	private final Scanner scan; // Cada cliente tenga su propio Scanner
	
	
	public Utilidades() {
		this.scan = new Scanner(System.in);
	}
	
	public double leerDouble(String mensaje) {
		double num = 0;
		boolean error = false;
		do {
			error = false;
			try {
				System.out.print(mensaje);
				num = Double.parseDouble(scan.nextLine());
				if(num < 0) {
					System.out.println("[ERROR] Número incorrrecto");
					error = true;
				}
			}catch (Exception e) {
				System.out.println("[ERROR] Número incorrrecto");
				error = true;
			}
			
		}while(error);
		return num;
	}
	
	public int leerEntero(String mensaje) {
		int num = 0;
		boolean error = false;
		do {
			error = false;
			try {
				System.out.print(mensaje);
				num = Integer.parseInt(scan.nextLine());
			}catch (Exception e) {
				System.out.println("[ERROR] Número incorrrecto");
				error = true;
			}
			
		}while(error);
		return num;
	}
	
	public int leerEnteroRango(String mensaje, int min, int max) {
		int num = 0;
		boolean error = false;
		do {
			error = false;
			try {
				System.out.print(mensaje);
				num = Integer.parseInt(scan.nextLine());
				if(num < min || num > max) {
					System.out.println("[ERROR] Valor incorrecto debe estar entre "+min+ " y "+max);
					error = true;
				}
			}catch (Exception e) {
				System.out.println("[ERROR] Número incorrrecto");
				error = true;
			}
		}while(error);
		return num;
	}
	
	public String leerTexto(String mensaje) {
		String texto = "";
		do {
				System.out.print(mensaje);
				texto = scan.nextLine();
				if(texto.trim().isEmpty()) {
					System.out.println("[ERROR] El texto no puede estar vacío");
				}
		}while(texto.isEmpty());
		return texto;
	}
	
	public void mostrarListaEmpleados(List<Empleado> empleados) {
		if(empleados.isEmpty()) {
			System.out.println("[INFO] No hay coincidencias");
		}
		for (Empleado e : empleados) {
			System.out.println(e);
		}
	}
	
	
	
}
