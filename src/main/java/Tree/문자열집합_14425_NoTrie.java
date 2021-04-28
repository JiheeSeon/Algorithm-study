package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

class 문자열집합_14425_NoTrie {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int N = tmp[0]; int M = tmp[1];

        Set<String> set = new HashSet<>();
        for(int i = 0; i < N; i++){
            set.add(br.readLine());
        }

        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if(set.contains(br.readLine())) cnt++;
        }
        System.out.println(cnt);
    }
}
