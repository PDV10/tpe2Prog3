package tpe2;

import java.util.ArrayList;
import java.util.Iterator;

public class Estado {
	private int poss;
	private int km;
	public ArrayList<Arco<Integer>> solucionParcial;
	ArrayList<Integer> estacionesVisitadas;
	
	public Estado() {
		this.poss = 0;
		this.km = 0;
		solucionParcial = new ArrayList<>();
		estacionesVisitadas = new ArrayList<>();
	}
	
	public ArrayList<Integer> getEstacionesVisitadas() {
		return estacionesVisitadas;
	}
	
	public void addEstacionVisitada(int e) {
		this.estacionesVisitadas.add(e);
	}
	
	public void clearEstacionVisitada() {
		this.estacionesVisitadas.clear();
	}

	public ArrayList<Arco<Integer>> getSolucionParcial() {
		return solucionParcial;
	}

	public void addArco(Arco<Integer> arco) {
		this.solucionParcial.add(arco);
	}

	public void removeArco(Arco<Integer> arco) {
		this.solucionParcial.remove(arco);
	}

	public int getPoss() {
		return poss;
	}

	public void setPoss(int poss) {
		this.poss = poss;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}
	
}
