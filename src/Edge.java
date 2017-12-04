import java.util.Objects;

/**
 * A simple representation of a graph edge. Note that a {@code List<Edge>} can be easily be sorted
 * by edge weights.
 * <p/>
 * Do NOT not modify this file.
 *
 * @author Max Scheiber (scheiber), Lewis Ellis (ellisl), 15sp
 */
public class Edge implements Comparable<Edge> {
    public final int u;
    public final int v;
    public final int weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge edge = (Edge) o;
        return Objects.equals(u, edge.u) &&
                Objects.equals(v, edge.v) &&
                Objects.equals(weight, edge.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v, weight);
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(weight, other.weight);
    }

}
