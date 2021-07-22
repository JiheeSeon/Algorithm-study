package Tree.MST;

public class PrimEdge implements Comparable<PrimEdge> {
    int nextV, w;

    public PrimEdge(int nextV, int w) {
        this.nextV = nextV;
        this.w = w;
    }

    @Override
    public int compareTo(PrimEdge o) {
        return Integer.compare(w, o.w);
    }
}
