package Ejemplo4_Productor_Consumidor;

public class Productor extends Thread {

	private BufferFrases frases;
	private String frase;
	
	public Productor(String nombre, BufferFrases frases, String frase) {
		super(nombre);
		this.frases = frases;
		this.frase = frase;
	}
	
	@Override
	public void run() {
		frases.poner(frase);
		System.out.println(getName()+" ha dejado la frase "+frase);
	}
	
}
