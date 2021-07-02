package Sort.TopologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

/*
2056 작업
작업시간 / 선행작업개수(inDegree) / 선행작업 번호들 (graph)
서로 선행 관계가 없는 작업들은 동시에 수행 가능
 */

class 작업_2056 {
    int N;
    int[][] input;
    int[][] prerequiredJobs;

    int[] inDegree;
    Job[] jobs;
    ArrayList<Integer>[] graph;
    ArrayList<Job> startJob;

    int[] distance;

    public 작업_2056(int n, int[][] input) {
        N = n;
        this.input = input;

        setVariables();
    }

    void setVariables() {
        inDegree = new int[N + 1];
        graph = new ArrayList[N + 1];
        jobs = new Job[N + 1];
        distance = new int[N + 1];
        prerequiredJobs = new int[N + 1][];
        startJob = new ArrayList<>();

        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        Job job;
        for (int i = 0; i < input.length; i++) {
            jobs[i + 1] = new Job(i + 1, input[i][0]);
            if (input[i].length == 2) {
                startJob.add(jobs[i + 1]);
                continue;
            }

            inDegree[i + 1] = input[i][1];
            prerequiredJobs[i + 1] = new int[inDegree[i + 1]];
            for (int j = 0; j < inDegree[i + 1]; j++) {
                graph[input[i][2 + j]].add(i + 1);
                prerequiredJobs[i + 1][j] = input[i][2 + j];
            }
        }
    }

    int getAns(){
        Queue<Job> q = new LinkedList<>(startJob);

        Job nowJob;
        while (!q.isEmpty()) {
            nowJob = q.poll();

            if(prerequiredJobs[nowJob.number] == null) {
                distance[nowJob.number] = nowJob.time;
            } else {
                for (int prev : prerequiredJobs[nowJob.number])
                    distance[nowJob.number] = Math.max(distance[nowJob.number], distance[prev] + nowJob.time);
            }

            for(int next: graph[nowJob.number]){
                inDegree[next]--;
                if(inDegree[next] == 0) q.add(jobs[next]);
            }
        }
        System.out.println(Arrays.toString(distance));
        return distance[N];
    }
}

class Job {
    int number;
    int time;

    public Job(int number, int time) {
        this.number = number;
        this.time = time;
    }
}

class MainA2056 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] input = new int[N][];
        for (int i = 0; i < N; i++)
            input[i] = strToIntArr(br.readLine());

        System.out.println(new 작업_2056(N, input).getAns());

    }

    static int[] strToIntArr(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}

/*
17
2 0
4 1 1
1 1 1
3 0
9 1 4
2 3 5 2 3
10 1 3
3 1 3
1 2 6 7
3 2 6 7
9 2 7 8
1 2 10 11
1 1 9
2 1 9
7 1 13
5 1 14
5 2 15 16
*/