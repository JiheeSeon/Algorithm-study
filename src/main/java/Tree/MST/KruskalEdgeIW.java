package Tree.MST;

class KruskalEdgeIW implements Comparable<KruskalEdgeIW>{
    int v1, v2, w;

    public KruskalEdgeIW(int v1, int v2, int w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }

    @Override
    public int compareTo(KruskalEdgeIW o) {
        return Integer.compare(w, o.w);
    }
}
