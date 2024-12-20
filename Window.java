import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Window extends JPanel implements MouseListener, MouseMotionListener {

    public static int windowHeight = 720;
    public static int windowWidth = 1280;

    private final Menu menu = new Menu();

    private final ArrayList<RadioButton> buttons = menu.getRadioButtons();

    private final Graph graph = new Graph();

    private boolean dragging = false;
    private Node draggedNode = null;
    private int dragOffsetX, dragOffsetY;
    private int initialPositionX, initialPositionY;

    public Window() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Draw.draw(g2d, graph, buttons);
    }

    public void applyAlgorithm(RadioButton button) {
        Traversals traversals = new Traversals();

        if (button == menu.getRelatedComponentsButton()) {
            if (button.selected) {
                traversals.paintRelatedComponents(traversals.relatedComponents(graph));
            }
            else {
                traversals.resetGraphColors(graph);
            }
        }

//        if (button == menu.getStrongRelatedComponentsButton()) {
//            if (button.selected) {
//                graph = traversals.strongRelatedComponents(graph);
//            }
//            else {
//                graph = initialGraph;
//            }
//        }

        if (button == menu.getFindRootButton()) {
            if (!traversals.isTree(graph)) {
                JOptionPane.showMessageDialog(
                        null,
                        "Graful nu este arbore!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            else {
                Node root = traversals.findRoot(graph);
                if (button.selected) {
                    root.color = Color.RED;
                }
                else {
                    root.color = root.defaultColor;
                }
            }
        }
    }

    private void left_click_action(MouseEvent e){
        for (RadioButton button : buttons) {
            if (button.isClicked(e.getX(), e.getY())) {
                button.switchButtonState();
                if (button == menu.getIsOrientedButton()) {
                    graph.switchGraphType();
                }
                else {
                    applyAlgorithm(button);
                }
                return;
            }
        }
        for (Node node : graph.nodes) {
            if (node.isClicked(e.getX(), e.getY())) {
                if (graph.selectedNode == null) {
                    graph.selectedNode = node;
                } else {
                    graph.addEdge(graph.selectedNode, node);
                    graph.selectedNode = null;
                }
                return;
            }
        }
        graph.addNode(e.getX(), e.getY());
        graph.selectedNode = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            left_click_action(e);
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            graph.eraseNode(e.getX(), e.getY());
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Node node : graph.nodes) {
            if (node.isClicked(e.getX(), e.getY())) {
                dragging = true;
                draggedNode = node;
                dragOffsetX = e.getX() - node.x;
                dragOffsetY = e.getY() - node.y;

                initialPositionX = node.x;
                initialPositionY = node.y;
                return;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragging && draggedNode != null && graph.isOverlapping(draggedNode)) {
            draggedNode.x = initialPositionX;
            draggedNode.y = initialPositionY;
        }
        dragging = false;
        draggedNode = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging && draggedNode != null) {
            draggedNode.x = e.getX() - dragOffsetX;
            draggedNode.y = e.getY() - dragOffsetY;
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
