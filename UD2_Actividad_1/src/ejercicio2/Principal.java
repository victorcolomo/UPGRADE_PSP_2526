package ejercicio2;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		
		HiloFibonacci f4 = new HiloFibonacci(4);
		HiloFibonacci f7 = new HiloFibonacci(7);
		
		f4.start();
		//f4.join();
		f7.start();
		//f7.join();
		
	}
	
}
