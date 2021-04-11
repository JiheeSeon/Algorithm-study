package BinarySearch;

public class RecursiveExample {
    public int binarysearch(int[] arr, int R, int low, int high) {
        int mid = (high + low) / 2;
        if(arr[mid] == R) {
            return 1;
        }

        else if(arr[mid] < R) {
            return binarysearch(arr, R, mid + 1, high) + 1;
        } else {
            return binarysearch(arr, R, low, mid - 1) + 1;
        }
    }
}