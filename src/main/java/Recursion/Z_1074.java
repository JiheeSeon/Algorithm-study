package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

class Solution{
    int N, Y, X;
    int[] dy = {0, 0, 1, 1};
    int[] dx = {0, 1, 0, 1};
    int[][] graph;
    int ans = -1;

    public Solution(int[] info){
        N = info[0];
        Y = info[1];
        X = info[2];
    }

    int recursion(int n, int y, int x, int val){
        if(n == 1){
            for(int i = 0; i < 4; i++){
                if(y + dy[i] == Y && x + dx[i] == X) ans = val;
                val++;
            }
            return val;
        }

        for(int i = 0; i < 4; i++){
            val = recursion(n - 1, y + dy[i] * ((int)Math.pow(2, (n-1))), x + dx[i] * ((int)Math.pow(2, (n-1))), val);
            if(ans != -1) return ans;
        }

        return val;
    }

    int getAns(){
        return recursion(N, 0, 0, 0);
    }

    void display(){
        System.out.println();
        for(int y = 0; y < graph.length; y++){
            for(int x = 0; x < graph[y].length; x++){
                System.out.print(graph[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class MainA1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        System.out.println(new Solution(info).getAns());
    }
}