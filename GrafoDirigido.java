package tpe2;

import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class GrafoDirigido<T> implements Grafo<T> {

	private Map<Integer, ArrayList<Arco<T>>> grafo = new HashMap<Integer, ArrayList<Arco<T>>>();

	
	/**
	 * Complejidad: O(1) debido a que el metodo contiene vertice no debe recorrer todos los vertices para obtener uno,
	 * ya que conoce el indice y lo busca derecho
	 */
	@Override
	public void agregarVertice(int verticeId) {
		if(!contieneVertice(verticeId)) {
			grafo.put(verticeId,new ArrayList<Arco<T>>());
		}
	}
	
	
	/**
	 *Complejidad: O(a) donde  donde "a" es la cantidad de arcos del vertice los cuales se deben recorrer
	 */
	@Override
	public void borrarVertice(int verticeId) {
		
		if(contieneVertice(verticeId)) { // es O(1
			this.grafo.remove(verticeId); // es O(1)
			Iterator<Arco<T>> arcos = obtenerArcos();
			while(arcos.hasNext()) { //es O(a)
				
				if(arcos.next().getVerticeDestino() == verticeId ) {
					borrarArco(arcos.next().getVerticeOrigen(), arcos.next().getVerticeDestino());
				}
			}
		}
	}
	
	/**
	 * Complidad: O(a) donde "a" es la cantidad de arcos a iterar en el metodo existeArco y el resto de
	 * metodos (contieneVertice() y add()) son O(1)
	 */
	@Override
	public void agregarArco(int verticeInicio, int verticeDestino, T etiqueta) {
		// verifico que los vertices existan en el grafo                 y que el arco no exista (si existe no se puede crear de vuelta)
		if(contieneVertice(verticeInicio) && contieneVertice(verticeDestino) && !existeArco(verticeInicio, verticeInicio)) {
			// si cumple, creamos el arco con los parametros necesarios
			Arco nuevoArco = new Arco(verticeInicio,verticeDestino,etiqueta);
			// obtenemos el indice(clave) del grafo a partir del inicio
			// una vez que encuentra nos devuelve la lista, y a esa lista le agregamos el nuevo arco creado
			grafo.get(verticeInicio).add(nuevoArco);
		}
	}

	/**
	 *  Complejidad: O(a) debido a que el metodo existeArco() tiene una complejidad O(a),
	 *  ya que conoce el indice de los vertices y los encuentra derecho, pero si recorre
	 *  la cantidad de arcos de ese vertice en particular
	 */
	@Override
	public void borrarArco(int verticeInicio, int verticeDestino) {
		if(existeArco(verticeInicio, verticeDestino)) { // O(a)
			this.grafo.get(verticeInicio).remove(obtenerArco(verticeInicio, verticeDestino));
		}
	}
	
	/**
	 * Complejidad: O(1) debido a que el metodo contiene vertice no debe recorrer todos los vertices para obtener uno,
	 * ya que conoce el indice y lo busca derecho
	 */
	@Override
	public boolean contieneVertice(int verticeId) {
		return this.grafo.get(verticeId) != null;
	}
	
	/**
	 * Complejidad O(a) donde a es la cantidad de arcos que se deben iterar
	 */
	@Override
	public boolean existeArco(int verticeInicio, int verticeDestino) {
		// primero compruebo que los vertices existan
		if(contieneVertice(verticeInicio) && contieneVertice(verticeDestino)) { // O(1) * O(1) = O(1)
			// obtengo la arraylist de arcos a partir del verticeInicio del grafo
			ArrayList<Arco<T>> arcos = grafo.get(verticeInicio);
				
			Iterator<Arco<T>> aux = arcos.iterator();
			while(aux.hasNext()) {	// O(a)
				Arco<T> arco = (Arco<T>) aux.next();
				if(arco.getVerticeDestino() == verticeDestino) {
					return true;
				}
			}						
		}
		return false;
	}
	
	/** 
	 * Complejidad: O(a) donde a es la cantidad de arcos que se deben iterar
	 */ 
	@Override
	public Arco<T> obtenerArco(int verticeInicio, int verticeDestino) {
		if(contieneVertice(verticeInicio)) {
			ArrayList<Arco<T>> arcos = grafo.get(verticeInicio);
			Iterator<Arco<T>> aux = arcos.iterator();
			while(aux.hasNext()) {
				Arco<T> arco = (Arco<T>) aux.next();
				if(arco.getVerticeDestino() == verticeDestino) {
					return arco;
				}
			}
		}
		return null;
	}
	
	/**
	 * Complejidad: O(1) ya que el metodo .size() ya conoce el tamaño del grafo y no debe recorrerlo
	 */
	@Override
	public int cantidadVertices() {
		return grafo.size();
	}
	
	/**
	 * Complejidad: O(v) donde v es la cantidad de valores de cada vertice del grafo obtenidos con el metodo .keyset()
	 */
	@Override
	public int cantidadArcos() {
		// creo una variable vacia
		int cantArcos = 0;
		// obtengo una lista con todos los vertices del grafo y lo hago iterable
		Iterator<Integer> vertices = this.grafo.keySet().iterator();
		while(vertices.hasNext()) { // O(v)
			// a cantAcos le sumo la cantidad de arcos de cada vertice mientras que exista
			cantArcos += this.grafo.get(vertices.next()).size();
		}
		return cantArcos;
	}
	
	/**
	 * Complejidad: O(1) ya que el addAll agrega la lista obtenida sin iterarla 
	 */
	@Override
	public Iterator<Integer> obtenerVertices() {
		//creo una lista vacia a la cual le voy a agregar todos los vertices del grafo
		ArrayList<Integer> vertices = new ArrayList<>();
		// key set devuelve una lista(no accecible)  de las claves del grafo
		// a vertices le hago addall ya que this.grafo.keySet devuelve una lista con todos los vertices
		vertices.addAll(this.grafo.keySet());
		return vertices.iterator();
	}
	
	/**
	 * Complejidad: O(a) donde a es la cantidad de arcos de cada vertice a iterar dentro del while
	 */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		//creo una lista vacia a la cual le voy a agregar los adyacentes
		ArrayList<Integer> adyacentes = new ArrayList<>();
		//obtengo los arcos del vertice que me llega por parametro
		if(contieneVertice(verticeId)) {
			ArrayList<Arco<T>> arcos = this.grafo.get(verticeId);
			//lo transformo en interable 
			Iterator<Arco<T>> aux = arcos.iterator();
			while(aux.hasNext()) {
				//al arraylist adyacentes le agrego todos los vertices adyacentes que existan
				adyacentes.add(aux.next().getVerticeDestino());
			}			
		}
		return adyacentes.iterator();
	}
	
	/**
	 * Complejidad: O(v) donde v es la cantidad de valores de cada vertice del grafo obtenidos con el metodo .keyset()
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		//creo una lista vacia a la cual le voy a agregar los arcos
		ArrayList<Arco<T>> arcos = new ArrayList<>();
		// obtengo todos los vertices (en una lista) y lo hago iterable con .iterator()
		Iterator<Integer> vertices = this.grafo.keySet().iterator();
		while(vertices.hasNext()) {
			//a arcos le realizo addAll con cada lista de arcos de cada vertice
			arcos.addAll(this.grafo.get(vertices.next()));
		}
		return arcos.iterator();
	}
	
	/**
	 * Complejidad: O(1) ya que el addAll agrega la lista obtenida sin iterarla 
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		//creo una lista vacia a la cual le voy a agregar los arcos
		ArrayList<Arco<T>> arcos = new ArrayList<>();
		//a arcos le realizo addAll con cada lista de arcos del vertice que llega por parametro
		arcos.addAll(this.grafo.get(verticeId));

		return arcos.iterator();
	}
}
