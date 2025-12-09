package Ejemplo4_Productor_Consumidor;

import java.util.LinkedList;
import java.util.Queue;

public class BufferFrases {

	private Queue<String> cola = new LinkedList<String>();
	
	// Metodo para el hilo productor: recibe un texto y lo añade a la cola
	public synchronized void poner(String frase) {
		// Capacidad maxima 2
		while(cola.size() >= 2) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cola.add(frase);
		// Los productos después de añadir un frase a la cola, usan notify / notifyAll
		// para "activar" a los consumidor que esten a la espera (wait)
		notifyAll();
	}
	
	// Metodo para el hilo consumidor: saca una frase de la cola
	public synchronized String sacar() {
		
		// Si la cola esta vacía, los hilos consumidores se quedarán a la espera
		while(cola.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String frase = cola.remove();
		
		// "Despertamos" a los hilos productos por si estan en wait por superar la capacidad máxima
		notifyAll();
		return frase;
	}
	
	
}
