package Tree.Basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class 양구출작전_16437 {
    int V;
    VertexA16437[] vertices;
    ArrayList<VertexA16437>[] graph;

    public 양구출작전_16437(int V, VertexA16437[] vertices, ArrayList<VertexA16437>[] graph) {
        this.V = V;
        this.vertices = vertices;
        this.graph = graph;
    }

    int solve() {
        return dfs(vertices[1], new boolean[V + 1]);
    }

    int dfs(VertexA16437 node, boolean[] check) {
        // leaf node
        if(graph[node.id].isEmpty())
            return (vertices[node.id].isSheep) ? vertices[node.id].animalN : 0;

        check[node.id] = true;
        int remainedSheep = 0;
        for (VertexA16437 child : graph[node.id]) {
            if(check[child.id]) continue;

            remainedSheep += dfs(child, check);
        }

        return node.isSheep
                ? remainedSheep + node.animalN
                : (node.animalN > remainedSheep ? 0 : remainedSheep - node.animalN);
    }
}

class MainA16437{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        String[] tmp;

        VertexA16437[] vertices = new VertexA16437[V + 1];
        ArrayList<VertexA16437>[] graph = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++) {
            graph[v] = new ArrayList<>();
            vertices[v] = new VertexA16437(v);
        }

        int connectedV;
        for (int now = 2; now <= V; now++) {
            tmp = br.readLine().split(" ");

            vertices[now].isSheep = tmp[0].equals("S");
            vertices[now].animalN = Integer.parseInt(tmp[1]);

            connectedV = Integer.parseInt(tmp[2]);
            graph[now].add(vertices[connectedV]);
            graph[connectedV].add(vertices[now]);
        }

        System.out.println(new 양구출작전_16437(V, vertices, graph).solve());
    }
}

class VertexA16437{
    boolean isSheep = true;
    int id;
    int animalN = 0;

    public VertexA16437(int id) {
        this.id = id;
    }
}
