import java.util.ArrayList;

public class Graph {

    public ArrayList<Node> nodes = new ArrayList<>();
    public ArrayList<Edge> edges = new ArrayList<>();

    public Node selectedNode = null;

    public static boolean isOriented = false;

    public Boolean isOverlapping(Node node) {
        for (Node list_node : nodes) {
            if(node != list_node && Math.abs(list_node.x - node.x) <= Node.radius && Math.abs(list_node.y - node.y) <= Node.radius) {
                return true;
            }
        }
        return false;
    }

    public void addNode(int x, int y) {
        Node node = new Node(x, y, nodes.size() + 1);
        if(!isOverlapping(node)) {
            nodes.add(node);
        }
    }

    public void addEdge(Node start, Node end) {
        if(start == end) {
            return;
        }
        for(Edge edge : edges) {
            if ((edge.startNode == start && edge.endNode == end) ||
                    (!Graph.isOriented && edge.startNode == end && edge.endNode == start)) {
                return;
            }
        }
        edges.add(new Edge(start, end));
    }
}
