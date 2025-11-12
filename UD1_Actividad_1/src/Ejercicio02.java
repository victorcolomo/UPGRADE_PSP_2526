/*
 * EJERCICIO 2 – Escribe un programa que se ejecute desde Linux que: 

▪ Establezca el directorio de trabajo de ProcessBuilder 
▪ Recupere y muestre la ruta del directorio de trabajo 
▪ Cree un proceso y muestre su PID. 
▪ Detenga la ejecución del proceso durante 2 segundos utilizando, 
puedes utilizar Thread.sleep(int tiempo ms). 
▪ Compruebe si el proceso está vivo con el método isAlive():  
Si está vivo lo finalizará y se mostrará el mensaje "Terminamos el proceso de forma manual",  
Si ya ha finalizado, mostrará el mensaje "El proceso ha finalizado".
 */

import java.io.File;
import java.io.IOException;

public class Ejercicio02 {
	
	public static void main(String[] args) {
		
		String comando = "gedit";
		ProcessBuilder pBuilder = new ProcessBuilder(comando);
		
		pBuilder.directory(new File("/home"));
		
		try {
			Process proceso = pBuilder.start();
			
			File fichero = pBuilder.directory();
			System.out.println("RUTA: "+fichero.getAbsolutePath());
			
			long pid = proceso.pid();
			System.out.println("PID: "+pid);
			
			Thread.sleep(2000);
			
			if(proceso.isAlive()) {
				System.out.println("Terminamos el proceso de forma manual");
				proceso.destroy();
			}else {
				System.out.println("El proceso ha finalizado");
			}
			
			
			
		} catch (IOException e) {
			System.out.println("[ERROR] al ejecutar el proceso");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("[ERROR] proceso interrumpido");
			e.printStackTrace();
		}
		
		
		
	}
}
