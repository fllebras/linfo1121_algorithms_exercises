package graphs;


import java.util.ArrayList;
import java.util.List;

/**
 * Implement the Digraph.java interface in
 * the Digraph.java class using an adjacency list
 * data structure to represent directed graphs.
 */
public class Digraph {
    private final int V;
    private int E;
    private List<Integer>[] adj;

    public Digraph(int V) {
        // TODO
        this.V = V;
        this.E = 0;
        this.adj = new List[V];
        for (int i=0; i<V; i++){
            this.adj[i]=new ArrayList<>();
        }
    }

    /**
     * The number of vertices
     */
    public int V() {
        // TODO
         return this.V;
    }

    /**
     * The number of edges
     */
    public int E() {
        // TODO
         return this.E;
    }

    /**
     * Add the edge v->w
     */
    public void addEdge(int v, int w) {
        // TODO
        this.E++;
        this.adj[v].add(0,w);
    }

    /**
     * The nodes adjacent to node v
     * that is the nodes w such that there is an edge v->w
     */
    public Iterable<Integer> adj(int v) {
        // TODO
        return this.adj[v];
    }

    /**
     * A copy of the digraph with all edges reversed
     */
    public Digraph reverse() {
        // TODO
        Digraph copy = new Digraph(this.V);
        for(int i=0; i<V; i++){
            for(int v : this.adj[i]){
                copy.addEdge(v,i);
            }
        }
        return copy;
    }

}
