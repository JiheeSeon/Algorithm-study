package Tree;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.regex.Pattern;

class 트리원소삭제_1068 {
    int[] parent;

    public int solution(int[] pLocal, int toDelete) {
        parent = pLocal;

        Set<Integer> leaf = IntStream.range(0, parent.length).boxed().collect(Collectors.toCollection(HashSet::new));
        for(int i = 0; i < parent.length; i++) leaf.remove(parent[i]);

        for(int l : leaf){
            deleteIfNeeded(l, toDelete);
        }

        // 단말 노드 체크
        // 루트 노드 또한 단말노드가 될 수 있기 때문에 leaf 목록에 -1부터 세팅
        leaf = IntStream.range(-1, parent.length).boxed().collect(Collectors.toCollection(HashSet::new));
        for(int i = 0; i < parent.length; i++){
            if(parent[i] == -2) leaf.remove(i);
            else leaf.remove(parent[i]);
        }
        return leaf.size();
    }

    boolean deleteIfNeeded(int currNode, int toDelete){
        if(currNode == -1) return false;
        if(currNode == -2) return true;
        if(currNode == toDelete){
            parent[currNode] = -2;
            return true;
        }

        boolean b = deleteIfNeeded(parent[currNode], toDelete);
        if(b) parent[currNode] = -2;
        return b;
    }
}
class Main1068{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] parent = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int toDelete = Integer.parseInt(br.readLine());

        if(parent[toDelete] == -1){
            System.out.println(0); return;
        }
        System.out.println(new 트리원소삭제_1068().solution(parent, toDelete));
    }
}