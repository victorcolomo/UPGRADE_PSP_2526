import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * EJERCICIO 2 - Crea un programa al que se le pase como argumento de línea
 *  de comandos la ruta o path de un directorio. 
 *  El path introducido debe ser el de un directorio, y si no existe, 
 *  debe mostrarse un mensaje de error, y también si existe, 
 *  pero no es un directorio. Para ello, debe utilizarse la clase File. 

	Si existe, debe mostrarse el resultado del comando dir sobre ese directorio, 
	pero las líneas deben ir numeradas, teniendo la primera el número 1. 
	El programa debe obtener un stream asociado a la salida estándar del proceso y después leer línea a línea de él. Para ejecutar el proceso puedes utilizar la clase ProcessBuilder. 
 */
public class Ejercicio02 {

	
	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.out.println("El programa necesita un sólo argumento");
			System.exit(1);
		}
		
		String ruta = args[0];
		System.out.println("Comprobamos ruta: "+ruta);
		File fichero = new File(ruta);
		
		// Comprobamos si la ruta existe y es un directorio
		if(fichero.isDirectory()) {
			try {
				// Si todo es correcto, ejecutamos el comando dir
				ProcessBuilder pb = new ProcessBuilder("cmd", "/C", "dir", ruta);
			
				Process p = pb.start();
				
				InputStream is = p.getInputStream();
				BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
				String linea;
				
				while( (linea = bReader.readLine())!= null) {
					System.out.println(linea);
				}
				
				bReader.close();
				
			} catch (IOException e) {
				System.out.println("Error al ejecutar el coando");
				e.printStackTrace();
			}
			
		}else {
			System.out.println("La ruta "+ruta+" no es correcta");
		}

	}

}
