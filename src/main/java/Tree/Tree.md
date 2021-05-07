# Tree Index

### 1. 트리순회 문제 (1991)
- 탐색 방식 : 부모가 child에 대한 정보를 가지고 자식으로 타고 내려가서 탐색
  
- 전위, 중위, 후위 순회 방식에 대한 기본적인 이해
    - preorder : visit current -> left child -> right child
    - inorder : left child -> visit current -> right child
    - postorder : visit current -> left child -> right child
    -> 여기서 visit current 는 StringBuilder 에 append 하는 것으로 표시
    -> 트리의 루트에서 자식으로 내려가는 구조
      - 자식이 없는 경우를 return 포인트로 잡아줌
  
- 트리를 저장하는 방식
  - 이진 트리라는 것이 보장되어있으므로 left, right child를 갖는 노드 클래스를 만들 수 있음
    - 이 때 중요한 것은 본인과 child 정보를 담는다는 것 :: 상 -> 하 방향으로 재귀호출하므로
  - 알파벳 순서대로 노드가 들어오지 않는 경우에는 Map을 사용할 수 있음.
    - 알파벳 순으로 들어온다는걸 이용해서 sol2 에서는 -'A'로 배열 index로 사용해서 저장
  - 이진트리가 아니라면 child들을 ArrayList로 갖고 있는 것도 방법 :: 일반 graph 저장하듯이


### 2. 