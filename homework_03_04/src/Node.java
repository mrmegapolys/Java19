public class Node<K, V> {
    private final K key;
    private V value;
    private boolean isTombstone;

    public Node(K key, V value, boolean isTombstone) {
        this.key = key;
        this.value = value;
        this.isTombstone = isTombstone;
    }

    public Node(K key, V value) {
        this(key, value, false);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean isTombstone() {
        return isTombstone;
    }

    public void setTombstone(boolean tombstone) {
        isTombstone = tombstone;
    }
}
