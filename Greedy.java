package tpe2;


import java.util.ArrayList;
import java.util.Iterator;

public class Greedy {
    private ArrayList<Integer> estaciones = new ArrayList<>();
    ArrayList<Arco<Integer>> solucion = new ArrayList<Arco<Integer>>();
    private UnionFind unionFind;
    private int metrica;
    private int mejoresKms;
    
    public Greedy(ArrayList<Integer> estaciones){
        this.estaciones = estaciones;
        this.metrica = 0;
        this.mejoresKms = 0;
        this.unionFind = new UnionFind(this.estaciones.size());
    }

    public ArrayList<Arco<Integer>> greedy(ArrayList<Arco<Integer>> dataset){
        
        burbuja(dataset); //ORDENAR DE MENOR A MAYOR km
        Iterator<Arco<Integer>> it = dataset.iterator();
        
        while (it.hasNext() && solucion.size() <= this.estaciones.size()-1){
            this.metrica++;
            Arco<Integer> arco = it.next();
            int origenEstacion = estaciones.indexOf(arco.getVerticeOrigen());
    		int destinoEstacion = estaciones.indexOf(arco.getVerticeDestino());
    		
    		// si el grafo no es conexo, entra al if y une el origen y el destino del arco
            if(!esConexo(origenEstacion,destinoEstacion)){
            	solucion.add(arco);
            	this.mejoresKms += arco.getEtiqueta();
            	unionFind.union(origenEstacion, destinoEstacion);
            }
        }
        return solucion;
    }
    
    private boolean seUsanTodas(ArrayList<Integer> estaciones) {
		
		return solucion.size()<= this.estaciones.size()-1;
	}

	// si los padres de los conjuntos del origen y destino del arco son iguales (forman parte del mismo conjunto)
    private boolean esConexo(int origenEstacion,int destinoEstacion) {
		return unionFind.find(origenEstacion) == unionFind.find(destinoEstacion);
	}
    
    public static void burbuja(ArrayList<Arco<Integer>> arcos) {
        for (int i = 0; i < arcos.size(); i++) {
            for (int j = 0; j < arcos.size() - 1; j++) {
                Arco<Integer> arcoActual = arcos.get(j);
                Arco<Integer> arcoSiguiente = arcos.get(j+1);
                if (arcoActual.getEtiqueta() > arcoSiguiente.getEtiqueta()) {
                    arcos.set(j,arcoSiguiente);
                    arcos.set(j+1,arcoActual);
                }
            }
        }
    }

    public int getMejoresKms() {
        return mejoresKms;
    }

    public int getMetrica() {
        return metrica;
    }

    public UnionFind getUnionFind() {
        return unionFind;
    }
    public void setUnionFind(UnionFind unionFind){
        this.unionFind = unionFind;
    }
}