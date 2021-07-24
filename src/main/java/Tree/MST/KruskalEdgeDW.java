package Tree.MST;

public class KruskalEdgeDW implements Comparable<KruskalEdgeDW>{
    int v1, v2;
    double w;

    public KruskalEdgeDW(int v1, int v2, double w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }

    @Override
    public int compareTo(KruskalEdgeDW o) {
        return Double.compare(w, o.w);
    }
}
