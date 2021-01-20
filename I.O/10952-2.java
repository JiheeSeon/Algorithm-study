import java.io.*;
import java.util.regex.Pattern;
import java.util.stream.*;

class Main10952_2{
    public static void main(String[] args) throws IOException {
        /*Stream을 이용한 방법*/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(true){
            s = br.readLine();
            if(s.equals("0 0"))
                break;
            System.out.println(Pattern.compile(" ").splitAsStream(s).map(Integer::parseInt).collect(Collectors.toList()).stream().mapToInt(a->a).sum());
        }
    }
}
