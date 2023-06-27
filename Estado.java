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
	
	// retornamos el clone ?
	public ArrayList<Arco<Integer>> getSolucionParcial() {
		return solucionParcial;
	}
	
	/**
	 *  metodo que se va quedando con la mejor solucion parcial
	 * 	primero borra la lista (es caso de que exista una anterior/peor) 
	 * 	luego completa con la solucion que llega por parametro
	 * 	y mantiene actualizado los km
	 */
	public void setSolucionParcial(ArrayList<Arco<Integer>> s) {
		this.solucionParcial.clear();
		km = 0;
		Iterator<Arco<Integer>> arcos = s.iterator();
		while(arcos.hasNext()) {
			Arco<Integer> arco = arcos.next();
			addArco(arco);
		}
	}

	private void addArco(Arco<Integer> arco) {
		this.solucionParcial.add(arco);
		setKm(arco.getEtiqueta());
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
		this.km += km;
	}
	
}
