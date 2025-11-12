import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejemplo1 {

	public static void main(String[] args) {
		
		// /C ejecutamos y cerramos el simbolo del sistema
		// /K ejecutamos y no se cierra el simbolo del sistema
		ProcessBuilder pBuilder = new ProcessBuilder("cmd","/C","ping","8.8.8.8");
		pBuilder.directory(new File("C:\\"));
		try {
			Process proceso = pBuilder.start();
			
			InputStream is =  proceso.getInputStream();
			BufferedReader br = new BufferedReader( new InputStreamReader(is)  );
			
			System.out.println("*** SALIDA ESTÁNDAR ****");
			String linea = br.readLine();
			while(linea != null) {
				System.out.println(linea);
				linea = br.readLine();
			}
			
			InputStream iError =proceso.getErrorStream();
			BufferedReader brError = new BufferedReader( new InputStreamReader(iError));
			System.out.println("*** SALIDA ERROR ****");
			String lineaError = brError.readLine();
			while(lineaError != null) {
				System.out.println(lineaError);
				lineaError = br.readLine();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
