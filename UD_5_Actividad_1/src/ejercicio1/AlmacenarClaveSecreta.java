package ejercicio1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AlmacenarClaveSecreta {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		// Creamos generador de claves, para el algoritmo AES
		KeyGenerator generador = KeyGenerator.getInstance("AES");
		// Generamos la clave secreta: como es simetrico la usaremos para cifrar y descrifrar
		SecretKey clave = generador.generateKey();
		
		// Almacenamos la clave en un fichero
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("clave.raw"));
			oos.writeObject(clave);
			oos.close();
			System.out.println("Clave guardada");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
