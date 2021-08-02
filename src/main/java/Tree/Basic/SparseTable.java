package Tree.Basic;

/*
  다음 예제 참고
  https://namnamseo.tistory.com/entry/Sparse-Table
*/

class SparseTable {
    int N;
    int[] next;
    int[][] table;

    public SparseTable(int N, int[] next) {
        this.N = N;
        this.next = next;
    }

    void init(int MAX_LGK) {
        table = new int[MAX_LGK + 1][next.length];
        table[0] = next.clone();

        for (int k = 1; k <= MAX_LGK; k++) {
            for (int i = 1; i <= N; i++) {
                table[k][i] = table[k - 1][table[k - 1][i]];
            }
        }
    }

    int solve(int startV, int k) {
        int MAX_LGK = (int)(Math.log10(k)/Math.log10(2));
        init(MAX_LGK); // set sparse table

        int nowV = startV;

        int iPtr = MAX_LGK + 1;
        while (--iPtr >= 0) {
            /*테크닉 익혀놓기 -> 최상의 자리부터 하나씩 내려오면서 체크*/
            if ((k & (1 << iPtr)) != 0) {
                nowV = table[iPtr][nowV];
            }
        }

        return nowV;
    }
}

class SparseTableApp{
    public static void main(String[] args) {
        System.out.println(new SparseTable(9, new int[]{0, 4, 3, 5, 2, 1, 5, 3, 6, 6}).solve(6, 7));
    }
}