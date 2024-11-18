import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Traversals {

    private final ArrayList<Node> U = new ArrayList<>();
    private final ArrayList<Node> V = new ArrayList<>();
    private final ArrayList<Node> W = new ArrayList<>();
    private ArrayList<Node> nPrim = new ArrayList<>();

    private final HashMap<Node, Node> p = new HashMap<>();

    private void initialiseParameters(Graph graph, Node startNode) {

        U.clear();
        V.clear();
        W.clear();
        p.clear();

        for (Node node : graph.nodes) {
            if (node == startNode) {
                V.add(node);
                nPrim.add(node);
                p.put(startNode, null);
            }
            else {
                U.add(node);
            }
        }
    }

    private boolean noInnerDegreeGreaterThanOne(Graph graph) {
        for (Node node : graph.nodes) {
            int innerDegree = 0;
            for (Edge edge : graph.edges) {
                if (edge.endNode == node) {
                    innerDegree++;
                    if (innerDegree > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean hasCycle(Graph graph) {
        HashMap<Node, Boolean> visited = new HashMap<>();
        for (Node node : graph.nodes) {
            visited.put(node, false);
        }

        for (Node startNode : graph.nodes) {
            if (!visited.get(startNode)) {
                if (hasCycleIterative(graph, startNode, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleIterative(Graph graph, Node startNode, HashMap<Node, Boolean> visited) {

        initialiseParameters(graph, startNode);

        while (!V.isEmpty()) {
            Node x = V.getLast();
            V.remove(x);
            if (!visited.get(x)) {
                visited.put(x, true);
            }
            for (Node y : graph.adjacentList.get(x)) {
                if (!visited.get(y)) {
                    V.add(y);
                    p.put(y, x);
                }
                else if (!y.equals(p.get(x))) {
                    return true;
                }
            }
        }

        return false;
    }


    public boolean isTree(Graph graph) {
        return relatedComponents(graph).size() == 1 && !hasCycle(graph) && noInnerDegreeGreaterThanOne(graph);
    }

    // Gasire radacina
    public Node findRoot(Graph graph) {
        if (graph.isOriented) {
            HashMap<Node, Integer> inDegree = new HashMap<>();
            for (Node node : graph.nodes) {
                inDegree.put(node, 0);
            }
            for (Edge edge : graph.edges) {
                Node endNode = edge.endNode;
                inDegree.put(endNode, inDegree.get(endNode) + 1);
            }
            for (Node node : graph.nodes) {
                if (inDegree.get(node) == 0) {
                    return node;
                }
            }
            return null;
        }
        return null;
    }

    private Color pickRandomColor() {
        Random rand = new Random();
        int red = 50 + rand.nextInt(206);
        int green = 50 + rand.nextInt(206);
        int blue = 50 + rand.nextInt(206);
        return new Color(red, green, blue);
    }

    public void resetGraphColors(Graph graph) {
        for (Node node : graph.nodes) {
            node.color = node.defaultColor;
        }
    }

    public void paintRelatedComponents(ArrayList<ArrayList<Node>> components) {
        for (ArrayList<Node> component : components) {
            Color color = pickRandomColor();
            for (Node node : component) {
                node.color = color;
            }
        }
    }

    // Componente conexe
    public ArrayList<ArrayList<Node>> relatedComponents(Graph graph) {

        ArrayList<ArrayList<Node>> components = new ArrayList<>();

        initialiseParameters(graph, graph.nodes.getLast());

        while (W.size() != graph.nodes.size()) {
            while (!V.isEmpty()) {
                Node x = V.getLast();
                for (Node y : graph.adjacentList.get(x)) {
                    if (U.contains(y)) {
                        U.remove(y);
                        V.add(y);
                        nPrim.add(y);
                    }
                }
                V.remove(x);
                W.add(x);
            }

            if (!nPrim.isEmpty()) {
                components.add(nPrim);
            }

            if (!U.isEmpty()) {
                Node startNode = U.getLast();
                U.remove(startNode);
                V.add(startNode);
                nPrim = new ArrayList<>();
                nPrim.add(startNode);
            }
        }

        return components;
    }

//    public void ptdf(Graph graph) {
//
//    }
//
//    public void graphInversion(Graph graph) {
//        for (Edge edge : graph.edges) {
//            edge.invertEdge();
//        }
//    }
//
//    private Graph groupNodes(ArrayList<ArrayList<Node>> components) {
//        Graph graph = new Graph();
//        for (ArrayList<Node> strongComponent : components) {
//            Node newNode = new Node();
//            for (Node node : strongComponent) {
//                newNode.x = newNode.x + node.x;
//                newNode.y = newNode.y + node.y;
//                newNode.values.add(node.value);
//            }
//            newNode.color = pickRandomColor();
//            newNode.x = newNode.x / strongComponent.size();
//            newNode.y = newNode.y / strongComponent.size();
//            graph.nodes.add(newNode);
//        }
//
//        return graph;
//    }
}
