package tpe2;

import java.util.ArrayList;
import java.util.Iterator;

public class Backtracking<T> {
	ArrayList<Arco<Integer>> dataset = new ArrayList<Arco<Integer>>();
	ArrayList<Arco<Integer>> mejorSolucion = new ArrayList<Arco<Integer>>();
	ArrayList<Integer> estaciones = new ArrayList<>();
	int mejoresKms;
	
	public Backtracking(ArrayList<Arco<Integer>> dataset, ArrayList<Integer> estaciones) {
		this.dataset = dataset;
		this.estaciones = estaciones;
		this.mejoresKms = 0;
	}
	
	public ArrayList<Arco<Integer>> back(){
		Estado e = new Estado();
		backtracking(e);
		return mejorSolucion;
	}

	private	void backtracking(Estado e) {
		if(e.getSolucionParcial().size() == this.estaciones.size()-1) {
			if(e.getKm() <= this.mejoresKms) {
				mejorSolucion.clear();
				mejorSolucion.addAll(e.getSolucionParcial());
				this.mejoresKms = e.getKm();
			}
		}else {

			Iterator<Arco<Integer>> arcos = this.dataset.iterator();
			while(arcos.hasNext()){
				Arco<Integer> arco = arcos.next();

				if(arco.getEtiqueta() + e.getKm() < this.mejoresKms){
					if(!e.getSolucionParcial().contains(arco)){
						e.addArco(arco);
						e.setPoss(e.getPoss() + 1);
						if(e.getSolucionParcial().size() <= this.estaciones.size()-1) {
							backtracking(e);
						}
						e.removeArco(arco);
						e.setPoss(e.getPoss() - 1);
					}
				}



			}

		}
	}
}

