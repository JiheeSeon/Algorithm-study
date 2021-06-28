package ParametricSearch;

import java.io.*;
import java.util.regex.Pattern;
import java.util.Arrays;

class 예산_2512 {
    int N;
    long[] arr;
    long totalBudget;

    public 예산_2512(int N, long[] arr, long totalBudget){
        this.N = N;
        this.arr = arr;
        this.totalBudget = totalBudget;
    }

    long getAns(){
        Arrays.sort(arr);
        long max = 0;
        long sum = 0;
        for(long budget: arr){
            sum += budget;
            max = Math.max(budget, max);
        }
        return (sum <= totalBudget) ? max :  parametricSearch();
    }

    long parametricSearch(){
        long low = 1;
        long high = totalBudget;
        long mid;
        long budgetSum;

        while(low <= high){
            mid = (low + high) / 2;
            budgetSum = 0;
            for(long budget: arr){
                budgetSum += Math.min(budget, mid);
            }

            if(budgetSum > totalBudget){
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }

        return high;
    }
}

class MainA2512{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = Pattern.compile(" ").splitAsStream(br.readLine()).mapToLong(Long::parseLong).toArray();
        long totalBudget = Long.parseLong(br.readLine());
        System.out.println(new 예산_2512(N, arr, totalBudget).getAns());
    }
}