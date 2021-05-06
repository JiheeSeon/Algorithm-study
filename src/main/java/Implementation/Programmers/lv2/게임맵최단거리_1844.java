package Implementation.Programmers.lv2;
import java.util.*;

class 게임맵최단거리_1844 {
    int yHeight = 0;
    int xWidth = 0;
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};
    int[][] check = {{}};
    int[][] graph = {{}};

    public int solution(int[][] maps) {
        yHeight = maps.length;
        xWidth = maps[0].length;

        // -1 이 나올 수 있는 경우부터 체크
        if(
                ((yHeight > 1 && xWidth == 1) && (maps[yHeight - 2][0] == 0))
                        ||((yHeight == 1 && xWidth > 1) && (maps[0][xWidth - 2] == 0))
                        ||((yHeight > 1 && xWidth > 1) && (maps[yHeight - 2][xWidth - 1] == 0) && (maps[yHeight - 1][xWidth - 2] == 0))
        ){
            return -1;
        }

        // BFS 돌기
        check = new int[yHeight][xWidth];
        graph = maps;

        bfs();

        int ans = check[yHeight - 1][xWidth - 1];
        return ans == 0 ? -1 : ans;
    }
    void bfs(){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        check[0][0] = 1;

        Point now;
        int nextY, nextX;
        while(!q.isEmpty()){
            now = q.poll();

            for(int i = 0; i < 4; i++){
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];
                if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth
                        || check[nextY][nextX] != 0 || graph[nextY][nextX] == 0)
                    continue;

                check[nextY][nextX] = check[now.y][now.x] + 1;
                q.add(new Point(nextY, nextX));
            }
        }
    }
    private class Point{
        int y, x;

        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
