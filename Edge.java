import java.awt.Color;

public class Edge {

    public Node startNode, endNode;
    public Color edgeColor = Color.BLACK;
    public int lineWidth = 3;
    public int arrowLength = 15;

    public Edge(Node node1, Node node2) {
        this.startNode = node1;
        this.endNode = node2;
    }
}
