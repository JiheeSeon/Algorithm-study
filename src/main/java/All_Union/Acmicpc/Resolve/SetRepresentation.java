package All_Union.Acmicpc.Resolve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class SetRepresentation {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int parentSize = input[0]; int opN = input[1];
        parent = IntStream.rangeClosed(0, parentSize).toArray();

        for (int i = 0; i < opN; i++) {
            input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            if (input[0] == 0){
                union(input[1], input[2]);
            }else{
                stb.append(find(input[1]) == find(input[2]) ? "YES" : "NO").append("\n");
            }
        }

        System.out.println(stb);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) return;

        if(parentA > parentB) parent[parentA] = parentB;
        else parent[parentB] = parentA;
    }

    static int find(int a) {
        return a == parent[a] ? a : (parent[a] = find(parent[a]));
    }
}
