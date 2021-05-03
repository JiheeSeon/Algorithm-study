package Implementation.Programmers.lv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 다리를지나는트럭_42583 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();
    }
}
class Solution{
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int idx = 0; int loopIdx;
        int sum;
        int ans = 0;

        while(idx < truck_weights.length){
            loopIdx = idx + 1;
            sum = truck_weights[idx];

            while(loopIdx < truck_weights.length){
                sum += truck_weights[loopIdx++];
                if(sum > weight) break;
            }

            ans += (bridge_length + loopIdx - idx);
            idx = loopIdx;

        }
        return ans;
    }
}
