package ejercicio1;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * EJERCICIO 1 – Escribe un programa que: 
		 * ▪ Obtenga el objeto Thread correspondiente al hilo actual de ejecución 
		 * ▪ Muestre información acerca de  él: identificador, nombre, estado y prioridad. 
		 * ▪ Cambiar el nombre, la prioridad del hilo y detén la ejecución durante dos segundos 
		 * ▪ Vuelve a mostrar sus datos llamando al método toString() 
		 * 
		 * Este programa no tiene que crear ningún hilo, solo mostrar información sobre el hilo actualmente en
		 * ejecución.
		 * 
		 */
		
		Thread hiloPrincipal = Thread.currentThread();
		System.out.println(hiloPrincipal);
		// Mostramos informacion
		System.out.println("Identificador: "+hiloPrincipal.getId());
		System.out.println("Nombre: "+hiloPrincipal.getName());
		System.out.println("Estado: "+hiloPrincipal.getState());
		System.out.println("Prioridad: "+hiloPrincipal.getPriority());
		// Modificamos información del hilo
		hiloPrincipal.setName("PSP");
		hiloPrincipal.setPriority(10);
		
		hiloPrincipal.sleep(2000);
		
		System.out.println(hiloPrincipal.toString());
		

	}

}
