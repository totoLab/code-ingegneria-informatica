package esercitazioni.map;

import java.io.Serializable;
import java.util.Iterator;

public interface CustomMapIF<K,V> extends Iterable<Entry<K,V>>, Serializable{

	//num of entry
	public default int size() {
		int cnt=0;
		Iterator<Entry<K,V>> it = iterator();
		while(it.hasNext()) {
			it.next(); //perché devo metterlo? -- soluz: per fare avanzare il puntatore
			cnt++;
		}
		return cnt;
	}
	
	//mappa vuota
	public default boolean isEmpty() {
		return size()==0;
	}
	
	//inefficiente
	public default V getValue(K key) {
		Iterator<Entry<K,V>> it = iterator();
		while(it.hasNext()) {
			Entry<K,V> elem = it.next();
			if(elem.getK().equals(key)) {
				return elem.getV();
			}
		}
		return null;
	}
	
	//inefficiente
	public default boolean contains(K key) {
		Iterator<Entry<K,V>> it = iterator();
		while(it.hasNext()) {
			Entry<K,V> elem = it.next();
			if(elem.getK().equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	public void put(K k,V v);
	
	//inefficiente
	public default boolean removeKey(K key) {
		Iterator<Entry<K,V>> it = this.iterator();
		while(it.hasNext()) {
			Entry<K,V> elem = it.next();
			//una sola chiave
			if(elem.getK().equals(key)) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	//posso solo farlo così: devo scorrere tutti gli elementi per cercare V
	public default boolean removeValue(V val) {
		Iterator<Entry<K,V>> it = this.iterator();
		boolean delete=false;
		while(it.hasNext()) {
			Entry<K,V> elem = it.next();
			//rimuovo tutti i valori anche se associati a più chiavi
			if(elem.getV().equals(val)) {
				it.remove();
				delete=true;
				//return true; //rimuovo singolo
			}
		}
		if(delete) return true;
		return false;
	}
	
	public void saveMap(String path);
	public void restoreMap(String path);
	public void saveAsText(String path);
}
