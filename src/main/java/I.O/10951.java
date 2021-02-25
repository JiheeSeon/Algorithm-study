package I.O;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

/*BufferedReader, BufferedWriter, StringTokenizer, StringBuilder 사용*/

class Main10951{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder stb = new StringBuilder();
        String input;
        while((input = br.readLine()) != null){
            st = new StringTokenizer(input, " ");
            if (st.hasMoreTokens()){
                stb.append(Integer.toString(Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken()))).append("\n");
                //  만약 여기서 bw.write 하면 앞에 있던 string builder 에 append 되어있던 애들이 다시 append될 것 > 의도한 작업 아님
            }
            else
                break;
        }
        // System.out.println(stb.toString()); 와 같은 효과
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
}