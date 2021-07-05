package Heap;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/*
가방에 하나의 보석만 넣을 수 있음
총 가방에 담은 보석의 가치가 최대가 되려면,
각 가방에 자기보다 작거나 같은 무게인 애들 중 최대 가격인 애를 빠르게 찾아야 함.
또한 가방 무게 제한이 작은 것부터 진행해야 함.

Example
3 2
1 100
10 3
5 4
10
1

에서 10부터 진행하면 무게 10인 가방에 100만 넣고
1인 가방에는 넣을 게 없어서 100이 최종값, 최댓값(1 - 100, 10 - 3 => 103) 이 되지 않음.
-> 작은 가방 무게제한이 있는 것부터 순차적으로 진행해야 함.
-> sort << minHeap (최솟값을 빠르게 찾아냄)

TC
input
7 5
1 180
1 100
2 40
3 25
4 30
10 100
11 35
2
4
11
13
15

output
180 + 100 + 100 + 40 + 35 = 455


input
3 6
1 500
1 400
1 100
10
3
4
5
100
50

output
500 + 400 + 100 = 1000


<<--Wrong TC-->>
1. break -> continue
input
3 5
10 500
20 560
40 100
9
8
6
5
10

output
500
*/

class 보석도둑_1202{
    int jewelryN, bagN;
    List<Jewelry> jewelryList;
    List<Long> bagList;

    PriorityQueue<Long> bagMinHeap; // 2. sort(nlogn ~ n^2(초과범위)) -> PQ :: 시간초과 -> 맞았습니다
    PriorityQueue<Jewelry> jewelryMinHeap; // 무게의 min 값을 루트로 느슨히 정렬된 minHeap (무게 기준)
    PriorityQueue<Jewelry> jewelryMaxHeap; // 특정 무게 이하의 보석 중에 가장 가치가 높은 보석이 root 로 작용하는 maxHeap (가격 기준)

    public 보석도둑_1202(int jewelryN, int bagN, List<Jewelry> jewelryList, List<Long> bagList){
        this.jewelryN = jewelryN;
        this.bagN = bagN;
        this.jewelryList = jewelryList;
        this.bagList = bagList;

        bagMinHeap = new PriorityQueue<>(bagList);
        jewelryMinHeap = new PriorityQueue<>(Comparator.comparingLong(j -> j.weight));
        jewelryMinHeap.addAll(jewelryList);
        jewelryMaxHeap = new PriorityQueue<>((j1, j2) -> Long.compare(j2.price, j1.price));
    }

    long getAns(){
        long ret = 0;
        long price;
        long bagWeight;

        while(!bagMinHeap.isEmpty()){
            bagWeight = bagMinHeap.poll();
            // 이 루프를 안 돈다는건 이미 다 maxHeap 으로 넘어갔다는 것
            while(!jewelryMinHeap.isEmpty() && jewelryMinHeap.peek().weight <= bagWeight){ jewelryMaxHeap.add(jewelryMinHeap.poll()); }

            // maxHeap 이 비었다는 것은 bag 개수가 훨씬 많으면 넘어감 (maxHeap이 비었다가 나중에야 찰 수 있으므로 중단시키면 안됨)
            if(jewelryMaxHeap.isEmpty()) continue; // 1. break -> continue :: 틀렸습니다 -> 시간초과

            price = jewelryMaxHeap.poll().price;
            ret += price;
        }
        return ret;
    }
}

class Jewelry {
    long weight, price;

    public Jewelry(long[] info){
        this.weight = info[0];
        this.price = info[1];
    }
}

class MainA1202{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] tmp = strToLongArr(br.readLine());
        int jewelryN = (int)tmp[0]; int bagN = (int)tmp[1];

        List<Jewelry> jewelryList = new LinkedList<>();
        for(int j = 0; j < jewelryN; j++){
            jewelryList.add(new Jewelry(strToLongArr(br.readLine())));
        }

        List<Long> bagList = new LinkedList<>();
        for(int i = 0; i < bagN; i++)
            bagList.add(Long.parseLong(br.readLine()));

        System.out.println(new 보석도둑_1202(jewelryN, bagN, jewelryList, bagList).getAns());
    }
    static long[] strToLongArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToLong(Long::parseLong).toArray();
    }
}
