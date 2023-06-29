package tpe2;

import java.lang.reflect.Array;
import java.util.*;


public class ServicioBFS<T> {

	private Grafo<T> grafo;
	private ArrayList<Integer> fila;
	private Map<Integer,Boolean> vertices;


	public ServicioBFS(Grafo<T> grafo) {
		this.grafo = grafo;
		this.vertices = new HashMap<Integer,Boolean>();
	}
	
	public ArrayList<Integer> bfsForest() {
		// obtenemos todos los vertices del grafo
		Iterator<Integer> verticesAux = this.grafo.obtenerVertices();
		fila = new ArrayList<Integer>();

		//TOMAMOS LOS VERTICES UTILIZADOS EN EL GRAFO Y LOS INICIALIZAMOS EN BLANCO
		while(verticesAux.hasNext()){
			this.vertices.put(verticesAux.next(), false);
		}
		// reiniciamos los vertices del grafo
		verticesAux =this.grafo.obtenerVertices();

		//los volvemos a iterrar desde el principio
		
			while(verticesAux.hasNext()){
				//obtengo el actual y avanzo al siguiente
				int auxVertice = verticesAux.next();
				// si el color del vertice actual es blanco entro al if
				if(vertices.get(auxVertice) == false){
					// agrego a la lista todo lo que retorne
					
					fila = this.bfsForest_visit(auxVertice);
				}
			}
			return fila;
	}
	
	
	private ArrayList<Integer> bfsForest_visit(int vertice){
		// creo la fila con las que voy a trabajar
		ArrayList<Integer> recorrido = new ArrayList<>();
		// cambio el valor del vertice (de la clave) a true para dejarlo marcado
		this.vertices.put(vertice, true);
		// agrego a la fila auxiliar, la cual se va a ir modificando constantemente
		this.fila.add(vertice);
		
		// pregunto si la fila contiene algo
		while(!this.fila.isEmpty()){
			// elimino el primer valor de la fila y lo guardo
			int valorRetorno = this.fila.remove(0);
			// obtengo los adyacentes del vertice que se elimino de la fila auxiliar
			Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(valorRetorno);
			// agrego al recorrido final el vertice
			recorrido.add(valorRetorno);
			
			// recorro los adyacentes del vertice si es que tiene
			while(adyacentes.hasNext()){
			// obtengo el valor y paso al siguiente
			int valor = adyacentes.next();
				// verifico que el valor no haya sido visitado ya 
				if(this.vertices.get(valor) == false){
					// lo agrego a la fila auxiliar
					this.fila.add(valor);
					// modifico el valor de la clave a true para marcarlo como visitado 
					this.vertices.put(valor, true);
				}
			}
		}

		return recorrido;
	}
}
