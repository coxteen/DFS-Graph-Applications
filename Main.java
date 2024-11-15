import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graph Builder");
        Window window = new Window();
        frame.add(window);
        frame.setSize(Window.windowWidth, Window.windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
