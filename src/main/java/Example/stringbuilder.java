package Example;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuffer;

class Main3STB{
    public static void main(String[] args) throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("first\n"); 
        stringBuffer.append("second\n");
        bufferedWriter.write(stringBuffer.toString()); //StringBuilder의 반환 객체를 String 형태로 변환
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}