package tpe2;

import java.util.*;


public class ServicioCaminos<T> {

	private ArrayList<Arco<?>> arcosVisitados;
	private List<List<Integer>> caminos;
	private Grafo<T> grafo;
	private int origen;
	private int destino;
	private int lim;
	
	public ServicioCaminos(Grafo<T> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
		this.caminos = new ArrayList<>();
		this.arcosVisitados = new ArrayList<>();
	}

	public List<List<Integer>> caminos() {
		ArrayList<Integer> camino_parcial = new ArrayList<>();
		camino_parcial.add(this.origen);
		lista_Caminos(camino_parcial, this.origen);
		return this.caminos;
	}
	
	private void lista_Caminos(ArrayList<Integer> camino_parcial, int vertice){
		Iterator<Arco<T>> arcos = this.grafo.obtenerArcos(vertice);
		if(vertice != this.destino) {
			
			while(arcos.hasNext()) {
				Arco<T> arco = arcos.next();
				int verticeAux = arco.getVerticeDestino();
				if(!this.arcosVisitados.contains(arco)){
					this.arcosVisitados.add(arco);
					camino_parcial.add(verticeAux);
					lista_Caminos(camino_parcial,verticeAux);
					camino_parcial.remove(camino_parcial.size()-1);
					this.arcosVisitados.remove(arco);
				}
			}
		}else {
			if( this.arcosVisitados.size() <= this.lim) {
				this.caminos.add(new ArrayList<>(camino_parcial));
			}
		}
	}
}
