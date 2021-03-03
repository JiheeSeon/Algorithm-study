package Sort;

import java.io.*;
import java.util.regex.Pattern;

class QuickSort {
    static int[] input; //5 3 8 4 9 1 6 2 7

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        quickSort(input, 0, input.length - 1);

        StringBuilder stb = new StringBuilder();
        for (int i : input)
            stb.append(i).append(" ");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void quickSort(int[] list, int left, int right) {
        int pivotPos;

        if (left < right) {
            pivotPos = partition(list, left, right);

            quickSort(list, left, pivotPos - 1);
            quickSort(list, pivotPos + 1, right);
        }

    }

    static int partition(int[] list, int left, int right) {
        int pivot, tempForSwap;
        int low, high;

        low = left;
        high = right + 1;
        pivot = list[left]; // 가장 왼쪽의 원소를 피벗으로

        while (true){
            if (low >= high) break;

            do{
                low++;
            }while(low <= right && list[low] < pivot);

            do{
                high--;
            }while(high >= left && list[high] > pivot);

            if (low < high){
                tempForSwap = list[low];
                list[low] = list[high];
                list[high] = tempForSwap;
            }
        }

        tempForSwap = list[left];
        list[left] = list[high];
        list[high] = tempForSwap;

        return high;
    }
}
