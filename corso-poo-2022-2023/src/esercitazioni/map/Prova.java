package esercitazioni.map;

public class Prova {

	public static void main(String...args) {
		CustomMapIF<Integer, String> maps = new CustomHashTable<>(10, 1);
		/*for(int i=0; i<20; i+=4) {
			maps.put(i, "Aleeee");
		}*/
		maps.put(2, "Aleeee");
		maps.put(12, "Aleeee");
		maps.put(22, "Aleeee");
		System.out.println(maps.toString());
		System.out.println(maps.size());
		
		System.out.println(maps.contains(12)+"   "+maps.contains(3));
		maps.removeKey(12);
		System.out.println(maps);
		
		String path = "/Users/paolofrancescosciammarella/Desktop/";
		maps.saveMap(path+"obj.dat");
		maps.saveAsText(path+"prova.txt");
		
		CustomMapIF<Integer, String> newMap = new CustomHashTable<>(10, 1);
		newMap.restoreMap(path+"obj.dat");
		System.out.println(">> RESTORE: "+newMap);
		
		maps.removeValue("Aleeee");
		System.out.println(maps);
		
	}
}
