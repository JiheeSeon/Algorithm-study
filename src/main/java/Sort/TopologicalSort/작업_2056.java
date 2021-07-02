package Sort.TopologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

/*
2056 작업

처음에 삽질했던 부분
큐에 어떤 순서로 넣어야 하는지
-> 시작점들을 한번에 넣을지, 아니면 하나 하나씩 넣을지
-> 우선순위큐를 사용해야 할지 등

문제 분석
위상정렬의 주요 특성을 알 수 있음.
위상정렬은, 들어오는 차수가 0일 때(아무도 날 안 가리킬 때) 큐에 들어감
-> 즉 나를 가리키는 애는 이미 처리되었다고 봐야 함
-> 나를 가리켰던 애의 최단거리는 이미 구했음.
-> 이를 DP 로 저장해 놓아서 꺼내쓰면 됨. 각 구간의 최댓값을 찾아야 함.
   (MPI Barrier 와 같은 효과를 내는 것)

Testcases
TC #1.
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
output : 28

TC #2.
5
2 0
3 1 1
4 1 1
7 1 3
8 0
output : 13
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
        return Arrays.stream(distance).max().getAsInt(); // 마지막 index 값으로 하게 되면 시작점으로 받을 때 문제가 됨
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