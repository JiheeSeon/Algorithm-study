package Tree.Basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class 전화번호목록_5052 {
    String[] words;
    Trie trie;

    public 전화번호목록_5052(String[] words) {
        this.words = words;
        trie = new Trie();
    }

    String solve() {
        for (String word : words) {
            if(!trie.insert(word)) return "NO";
        }
        return "YES";
    }

    private class TrieNode{
        Map<Character, TrieNode> childNodes = new HashMap<>();
        boolean isLastChar;
    }

    private class Trie{
        TrieNode rootNode;

        public Trie() {
            rootNode = new TrieNode();
        }

        boolean insert(String word) {
            TrieNode node = rootNode;

            for (int i = 0; i < word.length(); i++) {
                node = node.childNodes.computeIfAbsent(word.charAt(i), c -> new TrieNode());
                if(node.isLastChar) return false;
            }
            node.isLastChar = true;
            return true;
        }
    }
}


class MainA5052{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int N;
        String[] words;
        StringBuilder stb = new StringBuilder();

        while (--T >= 0) {
            N = Integer.parseInt(br.readLine());

            words = new String[N];
            for(int i = 0; i < N; i++) words[i] = br.readLine();
            Arrays.sort(words); // 길이순 정렬 필요

            stb.append(new 전화번호목록_5052(words).solve()).append("\n");
        }

        System.out.print(stb);
    }
}