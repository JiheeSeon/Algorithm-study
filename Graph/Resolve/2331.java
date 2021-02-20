package Resolve;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class Main2331Resolve {
    static int numOfVertex, numOfEdge;
    static int startVertex, power;
    static ArrayList<Integer> graph;
    static int[] visited;
    static int[] temp;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        temp = getInputAndSplit();
        startVertex = temp[0];
        power = temp[1];
        graph = new ArrayList<Integer>();

        while (true) { // making graph
            int nextVertex = getNextVertex(startVertex);

            if (graph.contains(nextVertex))  break;

            graph.add(nextVertex);
        }


    }

    static int getNextVertex(int currentVertex) throws IOException {
        return Pattern.compile("").splitAsStream(Integer.toString(currentVertex)).mapToInt(a -> (int) Math.pow(Integer.parseInt(a), power)).sum();
    }

    static int[] getInputAndSplit() throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }
}