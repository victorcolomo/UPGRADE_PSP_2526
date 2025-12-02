package Ejemplo2_Runnable;

public class Principal {

	public static void main(String[] args) {
		// Creamos los hilos
		HiloCorredor hilo1 = new HiloCorredor("Pepe");
		HiloCorredor hilo2 = new HiloCorredor("Juan");

		// Iniciamos los hilos
		// Podemos inicializarla al crearlos o desde el constructor
		//hilo1.getHilo().start();
		//hilo2.getHilo().start();

		// Esperar a que terminen los hilos
		try {
			hilo1.getHilo().join();
			hilo2.getHilo().join();
		} catch (InterruptedException e) {
			System.out.println("Hilo interrumpido");
			e.printStackTrace();
		}

		System.out.println("** FIN DE LA CARRERA **");

		// Mostrar el ganador
		System.out.println("-> Ganador " + HiloCorredor.getGanador());
	}
}
