package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 최소힙_1927 {
    int[] arr = new int[(int)1e5 + 1];
    int lastIdx = 1;


    int deleteMin() {
        if(lastIdx == 1) return 0;

        int toRet = arr[1];
        arr[1] = arr[--lastIdx];
        deleteMin(1);
        return toRet;
    }

    private void deleteMin(int where) {
        if(where >= lastIdx) return;

        int leftChildIdx = where * 2; int rightChildIdx = where * 2 + 1;
        int minChildIdx;

        if(leftChildIdx >= lastIdx) return;
        else if(rightChildIdx >= lastIdx) minChildIdx = leftChildIdx;
        else minChildIdx = arr[leftChildIdx] < arr[rightChildIdx] ? leftChildIdx : rightChildIdx;

        // 부모가 자식보다 큰 경우
        if(arr[where] > arr[minChildIdx]){
            int tmp = arr[minChildIdx];
            arr[minChildIdx] = arr[where];
            arr[where] = tmp;

            deleteMin(minChildIdx);
        }
    }

    void insert(int a){
        if(lastIdx == 1){
            arr[lastIdx++] = a;
        } else insert(a, lastIdx++);
    }

    private void insert(int now, int where){
        if(where == 1){
            arr[where] = now;
            return;
        }

        // 현재 값이 부모보다 작으면 올라감
        if(now < arr[where / 2]){
            arr[where] = arr[where / 2]; // 현재 자리에 부모 값을 넣어줌.
            insert(now, where / 2);
        } else arr[where] = now;
    }
}

class MainA1927{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder stb = new StringBuilder();
        int val;

        최소힙_1927 minHeap = new 최소힙_1927();

        for(int i = 0; i < N; i++){
            val = Integer.parseInt(br.readLine());
            if(val == 0)
                stb.append(minHeap.deleteMin()).append("\n");
            else minHeap.insert(val);
        }

        System.out.print(stb);
    }
}