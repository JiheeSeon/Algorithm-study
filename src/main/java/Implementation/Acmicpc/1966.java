package Implementation.Acmicpc;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Main1966 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int numOfDocs = Integer.parseInt(br.readLine());
        LinkedList<Element> elements;

        int docNum, docToAsk;
        int[] input, priority;

        int count;

        StringBuilder stb = new StringBuilder();

        for (int i = 0; i < numOfDocs; i++) {
            elements = new LinkedList<>();

            input = processInput();
            docNum = input[0];
            docToAsk = input[1];

            priority = processInput();
            for (int j = 0; j < docNum; j++) {
                elements.add(new Element(j, priority[j]));
            }

            int max;
            Element temp;

            count = 0;

            while (!elements.isEmpty()) {
                max = Collections.max(elements, Comparator.comparing((Element e) -> e.priority).thenComparing(e -> e.position, Comparator.reverseOrder())).position;

                for (int k = 0; k < max; k++){
                    temp = elements.poll();
                    elements.add(temp);
                }

                count++;

                if (elements.poll().position == docToAsk) {
                    stb.append(count).append("\n");
                    break;
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static int[] processInput() throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }

    private static class Element {
        int position, priority;

        public Element(int position, int priority) {
            this.position = position;
            this.priority = priority;
        }
    }
}