package Implementation.Programmers.lv1;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

class Main68644{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        int[] ans = solution(input);
        for(int i : ans)
            stb.append(i).append(" ");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static int[] solution(int[] numbers) {
        int[] answer = {};

        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

        for(int i = 0; i < numbers.length; i++){
            for(int j = i + 1; j < numbers.length; j++){
                map.put(numbers[i] + numbers[j], 1);
            }
        }

        answer = map.keySet().stream().mapToInt(o->o).toArray();
//        answer = (Integer[]) map.keySet().toArray();
        return answer;
    }
}