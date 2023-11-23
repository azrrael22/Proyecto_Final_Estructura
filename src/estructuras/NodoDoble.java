package estructuras;

/**
* @author Juan Sebastian Snachez Alvarez
* @author Adriaann Mauricio Rodriguez Rubiano
*/
public class NodoDoble<T> {
	private T dato;
	private String nombre;
	private NodoDoble<T> siguienteNodo; 
	private NodoDoble<T> nodoAnterior; 
	
	public NodoDoble(T dato, String nombre) {
		super();
		this.dato = dato;
		this.nombre = nombre;
		siguienteNodo = null;
		nodoAnterior = null;
	}
	
	public void conectar(NodoDoble<T> nodo) {
		// si la posicion indice existe en el vector de enlaces
		siguienteNodo = nodo;		
	}

	public void conectarAnterior(NodoDoble<T> nodo) {
		// si la posicion indice existe en el vector de enlaces
		nodoAnterior = nodo;		
	}

	
	
	public NodoDoble<T> siguienteNodo() {
		return siguienteNodo;
	}

	public NodoDoble<T> anteriorNodo() {
		return nodoAnterior;
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
