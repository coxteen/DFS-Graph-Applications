import java.util.ArrayList;
import java.util.Random;

public class Traversals {

    private static ArrayList<Node> U = new ArrayList<>();
    private static ArrayList<Node> V = new ArrayList<>();
    private static ArrayList<Node> W = new ArrayList<>();

    private static ArrayList<Integer> p = new ArrayList<>();
    private static ArrayList<Integer> o = new ArrayList<>();

    private static Integer k;

    private static void initialiseParameters(Graph graph, Node startNode) {

        U.clear();
        V.clear();
        W.clear();

        p = new ArrayList<>(graph.nodes.size());
        o = new ArrayList<>(graph.nodes.size());

        k = 1;
        o.set(startNode.value, 0);

        for (Node node : graph.nodes) {
            if (node != startNode) {
                U.add(node);
                o.set(node.value, Integer.MAX_VALUE);
            }
            else {
                V.add(node);
            }
            p.set(node.value, 0);
        }
    }

    private static Node selectRandomNode(ArrayList<Node> nodes) {
        Random random = new Random();
        int index = random.nextInt(nodes.size());
        return nodes.get(index);
    }

    public static void genericTraversal(Graph graph, Node startNode) {

        System.out.println("Merge!");

//        initialiseParameters(graph, startNode);
//
//        while (!V.isEmpty()) {
//            Node x = selectRandomNode(V);
//
//        }

    }

    public void totalGenericTraversal() {

    }
}
