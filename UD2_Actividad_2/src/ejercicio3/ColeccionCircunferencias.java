package ejercicio3;

import java.util.LinkedList;
import java.util.Queue;

public class ColeccionCircunferencias {

	private Queue<Circunferencia> cola = new LinkedList<Circunferencia>();
	
	// Metodo llamado por los hilos productores
	synchronized public void addCircuferencia(Circunferencia c) {
		
		while(cola.size() > 3) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		cola.add(c);
		notifyAll();
		
	}
	// Metodo llamado por los hilos consumidores
	synchronized public Circunferencia sacarCircunferencia() {
		
		// No se pueden sacar elementos de la cola si esta vacia
		while(cola.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Circunferencia c = cola.peek();
		notifyAll();
		return c;
	}
	
}
