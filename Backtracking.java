package tpe2;

import java.util.ArrayList;
import java.util.Iterator;

public class Backtracking<T> {
	private ArrayList<Arco<Integer>> dataset = new ArrayList<Arco<Integer>>();
	private ArrayList<Arco<Integer>> mejorSolucion = new ArrayList<Arco<Integer>>();
	private ArrayList<Integer> estaciones = new ArrayList<>();
	private UnionFind unionFind ;
	private int metrica ;
	private int mejoresKms;


	public Backtracking(ArrayList<Arco<Integer>> dataset, ArrayList<Integer> estaciones) {
		this.dataset = dataset;
		this.estaciones = estaciones;
		this.mejoresKms = 0;
		this.metrica = 0;
		this.unionFind = new UnionFind(estaciones.size());
	}
	
	public ArrayList<Arco<Integer>> back(){
		Estado e = new Estado();
		backtracking(e);
		return mejorSolucion;
	}

	private	void backtracking(Estado e) {
		this.metrica++;
		
		if(e.getPoss() == dataset.size() || e.getSolucionParcial().size() == this.estaciones.size()-1) { //O(2^T)
			if(this.unionFind.numberOfSets() == 1) {
				if(e.getKm() <= this.mejoresKms || this.mejoresKms == 0) {					
					this.mejoresKms = e.getKm();
					mejorSolucion.clear();
					mejorSolucion.addAll(e.getSolucionParcial());
				}
			}
		}else {
			int possActual = e.getPoss();//O(1)
			Arco<Integer> arco = this.dataset.get(possActual); //O(1)
			
			int origenEstacion = estaciones.indexOf(arco.getVerticeOrigen()); //O(e)
			int destinoEstacion = estaciones.indexOf(arco.getVerticeDestino()); //O(e)
			
			if(unionFind.find(origenEstacion) != unionFind.find(destinoEstacion)){ //O(e)
				if((e.getKm() + arco.getEtiqueta() < this.mejoresKms) || this.mejoresKms == 0) {		
					
					e.addArco(arco);
					e.setKm(e.getKm() + arco.getEtiqueta());
					UnionFind clon = getUnionFind().clone();
					unionFind.union(origenEstacion, destinoEstacion);
					e.setPoss(possActual+1);
					
					backtracking(e);	
						
					setUnionFind(clon);
					e.removeArco(arco);
					e.setPoss(possActual);	
					e.setKm(e.getKm()-arco.getEtiqueta());
				}
			}
				e.setPoss(possActual+1);
				
				backtracking(e);					
			
				e.setPoss(possActual);		
		}
	}
	

	public void setUnionFind(UnionFind unionFind) {
		this.unionFind = unionFind;
	}

	public UnionFind getUnionFind() {
		return unionFind;
	}
	
	public int getMetrica() {
		return metrica;
	}

	public int getMejoresKms() {
		return mejoresKms;
	}
}

