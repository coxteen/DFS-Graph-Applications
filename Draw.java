import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Font;
import java.util.ArrayList;

public class Draw {

    private static void drawArrows(Graphics2D g2d, Edge edge) {
        int deltaX = edge.endNode.x - edge.startNode.x;
        int deltaY = edge.endNode.y - edge.startNode.y;

        double angle = Math.atan2(deltaY, deltaX);

        double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        double[] directionVector = {deltaX / magnitude, deltaY / magnitude};

        int xArrow1 = (int) (edge.endNode.x - edge.arrowLength * Math.cos(angle - Math.PI / 6));
        int yArrow1 = (int) (edge.endNode.y - edge.arrowLength * Math.sin(angle - Math.PI / 6));
        int xArrow2 = (int) (edge.endNode.x - edge.arrowLength * Math.cos(angle + Math.PI / 6));
        int yArrow2 = (int) (edge.endNode.y - edge.arrowLength * Math.sin(angle + Math.PI / 6));

        int differenceX = (int) (directionVector[0] * edge.startNode.radius / 2);
        int differenceY = (int) (directionVector[1] * edge.startNode.radius / 2);

        g2d.drawLine(edge.endNode.x - differenceX, edge.endNode.y - differenceY, xArrow1 - differenceX, yArrow1 - differenceY);
        g2d.drawLine(edge.endNode.x - differenceX, edge.endNode.y - differenceY, xArrow2 - differenceX, yArrow2 - differenceY);
    }

    private static void drawEdge(Graphics2D g2d, Edge edge, Graph graph) {
        g2d.setColor(edge.edgeColor);
        g2d.setStroke(new BasicStroke(edge.lineWidth));
        g2d.drawLine(edge.startNode.x, edge.startNode.y, edge.endNode.x, edge.endNode.y);

        if (graph.isOriented) {
            drawArrows(g2d, edge);
        }
    }

    private static void drawNode(Graphics2D g2d, Node node, Node selected_node) {
        if (node == selected_node) {
            g2d.setColor(node.selectedNodeColor);
        }
        else {
            g2d.setColor(node.color);
        }
        if (node.values.isEmpty()) {
            g2d.fillOval(node.x - node.radius / 2, node.y - node.radius / 2, node.radius, node.radius);
        }
        else {
            g2d.fillOval(node.x - node.radius / 2, node.y - node.radius / 2, node.radius * node.values.size(), node.radius);
        }
    }

    private static void drawNumber(Graphics2D g2d, Node node) {
        Font font = new Font(node.fontName, Font.BOLD, node.fontSize);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        if (node.values.isEmpty()) {
            if (node.value < 10) {
                g2d.drawString(String.valueOf(node.value), node.x - 8, node.y + 9);
            }
            else {
                g2d.drawString(String.valueOf(node.value), node.x - 16, node.y + 9);
            }
        }
        else {
            String label = "";
            for (int i = 0; i < node.values.size() - 1; i++) {
                label += node.values.get(i) + ",";
            }
            label += node.values.get(node.values.getLast());
            g2d.drawString(label, node.x - 8, node.y + 9);
        }
    }

    private static void drawButton(Graphics2D g2d, RadioButton button) {
        g2d.setStroke(new BasicStroke(button.strokeWidth));
        g2d.drawOval(button.buttonX - button.radius / 2, button.buttonY - button.radius / 2, button.radius, button.radius);

        if (button.selected) {
            g2d.setColor(Color.BLACK);
            g2d.fillOval(button.buttonX - button.radius / 4, button.buttonY - button.radius / 4, button.radius / 2, button.radius / 2);
        }
        g2d.setFont(new Font(button.labelFont, Font.BOLD, button.labelFontSize));
        g2d.setColor(Color.BLACK);
        g2d.drawString(button.label, button.labelX, button.labelY);
    }

    private static void drawMenu(Graphics2D g2d) {
        g2d.drawLine(
                Menu.menuLeftLimit,0,
                Menu.menuLeftLimit, Window.windowHeight
        );
    }

    public static void draw(Graphics2D g2d, Graph graph, ArrayList<RadioButton> buttons) {
        for (Edge edge : graph.edges) {
            drawEdge(g2d, edge, graph);
        }
        for (Node node : graph.nodes) {
            drawNode(g2d, node, graph.selectedNode);
            drawNumber(g2d, node);
        }
        for (RadioButton button : buttons) {
            drawButton(g2d, button);
        }
        drawMenu(g2d);
    }
}
