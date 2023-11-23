package estructuras;

/**
* @author Juan Sebastian Snachez Alvarez
* @author Adriaann Mauricio Rodriguez Rubiano
*/
public class NodoSimple<T> {
	private T dato;
	private String nombre;
	private NodoSimple<T> siguienteNodo; //Nodo siguiente;
	
	public NodoSimple(T dato, String nombre) {
		super();
		this.dato = dato;
		this.nombre = nombre;
		siguienteNodo = null;
	}
	
	public void conectar(NodoSimple<T> nodo) {
		// si la posicion indice existe en el vector de enlaces
		
		siguienteNodo = nodo;		
	}

	public NodoSimple<T> siguienteNodo() {
		return siguienteNodo;
	}

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public String getNombre() {
		return nombre;
	}
	
}
