package tpe2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
	
	public static void main(String[] args) {

		String patho = "C:/Users/patri/programacion3/trabajoPractico3/src/tpe2/datasets";
		String path = "./datasets/dataset1.txt";
		CSVReader reader = new CSVReader(patho+"/dataset3.txt");//pato
		//CSVReader reader = new CSVReader(path); //agus
		reader.read();
		
		ArrayList<Arco<Integer>> dataset = reader.arcos;
		ArrayList<Integer> estaciones = reader.estaciones;
		
		Backtracking backtraking = new Backtracking(dataset,estaciones);
		ArrayList<Arco<Integer>> solucion = backtraking.back();
		
		System.out.println("universo: " + dataset+ "\n");
		
		System.out.println("tecnica utilizada: BACKTRAKING");
		System.out.println("estaciones: "+estaciones);
		System.out.println("lista de tuneles: " + solucion);
		System.out.println("cantidad de metros de los tuneles: " + backtraking.getMejoresKms());
		System.out.println("metrica: "+ backtraking.getMetrica());

		System.out.println("\n");
		
		Greedy greedy = new Greedy(estaciones);
		ArrayList<Arco<Integer>> solucionGreedy = greedy.greedy(dataset);

		System.out.println("tecnica utilizada: GREEDY");
		System.out.println("estaciones: "+estaciones);
		System.out.println("lista de tuneles: " + solucionGreedy);
		System.out.println("cantidad de metros de los tuneles: " + greedy.getMejoresKms());
		System.out.println("metrica: "+ greedy.getMetrica());
	}
}