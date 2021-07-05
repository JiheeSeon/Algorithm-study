package Heap;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/*
가방에 하나의 보석만 넣을 수 있음

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
    long[] bags;
    PriorityQueue<Jewelry> minHeap; // 무게의 min 값을 루트로 느슨히 정렬된 minHeap (무게 기준)
    PriorityQueue<Jewelry> maxHeap; // 특정 무게 이하의 보석 중에 가장 가치가 높은 보석이 root 로 작용하는 maxHeap (가격 기준)

    public 보석도둑_1202(int jewelryN, int bagN, List<Jewelry> jewelryList, long[] bags){
        this.jewelryN = jewelryN;
        this.bagN = bagN;
        this.jewelryList = jewelryList;
        this.bags = bags;

        minHeap = new PriorityQueue<>(Comparator.comparingLong(j -> j.weight));
        minHeap.addAll(jewelryList);
        maxHeap = new PriorityQueue<>((j1, j2) -> Long.compare(j2.price, j1.price));
    }

    long getAns(){
        Arrays.sort(bags);

        long ret = 0;
        long price;
        for(long bagWeight : bags){
            // 이 루프를 안 돈다는건 이미 다 maxHeap 으로 넘어갔다는 것
            while(!minHeap.isEmpty() && minHeap.peek().weight <= bagWeight){ maxHeap.add(minHeap.poll()); }

            // maxHeap 이 비었다는 것은 bag 개수가 훨씬 많다는 것
            if(maxHeap.isEmpty()) continue; // break -> continue :: 틀렸습니다 -> 시간초과

            price = maxHeap.poll().price;
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

    @Override
    public String toString() {
        return "Jewelry(" +
                "weight=" + weight +
                ", price=" + price +
                ')';
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

        long[] bags = new long[bagN];
        for(int i = 0; i < bags.length; i++)
            bags[i] = Long.parseLong(br.readLine());

        System.out.println(new 보석도둑_1202(jewelryN, bagN, jewelryList, bags).getAns());
    }
    static long[] strToLongArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToLong(Long::parseLong).toArray();
    }
}
