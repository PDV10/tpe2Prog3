package tpe2;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {

		String patho = "C:/Users/patri/programacion3/trabajoPractico3/src/tpe2/datasets";
		String path = "C:/Users/patri/programacion3/trabajoPractico3/src/tpe2/datasets";
		CSVReader reader = new CSVReader(path+"/dataset3.txt");
		reader.read();
		
		ArrayList<Arco<Integer>> arcos = reader.arcos;
		ArrayList<Integer> estaciones = reader.estaciones;
		
		Backtracking backtraking = new Backtracking(arcos,estaciones);
		ArrayList<Arco<Integer>> solucion = backtraking.back();
		
		System.out.println("tecnica utilizada: backtracking");
		System.out.println("estaciones: "+estaciones);
		System.out.println("lista de tuneles: " + solucion);
		System.out.println("cantidad de metros de los tuneles: " + backtraking.getMejoresKms());
		System.out.println("metrica: "+ backtraking.getMetrica());
	}
}