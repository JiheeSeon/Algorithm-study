package Tree.MST;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KruskalEdgeDW that = (KruskalEdgeDW) o;
        return v1 == that.v1 && v2 == that.v2 && Double.compare(that.w, w) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2, w);
    }
}
