package estructuras;

import java.util.Iterator;

/**
* @author Juan Sebastian Snachez Alvarez
* @author Adriaann Mauricio Rodriguez Rubiano
*/
public class ListaEnlazada<T> implements Iterable<T>{

	private NodoSimple<T> primerNodo; // referencia al primer nodo
	private NodoSimple<T> ultimoNodo; // referencia al ultimo nodo
	private NodoSimple<T>nodoActual; // referencia al nodo actual (en el que estoy ubicado actualmente)
	private int longitud; // cuantos nodos hay

	public ListaEnlazada() {
		primerNodo = null;
		ultimoNodo = null;
		nodoActual = null;
		longitud = 0;
	}

	/**
	 * agrega un nodo al final de la lista
	 * 
	 * @param nodo
	 */
	public void agregarNodoAlFinal(NodoSimple<T>nuevoNodo) {
		// la lista esta vacia
		nodoActual = nuevoNodo;
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
	public void agregarNodoFinal(T elemento) { // .add del arraylist
		// la lista esta vacia
		NodoSimple<T>nuevoNodoSimple = new NodoSimple<T>(elemento, elemento.toString());
		agregarNodoAlFinal(nuevoNodoSimple);
	}

	/*
	 * ubicarse en el primer nodo de la lista
	 */
	public void irPrimerNodo() {
		nodoActual = primerNodo;
	}

	/*
	 * ubicarse en el últomo nodo de la lista
	 */
	public void irUltimoNodo() {
		nodoActual = ultimoNodo;
	}

	public boolean estaVacia() {
		return longitud == 0;
	}

	/*
	 * MODIFICADO-----------------------------------------------------------------------------------------
	 * ubicarse en siguiente nodo de la lista, si esta en el ultimo retornara null
	 */
	public boolean irSiguienteNodo() {
		boolean irSiguiente = false;
		if (nodoActual != null) {
			nodoActual = nodoActual.siguienteNodo();
			irSiguiente = true;
		}
		return irSiguiente;
	}

	public T obtenerDatoActual() {
		return nodoActual.getDato();
	}

	public T obtenerDatoPrimero() {
		irPrimerNodo();
		return obtenerDatoActual();
	}

	public int getLongitud() {
		return longitud;
	}

	public NodoSimple<T>obtenerNodoActual() {
		// obtener la posicion de memoria donde esta el nodo actual
		return nodoActual;
	}

	public int obtenerPosicionNodoActual() {
		return buscarPosicionNodo(nodoActual);
	}

	@Override
	public String toString() {
		String msj = "ListaEnlazada: { ";
		irPrimerNodo();
		while (nodoActual != null) {
			msj = msj + nodoActual.getNombre() + " -> ";
			nodoActual = nodoActual.siguienteNodo();
		}
		return msj + " }";
	}

	/**
	 * agrega un nodo al inicio de la lista
	 * 
	 * @param dato
	 */
	public void agregarNodoInicio(T elemento) { // .add del arraylist
		// la lista esta vacia
		NodoSimple<T>nuevoNodoSimple= new NodoSimple<T>(elemento, elemento.toString());
		agregarNodoAlInicio(nuevoNodoSimple);
	}

	public void agregarNodoAlInicio(NodoSimple<T>nuevoNodo) {
		// la lista esta vacia
		if (estaVacia()) {
			agregarNodoAlFinal(nuevoNodo);
		} else {
			nuevoNodo.conectar(primerNodo);
			primerNodo = nuevoNodo;
			nodoActual = nuevoNodo;
			longitud = longitud + 1;
		}
	}

	/**
	 * MODIFICADO agrega un nodo a la derecha del actual de la lista
	 * 
	 * @param dato
	 */
	public void agregarNodoADerecha(T elemento) { // .add del arraylist
		NodoSimple<T>nuevoNodoSimple= new NodoSimple<T>(elemento, elemento.toString());
		agregarNodoDerecha(nuevoNodoSimple);
	}

	/**
	 * MODIFICADO agrega un nodo a la derecha del actual de la lista
	 * 
	 * @param dato
	 */
	public void agregarNodoDerecha(NodoSimple<T>nuevoNodo) { // .add del arraylist
		// la lista esta vacia
		if (estaVacia() || nodoActual == ultimoNodo) {
			agregarNodoAlFinal(nuevoNodo);
		} else {
			NodoSimple<T>nodoAuxiliar = nodoActual.siguienteNodo();
			nodoActual.conectar(nuevoNodo);
			// nuevoNodoSimple.conectar(nodoActual,1); EN CASO DE SER DOBLEMENTE ENLAZADA
			nuevoNodo.conectar(nodoAuxiliar);
			nodoActual = nuevoNodo;
			longitud = longitud + 1;
		}
	}

	/*
	 * metodo que elimina el primer nodo
	 */
	public void eliminarPrimerNodo() {
		if (estaVacia()) {
			return; // o exception
		}
		if (longitud == 1) {
			vaciarLista();
		} else {
			irPrimerNodo(); // hace que nodoActual sea el nodoInicial
			nodoActual = nodoActual.siguienteNodo();
			primerNodo = nodoActual;
			longitud--;
		}

	}

	/*
	 * metodo para eliminar el ultimo nodo de la lista .
	 *
	 */
	public void eliminarUltimoNodo() {
		if (estaVacia()) {
			return; // o exception
		}

		if (longitud == 1) {
			vaciarLista();
		} else {
			
			nodoActual = buscarAnteriorNodo(ultimoNodo);
			ultimoNodo  = nodoActual; 
			ultimoNodo.conectar(null);
			longitud--;
		}
	}
	
	public NodoSimple<T>buscarAnteriorNodo(NodoSimple<T>nodo) {
		NodoSimple<T>nodoAuxiliar = primerNodo;
		while (nodoAuxiliar != null) {
			if (nodoAuxiliar.siguienteNodo() == nodo) {
				return nodoAuxiliar;
			} else {
				nodoAuxiliar = nodoAuxiliar.siguienteNodo();
			}
		}
		return nodoAuxiliar;
	}

	public void eliminarPosicion(int i) {
		irAPosicionNodo(i);
		eliminarNodoActual();
	}

	/*
	 * Metodo que permite eliminar un nodo intermedio de la lista Eliminar nodo
	 * Actual
	 */

	public void irAPosicionNodo(int posicion) {
		// Valida la posicion debe estar entre [0 y longitud)
		if( posicion < longitud && posicion >= 0 ) {
			irPrimerNodo();
			for(int i = 0 ; i < posicion ; i++)	{
				nodoActual = nodoActual.siguienteNodo();
			}
		}
	}

	public void eliminarNodoActual() {
		if (estaVacia()) {
			return; // o exception
		} else if (longitud == 1) {
			vaciarLista();
		} else if( nodoActual == primerNodo ) {
			eliminarPrimerNodo();
		} else if( nodoActual == ultimoNodo ) {
			eliminarUltimoNodo();
		} else {
			NodoSimple<T>nodoAnterior = buscarAnteriorNodo(nodoActual);
			nodoAnterior.conectar( nodoActual.siguienteNodo() );
			nodoActual = nodoAnterior;
			longitud--;
		}
	}

	/*
	 * Eliminar nodo que tenga un elemento x
	 */
	public void eliminarNodoElementoX(String elemento) {
		irPrimerNodo();
		for(int i = 0; i < getLongitud() ; i++ ) {
			if( obtenerDatoActual().equals(elemento) ) {
				eliminarNodoActual();
				return;
			}	
			nodoActual = nodoActual.siguienteNodo();
		}	
	}	

	/*
	 * Metodo que permnite buscar un elemento en la lista y retorna su posicion
	 */
	public int buscarElementoLista(T elemento) {
		irPrimerNodo();
		for(int i = 0; i < getLongitud() ; i++ ) {
			if( obtenerDatoActual().equals(elemento) ) {
				return i;
			}
			nodoActual = nodoActual.siguienteNodo();
		}
		return -1;
	}
	
	/*
	 * Metodo que me permite buscar la posicion de un nodo
	 */
	public int buscarPosicionNodo(NodoSimple<T>nodo) {
		if (estaVacia())
			return -1;
		else {
			NodoSimple<T>nodoAuxiliar = primerNodo;
			boolean flag = true;
			int contador = 0;
			while (flag) {
				if (nodoAuxiliar == nodo) {
					return contador;
				} else {
					if (contador == longitud) {
						flag = false;
					} else {
						nodoAuxiliar = nodoAuxiliar.siguienteNodo();
						contador++;
					}
				}
			}
			return -1;
		}
	}

	/*
	 * Metodo que permite localizar el nodo que almacena un determinado Elemento
	 */
	public NodoSimple<T>localizarNodoDeElemento(T elemento) {
		irPrimerNodo();
		for(int i = 0; i < getLongitud() ; i++ ) {
			if( obtenerDatoActual().equals(elemento) ) {
				return nodoActual;
			}
			nodoActual = nodoActual.siguienteNodo();
		}
		return null;
	}

	/*
	 * Metodo que permite vacias la lista
	 */

	public void vaciarLista() {
		nodoActual = null;
		ultimoNodo = null;
		primerNodo = null;
		longitud = 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new IteradorListaSimple<T>(primerNodo);
	}
}
