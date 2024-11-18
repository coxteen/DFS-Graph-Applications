import java.awt.Color;
import java.util.ArrayList;

public class Node {

    public int x, y;
    public int value;

    public ArrayList<Integer> values = new ArrayList<>();

    public Color color = Color.MAGENTA;
    public Color selectedNodeColor = Color.GRAY;

    public int radius = 60;
    public int fontSize = 30;

    public Color defaultColor = Color.MAGENTA;
    public String fontName = "Arial";

    public Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public boolean isClicked(int mouseX, int mouseY) {
        return Math.abs(x - mouseX) < radius / 2 && Math.abs(y - mouseY) < radius / 2;
    }
}
