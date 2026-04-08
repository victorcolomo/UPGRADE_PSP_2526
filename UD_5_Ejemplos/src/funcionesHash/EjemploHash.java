package funcionesHash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EjemploHash {

	public static void main(String[] args) {
		String mensaje = "Hola mundo";
		
		// Hash trabaja con flujos de bytes
		// Convertimos la cadena a flujo de bytes
		byte[] bytes = mensaje.getBytes();
		MessageDigest mDigest = null;
		try {
			mDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		mDigest.update(bytes);
		
		// Llamamos al metodo digest para obtener el resumen (encriptacion)
		byte[] resumen = mDigest.digest();
		
		System.out.println("Resumen Hash "+new String(resumen));
		
	}
}
