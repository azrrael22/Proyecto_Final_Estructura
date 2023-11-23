package estructuras;

import java.util.Iterator;

/**
* @author Juan Sebastian Snachez Alvarez
* @author Adriaann Mauricio Rodriguez Rubiano
*/
public class Pila<T> implements Iterable<T>{

	private NodoSimple<T> nodoTope; // referencia al primer nodo
	private int longitud; // cuantos nodos hay

	public Pila() {
		nodoTope = null;
		longitud = 0;
	}


	public boolean estaVacia() {
		return longitud == 0;
	}


	public T peek() {
		return nodoTope.getDato();
	}

	public int getLongitud() {
		return longitud;
	}
	
	@Override
	public String toString() {
		String msj = "Pila: { ";
		NodoSimple<T> nodoActual = nodoTope;
		while (nodoActual != null) {
			msj = msj + nodoActual.getNombre() + " -> ";
			nodoActual = nodoActual.siguienteNodo();
		}
		return msj + " } ";
	}

	/**
	 * agrega un nodo al inicio de la lista
	 * @param dato
	 */
	public void push(T elemento) { // .add del arraylist
		// la lista esta vacia
		NodoSimple<T> nuevoNodoSimple = new NodoSimple<T>(elemento, elemento.toString());
		push(nuevoNodoSimple);
	}

	private void push(NodoSimple<T> nuevoNodo) {
		// la lista esta vacia
		if (estaVacia()) {
			nodoTope = nuevoNodo;
		} else {
			nuevoNodo.conectar(nodoTope);
			nodoTope = nuevoNodo;
		}
		longitud = longitud + 1;
	}

	public T pop() {
		T dato = peek();
		eliminarTope();
		return dato;
	}
	
	/*
	 * metodo que elimina el primer nodo
	 */
	private void eliminarTope() {
		if (estaVacia()) {
			return; // o exception
		}
		if (longitud == 1) {
			vaciarPila();
		} else {
			nodoTope = nodoTope.siguienteNodo();
			longitud--;
		}

	}


	/*
	 * Metodo que permite vacias la lista
	 */

	public void vaciarPila() {
		nodoTope = null;
		longitud = 0;
	}


	@Override
	public Iterator<T> iterator() {
		return new IteradorPila<T>(nodoTope);
	}


}
