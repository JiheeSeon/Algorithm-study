package Implementation.Programmers.lv2;

/*
문제분석
DFS에서 영역의 개수 및 영역 별 넓이 구하는 기본 문제의 변형
변형되었던 부분은 color를 확인했어야 했다는 것!
20분 내로 풀었다!
*/
class 카카오프렌즈컬러링북_1829 {
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};
    int[][] check={{}};
    int max;
    int yH; int xW;

    public int[] solution(int yHeight, int xWidth, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        yH = yHeight;
        xW = xWidth;

        check = new int[yHeight][xWidth];

        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                if(check[y][x] == 0 && picture[y][x] != 0){
                    max = 0;
                    dfs(new Point(y, x), picture[y][x], ++numberOfArea, picture);
                    if(max > maxSizeOfOneArea) maxSizeOfOneArea = max;
                }
            }
        }


        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    void dfs(Point curr, int color, int areaN, int[][] picture){
        if(curr.y >= yH || curr.x >= xW || curr.y < 0 || curr.x < 0 ||
                check[curr.y][curr.x] != 0 || picture[curr.y][curr.x] != color) return;

        check[curr.y][curr.x] = areaN;
        max++;

        for(int i = 0; i < 4; i++){
            dfs(new Point(curr.y + dy[i], curr.x + dx[i]), color, areaN, picture);
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
