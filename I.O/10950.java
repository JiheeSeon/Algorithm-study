import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            System.out.println(Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken()));
        }
    }
}