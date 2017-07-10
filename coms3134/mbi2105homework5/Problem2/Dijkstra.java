/*
* Mike Introini
* mbi2105
*
*  Implement Dijkstra's algorithm to find shortest paths between pairs of cities on a map.
*
*
*/


import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.Math;

public class Dijkstra {

  // Keep a fast index to nodes in the map
  private Map<String, Vertex> vertexNames;

  /**
   * Construct an empty Dijkstra with a map. The map's key is the name of a vertex
   * and the map's value is the vertex object.
   */
  public Dijkstra() {
    vertexNames = new HashMap<String, Vertex>();
  }

  /**
   * Adds a vertex to the dijkstra. Throws IllegalArgumentException if two vertices
   * with the same name are added.
   * 
   * @param v
   *          (Vertex) vertex to be added to the dijkstra
   */
  public void addVertex(Vertex v) {
    if (vertexNames.containsKey(v.name))
      throw new IllegalArgumentException("Cannot create new vertex with existing name.");
    vertexNames.put(v.name, v);
  }

  /**
   * Gets a collection of all the vertices in the dijkstra
   * 
   * @return (Collection<Vertex>) collection of all the vertices in the dijkstra
   */
  public Collection<Vertex> getVertices() {
    return vertexNames.values();
  }

  /**
   * Gets the vertex object with the given name
   * 
   * @param name
   *          (String) name of the vertex object requested
   * @return (Vertex) vertex object associated with the name
   */
  public Vertex getVertex(String name) {
    return vertexNames.get(name);
  }

  /**
   * Adds a directed edge from vertex u to vertex v
   * 
   * @param nameU
   *          (String) name of vertex u
   * @param nameV
   *          (String) name of vertex v
   * @param cost
   *          (double) cost of the edge between vertex u and v
   */
  public void addEdge(String nameU, String nameV, Double cost) {
    if (!vertexNames.containsKey(nameU))
      throw new IllegalArgumentException(nameU + " does not exist. Cannot create edge.");
    if (!vertexNames.containsKey(nameV))
      throw new IllegalArgumentException(nameV + " does not exist. Cannot create edge.");
    Vertex sourceVertex = vertexNames.get(nameU);
    Vertex targetVertex = vertexNames.get(nameV);
    Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
    sourceVertex.addEdge(newEdge);
  }

  /**
   * Adds an undirected edge between vertex u and vertex v by adding a directed
   * edge from u to v, then a directed edge from v to u
   * 
   * @param nameU
   *          (String) name of vertex u
   * @param nameV
   *          (String) name of vertex v
   * @param cost
   *          (double) cost of the edge between vertex u and v
   */
  public void addUndirectedEdge(String nameU, String nameV, double cost) {
    addEdge(nameU, nameV, cost);
    addEdge(nameV, nameU, cost);
  }

  // STUDENT CODE STARTS HERE

  /**
   * Computes the euclidean distance between two points as described by their
   * coordinates
   * 
   * @param ux
   *          (double) x coordinate of point u
   * @param uy
   *          (double) y coordinate of point u
   * @param vx
   *          (double) x coordinate of point v
   * @param vy
   *          (double) y coordinate of point v
   * @return (double) distance between the two points
   */
  public double computeEuclideanDistance(double ux, double uy, double vx, double vy) {
        // Calculate the Square Root of
        return Math.hypot((ux-vx),(uy-vy)); // Replace this
  }

  /**
   * Calculates the euclidean distance for all edges in the map using the
   * computeEuclideanCost method.
   */
  public void computeAllEuclideanDistances() {
    // Run through each vertex with Depth-First Search to calculate distances 
      for (Vertex v : getVertices()){
        dfs(v);
      }
  }


  /**
   * Performs Depth-First Search and calculates Euclidian Distances for each
   * Edge
   *
   * @param v
   *          (vertex) vertex being visited
   */
  public void dfs(Vertex v) {
    // Mark vertex as visited
    v.known = true;

    // Check each adjacent edge of the vertex currently being visited
    for (Edge w : v.adjacentEdges) {
    
      // Compute the edge distance from the visited vertex "v" to 
      // the distant end "w.target"
      w.distance = computeEuclideanDistance(w.target.x, w.target.y, v.x, v.y );
    
      // If the distant end hasn't been visited, run through DFS again and mark
      // it as visited.
      if (!w.target.known) {
        dfs(w.target);
      }
    
    }
  
  }

  /**
   * Dijkstra's Algorithm. 
   * 
   * @param s
   *          (String) starting city name
   */
  public void doDijkstra(String s) {
    /* Variables
    * 
    * needsToBeVisited: List that keeps track of vertexes that still
    * need to be marked as "known".
    *
    * startingVertex: Starting point on the graph.
    *
    * count: Keeps count of how many times a vertex has been marked as "known". 
    *
    */ 
    LinkedList<Vertex> needsToBeVisited = new LinkedList<>();
    Vertex startingVertex = getVertex(s);
    int count = 0;

    // Set all vertex distances to infinity and mark as "not visited"
    for(Vertex v : getVertices()) {
      v.distance = Double.POSITIVE_INFINITY;
      v.known = false;
    }

    // Set starting vertex distance to 0 and add to the list
    startingVertex.distance = 0;
    needsToBeVisited.add(startingVertex);

    // Main loop
    while (count != vertexNames.size()) {

      // find the vertex in the list with the minimum distance 
      Vertex v = findMin(needsToBeVisited);

      v.known = true;
      count++;

      // Loop through each vertex adjacent to v
      for (Edge w : v.adjacentEdges) {

        if (!w.target.known) {
          
          // Calculate distance from parent
          Double cvw = v.distance + cost(w);
          
          // Update vertex distance with new value if necessary
          // and set the prev (path)
          if (cvw < w.target.distance) {
            w.target.distance = cvw;
            w.target.prev = v;
            needsToBeVisited.add(w.target);
          }
        }
      }
    }
  }


