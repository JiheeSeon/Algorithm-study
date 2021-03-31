package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

class Main15565{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int N = input[0], K = input[1];

        String s = br.readLine().replace(" ","");

        int result = Integer.MAX_VALUE;
        int numOfOne;
        int setSize;

        for (int start = 0; start < N; start++){
            if (s.charAt(start) == '2') continue;

            setSize = 0;
            numOfOne = 0;

            for(int end = start; end < N; end++){
                if (s.charAt(end) == '1') numOfOne++;
                setSize++;

                if(numOfOne == K){
                    result = Math.min(result, setSize);
                    break;
                }
            }
        }

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
}