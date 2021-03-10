package Greedy.Programmers;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Main42862{
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = 5;
        int[] lost = {2,4};
        int[] reserve = {1, 3, 5};
        bw.write(Integer.toString(solution(N, lost, reserve)));
        bw.flush();
        bw.close();
    }
    static int solution(int n, int[] lost, int[] reserve) {
        int[] spare = new int[n + 1];

        List<Integer> lostList = Arrays.stream(lost).boxed().collect(Collectors.toList());
        List<Integer> reserveList = Arrays.stream(reserve).boxed().collect(Collectors.toList());

        int i;

        for(i = 1; i <= n; i++){
            if (lostList.contains(i)) spare[i]--;
            if (reserveList.contains(i)) spare[i]++;
        }

        for(i = 1; i < n; i++){
            if(spare[i] == 1 && spare[i + 1] == -1){
                spare[i]--; spare[i+1]++;
            }
            else if(spare[i] == -1 && spare[i + 1] == 1){
                spare[i]++; spare[i+1]--;
            }
        }

        int result = 0;
        for(i = 1; i <= n; i++){
            if(spare[i] != -1) result++;
        }
        return result;
    }
}