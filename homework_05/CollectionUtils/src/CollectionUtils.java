import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static List newArrayList() {
        return new ArrayList();
    }

    public static int indexOf(List source, Object o) {
        return 0;
    }

    public static List limit(List source, int size) {
        return null;
    }


    public static void add(List source, Object o) {

    }

    public static void removeAll(List removeFrom, List c2) {

    }

    public static boolean containsAll(List c1, List c2) {
        return false;
    }

    public static boolean containsAny(List c1, List c2) {
        return false;
    }

    public static List range(List list, Object min, Object max) {
        return null;
    }

    public static List range(List list, Object min, Object max, Comparator comparator) {
        return null;
    }

}