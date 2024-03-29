import java.util.*;

class Graph_DFS {
    private int vertices;
    private LinkedList<Integer>[] adjacencyList;
    private int time;
    private String[] color;
    private int[] d;
    private int[] f;
    private int[] predecessor;

    public Graph_DFS(int v) {
        this.vertices = v;
        this.adjacencyList = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adjacencyList[i] = new LinkedList<>();
        }
        this.color = new String[v];
        this.d = new int[v];
        this.f = new int[v];
        this.predecessor = new int[v];
    }

    public void addEdge(int start, int end) {
        adjacencyList[start].add(end);
    }

 
    public void dfs() {
        time = 0;
        Arrays.fill(color, "WHITE");
        Arrays.fill(predecessor, -1);
        
        for (int i = 0; i < vertices; i++) {
            if (color[i].equals("WHITE")) {
                dfsVisit(i);
            }
        }
    }

    private void dfsVisit(int vertex) {
        time++;
        d[vertex] = time;
        color[vertex] = "GRAY";

        for (int neighbor : adjacencyList[vertex]) {
            if (color[neighbor].equals("WHITE")) {
                predecessor[neighbor] = vertex;
                dfsVisit(neighbor);
            }
        }

        color[vertex] = "BLACK";
        time++;
        f[vertex] = time;
    }

    public void printAttributes() {
        System.out.println("Vertex\tColor\tDiscovery Time\tFinish Time\tPredecessor");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + "\t" + color[i] + "\t" + d[i] + "\t\t" + f[i] + "\t\t" + predecessor[i]);
        }
    }

    public static void main(String[] args) {
        Graph_DFS graph = new Graph_DFS(6);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.dfs();
        graph.printAttributes();
    }
}
