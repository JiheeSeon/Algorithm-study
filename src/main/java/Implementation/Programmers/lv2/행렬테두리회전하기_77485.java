package Implementation.Programmers.lv2;

class 행렬테두리회전하기_77485 {
    public int[] solution(int yHeight, int xWidth, int[][] queries) {
        // initialiation
        int[][] plane = new int[yHeight][xWidth];
        int[][] saveVer = new int[yHeight][xWidth];

        int toSet = 1;
        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                saveVer[y][x] = toSet;
                plane[y][x] = toSet++;
            }
        }

        int cWidth; int cHeight;
        int startY, startX, endY, endX;

        int[] result = new int[queries.length];
        int idx = 0;
        int min;

        for(int[] q : queries){
            min = Integer.MAX_VALUE;

            startY = q[0] - 1;
            startX = q[1] - 1;
            endY = q[2] - 1;
            endX = q[3] - 1;

            cHeight = endY - startY;
            cWidth = endX - startX;

            for(int x = startX; x < endX; x++){
                plane[startY][x + 1] = saveVer[startY][x];
                min = Math.min(min, plane[startY][x+1]);
            }

            for(int y = startY; y < endY; y++){
                plane[y + 1][endX] = saveVer[y][endX];
                min = Math.min(min, plane[y + 1][endX]);
            }

            for(int x = endX; x > startX; x--){
                plane[endY][x - 1] = saveVer[endY][x];
                min = Math.min(min, plane[endY][x - 1]);
            }

            for(int y = endY; y > startY; y--){
                plane[y - 1][startX] = saveVer[y][startX];
                min = Math.min(min, plane[y - 1][startX]);
            }

            result[idx++] = min;

            for(int y = 0; y < yHeight; y++){
                // 좀 더 최적화할 방안이 있지 않았을까 싶음, 행렬복사만 해도 시간이 꽤 들어서..
                saveVer[y] = plane[y].clone();
            }
        }
        return result;
    }
}
