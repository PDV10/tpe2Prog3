package tpe2;


import java.util.ArrayList;
import java.util.Iterator;

public class Greedy {
    private MergeSort mergeSort;
    private ArrayList<Arco<Integer>> mejorSolucion = new ArrayList<Arco<Integer>>();
    private ArrayList<Integer> estaciones = new ArrayList<>();
    private ArrayList<Integer> estacionesVisitadas = new ArrayList<>();
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
        ArrayList<Arco<Integer>> solucion = new ArrayList<Arco<Integer>>();
        burbuja(dataset); //ORDENAR DE MENOR A MAYOR km
        Iterator<Arco<Integer>> it = dataset.iterator();
        boolean flag = true;
        while (it.hasNext() && flag){
            this.metrica++;
            Arco<Integer> arco = it.next();

            int origenEstacion = estaciones.indexOf(arco.getVerticeOrigen());
            int destinoEstacion = estaciones.indexOf(arco.getVerticeDestino());
            if(solucion.size() == this.estaciones.size() - 1 && this.estacionesVisitadas.size() == this.estaciones.size()) {
                this.estaciones.addAll(this.estaciones);
                flag = false;
                System.out.println(this.estacionesVisitadas);
            }
            if(noRedundante(arco)){
                solucion.add(arco);
                this.mejoresKms += arco.getEtiqueta();
                UnionFind clon = getUnionFind().clone();
                unionFind.union(origenEstacion, destinoEstacion);
            }

            if(!this.estacionesVisitadas.contains(arco.getVerticeDestino())){
                estacionesVisitadas.add(arco.getVerticeDestino());
            }
            if(!this.estacionesVisitadas.contains(arco.getVerticeOrigen())){
                estacionesVisitadas.add(arco.getVerticeOrigen());
            }

            /*if(!solucion.contains(arco)){
                if (!estacionesVisitadas.contains(arco.getVerticeDestino())){
                    if(noRedundante(arco)){
                        solucion.add(arco);
                        this.mejoresKms += arco.getEtiqueta();
                        UnionFind clon = getUnionFind().clone();
                        unionFind.union(origenEstacion, destinoEstacion);
                    }
                }



            }*/


        }
        return solucion;
    }

    public boolean noRedundante(Arco<Integer> arco){
        int origenEstacion = estaciones.indexOf(arco.getVerticeOrigen());
        int destinoEstacion = estaciones.indexOf(arco.getVerticeDestino());
        return origenEstacion != destinoEstacion;
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

    public ArrayList<Integer> getEstaciones() {
        return estaciones;
    }
    public UnionFind getUnionFind() {
        return unionFind;
    }
    public void setUnionFind(UnionFind unionFind){
        this.unionFind = unionFind;
    }
}
