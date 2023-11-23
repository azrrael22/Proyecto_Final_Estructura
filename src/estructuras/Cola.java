package estructuras;

import java.util.Iterator;

/**
* @author Juan Sebastian Snachez Alvarez
* @author Adriaann Mauricio Rodriguez Rubiano
*/
public class Cola<T> implements Iterable<T> {

	private NodoSimple<T> primerNodo; // referencia al primer nodo
	private NodoSimple<T> ultimoNodo; // referencia al ultimo nodo
	private int longitud; // cuantos nodos hay

	public Cola() {
		primerNodo = null;
		ultimoNodo = null;
		longitud = 0;
	}
	
	/**
	 * agrega un nodo al final de la lista
	 * 
	 * @param nodo
	 */
	private void adicionarNodoAlFinal(NodoSimple<T> nuevoNodo) {
		// la lista esta vacia
		if (estaVacia()) {
			primerNodo = nuevoNodo;
			ultimoNodo = nuevoNodo;
		} else {
			ultimoNodo.conectar(nuevoNodo);
			ultimoNodo = nuevoNodo;
		}

		// la ista tiene al menos un elemento
		longitud = longitud + 1;
	}

	
	
	
	/**
	 * MODIFICADO agrega un nodo al final de la lista
	 * 
	 * @param elemento
	 */
	public void adicionarNodoFinal(T elemento) { // .add del arraylist
		// la lista esta vacia
		NodoSimple<T> nuevoNodoSimple = new NodoSimple<T>(elemento, elemento.toString());
		adicionarNodoAlFinal(nuevoNodoSimple);
	}

	public boolean estaVacia() {
		return longitud == 0;
	}


	public T peek() {
		return primerNodo.getDato();
	}

	public int getLongitud() {
		return longitud;
	}

	@Override
	public String toString() {
		String msj = "Cola: { ";
		NodoSimple<T> nodoActual = primerNodo;
		for (int i = 0; i < getLongitud(); i++) {
			msj = msj + nodoActual.getNombre() + " -> ";
			nodoActual = nodoActual.siguienteNodo();
		}
		return msj + " }";
	}

	public T poll() {
		T dato = peek();
		eliminarPrimero();
		return dato;
	}

	/*
	 * metodo que elimina el primer nodo
	 */
	private void eliminarPrimero() {
		if (estaVacia()) {
			return; // o exception
		}
		if (longitud == 1) {
			vaciarCola();
		} else {
			primerNodo = primerNodo.siguienteNodo();
			longitud--;
		}

	}

	/*
	 * Metodo que permite vaciar la lista
	 */

	public void vaciarCola() {
		ultimoNodo = null;
		primerNodo = null;
		longitud = 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new IteradorCola<>(primerNodo);
	}



}

