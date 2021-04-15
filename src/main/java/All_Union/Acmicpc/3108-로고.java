package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class Main3108{
    static Rectangle[] rectangles;
    static Map<Rectangle, Rectangle> parent = new HashMap<>();
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rectangleN = Integer.parseInt(br.readLine());
        rectangles = new Rectangle[rectangleN];
        check = new boolean[rectangleN];

        int[] input;
        for(int i = 0; i < rectangleN; i++) {
            input = strToIntArray(br.readLine());
            rectangles[i] = new Rectangle(input);
            parent.put(rectangles[i], rectangles[i]);
        }

        Arrays.sort(rectangles);

        for (int i = 0; i < rectangleN; i++) {
            for (int j = i + 1; j < rectangleN; j++){
                if((!check[i] || !check[j]) && toUnion(rectangles[i], rectangles[j])){
                    check[i] = true; check[j] = true;
                    union(rectangles[i], rectangles[j]);
                }
            }
        }

        for (Rectangle r : parent.keySet()) find(r);

//        System.out.println(Arrays.toString(parent.values().toArray()));
        Set<Rectangle> set = new HashSet<>(parent.values());
//        for (Rectangle r : set) {
//            System.out.println(r);
//        }
        System.out.println(set.size());
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static boolean toUnion(Rectangle r1, Rectangle r2) {
        boolean b1 = (r1.startX <= r2.startX && r2.startX <= r1.endX);
        boolean b2 = (r1.startX <= r2.endX && r2.endX <= r1.endX);
        boolean b3 = (r1.startY <= r2.startY && r2.startY <= r1.endY);
        boolean b4 = (r1.startY <= r2.endY && r2.endY <= r1.endY);

        boolean bex1 = (r1.startX < r2.startX && r2.startX < r1.endX);
        boolean bex2 = (r1.startX < r2.endX && r2.endX < r1.endX);
        boolean bex3 = (r1.startY < r2.startY && r2.startY < r1.endY);
        boolean bex4 = (r1.startY < r2.endY && r2.endY < r1.endY);

        boolean exclude = bex1 && bex2 && bex3 && bex4;

        if(exclude) return false;
        return (b1 || b2) && (b3 || b4);
    }

    static void union(Rectangle r1, Rectangle r2){
        Rectangle pR1 = find(r1);
        Rectangle pR2 = find(r2);

        if(pR1.equals(pR2)) return;

        if(pR1.compareTo(pR2) < 0) parent.put(pR2, pR1);
        else parent.put(pR1, pR2);
    }

    static Rectangle find(Rectangle rectangle) {
        if(rectangle.equals(parent.get(rectangle))) return rectangle;
        else{
            Rectangle pRectangle = find(parent.get(rectangle));
            parent.put(rectangle, pRectangle);
            return pRectangle;
        }
    }

    static private class Rectangle implements Comparable<Rectangle>{
        int startX, startY, endX, endY;

        public Rectangle(int[] arr) {
            this.startX = arr[0]; this.startY = arr[1];
            this.endX = arr[2]; this.endY = arr[3];
        }

        @Override
        public int compareTo(Rectangle o) {
            if(this.startX == o.startX){
                if(this.startY == o.startY){
                    if(this.endX == o.endX){
                        if(this.endY == o.endY) return 0;
                        else return Integer.compare(this.endY, o.endY);
                    } else return Integer.compare(this.endX, o.endX);
                } else return Integer.compare(this.startY, o.startY);
            } else return Integer.compare(this.startX, o.startX);
        }

        @Override
        public String toString() {
            return "Rectangle (" + "startX=" + startX + ", startY=" + startY + ", endX=" + endX + ", endY=" + endY + ')';
        }
    }
}

/*
5
5 0 8 3
6 1 7 2
3 3 6 6
4 4 5 5
1 1 4 4

7
1 1 2 2
2 3 3 5
3 5 7 8
5 7 9 9
7 3 10 5
11 9 16 12
13 10 15 11
*/