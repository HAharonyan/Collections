import java.util.*;

public interface Map<K, V> {

	void put(K key, V value); 
	void putAll(Map<? extends K, ? extends V> m);
	V get(K key); 
	V remove(K key); 
	boolean remove(K key, V value);
	V replace(K key, V value);
	boolean replace(K key, V oldValue, V newValue);

	Set<K> keySet();
	Collection<V> values();
	Set<Map.Entry<K, V>> entrySet();

	boolean containsKey(K key);
	boolean containsValue(V value);
	int size();
	boolean isEmpty();
	void clear();

	interface Entry<K, V> {
		V getValue();
		K getKey();
		void setValue(V value);
	}
}
