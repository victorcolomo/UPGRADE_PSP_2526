import java.io.IOException;

/**
 * EJERCICIO 1 – Escribe un programa que se ejecute desde Windows que: 
▪ Reciba como argumentos el comando y las opciones del comando que se quiere ejecutar. 
Si el programa no recibe ningún argumento, se mostrará un mensaje de error. 
▪ Cree un proceso hijo que ejecute el comando con las opciones correspondientes. 
▪ Si la ejecución falla, debe mostrar un mensaje de error 
▪ El proceso padre debe esperar a que el hijo termine y mostrar: 
	El comando ejecutado. 
	El código de finalización 
 */
public class Ejercicio01 {
	
	public static void main(String[] args) {
		
		if(args.length < 1) {
			System.out.println("[ERROR] no se han pasado argumento al programa");
			System.exit(1);
		}else {
			ProcessBuilder pBuilder = new ProcessBuilder(args);
			try {
				Process proceso = pBuilder.start();
				// Esperemos a que el proceso termine
				int codigo = proceso.waitFor();
				System.out.println("Codigo de finalización "+codigo);
				System.out.println("Comando Ejecutado: "+pBuilder.command());
				System.exit(0);
			} catch (IOException e) {
				System.out.println("[ERROR] En la ejecución del proceso");
				e.printStackTrace();
				System.exit(2);
			} catch (InterruptedException e) {
				System.out.println("[ERROR] Proceso interrumpido");
				e.printStackTrace();
			}
		}
	}
}
