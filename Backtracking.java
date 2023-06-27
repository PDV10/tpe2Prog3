package tpe2;

import java.util.ArrayList;

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
			
		}
	}
}

