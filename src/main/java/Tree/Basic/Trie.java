package Tree.Basic;

import java.util.HashMap;
import java.util.Map;

/*
Q. 트라이를 일반 그래프로 나타낼 수는 없을까?
ex. 양방향 그래프로 나타낸 뒤 check 배열로 단방향 순회하도록

A. NO
기존에 입력받았던 그래프는 노드가 unique value 가졌음.
그러나 트라이의 경우 접두사 트리로
character(letter)는 여러번 나올 수 있으나,
단어에서 몇번째 순서로 나온 문자인지가 branch 의 level 이 가르켜줌.

ArrayList<>[]로 나타냈던 그래프는 고유한 노드의 일정한 경로를 나타냄.
ex. 1 -> 3, 7, 9 가 그래프에 나타나있으면 13, 17, 19로 무조건 갈 것.
but 트라이의 경우 label(character)가 절대적으로 경로를 결정짓지 않음.
Rather 어느 맥락에서(특정 sequence 뒤) 나온 글자인지에 따라 다 독립된 노드로 취급되어야 함.

Trie에서 고유한 노드는 특정 맥락을 공유한 채로 k번째에 나온 character c를 단위로 함.
(단순히 문자가 같다고 고유한 노드가 아니라는 것)
따라서 위에 문자들의 연결관계를 Node에서 포함하고 있어야 함.
이 때, 같은 접두어지만 여러 단어로 이어질 수 있으므로 key-value 기반 map을 가지고 있어야 함.
*/

class TrieNode{
    /*
    Trie : M-way Tree
    > Trie Node : 현재까지의 접두어에서 파생될 수 있는 글자가 여러개
    */
    Map<Character, TrieNode> childNodes = new HashMap<>();
    boolean isLastChar;
}

class Trie {
    TrieNode rootNode;

    public Trie() {
        rootNode = new TrieNode();
    }

    /*
    입력 받은 단어의 각 알파벳을 계층구조의 자식노드로 만들어 넣음.
    이미 같은 알파벳이 존재하면 공통 접두어 부분까지는 생성하지 않음.
    */
    void insert(String word) {
        TrieNode node = rootNode;
        for (int i = 0; i < word.length(); i++) {
            node = node.childNodes.computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }
        node.isLastChar = true;
    }

    /*
    특정 단어가 Trie 에 존재하는지 체크 위한 두 가지 조건
    1. 루트노드부터 순서대로 알파벳이 일치하는 자식노드들이 존재 할 것
    2. 해당 단어의 마지막 글자에 해당하는 노드의 isLastChar == true
       (해당 글자를 마지막으로 하는 단어가 있다는 뜻)
    */
    boolean contains(String word) {
        TrieNode pNode = rootNode;
        TrieNode cNode;

        for (int i = 0; i < word.length(); i++) {
            cNode = pNode.childNodes.getOrDefault(word.charAt(i), null);
            if(cNode == null) return false;
            pNode = cNode;
        }
        return pNode.isLastChar;
    }

    boolean delete(String word){
        return delete(rootNode, word, 0);
    }

    /*
    삭제 과정은 마지막 글자에서 부모 노드 방향으로 되돌아오며 진행
    Example> Trie { POWER, POW, PIE, PI }
             P -  O    - W(*) - E - R(*)
               -  I(*) - E(*)

    case 1. PMS 삭제  -> false
            M부터 존재하지 않는 단어
            즉, 내려가는 과정에서 없는 child일 경우 과감히 false return
    case 2. POWE 삭제 -> false
            POWE는 접두사일 뿐, 하나의 단어가 아님
            다 내려간 후, 마지막 단어 E에서
            하나의 단어로 종결되는 단어인지 단순히 접두어였는지 확인해야 (isLastChar)
    case 3. POW 삭제 -> true
            POWER가 삭제되면 안됨. POW를 구성하는 노드는 접두어의 역할을 하기 때문에 삭제되면 안됨.
            (경로로서의 역할은 해야 할 거 아니니)
            이 경우 W의 isLastChar, 즉 완성된 하나의 단어라는 표식만 제거

    정리
    내려가면서 : 단어를 삭제하는 과정에서 이미 존재하지 않는 단어는 여지없이 컷
    올라오면서 : 접두어로서의 역할뿐인지, 가장 긴 단어로 실질적인 노드 삭제가 필요한 부분인지
                !isLastChar                   CNode.childNodes.isEmpty()
                * 아래에 유효한 child가 있으면 무조건 올라오면서 삭제 불가

    Bottom -> Up Traverse
    삭제 진행은 마지막 글자에서 부모 노드 방향으로 되돌아 오는 과정에서 진행된다는 점에 유의

    1. 자식 노드를 가지고 있지 않아야. ex. PI 지우다가 PIE까지 삭제되면 안됨.
    2. 삭제를 시작하는 첫 노드는 isLastChar == true b/c false인 경우는 Trie에 없는 단어란 뜻
    3. 삭제를 진행하던 중에는 isLastChar==false
       삭제 과정 중에서 isLastChar가 true > 또다른 단어가 있다는 의미 > 삭제 대상이 아님.
    */
    private boolean delete(TrieNode pNode, String word, int idx) {
        char c = word.charAt(idx);

        TrieNode cNode = pNode.childNodes.getOrDefault(c, null);
        if(cNode == null) return false; // 더 내려가볼 것도 없이 false 뱉음 b/c 매칭하지 않는다는 이야기

        // 맨 마지막 문자에 도달하는 경우
        if (++idx == word.length()) {
            // 끝까지 도달했어도 접두어일뿐, 단어로서의 효력 없는 경우 (case 2)
            if(!cNode.isLastChar) return false;

            cNode.isLastChar = false; // 사실상 삭제 효과 (case 3)
            // 해당 브랜치에서 가장 긴 단어였을 경우
            if(cNode.childNodes.isEmpty()){
                pNode.childNodes.remove(c);
            }
        } else{
            if(!delete(cNode, word, idx)) return false;
            /* 아래에서 성공적으로 삭제하면
               삭제 중 자식 노드가 없고 현재 노드로 끝나는 다른 단어가 없는 경우 삭제 진행
               (해당 글자를 마지막으로 하는 단어가 있다는 뜻)*/
            if (!cNode.isLastChar && cNode.childNodes.isEmpty()) {
                pNode.childNodes.remove(c);
            }
        }
        return true;
    }
}