package Implementation.Programmers.lv1;

import java.util.stream.*;

class Main12912 {
    public long solution(int a, int b) {
        return LongStream.rangeClosed(Math.min(a, b), Math.max(a, b)).sum();
    }
}