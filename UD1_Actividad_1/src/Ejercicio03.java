import java.io.IOException;

/**
 * EJERCICIO 3 – Escribe un programa que se ejecute desde Windows que: 

▪ Cree un proceso. 
▪ Compruebe cada 3 segundos si el proceso sigue ejecutándose, 
hasta que finalice. 
▪ Tras cada comprobación, muestre un mensaje indicando si el proceso está activo 
o ha terminado. 
▪ Para hacer una pausa con una duración determinada, se puede utilizar 
Thread. sleep(int tiempo ms). 
 */
public class Ejercicio03 {
	public static void main(String[] args) {
		
		ProcessBuilder pBuilder = new ProcessBuilder("notepad");
		
		try {
			Process proceso = pBuilder.start();
			long pid = proceso.pid();
			
			while(proceso.isAlive()) {
				System.out.println("Proceso "+pid+" está vivo");
				Thread.sleep(3000);
			}
			
			int codigo = proceso.exitValue();
			System.out.println("El proceso ha terminado con el código "+codigo);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
