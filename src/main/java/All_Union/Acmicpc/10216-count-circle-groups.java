package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class Main10216{
    static Map<Circle, Circle> parent = new HashMap<>();
    static Circle[] circles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N;

        int[] xyr;
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            circles = new Circle[N];

            for (int j = 0; j < N; j++) {
                xyr = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
                circles[j] = new Circle(xyr[0], xyr[1], xyr[2]);
            }

            Arrays.sort(circles,
                    Comparator.comparing((Circle c)->c.x)
                            .thenComparing(c -> c.y)
                            .thenComparing(c -> c.r));

            System.out.println(solution(N));
        }
    }

    static int solution(int N) {
        int result = 0;

        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){
                if ((circles[j].x - circles[i].x)*(circles[j].x - circles[i].x)
                        + (circles[j].y - circles[i].y)*(circles[j].y - circles[i].y)
                        > (circles[i].r + circles[j].r) * (circles[i].r + circles[j].r)) continue;

                union(circles[i], circles[j]);
            }
        }

        Map<Circle, Integer> map = new HashMap<>();

        for(Circle c : circles) {
            map.put(find(parent.getOrDefault(c, c)), map.getOrDefault(find(parent.getOrDefault(c, c)), 0) + 1);
        }

        return map.size();
    }

    static void union(Circle c1, Circle c2) {
        Circle pC1 = find(c1);
        Circle pC2 = find(c2);

        if(pC1.equals(pC2)) return;

        if(pC1.compareTo(pC2) < 0) parent.put(pC1, pC2);
        else parent.put(pC2, pC1);
    }

    static Circle find(Circle circle) {
        if(circle == parent.getOrDefault(circle, circle))
            return circle;
        else{
            Circle toPut = find(parent.getOrDefault(circle, circle));
            parent.put(circle, toPut);
            return toPut;
        }
    }

    private static class Circle implements Comparable<Circle>{
        int x, y, r;

        public Circle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        @Override
        public int compareTo(Circle c) {
            if (this.x > c.x) return -3;
            else if(this.y > c. y) return -2;
            else if(this.r > c.r) return -1;
            else return 0;
        }

        @Override
        public String toString() {
            return "Circle (" + "x=" + x + ", y=" + y + ", r=" + r + ')';
        }
    }
}
/*

1
4
0 0 1
0 4 1
0 1 1
0 3 1

2
4
0 0 1
0 1 1
1 0 1
10 0 1
2
0 0 10
0 0 1
*/