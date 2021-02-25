import java.io.*;
import java.util.regex.Pattern;

class Main1912 {
    static long[] input;
    static long[] groupedInput;
    static long[] memo;
    static int resultIndex;

    /*연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합으로 하기에는 연속성을 매번 체크 불가, 플래그를 두지 않는 이상*/

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        input = Pattern.compile(" ").splitAsStream("0 " + br.readLine()).mapToLong(Long::parseLong).limit(N + 1).toArray();
        groupedInput = new long[N + 1];
        memo = new long[N+1];

        groupInput();
        for(int i = 1 ; i <= N; i++)
            getSuccessiveSum(i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int m = 0 ; m <= N ; m++)
            if(memo[m] != 0)
                System.out.println(memo[m]);

        bw.write(Long.toString(memo[resultIndex])); //memo[N]
        bw.flush();
        bw.close();
    }

    static void getSuccessiveSum(int n) {
        int groupIdx = 0;

        while(groupedInput[groupIdx] != 0 && groupIdx <= n){
            if(groupedInput[groupIdx] > 0) {
                memo[groupIdx] = groupedInput[groupIdx];
                memo[++groupIdx] = groupedInput[groupIdx] + groupedInput[groupIdx + 1] + groupedInput[groupIdx + 2];
            }
            groupIdx++;
        }

    }

    static void groupInput(){
        int plus = 1;
        int sign;
        int groupIdx = 0;
        int n = input.length;

        //문제 축소
        for (int i = 1; i < n; i++) {
            sign = input[i] > 0 ? 1 : 0;

            if (plus == sign) {
                groupedInput[groupIdx] += input[i];
            } else {
                plus = sign;
                groupedInput[++groupIdx] = input[i];
            }
        }

    }
}