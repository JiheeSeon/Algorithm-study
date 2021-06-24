package BinarySearch;

import java.io.*;
import java.util.regex.Pattern;
import java.util.Arrays;

class 수찾기_1920{
    int N, M;
    int[] given;
    int[] toFind;

    public 수찾기_1920(int N, int M, int[] given, int[] toFind){
        this.N = N;
        this.M = M;
        this.given = given;
        this.toFind = toFind;
    }

    String getAns(){
        Arrays.sort(given);

        StringBuilder stb = new StringBuilder();
        for(int f: toFind){
            stb.append(parametric_search(f)).append("\n");
        }
        return stb.toString();
    }

    int parametric_search(int f){
        int low = 0;
        int high = N - 1;
        int mid;

        while(low <= high){
            mid = (low + high) / 2;
            if(given[mid] == f) return 1;
            if(given[mid] < f) low = mid + 1;
            else high = mid - 1;
        }
        return 0;
    }
}

class MainA1920 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] given = strToIntArr(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] toFind = strToIntArr(br.readLine());
        System.out.print(new 수찾기_1920(N, M, given, toFind).getAns());

    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}