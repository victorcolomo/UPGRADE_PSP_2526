package ejercicio1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class AlgoritmoAES {

	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// 1) Leer la clave del fichero
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("clave.raw"));
			
			SecretKey clave = (SecretKey) ois.readObject();
			
			// 2) Craer un objeto Cipher para el algoritmo AES
			Cipher descrifrador = Cipher.getInstance("AES");
			
			// 3) Configurar el Cipher en modo cifrada
			descrifrador.init(Cipher.ENCRYPT_MODE, clave);


			System.out.print("Mensaje a cifrar: ");
			String mensajeOriginal = scan.nextLine();
			
			// Cipher trabaja con bytes, transformamos el mensaje a bytes
			byte[] bytesMensajeOriginal = mensajeOriginal.getBytes();
			
			// 4) Cifrar el mensaje
			byte[] bytesMensajeCifrado = descrifrador.doFinal(bytesMensajeOriginal);
			
			System.out.println("MENSAJE CIFRADO");
			System.out.println(new String(bytesMensajeCifrado));
			
			
			// 5) Cambiamos el modo de configuracion a descifrado
			descrifrador.init(Cipher.DECRYPT_MODE, clave);
			
			// 6) Desciframos el mensaje
			byte[] bytesMensajeDescifrado = descrifrador.doFinal(bytesMensajeCifrado);
			
			System.out.println("MENSAJE DESCIFRADO");
			System.out.println(new String(bytesMensajeDescifrado));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
