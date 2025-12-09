package ejercicio2;

public class HiloConsumidor extends Thread {

	// Atributo el objeto compartido por todos los hilos
	private Colecta colecta;

	public HiloConsumidor(String nombre, Colecta colecta) {
		super(nombre);
		this.colecta = colecta;
	}

	@Override
	public void run() {
		// Bucle infinito
		while (true) {
			// Generamos aleatorios: entre 100 y 400, a intervalos de tiempo aleatorios entre 20 y 300 ms
			int espera = (int) (Math.random() * 281) + 20;
			int cantidad = (int) (Math.random() * 301) + 100;

			colecta.sacarRecaudacion(cantidad);
			System.out.println(getName() + " ha sacado " + cantidad);

			try {
				HiloRecolector.sleep(espera);
			} catch (InterruptedException e) {
				System.out.println("[ERROR] en la ejecucion del hilo " + getName());
				e.printStackTrace();
			}
		}
	}
}
