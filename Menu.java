import java.util.ArrayList;

public class Menu {

    public static int menuLeftLimit = 300;

    private final int bX = 30;
    private final int bY = 40;
    private final int lX = 60;
    private final int lY = 43;

    private final RadioButton isOrientedButton = new RadioButton(bX, bY, "Orientat", lX, lY);
    private final RadioButton gtButton = new RadioButton(bX, bY * 2, "Parcurgere generica", lX, lY * 2);

    public RadioButton getIsOrientedButton() {
        return isOrientedButton;
    }

    public RadioButton getGtButton() {
        return gtButton;
    }

    public ArrayList<RadioButton> getRadioButtons() {
        ArrayList<RadioButton> radioButtons = new ArrayList<>();
        radioButtons.add(isOrientedButton);
        radioButtons.add(gtButton);
        return radioButtons;
    }
}