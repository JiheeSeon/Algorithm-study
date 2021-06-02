package Graph.Archive.BDFS;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class 영역구하기_2583 {
    int yHeight, xWidth, areaN;
    boolean[][] graph;
    boolean[][] check;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 영역구하기_2583(int yHeight, int xWidth, int areaN, int[][] areas){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.areaN = areaN;
        graph = new boolean[yHeight][xWidth];
        check = new boolean[yHeight][xWidth];

        int sY, sX, eY, eX;
        for(int a = 0; a < areaN; a++){
            sY = areas[a][1]; sX = areas[a][0];
            eY = areas[a][3]; eX = areas[a][2];

            for(int y = sY; y < eY; y++){
                for(int x = sX; x < eX; x++){
                    graph[y][x] = true;
                }
            }
        }
    }

    String getAns(){
        ArrayList<Integer> areas = new ArrayList<>();

        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                if(!check[y][x] && !graph[y][x]){
                    areas.add(dfs(y, x, 0));
                }
            }
        }

        return new StringBuilder(Integer.toString(areas.size())).append("\n").append(areas.stream().sorted().map(x->Integer.toString(x)).collect(Collectors.joining(" "))).toString();
    }

    int dfs(int y, int x, int cnt){
        if(y < 0 || x < 0 || y >= yHeight || x >= xWidth || check[y][x] || graph[y][x]) return cnt;

        check[y][x] = true; cnt++;

        for(int i = 0; i < 4; i++){
            cnt = dfs(y + dy[i], x + dx[i], cnt);
        }
        return cnt;
    }
}

class MainA2583 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int yHeight = tmp[0];
        int xWidth = tmp[1];
        int areaN = tmp[2];
        int[][] areas = new int[areaN][4];

        for(int a = 0; a < areaN; a++){
            areas[a] = strToIntArr(br.readLine());
        }

        System.out.println(new 영역구하기_2583(yHeight, xWidth, areaN, areas).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}