import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

    public Graph() {

    }

     public ArrayList<Node> nodes = new ArrayList<>();
     public ArrayList<Edge> edges = new ArrayList<>();

     public HashMap<Node, List<Node>> adjacentList = new HashMap<>();
     public HashMap<Node, List<Node>> orientedAdjacentList = new HashMap<>();

     public Node selectedNode = null;

     public boolean isOriented = false;

     public Boolean isOverlapping(Node node) {
        for (Node list_node : nodes) {
            if(node != list_node && Math.abs(list_node.x - node.x) <= node.radius && Math.abs(list_node.y - node.y) <= node.radius) {
                return true;
            }
            if(node.x <= Menu.menuLeftLimit + node.radius / 2) {
                return true;
            }
        }
        return false;
     }

     private Integer findValue() {
         if (nodes.isEmpty()) {
             return 1;
         }
         ArrayList<Integer> lostValues = new ArrayList<>();
         for (Node node : nodes) {
             lostValues.add(node.value);
         }
         lostValues.sort(Integer::compareTo);
         for (int i = 0; i < lostValues.size(); ++i) {
             if (lostValues.get(i) != i + 1) {
                 return i + 1;
             }
         }
         return nodes.size() + 1;
     }

     public void addNode(int x, int y) {
         Integer nodeValue = findValue();
         Node node = new Node(x, y, nodeValue);
         if(!isOverlapping(node)) {
             nodes.add(node);
             adjacentList.put(node, new ArrayList<>());
             orientedAdjacentList.put(node, new ArrayList<>());
         }
     }

     private void remakeAdjacentLists(Node nodeToRemove) {
         adjacentList.remove(nodeToRemove);
         orientedAdjacentList.remove(nodeToRemove);
         for (Node node : nodes) {
             if (node != nodeToRemove) {
                 adjacentList.get(node).remove(nodeToRemove);
                 orientedAdjacentList.get(node).add(nodeToRemove);
             }
         }
     }

     public void eraseNode(int x, int y) {
         Node nodeToRemove = null;
         for (Node node : nodes) {
             if (node.isClicked(x, y)) {
                 nodeToRemove = node;
                 break;
             }
         }
         if (nodeToRemove != null) {
             remakeAdjacentLists(nodeToRemove);
             ArrayList<Edge> edgesToRemove = new ArrayList<>();
             for (Edge edge : edges) {
                 if (edge.startNode == nodeToRemove || edge.endNode == nodeToRemove) {
                     edgesToRemove.add(edge);
                 }
             }
             for (Edge edge : edgesToRemove) {
                 edges.remove(edge);
             }
             nodes.remove(nodeToRemove);
         }
     }

     public void addEdge(Node start, Node end) {
         if(start == end) {
             return;
         }
         for(Edge edge : edges) {
             if ((edge.startNode == start && edge.endNode == end) ||
                    (!isOriented && edge.startNode == end && edge.endNode == start)) {
                 return;
             }
         }
         edges.add(new Edge(start, end));

         adjacentList.get(start).add(end);
         adjacentList.get(end).add(start);
         orientedAdjacentList.get(start).add(end);
     }

     public void switchGraphType() {
        isOriented = !isOriented;
    }
}
