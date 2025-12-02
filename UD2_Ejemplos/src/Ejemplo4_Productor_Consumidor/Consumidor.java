package Ejemplo4_Productor_Consumidor;

public class Consumidor extends Thread{
	
	private BufferFrases frases;
	
	public Consumidor(String nombre, BufferFrases frases) {
		super(nombre);
		this.frases = frases;
	}
	
	@Override
	public void run() {
		String  f = frases.sacar();
		System.out.println(getName()+" ha codigo la frase "+f);
	}
	
}
