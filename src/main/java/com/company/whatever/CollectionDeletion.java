import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class CollectionDeletion {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Integer i = Integer.valueOf(1);
        list.add(i);
        list.add(3);
        list.add(5);
        list.add(8);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
            // list.remove(index)
        }
        System.out.println(list.isEmpty());
    }
}