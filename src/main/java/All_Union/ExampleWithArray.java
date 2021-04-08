package All_Union;

import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class ExampleWithArray {
    static int[] parent; // 자신의 부모를 넣은 배열

    public static void main(String[] args) throws IOException{
        parent = IntStream.range(0, 10).toArray();
        // parent [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println("0. Initialization");
        System.out.println(Arrays.toString(parent));


        union(0, 1); // [0, 0, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println("1. union set of 0 and 1");
        System.out.println(Arrays.toString(parent));

        union(5, 3); // [0, 0, 2, 3, 4, 3, 6, 7, 8, 9]
        System.out.println("2. union set of 5 and 3");
        System.out.println(Arrays.toString(parent));


        union(3, 1); // [0, 0, 2, 0, 4, 3, 6, 7, 8, 9]
        /* union(1, 3)
            find(1)
            -> 1이 해당 집합의 최종 parent가 아님
            -> parent[1] = find(0) <- 0
            -> find(0) -> 0 반환
            find(3)
            ->
            parent [0,0,2,0,
         */
        System.out.println("3. union set of 3 and 1");
        System.out.println(Arrays.toString(parent));


        union(2, 4); // [0, 0, 2, 0, 2, 3, 6, 7, 8, 9]
        System.out.println("4. union set of 2 and 4");
        System.out.println(Arrays.toString(parent));

        union(6, 4); // [0, 0, 2, 0, 2, 3, 2, 7, 8, 9]
        System.out.println("5. union set of 6 and 4");
        System.out.println(Arrays.toString(parent));

        union(9, 7); //[ 0, 0, 2, 0, 2, 3, 2, 7, 8, 7]
        System.out.println("6. union set of 9 and 7");
        System.out.println(Arrays.toString(parent));

        union(4, 7); // [0, 0, 2, 0, 2, 3, 2, 2, 8, 7]
        System.out.println("7. union set of 4 and 7");
        System.out.println(Arrays.toString(parent));

        System.out.println("8. find");
        for (int i = 0; i < parent.length; i++) {
            find(i);
            System.out.println(Arrays.toString(parent));
        } // [0, 0, 2, 0, 2, 0, 2, 2, 8, 2]

        union(5, 4); // [0, 0, 0, 0, 2, 0, 2, 2, 8, 2]
        System.out.println("9. union set of 5 and 4");
        System.out.println(Arrays.toString(parent));
    }

    // node1이 속한 집합과 node2가 속한 집합을 합친다.
    static void union(int n1Idx, int n2Idx){
        int parent1 = find(n1Idx);
        int parent2 = find(n2Idx);

        if(parent1 == parent2) return;

        // 부모를 더 작은 값으로 설정
        if(parent1 > parent2) parent[parent1] = parent2;
        else parent[parent2] = parent1;
    }

    // 해당하는 노드의 최종 부모를 찾음
    static int find(int nodeIdx){
        return parent[nodeIdx] == nodeIdx
                ? nodeIdx
                : (parent[nodeIdx] = find(parent[nodeIdx])); // path compression
    }

    /* extended version
    public static int find(int a) {
        if (parent[a] == a)
            return a;
        else {
            return parent[a] = find(parent[a]);
        }
    }
     */
}
