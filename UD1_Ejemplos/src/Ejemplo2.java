import java.io.IOException;
import java.io.File;

public class Ejemplo2 {

	public static void main(String[] args) {
		
		ProcessBuilder pBuilder = new ProcessBuilder("java","Suma");
		pBuilder.directory(new File("bin"));
		pBuilder.redirectOutput(new File("salida.txt"));
		pBuilder.redirectError(new File("error.txt"));
		
		File fichero = new File("entrada.txt");
		
		if (fichero.exists()) {
			pBuilder.redirectInput(new File("entrada.txt"));
		}
		
		try {
			pBuilder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