  /**
   * Calculate Cost 
   * 
   * @param w
   *          (Edge) Edge distance to be calculated
   *
   * @return c
   *          (Double) Cost after calculation
   */
  private double cost(Edge w) {
    Double c = 0.0;
    
    c = w.source.distance + w.distance;

    return c;    
  }

  /**
   * Find Minimum Vertex 
   * 
   * @param list
   *          (LinkedList<Vertex>) Edge distance to be calculated
   *
   * @return minVertex
   *          (Vertex) Vertex with lshortest distance
   */
  private Vertex findMin (LinkedList<Vertex> list) {

    Vertex minVertex = list.getFirst();

    // Find min value in the list
    for (int i = 0; i<list.size(); i++) {
      if (list.get(i).distance < minVertex.distance) {
        minVertex = list.get(i);
      }
    }

    // Perform remove so that this value is not considered in the future
    list.remove(minVertex);

    return minVertex;
  }


  /**
   * Returns a list of edges for a path from city s to city t. This will be the
   * shortest path from s to t as prescribed by Dijkstra's algorithm
   * 
   * @param s
   *          (String) starting city name
   * @param t
   *          (String) ending city name
   * @return (List<Edge>) list of edges from s to t
   */
  public List<Edge> getDijkstraPath(String s, String t) {
    doDijkstra(s);
    // TODO
    
    List<Edge> path = new LinkedList<>();
    return getDijkstraPath(s, t, path); 
  }


  /**
   * Helper funnction for getDijkstraPath
   * 
   * @param s
   *          (String) starting city name
   * @param t
   *          (String) ending city name
   * @param e
   *          (List<Edge>) list of edges being collected
   *  
   * @return (List<Edge>) list of edges from s to t
   */
  private List<Edge> getDijkstraPath(String s, String t, List<Edge> e){

    Vertex v = getVertex(t);

    if (v.prev != null) {

      getDijkstraPath(s, v.prev.name, e );

      // Loop through each adjacent edge to extract the
      // desired edge for the list.
      for (Edge w : v.prev.adjacentEdges) {

        // Compare source vertex (from adjacent edge) to
        // its parent vertex
        if (w.source.equals(v.prev) && w.target.equals(v)) {
          e.add(w);
        }
      }

    }

    return e;
  }
  

  // STUDENT CODE ENDS HERE

  /**
   * Prints out the adjacency list of the dijkstra for debugging
   */
  public void printAdjacencyList() {
    for (String u : vertexNames.keySet()) {
      StringBuilder sb = new StringBuilder();
      sb.append(u);
      sb.append(" -> [ ");
      for (Edge e : vertexNames.get(u).adjacentEdges) {
        sb.append(e.target.name);
        sb.append("(");
        sb.append(e.distance);
        sb.append(") ");
      }
      sb.append("]");
      System.out.println(sb.toString());
    }
  }

  /** 
   * A main method that illustrates how the GUI uses Dijkstra.java to 
   * read a map and represent it as a graph. 
   * You can modify this method to test your code on the command line. 
   */
  public static void main(String[] argv) throws IOException {
    String vertexFile = "cityxy.txt"; 
    String edgeFile = "citypairs.txt";

    Dijkstra dijkstra = new Dijkstra();
    String line;

    // Read in the vertices
    BufferedReader vertexFileBr = new BufferedReader(new FileReader(vertexFile));
    while ((line = vertexFileBr.readLine()) != null) {
      String[] parts = line.split(",");
      if (parts.length != 3) {
        vertexFileBr.close();
        throw new IOException("Invalid line in vertex file " + line);
      }
      String cityname = parts[0];
      int x = Integer.valueOf(parts[1]);
      int y = Integer.valueOf(parts[2]);
      Vertex vertex = new Vertex(cityname, x, y);
      dijkstra.addVertex(vertex);
    }
    vertexFileBr.close();

    BufferedReader edgeFileBr = new BufferedReader(new FileReader(edgeFile));
    while ((line = edgeFileBr.readLine()) != null) {
      String[] parts = line.split(",");
      if (parts.length != 3) {
        edgeFileBr.close();
        throw new IOException("Invalid line in edge file " + line);
      }
      dijkstra.addUndirectedEdge(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
    edgeFileBr.close();

    // Compute distances. 
    // This is what happens when you click on the "Compute All Euclidean Distances" button.
    System.out.println("Print Euclidean Distances:");
    dijkstra.computeAllEuclideanDistances();
    
    // print out an adjacency list representation of the graph
    //dijkstra.printAdjacencyList();

   // This is what happens when you click on the "Draw Dijkstra's Path" button.

   // In the GUI, these are set through the drop-down menus.
   String startCity = "SanFrancisco";
   String endCity = "Boston";

   // Get weighted shortest path between start and end city.
   List<Edge> path = dijkstra.getDijkstraPath(startCity, endCity);

   System.out.print("Shortest path between "+startCity+" and "+endCity+": ");
   System.out.println(path);
  }

}
