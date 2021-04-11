package BinarySearch;

public class IterationExample2 {
    public int search(int[] A, int R) {
        /*
        입력 : 모든 수가 자연수이고 오름차순 정렬된 배열 A, 찾아야 할 값 R
        출력 : 해당하는 값이 있는 인덱스, 없으면 -1

        함수의 인자 :: 배열과 찾아야 할 값
        초기화 :: 시작 index 0 - 끝 index A.length - 1
        매 루프마다 시작과 끝을 탐색 결과에 따라 조정해서 범위 좁혀나가야 함.
        루프의 종료 조건 : start > end

        루프 불변성
        */

        int start = 0; int end = A.length - 1;
        int mid = -1;

        while(end >= start){
            mid = (start + end) / 2;

            if (A[mid] > R){
                end = mid - 1;
            } else if (A[mid] < R){
                start = mid + 1;
            } else{
                return mid;
            }
        }

        return -1;
    }
}
class MainForBinarySearchIteration{
    public static void main(String[] args) {
        System.out.println(new IterationExample2().search(new int[]{17, 28, 43, 67, 88, 92, 100}, 17));
        System.out.println(new IterationExample2().search(new int[]{17, 28, 43, 67, 88, 92, 100}, 44));
        System.out.println(new IterationExample2().search(new int[]{17, 28, 43, 67, 88, 92, 100}, 104));
        System.out.println(new IterationExample2().search(new int[]{17, 28, 43, 67, 88, 92, 100}, 92));

    }
}
