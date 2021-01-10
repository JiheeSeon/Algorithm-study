import java.util.Arrays;
import java.util.Optional;
import java.lang.StringBuilder;

class Main4{
    public static void main(String[] args){
        // 배열의 합 구하기
        StringBuilder stb = new StringBuilder("");
        Arrays.stream(stb).map(e -> Integer.parseInt(e)).reduce((accu, curr) -> accu+curr);
    }
