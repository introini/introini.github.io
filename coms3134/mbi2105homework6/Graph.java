import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collectors;

public class Graph {

  // Keep a fast index to nodes in the map
  private Map<Integer, Vertex> vertexNames;

  /**
   * Construct an empty Graph with a map. The map's key is the name of a vertex
   * and the map's value is the vertex object.
   */
  public Graph() {
    vertexNames = new HashMap<>();
  }

  /**
   * Adds a vertex to the graph. Throws IllegalArgumentException if two vertices
   * with the same name are added.
   * 
   * @param v
   *          (Vertex) vertex to be added to the graph
   */
  public void addVertex(Vertex v) {
    if (vertexNames.containsKey(v.name))
      throw new IllegalArgumentException("Cannot create new vertex with existing name.");
    vertexNames.put(v.name, v);
  }

  /**
   * Gets a collection of all the vertices in the graph
   * 
   * @return (Collection<Vertex>) collection of all the vertices in the graph
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
  public void addEdge(int nameU, int nameV, Double cost) {
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
   * @param name
   *          (String) name of vertex u
   * @param name2
   *          (String) name of vertex v
   * @param cost
   *          (double) cost of the edge between vertex u and v
   */
  public void addUndirectedEdge(int name, int name2, double cost) {
    addEdge(name, name2, cost);
    addEdge(name2, name, cost);
  }


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
    return Math.sqrt(Math.pow(ux - vx, 2) + Math.pow(uy - vy, 2));
  }

  /**
   * Computes euclidean distance between two vertices as described by their
   * coordinates
   * 
   * @param u
   *          (Vertex) vertex u
   * @param v
   *          (Vertex) vertex v
   * @return (double) distance between two vertices
   */
  public double computeEuclideanDistance(Vertex u, Vertex v) {
    return computeEuclideanDistance(u.x, u.y, v.x, v.y);
  }

  /**
   * Calculates the euclidean distance for all edges in the map using the
   * computeEuclideanCost method.
   */
  public void computeAllEuclideanDistances() {
    for (Vertex u : getVertices())
      for (Edge uv : u.adjacentEdges) {
        Vertex v = uv.target;
        uv.distance = computeEuclideanDistance(u.x, u.y, v.x, v.y);
      }
  }



  // STUDENT CODE STARTS HERE

  public void generateRandomVertices(int n) {
    vertexNames = new HashMap<>(); // reset the vertex hashmap
  
    // Your code here...
    Random rnd = new Random();
    for (int i = 0; i < n; i++) {
      Vertex nV = new Vertex(i, rnd.nextInt(99), rnd.nextInt(99));
      addVertex(nV);
    }
  
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        addUndirectedEdge(i, j, computeEuclideanDistance(vertexNames.get(i), vertexNames.get(j)));
      }
    }
    computeAllEuclideanDistances(); // compute distances
  }
    
  // Utility function to find the target vertex with the min distance to it.
  private Vertex findMin(List<Edge> list) {
    Edge min = list.get(0);
    for (Edge w : list) {
      if(!w.target.known && w.distance < min.distance) {
        min = w;
      }
    }
    return min.target;
  }
  /*

  Unable to complete this portion of the problem
  I intended to solve this by following the sugested algorithm from Piazza
  as well as the suggested algorithm found at:
  https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm

  While I believe I understand each part of the algorithm, was not able to find
  a simple way of constructing this. Perhaps this was an issue of trying to 
  find a solution with 1 specific starting point, so as to make sure I
  understood the algo. 

  */
    
  public List<Edge> nearestNeighborTsp() {
    List<Edge> edges = new LinkedList<>();
    List<Vertex> tour = new LinkedList<>();
    
    // Set all vertex distances to infinity and mark as "not visited"
    for(Vertex v : getVertices()) {
      v.known = false;
    }
    
    // Set starting vertex 
 
    Vertex v = vertexNames.get(0);

    // Main Loop
     while (tour.size() != getVertices().size()){
      //Gather all unknown adjacent edges
        for (Edge w : v.adjacentEdges) {
          if (!w.target.known) {
            edges.add(w);
          }
        }
        
        // Find the minimum edge
        v = (findMin(edges));
        v.known = true;
        // add to list of visited vertexes
        tour.add(v);
      }
    
    return null;
  }

  /*

  Unable to complete this portion of the problem

  */
  
  public List<Edge> bruteForceTsp() {
    
    return null; // replace this line
  }
  
  public List<Integer> permutation(){
    int size =  getVertices().size();
    LinkedList<Integer> p = new LinkedList<>();
    int[] vNum = new int[size];
    
    return null;
  }
    
  
  
  
  // STUDENT CODE ENDS HERE



  /**
   * Prints out the adjacency list of the graph for debugging
   */
  public void printAdjacencyList() {
    for (int u : vertexNames.keySet()) {
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
}
