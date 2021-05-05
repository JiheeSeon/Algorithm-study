package Implementation.Programmers.lv2;

/*
문제분석
x 구간마다 y의 가용범위들만큼의 차를 더하면 된다!
double을 쓰지 않고 정수형 자료형을 쓸 때 올림처리와 내림처리에 유의해서 풀었어야 함.
*/
class 멀쩡한사각형_62048 {
    public long solution(int w, int h) {
        long ans = (long)w * h; // 모든 사각형의 개수로 초기화

        long y = (long)h;
        long nextY;
        long tmp;

        for(int x = 1; x <= w; x++){
            tmp = h - ((long)h * x) / w; // 일차함수에 기반한 식의 형태, 무조건 올림 처리
            nextY = ((long)h * x) % w != 0 ? tmp - 1: tmp; // 소수이면 내림 처리
            ans -= (y - nextY); // 가로가 1이므로 직선이 지나가는 사각형 영역의 넓이는 y의 높이만큼 빼줌
            y = tmp; // 올림처리된 것이 다음의 높이
        }

        return ans;
    }
}
