import java.awt.*;

public class Layouts {

    private final CardLayout mainCardLayout = new CardLayout();
    private final GridLayout mainButtonGridLayout = new GridLayout();;
    private final GridLayout mainTopGridLayout = new GridLayout();

    public Layouts() {
        mainButtonGridLayout.setColumns(3);
        mainTopGridLayout.setRows(1);
        mainTopGridLayout.setColumns(2);
    }

    public CardLayout mainCardLayout(){
        return mainCardLayout;
    }

    public GridLayout mainButtonGridLayout(){
        return mainButtonGridLayout;
    }

    public GridLayout mainTopGridLayout(){
        return mainTopGridLayout;
    }

    public GridLayout newEntryGridLayout(){
        GridLayout newEntryGridLayout = new GridLayout();
        newEntryGridLayout.setColumns(2);
        newEntryGridLayout.setRows(6);
        return newEntryGridLayout;
    }
}
