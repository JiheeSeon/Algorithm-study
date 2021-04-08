package All_Union;

import java.util.*;
import java.util.stream.LongStream;

public class ExampleWithMap {
    static Map<Long, Long> parent = new LinkedHashMap<>();

    public static void main(String[] args) {
        union(1, 3);
        System.out.println(Arrays.toString(parent.keySet().toArray()));
        System.out.println(Arrays.toString(parent.values().stream().mapToLong(x->x).toArray()));

        union(5, 3); // [0, 0, 2, 3, 4, 3, 6, 7, 8, 9]
        System.out.println(Arrays.toString(parent.keySet().toArray()));
        System.out.println(Arrays.toString(parent.values().stream().mapToLong(x->x).toArray()));

        union(3, 87); // [0, 0, 2, 0, 4, 3, 6, 7, 8, 9]
        System.out.println(Arrays.toString(parent.keySet().toArray()));
        System.out.println(Arrays.toString(parent.values().stream().mapToLong(x->x).toArray()));
    }
    static void union(long x, long y){
        long parentX = find(x);
        long parentY = find(y);

        if(parentX == parentY) return;

        if(parentX < parentY) parent.put(parentX, parentY);
        else parent.put(parentY, parentX);
    }

    static long find(long x) {
        parent.putIfAbsent(x, x);

        if (parent.get(x) == x) return x;
        else{
            parent.put(x, find(parent.get(x)));
            return find(parent.get(x));
        }
    }
}
