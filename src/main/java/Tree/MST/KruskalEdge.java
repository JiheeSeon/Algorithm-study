package Tree.MST;

class KruskalEdge implements Comparable<KruskalEdge>{
    int v1, v2, w;

    public KruskalEdge(int v1, int v2, int w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }

    @Override
    public int compareTo(KruskalEdge o) {
        return Integer.compare(w, o.w);
    }
}
