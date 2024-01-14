package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * You are asked to implement the ConnectedComponent class given a graph.
 * The Graph class available in the code is the one of the Java class API.
 * <p>
 * public class ConnectedComponents {
 * <p>
 * public static int numberOfConnectedComponents(Graph g){
 * // TODO
 * return 0;
 * }
 * }
 * Hint: Feel free to add methods or even inner-class (private static class)
 *       to help you but don't change the class name or the signature of the numberOfConnectedComponents method.
 *
 *
 */
public class ConnectedComponents {
    private static class CC{
        private boolean[] marked;
        private int[] id;
        private int count;
        public CC(Graph G) {
            marked = new boolean[G.V()];
            id = new int[G.V()];
            for (int s = 0; s < G.V(); s++)
                if (!marked[s])
                {
                    dfs(G, s);
                    count++;
                }
        }
        private void dfs(Graph G, int v) {
            marked[v] = true;
            id[v] = count;
            for (int w : G.adj(v))
                if (!marked[w])
                    dfs(G, w);
        }

    }

    /**
     * @return the number of connected components in g
     */
    public static int numberOfConnectedComponents(Graph g) {
        // TODO
        CC cc = new CC(g);
        return cc.count;
    }

    static class Graph {

        private List<Integer>[] edges;

        public Graph(int nbNodes)
        {
            this.edges = (ArrayList<Integer>[]) new ArrayList[nbNodes];
            for (int i = 0;i < edges.length;i++)
            {
                edges[i] = new ArrayList<>();
            }
        }

        /**
         * @return the number of vertices
         */
        public int V() {
            return edges.length;
        }

        /**
         * @return the number of edges
         */
        public int E() {
            int count = 0;
            for (List<Integer> bag : edges) {
                count += bag.size();
            }

            return count/2;
        }

        /**
         * Add edge v-w to this graph
         */
        public void addEdge(int v, int w) {
            edges[v].add(w);
            edges[w].add(v);
        }

        /**
         * @return the vertices adjacent to v
         */
        public Iterable<Integer> adj(int v) {
            return edges[v];
        }
    }

}
