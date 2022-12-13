package map;

import java.util.Iterator;

public abstract class AbstractCustomMap<K,V> implements CustomMapIF<K, V>{

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		Iterator<Entry<K,V>> it = iterator();
		while(it.hasNext()) {
			Entry<K,V> elem = it.next();
			sb.append("<"+elem.getK().toString()+","+elem.getV().toString()+"> ");
		}
		sb.append("]");
		return sb.toString();
	}
}
