package estructuras;

import java.util.Iterator;
/**
* @author Juan Sebastian Snachez Alvarez
* @author Adriaann Mauricio Rodriguez Rubiano
*/
class IteradorListaDoble<T> implements Iterator<T>{

	private NodoDoble<T> nodoActual; 
	
	public IteradorListaDoble(NodoDoble<T> nodoInicial) {
		nodoActual = nodoInicial;
	}
	
	@Override
	public boolean hasNext() {
		return nodoActual != null;
	}

	@Override
	public T next() {
		NodoDoble<T> auxiliar = nodoActual;
		nodoActual = nodoActual.siguienteNodo();
		return auxiliar.getDato();
	}
	
}
