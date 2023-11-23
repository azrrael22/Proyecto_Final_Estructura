package estructuras;

import java.util.Iterator;
/**
* @author Juan Sebastian Snachez Alvarez
* @author Adriaann Mauricio Rodriguez Rubiano
*/
class IteradorPila<T> implements Iterator<T>{

	private NodoSimple<T> nodoActual; 
	
	public IteradorPila(NodoSimple<T> nodoTope) {
		nodoActual = nodoTope;
	}
	
	@Override
	public boolean hasNext() {
		return nodoActual != null;
	}

	@Override
	public T next() {
		NodoSimple<T> auxiliar = nodoActual;
		nodoActual = nodoActual.siguienteNodo();
		return auxiliar.getDato();
	}
	
}
