package tpe2;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {

		String patho = "C:/Users/patri/programacion3/trabajoPractico3/src/tpe2/datasets";
		String path = "C:/Users/patri/programacion3/trabajoPractico3/src/tpe2/datasets";
		CSVReader reader = new CSVReader(path+"/dataset1.txt");
		reader.read();
		
		ArrayList<Arco<Integer>> arcos = reader.arcos;
		ArrayList<Integer> estaciones = reader.estaciones;
		
		System.out.println("estaciones: "+estaciones);
		
		Backtracking backtraking = new Backtracking(arcos,estaciones);
		ArrayList<Arco<Integer>> solucion = backtraking.back();
		
	
		
		System.out.println("tecnica utilizada: backtracking");
		System.out.println("lista de arcos: [E1-E2,E3-E5]");
		System.out.println("cantidad de metros de tunel: 180");
		System.out.println("metrica: ... ");
	}
}