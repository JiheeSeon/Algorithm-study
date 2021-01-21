import java.io.*;
import java.util.IntSummaryStatistics;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Main10818 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        IntSummaryStatistics intSummaryStatistics = Pattern.compile(" ").splitAsStream(br.readLine()).limit(T).map(Integer::parseInt).collect(Collectors.summarizingInt(Integer::intValue));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();
        stb.append(intSummaryStatistics.getMin()).append(" ").append(intSummaryStatistics.getMax());
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
}