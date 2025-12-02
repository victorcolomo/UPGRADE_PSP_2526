package Ejemplo1_Thread;

public class Principal {

	public static void main(String[] args) {
		
		// Creamos los hilos
		HiloCorredor hilo1 = new HiloCorredor("Pepe");
		HiloCorredor hilo2 = new HiloCorredor("Juan");
		
		// Iniciamos los hilos
		hilo1.start();
		hilo2.start();

		// Esperar a que terminen los hilos
		try {
			hilo1.join();
			hilo2.join();
		} catch (InterruptedException e) {
			System.out.println("Hilo interrumpido");
			e.printStackTrace();
		}
		
		System.out.println("** FIN DE LA CARRERA **");
		
		// Mostrar el ganador
		System.out.println("-> Ganador "+HiloCorredor.getGanador());
		
	}

}
