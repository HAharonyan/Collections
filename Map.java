public interface Map<K, V> {

	void put(K key, V value); 
	//void putAll(Map<? extends K, ? extends V> m);

	V get(K key); 

	V remove(K key); 
	//boolean remove(K key, V value);
/*
	

	Set<K> keySet();
	Collection<V> values();
	Set<Entry> entrySet();

	boolean containsKey(K key);
*/
	V replace(K key, V value);
	boolean replace(K key, V oldValue, V newValue);

	int size();
	boolean isEmpty();
	void clear();

	interface Entry<K, V> {
		V getValue();
		K getKey();
		void setValue(V value);
	}
}
