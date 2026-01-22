package ejercicio5;


/*
 * □ NumeroOculto: se definen los siguientes atributos: el número total de jugadores, el turno 
 * (jugador que esta jugando), el número a adivinar (generado aleatoriamente) y si el juego acabó o no. 
 * En el constructor se recibe el número de jugadores que participan y se inicializan el número a adivinar 
 * y el turno. 
Tiene varios métodos: uno que devuelve el turno, otro que indica si el juego se acabó o no y 
el tercer método que comprueba la jugada del jugador y averigua a quien le toca a continuación, 
este método recibirá el identificador de jugador y el número que ha jugado. 
En este método se indicará cual es el siguiente turno y si el juego ha finalizado 
porque algún jugador ha acertado el número.

 * 
 * 
 */
public class NumeroOculto {

	private int numeroJugadores;
	private int turno;
	private int numeroOculto;
	private boolean finJuego;
	private int jugadorGanador;
	
	public NumeroOculto(int numeroJugadores) {
		this.numeroJugadores = numeroJugadores;
		this.turno = 1;
		this.numeroOculto = (int)(Math.random()*20) + 1;
		System.out.println("[INFO] Número oculto "+this.numeroOculto);
		this.finJuego = false;
	}
	
	public synchronized int getTurno() {
		return this.turno;
	}
	
	public synchronized boolean isFinJuego() {
		return this.finJuego;
	}
	
	public synchronized int getJugadorGanador() {
		return this.jugadorGanador;
	}
	
	public synchronized void comprobarNumero(int jugador, int numeroJugador) {
		
		// Comprobar si el el turno del jugador
		if( jugador == getTurno()) {
			// Si es el turno del jugador
			System.out.println("[INFO] Jugador "+jugador+" su numero "+numeroJugador);
			// Comprobar si ha adivinado el numero
			if(numeroJugador == numeroOculto) {
				System.out.println("[INFO] Jugador "+jugador+ " ha acertado!!!");
				// Finaliza la partida
				this.finJuego = true;
				// Actualizamos el ganador
				this.jugadorGanador = jugador;
			}else {
				this.turno++;
				// Si es el ultimo, el turno "vuelve" al primero
				if(this.turno > numeroJugadores) {
					this.turno = 1;
				}
				// Cuando "cambiamos" el turno, activamos a los hilos que están dormidos
				notifyAll();
			}
		}else {
			// si no es su turno, dormirmos el hilos
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("[ERROR] Hilo interrumpido");
				e.printStackTrace();
			}
		}
		
		
	}
	
	
}
