package ejercicio5;

/*
 * ▪ Clase Cajera con 3 atributos con el nombre de la cajera, el objeto cliente a cobrar y el
 *  tiempo actual en el momento de crear el objeto cajera y métodos: 
□ private void esperarXsegundos(int segundos) 
□ private long tiempoTranscurrido(long tiempoProd)
El resultado de una operación de la cajera “cajera1” y “cajera2” con dos clientes 
(aunque se indiquen dos cajeras son dos instancias de la misma clase cajera que realiza 
dos procesos uno a continuación del otro) “Cliente1”- {2, 2, 1, 5, 2, 3} “Cliente2”- {1, 3, 5, 1, 

 */
public class Cajera extends Thread {

	private Cliente cliente;
	private long tiempoInicio;

	public Cajera(String nombre, Cliente cliente) {
		super(nombre);
		this.cliente = cliente;
		this.tiempoInicio = System.currentTimeMillis();
	}

	@Override
	public void run() {
		System.out.println("Cajera: "+getName()+" COMIENZA LA COMPORA DEL CLIENTE "+cliente.getNombre()+ " Tiempo: "+tiempoTranscurrido());
	
		// Recorremos los productos de los cliente
		int producto = 1;
		for(int tiempo : cliente.getProductos()) {
			esperarXsegundos(tiempo);
			System.out.println("Cajera: "+getName()+"Ha procesado el producto "+producto+" Tiempo: "+tiempoTranscurrido());
			producto++;
		}
		System.out.println("Cajera: "+getName()+"Ha terminado Tiempo: "+tiempoTranscurrido());
	}
	
	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException e) {
			System.out.println("[ERROR] Hilo interrumpidp");
			e.printStackTrace();
		}
	}
	
	private long tiempoTranscurrido() {
		return (System.currentTimeMillis() - this.tiempoInicio) / 1000;
	}

}
