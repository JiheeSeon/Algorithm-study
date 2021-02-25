package Graph.Resolve;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Main2331Resolve {
    static int startVertex, power;
    static ArrayList<Integer> graph;
    static int[] temp;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        temp = getInputAndSplit();
        startVertex = temp[0];
        power = temp[1];
        graph = new ArrayList<>();
        int cycleIndex;

        graph.add(startVertex);

        while (true) {
            int nextVertex = getNextVertex(startVertex);

            if (graph.contains(nextVertex)){
                cycleIndex = graph.indexOf(nextVertex);
                break;
            }
            graph.add(nextVertex);
            startVertex = nextVertex;
        }

        bw.write(Integer.toString(cycleIndex));
        bw.flush();
        bw.close();
    }

    static int getNextVertex(int currentVertex) {
        return Pattern.compile("").splitAsStream(Integer.toString(currentVertex)).mapToInt(a -> (int) Math.pow(Integer.parseInt(a), power)).sum();
    }

    static int[] getInputAndSplit() throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }
}