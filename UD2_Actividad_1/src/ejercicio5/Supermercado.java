package ejercicio5;

public class Supermercado {

	public static void main(String[] args) {
		
		Cliente c1 = new Cliente("Juan", new int[] {1,2,5,4});
		int productos[] =  {1,2,6};
		Cliente c2 = new Cliente("Ana", productos);
		
		Cajera cajera1 = new Cajera("Eva", c1);
		Cajera cajera2 = new Cajera("Antonio", c2);
		cajera1.start();
		cajera2.start();
	}
}
