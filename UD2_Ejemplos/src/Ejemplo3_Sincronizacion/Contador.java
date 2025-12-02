package Ejemplo3_Sincronizacion;

public class Contador {

	private int cuenta = 0;
	
	synchronized public int getCuenta() {
		return cuenta;
	}
	
	synchronized public int incrementar() {
		this.cuenta++;  // this.cuenta = this.cuenta + 1;
		return cuenta;
	}
	
}
