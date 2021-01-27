import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main11654Advanced{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int sumResult;

        while((input = br.readLine()) != null){
            sumResult = input.chars().mapToObj(a->(char)a).mapToInt(Character::getNumericValue).sum();
        }
    }
}