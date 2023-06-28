package tpe2;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {

		/*String patho = "C:/Users/patri/programacion3/trabajoPractico3/src/tpe2/datasets";*/
		String path = "./datasets/dataset1.txt";
		/*"./datasets/dataset3.txt"*/
		CSVReader reader = new CSVReader(path);
		reader.read();
		
		ArrayList<Arco<Integer>> arcos = reader.arcos;
		ArrayList<Integer> estaciones = reader.estaciones;
		
		/*Backtracking backtraking = new Backtracking(arcos,estaciones);
		ArrayList<Arco<Integer>> solucion = backtraking.back();
		
		System.out.println("tecnica utilizada: BACKTRAKING");
		System.out.println("estaciones: "+estaciones);
		System.out.println("lista de tuneles: " + solucion);
		System.out.println("cantidad de metros de los tuneles: " + backtraking.getMejoresKms());
		System.out.println("metrica: "+ backtraking.getMetrica());*/



		Greedy greedy = new Greedy(estaciones);
		ArrayList<Arco<Integer>> solucionG = greedy.greedy(arcos);
		ArrayList<Integer> estacionesG = greedy.getEstaciones();
		/*System.out.println(greedy.greedy(arcos,estaciones));*/

		System.out.println("tecnica utilizada: GREEDY");
		System.out.println("estaciones: "+ estacionesG);
		System.out.println("lista de tuneles: " + solucionG);
		System.out.println("cantidad de metros de los tuneles: " + greedy.getMejoresKms());
		System.out.println("metrica: "+ greedy.getMetrica());
	}
}