package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;


class 완전이진트리_9934 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine()); // 3
        int[] inorder = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        int elementN = inorder.length;
        if(elementN == 1 && L == 1) {
            System.out.println(inorder[0]);
            return;
        }
        if(elementN == 2 && L == 2) {
            System.out.println(inorder[1] + "\n" + inorder[0]);
            return;
        }
        if(elementN == 3 && L == 2) {
            System.out.println(inorder[1] + "\n" + inorder[0] + " " + inorder[2]);
            return;
        }
        int remainedN = elementN - (int)Math.pow(2, L - 1) + 1;

        ArrayList<Integer> benchmark = getPerfect(L);
        ArrayList<Integer> customized = new ArrayList<>();

        int idx = 0; int dst2StartIdx = -1;
        int dst = 1; int num = 0 ;int got;
        boolean first = true;

        if(remainedN != 0) {
            while (idx < benchmark.size()) {
                if(dst2StartIdx == idx) dst = 2;

                got = benchmark.get(idx);
                if (got == -1 || got == 1) num++;

                if (dst == 1) customized.add(got);
                else customized.add(got + benchmark.get(idx + 1));

                if (num == remainedN && first) {
                    dst2StartIdx = remainedN % 2 == 0 ? idx + 2 : idx + 1;
                    first = false;
                }

                idx += dst;
            }
        } else customized = benchmark;

        customized.remove(customized.size() - 1);

        ArrayList<Integer>[] tree = new ArrayList[L]; // depth 별로 Node들
        for (int l = 0; l < L; l++)
            tree[l] = new ArrayList<>();

        int depth = 0;

        for(int i = 0; i < inorder.length; i++) {
            depth += customized.get(i);
            tree[depth].add(inorder[i]);
        }

        StringBuilder stb = new StringBuilder();
        ArrayList<Integer> a;
        for(int l = 0; l < L; l++){
            a = tree[l];
            for(int i : a)
                stb.append(i).append(" ");
            stb.append("\n");
        }
        System.out.println(stb);
    }

    static ArrayList<Integer> getPerfect(int level) {
        ArrayList<Integer> toRet = new ArrayList<Integer>();
        if(level == 2){
            toRet.add(1); toRet.add(-1); toRet.add(1);
            return toRet;
        }
        if(level == 3){
            toRet.add(2); toRet.add(-1); toRet.add(1); toRet.add(-2);
            toRet.add(2); toRet.add(-1); toRet.add(1); toRet.add(-2);
            return toRet;
        }

        ArrayList<Integer> sub = getPerfect(level - 1);
        sub.remove(sub.size() - 1);
        sub.remove(0);

        toRet.add(level - 1);
        toRet.addAll(sub);
        toRet.add(-(level - 1)); toRet.add(level - 1);
        toRet.addAll(sub);
        toRet.add(-level + 1);

        return toRet;
    }
}
