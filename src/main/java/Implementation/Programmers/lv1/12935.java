package Implementation.Programmers.lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.Collectors;

class Main12935 {
    public static int[] solution(int[] arr) {
        OptionalInt min = Arrays.stream(arr).min();
        ArrayList<Integer> ar = (ArrayList<Integer>) Arrays.stream(arr).boxed().collect(Collectors.toList());
        if (min.isPresent()) ar.remove(ar.indexOf(min.getAsInt()));
        return ar.size() == 0 ? new int[]{-1} : ar.stream().mapToInt(o -> o).toArray();

        /*Other solution :: not remove, filter
        int min = Arrays.stream(arr).min().getAsInt();
        return Arrays.stream(arr).filter(i -> i != min).toArray();*/
    }

    public static void main(String[] args) {
        int[] res = solution(new int[]{-1, 3, 4, 2, 5, 10, 65});

        for (int i : res)
            System.out.println(i);
    }
}