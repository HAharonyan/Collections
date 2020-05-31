import java.util.*;

public class HashMap<K, V> implements Map<K, V> {

	private Node<K, V>[] array;
	private int size;
	private int capacity;
	private static final int initCapacity = 16;

	
	public HashMap() {
		this(initCapacity);
	}

	@SuppressWarnings("unchecked")
	public HashMap(int capacity) {
		if(capacity < 1) {
			throw new IllegalArgumentException();
		}

		array = (Node<K, V>[]) new Node[capacity];
		this.capacity = capacity;
		size = 0;
	}

	public HashMap(Map<? extends K, ? extends V> m) {
		putAll(m);
	}

	public void put(K key, V value) {
		int hash = key.hashCode();
		int index = hash % capacity;

		if(array[index] == null) {
			array[index] = new Node<K, V>(hash, key, value);
			size++;
			return;
		}

		Node<K, V> current = array[index];

		while(true){
			if(hash == current.getHash() && key.equals(current.getKey())) {
				current.setValue(value);
				return;
			}

			if(current.getNext() == null) {
				current.setNext(new Node<K, V>(hash, key, value));
				size++;
				return;
			} else {
				current = current.getNext();
			}
		}
	}

	public V get(K key) {
		Node<K, V> node = getNode(key);
		return node.getValue();	
	}

	public V remove(K key) {
		int hash = key.hashCode();
		int index = hash % capacity;

		if(array[index] == null) {
			throw new NoSuchElementException();
		}

		Node<K, V> current = array[index];

		if(hash == current.getHash() && key.equals(current.getKey())) {
			array[index] = null;
			size--;
			return current.getValue();
		}
		
		Node<K, V> entryBefore = current;
		current = current.getNext();

		while(current != null) {
			if(hash == current.getHash() && key.equals(current.getKey())) {
				entryBefore.setNext(current.getNext());
				size--;
				return current.getValue();
			} else {
				entryBefore = current;
				current = current.getNext();
			}
		}

		throw new NoSuchElementException();	
	}

	public boolean remove(K key, V value) {
		//To-do
		return false;
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		 //To-do
	}


	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		for(int i = 0; i < array.length; i++) {
			array[i] = null;
		}
	}

	public Set<K> keySet() {
		Set<K> keys = new HashSet<>();

		for(int i = 0; i < capacity; i++) {
			if(array[i] != null) {
				Node<K, V> node = array[i];
				while(node != null) {
					keys.add(node.getKey());
					node = node.getNext();
				}
			}
		}

		return keys;
	}

	public Collection<V> values() {
		Collection<V> values = new ArrayList<>();

		for(K key : keySet()) {
			Node<K, V> node = getNode(key);
			values.add(node.getValue());
		}

		return values;
	}

	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> entrySet = new HashSet<>();

		for(K key : keySet()) {
			Map.Entry<K, V> entry = getNode(key);
			entrySet.add(entry);
		}

		return entrySet;
	}

	public V replace(K key, V value) {
		Node<K, V> node = getNode(key);
		V oldValue = node.getValue();
		node.setValue(value);
		return oldValue;
	}

	public boolean replace(K key, V oldValue, V newValue) {
		Node<K, V> node = getNode(key);
		V oldVal = node.getValue();
		if(oldVal.equals(oldValue)) {
			node.setValue(newValue);
			return true;
		} else {
			return false;
		}
	}

	public boolean containsKey(K key) {
		for(K k : keySet()) {
			if(k.equals(key)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsValue(V value) {
		for(V v : values()) {
			if(v.equals(value)) {
				return true;
			}
		}
		return false;
	}

	private Node<K, V> getNode(K key) {
		int hash = key.hashCode();
		int index = hash % capacity;

		if(array[index] == null) {
			throw new NoSuchElementException();
		}

		Node<K, V> current = array[index];

		while(current != null) {
			if(hash == current.getHash() && key.equals(current.getKey())) {
				return current;
			} else {
				current = current.getNext();
			}
		} 

		throw new NoSuchElementException();
	}
}

class Node<K, V> implements Map.Entry<K, V> {
	private final int hash;
	private final K key;
	private V value;
	private Node<K, V> next;

	public Node(int hash, K key, V value) {
		this.hash = hash;
		this.key = key;
		this.value = value;
		next = null;
	}

	public int getHash() {
		return hash;
	}

	public V getValue() {
		return value;
	}

	public K getKey() {
		return key;
	}

	public Node<K, V> getNext() {
		return next;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public void setNext(Node<K, V> next) {
		this.next = next;
	}
}