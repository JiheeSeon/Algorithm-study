package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.Pattern;

class 삼십번_13116 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder stb = new StringBuilder();

        for(int t = 0; t < T; t++) {
            int[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            int A = tmp[0];
            int B = tmp[1];
            Set<Integer> l1 = getPath(A, new HashSet<>());
            Set<Integer> l2 = getPath(B, new HashSet<>());

            l1.retainAll(l2);
            int res = l1.stream().mapToInt(x -> (int) x).max().getAsInt();
            stb.append(res * 10).append("\n");
        }
        System.out.print(stb);
    }
    static Set<Integer> getPath(int curr, HashSet<Integer> path){
        if(curr == 1){
            path.add(1);
            return path;
        }
        path.add(curr);
        return getPath(curr / 2, path);
    }
}
