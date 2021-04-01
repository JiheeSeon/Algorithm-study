package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class Main20922{
    static int N, K;
    static int[] sequence;
    static int result;

    static Map<Integer, Integer> map = new HashMap<>();
    static int length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0]; K = input[1];

        sequence = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        solution();

        System.out.println(result);
    }
    static void solution(){
        int start = 0, end = 0;

        while (true){
            if (end == N){
                result = Math.max(length, result);
                break;
            }
            else if (map.getOrDefault(sequence[end], -1) == K){
                result = Math.max(length, result);

                while (sequence[start] != sequence[end]){
                    map.put(sequence[start], map.get(sequence[start]) - 1);
                    start++; length--;
                }
                end++;
            }
            else {
                map.put(sequence[end], map.getOrDefault(sequence[end], 0) + 1);
                end++; length++;
            }
        }
    }
}