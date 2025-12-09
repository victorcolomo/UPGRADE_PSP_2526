package ejercicio2;

public class HiloRecolector extends Thread{
	
	// Atributo el objeto compartido por todos los hilos
	private Colecta colecta;
	
	public HiloRecolector(String nombre, Colecta colecta) {
		super(nombre);
		this.colecta = colecta;
	}
	
	@Override
	public void run() {
		
		// Bucle infinito
		while(true) {
			
			// Generamos aleatorios: entre 100 y 500 ms, cantidad de entre 5 y 50€.
			int espera = (int) (Math.random() * 401) + 100;
			int cantidad = (int) (Math.random() * 46) + 5;
			
			colecta.addRecaudacion(cantidad);
			System.out.println(getName()+" ha recaudado "+cantidad);
			
			try {
				HiloRecolector.sleep(espera);
			} catch (InterruptedException e) {
				System.out.println("[ERROR] en la ejecucion del hilo "+getName());
				e.printStackTrace();
			}
		}
	
	}

}
