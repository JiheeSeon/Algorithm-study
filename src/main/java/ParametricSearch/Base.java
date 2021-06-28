package ParametricSearch;

class Base {
    public static void main(String[] args) {

    }
}
class BinarySearch_Basic {
    int target;
    int[] arr;

    public BinarySearch_Basic(int target, int[] arr) {
        this.target = target;
        this.arr = arr;
    }

    int binary_search() {
        int low = 0;
        int high = arr.length - 1;
        int mid;

        while(low <= high){
            mid = (low + high) / 2;
            if(arr[mid] == target) return mid;
            else if(arr[mid] > target)  high = mid - 1;
            else low = mid + 1;
        }

        return -1;
    }
}

class BinarySearch_1{
    int target;
    int[] arr;

    public BinarySearch_1(int target, int[] arr) {
        this.target = target;
        this.arr = arr;
    }

    int binary_search() {
        int low = 0;
        int high = arr.length - 1;
        int mid;

        while(low <= high){
            mid = (low + high) / 2;
            if(arr[mid] == target) return mid;
            else if(arr[mid] > target)  high = mid - 1;
            else low = mid + 1;
        }

        return -1;
    }
}