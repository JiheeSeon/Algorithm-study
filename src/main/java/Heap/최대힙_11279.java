package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 최대힙_11279 {
    int[] arr = new int[(int)1e5 + 1];
    int lastIdx = 1;

    void insert(int a) {
        insert(a, lastIdx++);
    }

    private void insert(int now, int where) {
        if(where == 1){
            arr[where] = now;
            return;
        }

        if(now > arr[where / 2]){
            arr[where] = arr[where / 2];
            insert(now, where / 2);
        } else arr[where] = now;
    }

    int deleteMax() {
        if(lastIdx == 1) return 0;

        int toRet = arr[1];
        arr[1] = arr[--lastIdx];
        deleteMax(1);
        return toRet;
    }

    private void deleteMax(int where) {
        if(where >= lastIdx) return;

        int leftChild = 2 * where;
        int rightChild = leftChild + 1;
        int maxChild;

        if(rightChild < lastIdx) maxChild = arr[leftChild] > arr[rightChild] ? leftChild : rightChild;
        else if(leftChild < lastIdx) maxChild = leftChild;
        else return;

        if(arr[where] < arr[maxChild]){
            int tmp = arr[maxChild];
            arr[maxChild] = arr[where];
            arr[where] = tmp;

            deleteMax(maxChild);
        }
    }
}

class MainA11729{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int val;
        최대힙_11279 maxHeap = new 최대힙_11279();
        StringBuilder stb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            val = Integer.parseInt(br.readLine());

            if(val == 0) stb.append(maxHeap.deleteMax()).append("\n");
            else maxHeap.insert(val);

        }

        System.out.print(stb);
    }
}