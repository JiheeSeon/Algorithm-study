import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/* BufferedReader, BufferedWriter 사용해보기 */
class Main {
    public static void main(String[] args) throws IOException { //BufferedReader 사용할 때 IOException 꼭 걸어줘야함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(Integer.parseInt(br.readLine())+Integer.parseInt(br.readLine()))); //형변환 안 시켜줘도 됨
        bw.flush();
        bw.close();
    }
}
