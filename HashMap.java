import java.util.NoSuchElementException;

public class HashMap<K, V> implements Map<K, V> {

	private Node<K, V>[] array;
	private int size;
	private static final initCapacity = 16;

	@SuppressWarnings("unchecked")
	public HashMap() {
		array = (Node<K, V>[]) new Node[initCapacity];
		size = 0;
	}

	public void put(K key, V value) {
		int hash = key.hashCode();
		int index = hash % 16;

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
		int index = hash % 16;

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

	public int size() {
		return size;
	}

	public void isEmpty() {
		return size == 0;
	}

	public void clear() {
		for(int i = 0; i < array.length; i++) {
			array[i] = null;
		}
	}

	public Set<K> keySet() {

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

	private Node<K, V> getNode(K key) {
		int hash = key.hashCode();
		int index = hash % 16;

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