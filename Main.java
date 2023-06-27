package tpe2;

public class Main {

	public static void main(String[] args) {

		String patho = "C:/Users/patri/programacion3/trabajoPractico3/src/tpe2/datasets";
		String path = "C:/Users/patri/programacion3/trabajoPractico3/src/tpe2/datasets";
		CSVReader reader = new CSVReader(path+"/dataset1.txt");
		reader.read();
		
		System.out.println(reader.arcos);
	}
}