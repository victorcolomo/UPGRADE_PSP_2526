package Ejemplo2_Runnable;

import java.util.Random;

public class HiloCorredor implements Runnable {

	// Como atributo tendrá un Thread
	private Thread hilo;
	// Atributo estático paara guardar el nombre del ganador
	private static String ganador = null;

	public HiloCorredor(String nombre) {
		hilo = new Thread(this, nombre);
		hilo.start();
	}

	@Override
	public void run() {
		Random rand = new Random();

		// Implementamos la lógica del hilo
		System.out.println("Empieza carrera " + hilo.getName());

		for (int km = 0; km <= 5; km++) {
			System.out.println(hilo.getName() + " va por el km " + km);
		}
		System.out.println("Termina carrera " + hilo.getName());

		// Si no ha llegado nadie a la meta
		// PROBLEMA !!!!! si los dos hilo entran a la vez
		if (ganador == null) {
			ganador = hilo.getName();
		}

	}
	
	public Thread getHilo() {
		return this.hilo;
	}
	
	public static String getGanador() {
		return ganador;
	}

}
