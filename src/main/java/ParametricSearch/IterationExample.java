package ParametricSearch;

public class IterationExample {
    public int binarysearch(int[] arr, int R) {
        int T = 0;
        int high = arr.length - 1;
        int low = 0;

        int mid;

        while(high >= low) {
            mid = (high + low) / 2;
            T++;
            if(arr[mid] == R) return T;
            else if (arr[mid] < R) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return T;
    }
}
