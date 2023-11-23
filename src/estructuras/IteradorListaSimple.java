package estructuras;

import java.util.Iterator;
/**
* @author Juan Sebastian Snachez Alvarez
* @author Adriaann Mauricio Rodriguez Rubiano
*/
class IteradorListaSimple<T> implements Iterator<T>{

	private NodoSimple<T> nodoActual; 
	
	public IteradorListaSimple(NodoSimple<T> nodoInicial) {
		nodoActual = nodoInicial;
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
