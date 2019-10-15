import java.util.Map;

public interface CountMap<K> {
    void add(K key);

    int getCount(K key);

    int remove(K key);

    int size();

    void addAll(CountMap<K> source);

    Map<K, Integer> toMap();

    void toMap(Map<K, Integer> destination);
}
