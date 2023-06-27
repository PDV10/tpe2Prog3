package tpe2;

import java.util.*;


public class ServicioDFS<T>{
	private Map<Integer,String> vertices;
	private Grafo<T> grafo;

	public ServicioDFS(Grafo<T> grafo) {
		this.grafo = grafo;
		this.vertices = new HashMap<Integer, String>();
	}

	public List<Integer> dfsForest() {
		// obtenemos todos los vertices del grafo
		Iterator<Integer> verticesAux = this.grafo.obtenerVertices();
		ArrayList<Integer> listaVertices = new ArrayList<Integer>();
		//TOMAMOS LOS VERTICES UTILIZADOS EN EL GRAFO Y LOS INICIALIZAMOS EN BLANCO
		while(verticesAux.hasNext()){
			this.vertices.put(verticesAux.next(), "blanco");
		}
		// reiniciamos los vertices del grafo
		verticesAux = this.grafo.obtenerVertices();
		
		//los volvemos a iterrar desde el principio
		while(verticesAux.hasNext()){
			//obtengo el actual y avanzo al siguiente
			int auxVertice = verticesAux.next();
			// si el color del vertice actual es blanco entro al if
			if(vertices.get(auxVertice) == "blanco"){
				// agrego a la lista todo lo que retorne
				listaVertices.addAll(this.dfsForest_visit(auxVertice));
			}
		}
		return listaVertices;
	}

	private List<Integer> dfsForest_visit(int vertice){
		ArrayList<Integer> listaVertices = new ArrayList<Integer>();
		listaVertices.add(vertice);
		this.vertices.put(vertice, "Amarillo");

		Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice);
		while (adyacentes.hasNext()){
			int auxVertice = adyacentes.next();
			String aux = this.vertices.get(auxVertice);

			if(aux.equals("blanco")){
				listaVertices.addAll(dfsForest_visit(auxVertice));
			}
			
		}
		this.vertices.put(vertice, "Negro");
		
		return listaVertices;
	}
}
