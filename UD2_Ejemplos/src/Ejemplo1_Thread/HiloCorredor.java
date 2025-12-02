package Ejemplo1_Thread;

import java.util.Random;

public class HiloCorredor extends Thread{
	// Atributo estático paara guardar el nombre del ganador
	private static String ganador = null;

	public HiloCorredor(String nombre) {
		super(nombre);
	}
	
	// Sobreescrimos el método run
	@Override
	public void run() {
		Random rand = new Random();
		
		// Implementamos la lógica del hilo
		System.out.println("Empieza carrera "+this.getName());
		
		for (int km=0; km <= 5 ; km++) {
			System.out.println(this.getName()+" va por el km "+km);
		}
		System.out.println("Termina carrera "+this.getName());
		
		// Si no ha llegado nadie a la meta
		// PROBLEMA !!!!! si los dos hilo entran a la vez
		if(ganador == null) {
			ganador = this.getName();
		}
	}
	
	// Metodo static para que el hilo principal pueda consultar el ganador
	public static String getGanador() {
		return ganador;
	}
}
