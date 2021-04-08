package All_Union.Programmers;

import java.util.Arrays;
import java.util.LinkedHashMap;

class Solution {
    static LinkedHashMap<Long, Long> parent = new LinkedHashMap<Long, Long>();

    public long[] solution(long k, long[] room_number) {
        long[] result = new long[room_number.length];
        int personIdx = 0;

        for(long wantedRoom : room_number){
            result[personIdx++] = find(wantedRoom);
            union(find(wantedRoom), find(wantedRoom) + 1);
        }

        return result;
    }

    void union(long x, long y){
        long parentX = find(x);
        long parentY = find(y);

        if (parentX == parentY) return;

        if(parentX < parentY) parent.put(parentX, parentY);
        else parent.put(parentY, parentX);
    }

    long find(long node){
        parent.putIfAbsent(node, node);
        if(node == parent.get(node)) return node;
        else{
            parent.put(node, find(parent.get(node)));
            return find(parent.get(node));
        }
    }
}
class Main64063{
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(10, new long[]{1, 3, 4, 2, 5, 6})));
    }
}