/*
 * Crea un programa Potencia.java que reciba dos argumentos enteros: 
 * la base y el exponente, que calcule la potencia (base^exponente) 
 * y muestre el resultado por pantalla
 */

import java.util.Scanner;

public class Potencia {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		if(args.length != 2) {
			System.err.println("Debe recibir dos argumentos");
			System.exit(1);
		}
		int base = 0;
		int exponente = 0;
		
		try {
			base = Integer.parseInt(args[0]);
			exponente = Integer.parseInt(args[1]);
		} catch (Exception e) {
			System.err.println("Los parámetros debe ser números enteros");
			System.exit(2);
		}
		
		double resultado = Math.pow(base, exponente);
		System.out.println(resultado);
		
	}
	
}
