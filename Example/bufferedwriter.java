import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out)); //선언
        bufferedWriter.write("Hello World!");
        // write한다고 해서 바로바로 출력되지 않습니다.
        // 직접 출력 stream에 반영되는 것이 아니라 성능을 위해 buffer에 저장해두었다가
        // BufferedWriter가 flush되거나 close되었을 때 한번에 출력 stream에 반영하기 때문입니다.
        bufferedWriter.flush(); 
        bufferedWriter.newLine(); // 줄바꿈이 필요할 경우 사용합니다.
        bufferedWriter.close(); // 버퍼 닫기
        // close는 stream을 닫아버리기 때문에 계속 출력하고자 한다면 flush 사용합니다.
    }
}