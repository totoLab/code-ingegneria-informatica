package map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CustomHashTable<K,V> extends AbstractCustomMap<K, V>{
	
	private List<Entry<K,V>>[] maps;
	private int loadFactor;
	
	public CustomHashTable(int dim, int loadFactor) {
		this.maps = new LinkedList[dim];
		for(int i=0; i<dim; i++) {
			maps[i]=new LinkedList<>();
		}
	}

	public V getValue(K key) {
		int pos = key.hashCode()%maps.length;
		Iterator<Entry<K,V>> it = maps[pos].iterator();
		while(it.hasNext()) {
			Entry<K,V> elem=it.next();
			if(elem.getK().equals(key)) {
				return elem.getV();
			}
		}
		return null;
	}
	
	public boolean contains(K key){
		int pos = key.hashCode()%maps.length;
		Iterator<Entry<K,V>> it = maps[pos].iterator();
		while(it.hasNext()) {
			Entry<K,V> elem = it.next();
			if(elem.getK().equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void put(K k, V v) {
		if(this.size()/maps.length>loadFactor) {
			rialloca();
		}
		int pos = k.hashCode()%maps.length;
		removeEntry(k,pos);
		Entry<K,V> elem = new Entry<>(k,v);
		if(maps[pos].size()>0) System.out.println("COLLISIONE");
		maps[pos].add(elem);
	}
	
	//elimino la chiave se presente
	private boolean removeEntry(K toFind, int pos) {
		Iterator<Entry<K,V>> it = maps[pos].iterator();
		while(it.hasNext()) {
			Entry<K,V> elem = it.next();
			if(elem.getK().equals(toFind)) {
				it.remove(); 
				return true;
			}
		}
		return false;
	}
	
	private void rialloca() {
		Iterator<Entry<K,V>> it = this.iterator();
		List<Entry<K,V>>[] newMap = new LinkedList[maps.length*2];
		for(int i=0; i<newMap.length; i++) { newMap[i]=new LinkedList<>(); }
		while(it.hasNext()) {
			Entry<K,V> elem = it.next();
			int pos = elem.getK().hashCode()%newMap.length;
			newMap[pos].add(elem);
		}
		maps=newMap;
	}

	@Override
	public boolean removeKey(K key) {
		int pos = key.hashCode()%maps.length;
		Iterator<Entry<K,V>> it = maps[pos].iterator();
		while(it.hasNext()) {
			if(it.next().getK().equals(key)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public void saveMap(String path) {
		try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            System.out.println("Oggetto salvato con successo");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	@Override
	public void restoreMap(String path) {
		try
		{
		    FileInputStream myFileInputStream = new FileInputStream(path);
		    ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
		    CustomHashTable<K,V> load = (CustomHashTable<K,V>) myObjectInputStream.readObject(); 
		    this.loadFactor=load.getLoadFactor();
		    this.maps=load.getMaps();
		    myObjectInputStream.close();
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	}
	
	@Override
	public void saveAsText(String path) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(path);
			pw.write(this.toString());
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			pw.close();
		}	
	}

	@Override
	public Iterator<Entry<K, V>> iterator() { return new IteratorMap(); }
	
	public List<Entry<K, V>>[] getMaps() { return maps; }
	public void setMaps(List<Entry<K, V>>[] maps) { this.maps = maps; }

	public int getLoadFactor() { return loadFactor; }
	public void setLoadFactor(int loadFactor) { this.loadFactor = loadFactor; }
	
	
	class IteratorMap implements Iterator<Entry<K,V>>{

		int suc=0;
		
		boolean scorriLista=false;
		Iterator<Entry<K,V>> it=null;
		
		@Override
		public boolean hasNext() {
			if(it==null || !it.hasNext()) {
				//cerco l'indice
				while(suc<maps.length && maps[suc].isEmpty()) {
					suc++;
				}
				//due casi:
				//ho scandito tutto l'array e non ho trovato risultati
				if(suc==maps.length) return false;
				//ho trovato un risultato
				it=maps[suc].iterator();
				//incremento succ per fargli puntare la cella successiva (per il controllo e non bloccarsi)
				suc++;
				return it.hasNext();
			}else
				return it.hasNext();
		}

		@Override
		public Entry<K, V> next() { 
			return it.next(); 
		}
	
		
		public void remove(){ 
			it.remove(); 
		}//remove	
	}
}
