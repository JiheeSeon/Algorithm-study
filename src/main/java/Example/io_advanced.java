package Example;

import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.lang.StringBuilder;

/* space 기준으로 끊어서 출력하는 예제
    [Input]
    hello what hi
    junghoon ho haha

    [Output]
    hello
    what
    hi
    junghoon
    ho
    ha
*/

class MainIOAdv{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        String input = "";
        while((input = br.readLine()) != null){
            stb.append(input);
            stb.append("\n");
        }
        StringTokenizer st = new StringTokenizer(input);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(st.hasMoreTokens()){
            bw.write(st.nextToken());
            // System.out.println(st.nextToken());
        }
        bw.flush();
        bw.close();
    }
}

// class Main2{
//     public static void main(String[] args) throws IOException{
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;
//         String t = "";
//         while((t = br.readLine()) != null){
//             st = new StringTokenizer(t);
//             System.out.println(Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken()));
//         }
//     }
// }


