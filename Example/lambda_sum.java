import java.util.Arrays;
import java.util.Optional;

class Main{
    public static void main(String[] args){
        // 배열의 합 구하기
        Arrays.stream(stb).map(e -> Integer.parseInt(e)).reduce((accu, curr) -> accu+curr);
    }
