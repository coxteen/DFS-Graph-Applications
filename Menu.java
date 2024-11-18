import java.util.ArrayList;

public class Menu {

    public static int menuLeftLimit = 400;

    private final int bX = 30;
    private final int bY = 40;
    private final int lX = 60;
    private final int lY = 43;

    private final RadioButton isOrientedButton = new RadioButton(
            bX, bY, "Orientat", lX, lY);
    private final RadioButton relatedComponentsButton = new RadioButton(
            bX, bY * 2, "Componente conexe", lX, lY * 2);
    // private final RadioButton strongRelatedComponentsButton = new RadioButton(
            // bX, bY * 3, "Componente tare conexe", lX, lY * 3);
    private final RadioButton findRootButton = new RadioButton(
            bX, bY * 4, "Gasire radacina", lX, lY * 4);

    public RadioButton getIsOrientedButton() {
        return isOrientedButton;
    }

    public RadioButton getRelatedComponentsButton() {
        return relatedComponentsButton;
    }

    // public RadioButton getStrongRelatedComponentsButton() {return strongRelatedComponentsButton;}

    public RadioButton getFindRootButton() { return findRootButton; }

    public ArrayList<RadioButton> getRadioButtons() {
        ArrayList<RadioButton> radioButtons = new ArrayList<>();
        radioButtons.add(isOrientedButton);
        radioButtons.add(relatedComponentsButton);
        // radioButtons.add(strongRelatedComponentsButton);
        radioButtons.add(findRootButton);
        return radioButtons;
    }
}
