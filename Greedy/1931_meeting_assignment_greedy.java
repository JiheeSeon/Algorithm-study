import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;

class Main1931Greedy {
    static Meeting[] schedule;

    static int[] dp;

    static LinkedList<Meeting> greedy = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numOfMeeting = Integer.parseInt(br.readLine());

        schedule = new Meeting[numOfMeeting];
        dp = new int[numOfMeeting];

        int i, j;
        int[] temp;

        for (i = 0; i < numOfMeeting; i++) {
            temp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            schedule[i] = new Meeting(temp[0], temp[1]);
        }

        Arrays.sort(schedule, (o1, o2) -> o1.endTime == o2.endTime ? Integer.compare(o1.startTime, o2.startTime) : Integer.compare(o1.endTime, o2.endTime));

        greedy.add(new Meeting(schedule[0].startTime, schedule[0].endTime));
        setScheduleWithGreedy();

        bw.write(Integer.toString(greedy.size()));
        bw.flush();
        bw.close();
    }

    static void setScheduleWithGreedy(){
        for (int i = 1; i < schedule.length; i++){
            try {
                if (greedy.getLast().endTime <= schedule[i].startTime)
                    greedy.add(schedule[i]);
            }catch (NullPointerException e){
                System.out.println(e.getMessage());
            }
        }
    }

    static class Meeting{
        int startTime, endTime;

        public Meeting(int startTime, int endTime){
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "Meeting(" +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    ')';
        }
    }
}

/* Test case
11
12 14
9 15
8 12
6 10
0 6
1 4
2 13
3 8
3 7
5 7
6 8
*/