package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 절댓값힙_11286 {
    int[] arr = new int[(int)1e5 + 1];
    int lastIdx = 1;

    void insert(int a){
        insert(a, lastIdx++);
    }

    private void insert(int now, int where){
        if(where == 1){
            arr[where] = now;
            return;
        }

        if((Math.abs(now) < Math.abs(arr[where / 2]))
                || (Math.abs(now) == Math.abs(arr[where / 2]) && now < arr[where / 2])) {
            arr[where] = arr[where / 2];
            insert(now, where / 2);
        } else arr[where] = now;
    }

    int deleteMin() {
        if(lastIdx == 1) return 0;

        int toRet = arr[1];
        arr[1] = arr[--lastIdx];
        deleteMin(1);
        return toRet;
    }

    private void deleteMin(int where) {
        if(where >= lastIdx) return;

        int leftChild = where * 2;
        int rightChild = leftChild + 1;
        int minChild;

        if(leftChild >= lastIdx) return;
        else if(rightChild > lastIdx) minChild = leftChild;
        else{
            if(Math.abs(arr[leftChild]) < Math.abs(arr[rightChild])) minChild = leftChild;
            else if(Math.abs(arr[leftChild]) > Math.abs(arr[rightChild])) minChild = rightChild;
            else if(arr[leftChild] <= arr[rightChild]) minChild = leftChild;
            else minChild = rightChild;
        }

        if((Math.abs(arr[where]) > Math.abs(arr[minChild]))
                || (Math.abs(arr[where]) == Math.abs(arr[minChild]) && (arr[where] > arr[minChild]))) {
            int tmp = arr[where];
            arr[where] = arr[minChild];
            arr[minChild] = tmp;

            deleteMin(minChild);
        }
    }
}

class MainA11286{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int val;
        StringBuilder stb = new StringBuilder();
        절댓값힙_11286 absoluteMinHeap = new 절댓값힙_11286();
        for(int i = 0; i < N; i++){
            val = Integer.parseInt(br.readLine());
            if(val == 0) stb.append(absoluteMinHeap.deleteMin()).append("\n");
            else absoluteMinHeap.insert(val);

        }
        System.out.println(stb);
    }
}