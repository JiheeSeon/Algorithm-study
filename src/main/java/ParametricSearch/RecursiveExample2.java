package ParametricSearch;

public class RecursiveExample2 {
    /*
    입력 모든 원소가 자연수로 구성되면서 오름차순인 배열, 찾고자 하는 값 R
    출력 R과 일치하는 값의 index, 없으면 -1

    함수의 인자 :: 배열, low, high, R
    R과 A[mid]를 비교해서 low 와 high 재조정 후 재귀호출
    */
    int search(int[] A, int low, int high, int R) {
        if (low > high) return -1;

        int mid = (low + high) / 2;
        if(A[mid] == R) return mid;
        else if(A[mid] > R) return search(A, low, mid - 1, R);
        else return search(A, mid + 1, high, R);
    }
}
class MainForBinarySearchRecursive{
    public static void main(String[] args) {
        System.out.println(new RecursiveExample2().search(new int[]{17, 28, 43, 67, 88, 92, 100}, 0, 6, 43));
        System.out.println(new RecursiveExample2().search(new int[]{17, 28, 43, 67, 88, 92, 100}, 0, 6, 44));
        System.out.println(new RecursiveExample2().search(new int[]{17, 28, 43, 67, 88, 92, 100}, 0, 6, 104));
        System.out.println(new RecursiveExample2().search(new int[]{17, 28, 43, 67, 88, 92}, 0, 5, 92));

    }
}

