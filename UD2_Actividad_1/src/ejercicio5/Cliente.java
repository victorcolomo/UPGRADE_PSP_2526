package ejercicio5;

/*
 * ▪ Clase Cliente que contiene 2 atributos, uno con el nombre del cliente y otro con un array de enteros que representa el 
 * carro de los productos comprados y el tiempo que la cajera tardará en pasar el producto por el escáner; es decir, 
 * que si tenemos un array con [1,3,5] significará que el cliente ha comprado 3 productos y que la cajera tardara en 
 * procesar el producto 1 en '1 segundo', el producto 2  en '3 segundos' y el producto 3 en '5 segundos', 
 * con lo cual tardara en cobrar al cliente toda su compra '9 segundos'
 */
public class Cliente {

	private String nombre;
	private int [] productos;  // {1,5,2}
	
	public Cliente(String nombre, int[] productos) {
		this.nombre = nombre;
		this.productos = productos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int[] getProductos() {
		return productos;
	}

	public void setProductos(int[] productos) {
		this.productos = productos;
	}
	
}
