package Queue;

import java.io.*;

class Main10845 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        QueueWithList queue = new QueueWithList(10000);

        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            switch (input[0]) {
                case "push" -> queue.push(Integer.parseInt(input[1]));
                case "pop" -> stb.append(queue.pop()).append("\n");
                case "empty" -> stb.append(queue.isEmpty()).append("\n");
                case "front" -> stb.append(queue.getFront()).append("\n");
                case "back" -> stb.append(queue.getBack()).append("\n");
                case "size" -> stb.append(queue.size()).append("\n");
            }
        }
        stb.setLength(stb.length() - 1);
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
}

class QueueWithList {
    private int[] queue;
    private int front;
    private int back;

    public QueueWithList(final int MAXSIZE) {
        front = 0;
        back = 0;
        queue = new int[MAXSIZE];
    }

    public void push(int element) {
        back %= queue.length;
        queue[back++] = element;
    }

    public int pop() {
        int to_return;
        if (back - front == 0)
            return -1;
        else
            to_return = queue[front];
            front = (front + 1) % queue.length;

        return to_return;

    }

    public int getFront() {
        return back - front == 0 ? -1 : front + 1;
    }

    public int getBack() {
        return back - front == 0 ? -1 : back;
    }

    public int isEmpty() {
        return back - front == 0 ? 1 : 0;
    }

    int size() {
        return back - front;
    }
}