package Util;

import java.util.regex.Pattern;

public class InputProcessor {
    public static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
