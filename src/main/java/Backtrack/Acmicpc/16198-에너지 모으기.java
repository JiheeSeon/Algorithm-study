package Backtrack.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Main15198{
    static int N;
    static LinkedList<Integer> weight;
    static int result = 0;
    static int localResult = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).boxed().collect(Collectors.toCollection(LinkedList::new));
        backtrack();

        System.out.println(result);
    }
    static void backtrack(){
        int w;
        for (int i = 1; i <= weight.size() - 2; i++){
            localResult += (weight.get(i - 1) * weight.get(i + 1));
            w = weight.remove(i);

            if (weight.size() == 2){
                result = Math.max(localResult, result);
            } else backtrack();

            weight.add(i, w);
            localResult -= (weight.get(i - 1) * weight.get(i + 1));
        }
    }
}