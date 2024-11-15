public class RadioButton {

    public int buttonX;
    public int buttonY;
    public int radius = 30;
    public int strokeWidth = 3;

    public String label;
    public int labelX;
    public int labelY;
    public int labelFontSize = 16;
    public String labelFont = "Arial";

    public boolean selected = false;

    public RadioButton(int buttonX, int buttonY, String label, int labelX, int labelY) {
        this.buttonX = buttonX;
        this.buttonY = buttonY;
        this.label = label;
        this.labelX = labelX;
        this.labelY = labelY;
    }

    public boolean isClicked(int mouseX, int mouseY) {
        return Math.abs(buttonX - mouseX) < radius / 2 && Math.abs(buttonY - mouseY) < radius / 2;
    }

    public void switchButtonState() {
        selected = !selected;
    }
}
