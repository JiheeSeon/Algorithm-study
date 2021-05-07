package Tree;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class 완전이진트리_중위순회_역추적_9934 {
    public String solution(int L, int[] inorder) {
        ArrayList<String>[] tree = new ArrayList[L]; // level마다 string의 arrayList

        for(int lv = 0; lv < L; lv++){
            tree[lv] = new ArrayList<String>();
        }
        tree = traverse(0, 0, inorder.length, tree, inorder);
        StringBuilder stb = new StringBuilder();
        for(ArrayList<String> lvStr : tree){
            stb.append(String.join(" ", lvStr)).append("\n");
        }
        return stb.toString();
    }

    ArrayList<String>[] traverse(int depth, int start, int end, ArrayList<String>[] tree, int[] inorder){
        if(start == end) return tree;

        int currIdx = (start + end) / 2;
        tree[depth].add(String.valueOf(inorder[currIdx]));
        traverse(depth + 1, start, currIdx, tree, inorder);
        traverse(depth + 1, currIdx + 1, end, tree, inorder);

        return tree;
    }
}
class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        int[] inorder = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        System.out.print(new 완전이진트리_중위순회_역추적_9934().solution(L, inorder));
    }
}