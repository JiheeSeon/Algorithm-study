package Backtrack.Acmicpc;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main10971{
    static int[][] weight;
    static int nodeN;

    static boolean[] visited;
    static int[] prevNode;
    static int[] prevWeight;

    static int result = Integer.MAX_VALUE; // min 값을 저장

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nodeN = Integer.parseInt(br.readLine());
        weight = new int[nodeN][nodeN];

        visited = new boolean[nodeN]; // node 번호 -> 방문 여부
        prevNode = new int[nodeN + 1]; // k번째 순회 -> 방문한 노드
        prevWeight = new int[nodeN + 1]; // k번째 순회 -> 이전 노드와의 weight

        for(int i = 0; i < nodeN; i++){
            weight[i] = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        }

        traverse(1); //1번째 순회부터 시작

        System.out.println(result);
    }

    static void traverse(int currChosenN){
        int sum;
        for(int i = 0; i < nodeN; i++){
            if (((currChosenN != nodeN + 1) && visited[i])
                    || (currChosenN != 1 && weight[prevNode[currChosenN - 2]][i] == 0))
                continue;
            if (currChosenN == nodeN + 1 && prevNode[0] != i) continue;

            if(currChosenN != nodeN + 1) visited[i] = true;
            prevNode[currChosenN - 1] = i;
            prevWeight[currChosenN - 1] = currChosenN == 1 ? 0 : weight[prevNode[currChosenN - 2]][i];

            // 마지막 자신의 회귀점으로 왔을 때
            if (currChosenN == nodeN + 1){
                sum = Arrays.stream(prevWeight).sum();
                if(sum < result){
                    result = sum;
                }
            }	else traverse(currChosenN + 1);

            if(currChosenN != nodeN + 1) visited[i] = false;
            prevNode[currChosenN - 1] = 0;
            prevWeight[currChosenN - 1] = 0;
        }
    }
}