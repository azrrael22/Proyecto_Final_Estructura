package estructuras;

import java.util.Iterator;

/**
* @author Juan Sebastian Snachez Alvarez
* @author Adriaann Mauricio Rodriguez Rubiano
*/
class IteradorListaCircularDoble<T> implements Iterator<T>{

	private NodoDoble<T> nodoActual; 
	private NodoDoble<T> nodoInicial;
	private boolean quedanNodos;
	
	public IteradorListaCircularDoble(NodoDoble<T> nodoInicial) {
		nodoActual = nodoInicial;
		this.nodoInicial = nodoInicial;
		quedanNodos = nodoInicial != null;
	}
	
	@Override
	public boolean hasNext() {
		return quedanNodos;
	}

	@Override
	public T next() {
		NodoDoble<T> auxiliar = nodoActual;
		nodoActual = nodoActual.siguienteNodo();
		if( nodoActual == nodoInicial ) {
			quedanNodos = false;
		}
		return auxiliar.getDato();
	}
	
}
