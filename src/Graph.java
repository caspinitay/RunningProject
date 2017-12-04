import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


public class Graph {
	
	private Map<Integer, Edge>[] adjArray;
	private Set<Edge> edges;
	private int n;
	
    /**
     * Initializes a graph of size {@code n}. All valid vertices in this graph thus have integer
     * indices in the half-open range {@code [0, n)}.
     * <p/>
     *
     * @param n the number of vertices in the graph
     * @throws IllegalArgumentException if {@code n} is negative
     */
    public Graph(int n) {
    	if (n < 0) {
    		throw new IllegalArgumentException();
    	}
    	
    	this.n = n;
    	
    	// Array of HashMaps - each index contains map of adjacent vertices and corresponding
    	// Edge object to that vertex
    	adjArray = (Map<Integer, Edge>[]) new HashMap[n];
    	
    	// Save set of edges for quick access
    	edges = new HashSet<Edge>();
    	
    	// Initialize each index as that vertex's outgoing edges
    	for (int i = 0; i < n; i++) {
    		adjArray[i] = new HashMap<Integer, Edge>();
    	}
    }

    /**
     * Returns the number of vertices in the graph.
     * <p/>
     *
     * @return the number of vertices in the graph
     * @implSpec This method should run in O(1) time.
     */
    public int getSize() {
        return n;
    }

    /**
     * Returns the set of edges in this graph. Since this is an undirected graph, if an edge {@code
     * u-v} exists in the graph, be sure that you return either an Edge object for the {@code u-v}
     * direction <em>or</em> an {@link Edge} object for the {@code v-u} direction, but not both.
     * <p/>
     *
     * @return a set containing this graph's edges
     * @implSpec This method should run in not more than expected O(|E|) time.
     */
    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    /**
     * Determines if an edge exists between two vertices.
     * <p/>
     *
     * @param u a vertex
     * @param v a vertex
     * @return {@code true} if the {@code u-v} edge is in this graph
     * @throws IllegalArgumentException if a specified vertex does not exist
     * @implSpec This method should run in expected O(1) time.
     */
    public boolean hasEdge(int u, int v) {
    	if (u > n-1 || v > n-1 || u < 0 || v < 0) {
    		throw new IllegalArgumentException();
    	}
    	
        return adjArray[u].containsKey(v);
    }

    /**
     * Creates an edge between {@code u} and {@code v} if it does not already exist. A call to this
     * method should <em>not</em> modify the edge weight if the {@code u-v} edge already exists.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param u one vertex to connect
     * @param v the other vertex to connect
     * @param weight the edge weight
     * @return {@code true} if the graph changed as a result of this call, false
     * otherwise (i.e. if the edge is already present)
     * @throws IllegalArgumentException if a specified vertex does not exist
     * @implSpec This method should run in expected O(1) time.
     */
    public boolean addEdge(int u, int v, int weight) {
    	
    	// Construct edge objects from u to v and from v to u
        Edge eTo = new Edge(u, v, weight);
        Edge eFrom = new Edge(v, u, weight);
        
        // Add edges to respective adjArray indices
        boolean ret = false;
        
        // Check if graph contains edge. If not, add edge.
        if (!adjArray[u].containsKey(v)) {
        	adjArray[u].put(v, eTo);
        	edges.add(eTo);
        	ret = true;
        }
        if (!adjArray[v].containsKey(u)) {
        	adjArray[v].put(u, eFrom);
        }
        
        return ret;
        
    }

    /**
     * Returns the weight of an edge.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param u a vertex
     * @param v a vertex
     * @return the edge weight of {@code u-v}
     * @throws NoSuchElementException if the {@code u-v} edge does not exist
     * @throws IllegalArgumentException if a specified vertex does not exist
     * @implSpec This method should run in expected O(1) time.
     */
    public int getWeight(int u, int v) {
    	if (u > n-1 || v > n-1 || u < 0 || v < 0) {
    		throw new IllegalArgumentException();
    	}
    	if (!hasEdge(u,v)) {
    		throw new NoSuchElementException();
    	}
    	
    	return adjArray[u].get(v).weight;
    }

    /**
     * Returns the neighbors of the specified vertex.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param v the vertex
     * @return all neighbors of the specified vertex or an empty set if there
     * are no neighbors
     * @throws IllegalArgumentException if the specified vertex does not exist
     * @implSpec This method should run in expected O(deg(v)) time.
     */
    public Set<Integer> getNeighbors(int v) {
    	if (v < 0 || v > n-1) {
    		throw new IllegalArgumentException();
    	}
    	
        return Collections.unmodifiableSet(adjArray[v].keySet());
    }
    
}
