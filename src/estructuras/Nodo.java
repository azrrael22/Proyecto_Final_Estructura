package estructuras;

/**
* @author Juan Sebastian Snachez Alvarez
* @author Adriaann Mauricio Rodriguez Rubiano
*/
import java.util.ArrayList;

public class Nodo {
	private String dato;
	private String nombre;
	private ArrayList<Nodo> siguienteNodo; //Nodo siguiente;
	
	public Nodo(String dato, String nombre) {
		super();
		this.dato = dato;
		this.nombre = nombre;
		this.siguienteNodo = new ArrayList<>();
		this.siguienteNodo.add(null);
	}
	
	public void conectar(Nodo nodo, int indice) {
		// si la posición indice existe en el vector de enlaces
		
				if (indice < siguienteNodo.size()) {
					siguienteNodo.set(indice, nodo);
				} else {
					int n = indice - siguienteNodo.size();

					// la lista de enlaces crece. En las posiciones intermedias
					// se asigna null
					for (int i = 0; i < n; i++) {
						siguienteNodo.add(null);
					}
					
						
					// en la posición indice del vector enlaces se almacena
					// una referencia al nodo destino
					siguienteNodo.add(nodo);
				}
	}

	public Nodo siguienteNodo() {
		return siguienteNodo.get(0);
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public String getNombre() {
		return nombre;
	}
	
}
