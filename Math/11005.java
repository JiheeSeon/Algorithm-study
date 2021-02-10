import java.io.*;
import java.util.regex.Pattern;

class Main11005{
    static String res = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToLong(Long::parseLong).toArray();
        long quotient = input[0];

        while(quotient > 0){
            res = convertLetter((int)(quotient%input[1])) + res;
            quotient /= input[1];
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res);
        bw.flush();
        bw.close();
    }

    static String convertLetter(int m){
        if(m >= 10)
            return Character.toString((char)(m + 55));
        else
            return Integer.toString(m);
    }

    /*Recursive Solution*/
    static void changeBaseRecursive(long a, long b){
        if(a == 1)
            res = convertLetter((int)a);
        else {
            changeBaseRecursive(a / b, b);
            res += convertLetter((int)(a%b));
        }
    }
}