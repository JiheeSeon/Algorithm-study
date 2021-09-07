# Review Core Algorithms

## 1. Exhaustive Search
### Backtrack

## 2. Graph
### 2.1 Shortest Path Algorithms
#### (1) Dijkstra's Algorithm
> 하나의 출발지에서 모든 점으로의 최단거리를 구하는 알고리즘
- **As a DP Problem**
  - 최단경로의 부분 경로는 역시 최단경로
    - 전체 최단거리 is composed by 여러개의 최단 거리
    - 하나의 최단거리를 구할 때 그 이전까지 구했던 최단거리 정보를 그대로 사용
    - 다른 곳을 경유했을 때 더 최단거리가 나온다면 현재까지 알고 있던 최단 경로를 갱신
- **Logic**
  1. 출발노드 설정
  2. 출발노드 기준으로 각 노드의 최소 비용 저장
  3. 방문하지 않은 노드 중 가장 비용이 적은 노드 선택
  4. 해당 노드와 연결된 노드 중 갱신될 수 있는 최소비용을 갱신
  5. 3 - 4 반복
- **Sample code** [>> Refer here!!](./SampleCode/Dijkstra.java)
  - _Code Analysis_
    - PriorityQueue를 통해 제일 가장 작게 설정된 

#### (2) Bellman Ford's Algorithm
> 음수 가중치가 있는 그래프에서 하나의 출발지에서 모든 점으로의 최단거리를 구하는 알고리즘
- **Logic**
  1. 시작점의 거리만 0으로 세팅해놓음
  2. V번 동안 edge relaxation
  3. 마지막 V번째에서 변화가 있으면 무한 cycle로 판단
- **Sample code** [>> Refer here!!](./SampleCode/BellmanFord.java)

#### (3) Floyd Warshall's Algorithm
> 모든 정점 간의 최단 경로를 구하는 알고리즘
- Logic : 3중 for문으로 경유지 -> src -> dst 순으로 결정
- **Sample code** [>> Refer here!!](./SampleCode/FloydWarshall.java)


### 2.2 MST (Minimum Spanning Tree)
#### Kruskal's Algorithm
> 가장 가중치가 작은 간선을 사이클이 생기지 않도록 선택하여 MST를 구성하는 방법 (간선 중심)
- Sample Code [>> Refer here!!](./SampleCode/Prim.java)

**어떻게 사이클이 생기지 않도록 하는가?<br>**
Union find 알고리즘에서 선택하고자 하는 간선의 양 끝점이 같은 트리인 경우를 밴
- Sample Code [>> Refer here!!](./SampleCode/Prim.java)

#### Prim's Algorithm
> 임의의 출발점을 선택한 후, 사이클이 생기지 않도록 선택된 정점과 연결된 간선들 중 가장 가중치가 작은 정점을 선택 (정점 중심)

**어떻게 사이클이 생기지 않도록 하는가?<br>**
정점 중심적으로 생각할 때, 사이클은 이미 나온 정점이 나왔을 때 생김.<br>
-> 이미 선택된 정점인지만 확인하면 됨.


### 2.3 Heap


## 3. Tree
### 3.1 BST

### 3.2 Segmented Tree

## 