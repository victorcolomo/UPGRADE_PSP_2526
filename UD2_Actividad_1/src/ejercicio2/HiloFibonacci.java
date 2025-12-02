package ejercicio2;

public class HiloFibonacci extends Thread{

	private int n;
	
	public HiloFibonacci(int n) {
		this.n = n;
	}
	
	@Override
	public void run() {

		int n1=0;
		int n2=1;
		int suma;
		StringBuffer texto = new StringBuffer();
		texto.append("Fibonnaci de: "+n+" - > ");
		for(int i=1; i <= n; i++) {
			texto.append(n1+" ");
			suma = n1+n2;
			n1=n2;
			n2=suma;
		}
		System.out.println(texto);	
	}
}
