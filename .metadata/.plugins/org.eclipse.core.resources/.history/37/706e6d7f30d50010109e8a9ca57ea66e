package ejercicio1;

public class PrincipaColecta {

	
	// Constante con el nº de hilos a crear
	private static final int NUM_HILOS = 4;
	
	public static void main(String[] args) {
		// Creamos el objeto compartido
		Colecta colecta = new Colecta(2000);
		// Vector con todos los hilos
		Thread[] hilos = new Thread[NUM_HILOS];
		
		// Creamos los hilos
		for(int i = 1; i <= NUM_HILOS ; i++) {
			HiloColecta hilo = new HiloColecta("Colecta_"+i, colecta);
			hilos[i-1] = hilo;
			hilo.start();
		}
		
		// Esperamos a que todos los hilos finalicen
		for(Thread h : hilos) {
			try {
				h.join();
			} catch (InterruptedException e) {
				System.out.println("[ERROR] en el hilo "+h.getName());
				e.printStackTrace();
			}
		}
		
		// Cuando finalicen todos los hilos ponemos mensajes
		System.out.println("Finalizada colecta");
		System.out.println("Cantidad recolectada: "+colecta.getRecaudacion());
		System.out.println("Número de ingresos: "+colecta.getNumeroIngreso());

	}

}
