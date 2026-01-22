package ejercicio5;

public class PrincipalJuego {

	
	public static void main(String[] args) {
		
		final int numeroJugadores = 3;
		
		NumeroOculto numeroOculto = new NumeroOculto(numeroJugadores);
		Thread jugadores[] = new Thread[numeroJugadores];
		
		for(int i = 1; i <= numeroJugadores ; i++) {
			Jugador j = new Jugador(numeroOculto, i);
			j.start();
			jugadores[i-1] = j;
		}
		
		for(Thread h : jugadores) {
			try {
				h.join();
			} catch (InterruptedException e) {
				System.out.println("[ERROR] Hilo interrumpido");
				e.printStackTrace();
			}
		}
		
		System.out.println("[INFO] Partida finalizada");
		System.out.println("[INFO] Jugador ganador "+numeroOculto.getJugadorGanador());
		
	}
	
}
