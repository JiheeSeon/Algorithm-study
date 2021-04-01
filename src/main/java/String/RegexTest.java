package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegexTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine(); // abcccceaee
        boolean b1 = s1.matches("abc+(a|e){2,5}");
        System.out.println(b1);

        String s2 = br.readLine(); //dfk?!d#12
        Boolean b2 = s2.matches("\\D*([\\d]|10|12)");
        System.out.println(b2);

        String s3 = br.readLine(); // azAEsdEg
        Boolean b3 = s3.matches("(?i)[a-z]*");
        System.out.println(b3);


        String s4 = br.readLine(); // abcd
        Boolean b4 = s4.matches("a(?<foo>bc)d");
        System.out.println(b4);
    }
}
