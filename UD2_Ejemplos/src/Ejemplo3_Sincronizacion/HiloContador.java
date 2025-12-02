package Ejemplo3_Sincronizacion;

public class HiloContador extends Thread{
	
	private Contador contador;
	private int repeticiones;
	private int miCuenta = 0;
	
	public HiloContador(String nombre, Contador contador, int repeticiones) {
		super(nombre);
		this.contador = contador;
		this.repeticiones = repeticiones;
	}
	
	@Override
	public void run() {
		for(int i= 1 ; i <= repeticiones; i++) {
			contador.incrementar();
			miCuenta++;
		}
		System.out.println("HILO "+getName()+" ha terminado mi cuenta: "+miCuenta);
	}
	

}
