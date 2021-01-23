import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Main1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] info = br.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int K = Integer.parseInt(info[1]);

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        int pointer = 1;
        while(!queue.isEmpty()){
            if(pointer % K == 0)
                stb.append(queue.poll()).append(", ");
            else
                queue.add(queue.poll());
            pointer++;
        }
        stb.setLength(stb.length() - 2);
        stb.insert(0,"<").append(">");

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
}