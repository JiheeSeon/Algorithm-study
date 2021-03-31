package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Main15565{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int N = input[0], K = input[1];

        String s = br.readLine().replace(" ","");

        int result = Integer.MAX_VALUE;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++){
            if (s.charAt(i) == '1') list.add(i);
        }

        if(list.size() < K) result = -1;
        else{
            for (int i = (K - 1); i < list.size(); i++){
                result = Math.min(list.get(i) - list.get(i - (K - 1)) + 1, result);
            }
        }
        System.out.println(result);
    }
}