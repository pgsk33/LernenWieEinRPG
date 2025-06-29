import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UIelements extends ImageService implements ActionListener {

    public static final String ADDENTRY_PANEL = "addentryPanel";
    final JComboBox<String> waehleKarteiBox;
    final JComboBox<String> waehleKarteiSetBoxfürAddEntry;
    private final Login login;
    Gegner currentEnemy;
    Spieler currentPlayer;
    Layouts layouts = new Layouts();
    JFrame mainframe;
    JPanel mainpanel;
    JPanel cardpanel;
    JPanel karteisetwechselPanel;
    JPanel addentryPanel;
    CardLayout cardlayout;
    JPanel cardheropanel = new JPanel();
    JPanel textPanel;
    JPanel heroPanel;
    JPanel hero2Panel;
    JPanel upperRowPanel;
    JPanel mainLowerButtonRow;
    JPanel gegnerPanel;
    JLabel frageLabel = new JLabel("Frage:");
    JTextField frageField = new JTextField();
    JLabel antwortLabel = new JLabel("Antwort:");
    JTextField antwortField = new JTextField();
    JLabel neuesKarteisetLabel = new JLabel("Name des neuen Kartensets:");
    JTextField neuesKarteisetField = new JTextField();
    JTextField currentLevel = new JTextField();
    JLabel karteiSetLabel = new JLabel("Kartenset auswählen:");
    JLabel wähleKarteiLabel = new JLabel("Kartenset:");
    JButton speichernButton = new JButton("Karte speichern");
    JButton neusSetSpeichern = new JButton("Neues Set Speichern");
    JButton setLöschenButton = new JButton("Set löschen");
    JMenuBar menuBar;
    JTextPane karteiText;
    ButtonHandler buttonHandler;
    JButton neuesSetButton;
    JButton karteisetwechselButton;
    JButton neuesKarteisetAnlegenButton;
    JButton turnAroundCardButton;
    JButton iKnowButton;
    JButton iDontKnowButton;
    JButton homeButton;
    Karteikarten karteikarten;
    Font font = new Font("Arial", Font.BOLD, 22);
    private String currentSet;

    public UIelements(Karteikarten karteikarten, Login login, Spieler player) {
        this.karteikarten = karteikarten;
        this.login = login;
        waehleKarteiBox = getBox();
        waehleKarteiSetBoxfürAddEntry = getBox();
        setCurrentSet();
        karteiText = karteiTextPane();
        karteikarten.kartenausgabe(Integer.parseInt(currentSet));
        setKarteiText(karteikarten.frageKarte);
        currentPlayer = player;
        currentEnemy = karteikarten.getRandomGegner();
        currentLevel.setText("Level: " + currentPlayer.playerLevel);
        currentLevel.setOpaque(false);
        currentLevel.setForeground(Color.white);
        currentLevel.setEditable(false);
        waehleKarteiBox.addActionListener(this);


        initGui();

    }

    public void initGui() {
        buttonHandler = new ButtonHandler();
        currentEnemy = karteikarten.getRandomGegner();
        gegnerPanel = getJPanelwithbackgroundForEnemyPanel(Integer.parseInt(currentEnemy.gegnerID));
        karteisetwechselButton = buttonHandler.getKarteisetWechsenButton();
        neuesKarteisetAnlegenButton = buttonHandler.getNeuesKarteisetAnlegenButton();
        turnAroundCardButton = buttonHandler.getTurnAroundCardButton();
        iKnowButton = buttonHandler.getiKnowButton();
        iDontKnowButton = buttonHandler.getiDontKnowButton();
        homeButton = buttonHandler.home();
        neuesSetButton = new JButton("Neues Set");
        mainLowerButtonRow = lowerButtonrow();

        karteisetwechselPanel = new JPanel();
        textPanel = TextPanel();

        cardlayout = new CardLayout();
        cardheropanel = new JPanel(cardlayout);
        heroPanel = heropanel(1);
        hero2Panel = heropanel(2);
        cardheropanel.add(heroPanel, "1");
        cardheropanel.add(hero2Panel, "2");
        cardlayout.show(cardheropanel, "1");

        upperRowPanel = upperRowPanel();
        menuBar = getJMenuBar();
        setCurrentSet();


        karteisetwechselPanel = karteisetwechselPanel();
        addentryPanel = addentryPanel();
        mainpanel = MainPanel();
        cardpanel = createCardPanel();
        mainframe = Mainframe();
        mainframe.add(cardpanel);
        buttonHandler = new ButtonHandler();
        neuesKarteisetAnlegenButton.addActionListener(this);
        karteisetwechselButton.addActionListener(this);
        turnAroundCardButton.addActionListener(this);
        iKnowButton.addActionListener(this);
        iDontKnowButton.addActionListener(this);
        speichernButton.addActionListener(this);
        homeButton.addActionListener(this);
        neuesSetButton.addActionListener(this);
        neusSetSpeichern.addActionListener(this);
        iDontKnowButton.setVisible(false);
        iKnowButton.setVisible(false);
        turnAroundCardButton.setVisible(true);

    }

    private JComboBox<String> getBox() {
        return new JComboBox<>(karteikarten.getAllSetNamesFromUser(login.getUserID()));
    }


    private JPanel karteisetwechselPanel() {
        karteisetwechselPanel = getJPanelForNormalBackground(karteisetwechselPanel);
        karteisetwechselPanel.setLayout(new GridLayout(4, 2));
        karteisetwechselPanel.add(wähleKarteiLabel);
        wähleKarteiLabel.setOpaque(false);
        wähleKarteiLabel.setForeground(Color.white);
        wähleKarteiLabel.setFont(font);
        karteisetwechselPanel.add(waehleKarteiBox);
        waehleKarteiBox.setBackground(Color.darkGray);
        waehleKarteiBox.setForeground(Color.white);
        waehleKarteiBox.setFont(font);
        waehleKarteiBox.setPreferredSize(new Dimension(400, 50));
        karteisetwechselPanel.add(neuesSetButton);
        neuesSetButton.setBackground(Color.black);
        neuesSetButton.setForeground(Color.white);
        neuesSetButton.setFont(font);
        karteisetwechselPanel.add(setLöschenButton);
        setLöschenButton.setBackground(Color.black);
        setLöschenButton.setForeground(Color.white);
        setLöschenButton.setFont(font);
        setLöschenButton.addActionListener(this);
        karteisetwechselPanel.add(neuesKarteisetLabel);
        neuesKarteisetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        neuesKarteisetLabel.setForeground(Color.white);
        neuesKarteisetLabel.setFont(font);
        neuesKarteisetLabel.setBackground(Color.black);
        neuesKarteisetLabel.setVisible(false);
        karteisetwechselPanel.add(neuesKarteisetField);
        neuesKarteisetField.setBackground(Color.black);
        neuesKarteisetField.setForeground(Color.white);
        neuesKarteisetField.setFont(font);
        neuesKarteisetField.setVisible(false);
        karteisetwechselPanel.add(neusSetSpeichern);
        neusSetSpeichern.setBackground(Color.black);
        neusSetSpeichern.setForeground(Color.white);
        neusSetSpeichern.setFont(font);
        neusSetSpeichern.setVisible(false);
        karteisetwechselPanel.add(karteiSetLabel);

        karteisetwechselPanel.add(waehleKarteiSetBoxfürAddEntry);
        return karteisetwechselPanel;
    }

    protected JFrame Mainframe() {
        JFrame mainFrame = new JFrame("Learning like a  RPG");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(720, 800);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setBackground(Color.black);
        return mainFrame;
    }

    public void setCurrentSet() {
        int selectedBox = waehleKarteiBox.getSelectedIndex();
        String userID = login.getUserID();
        System.out.println(userID);
        String[] allSetIDsFromUser = karteikarten.getAllSetIDsFromUser(userID);
        System.out.println(allSetIDsFromUser.length);
        if (selectedBox >= 0)
            currentSet = allSetIDsFromUser[selectedBox];
    }

    public void setGegner(int gegner) {
        gegnerPanel = getJPanelwithbackgroundForEnemyPanel(gegner);

    }

    private JPanel createCardPanel() {
        JPanel jPanel = getJPanelForNormalBackground(new JPanel());
        jPanel.setLayout(layouts.mainCardLayout());
        jPanel.add(mainpanel, "MainPanel");
        jPanel.add(addentryPanel, ADDENTRY_PANEL);
        jPanel.add(karteisetwechselPanel, "switchSetPanel");
        return jPanel;
    }


    protected JPanel MainPanel() {
        JPanel mainPanel = getJPanelwithbackgroundForMainPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(upperRowPanel());
        mainPanel.add(Box.createRigidArea(new Dimension(10, 100)));
        mainPanel.add(TextPanel());
        mainPanel.add(lowerButtonrow());
        return mainPanel;
    }

    protected JPanel TextPanel() {
        JPanel textPanel = new JPanel();
        textPanel.setPreferredSize(new Dimension (600,400));
        textPanel.add(karteiText);
        textPanel.setOpaque(false);
        return textPanel;
    }

    protected JTextPane karteiTextPane() {
        JTextPane karteiTextPane = new JTextPane();
        karteiTextPane.setEditable(false);
        karteiTextPane.setOpaque(false);
        karteiTextPane.setPreferredSize(new Dimension(450, 400));
        karteiTextPane.setContentType("text/html");
        int textsize = 21;
        karteiTextPane.setText("<html><div style='text-align:center; font-size:" + textsize + "px; color:white; width:100%; height:100%;'>" + karteikarten.frageKarte + "</div></html>");
        return karteiTextPane;
    }

    public void setKarteiText(String text) {
        int textSize = 17;
        try {
            textSize = (100 / text.length()) + 15;
        } catch (Exception e) {
        }
        karteiText.setText("<html><div style='text-align:center; font-size:" + textSize + "px; color:white; width:100%; height:100%;'>" + text + "</div></html>");
    }

    protected JPanel heropanel(int frame) {
        if (frame == 1){
            JPanel heropanel1 = getJPanelHero1();
            heropanel1.setOpaque(false);
            heropanel1.setPreferredSize(new Dimension(400, 200));
            return heropanel1;
        }
        if (frame == 2){
            JPanel heropanel2 = getJPanelHero2();
            heropanel2.setOpaque(false);
            heropanel2.setPreferredSize(new Dimension(400, 200));
            return heropanel2;
        }
        return null;
    }

    protected JPanel upperRowPanel() {
        JPanel upperRowPanel = new JPanel();
        upperRowPanel.setLayout(layouts.mainTopGridLayout());
        upperRowPanel.add(cardheropanel);
        cardheropanel.setOpaque(false);
        upperRowPanel.add(gegnerPanel);
        upperRowPanel.setPreferredSize(new Dimension(100, 500));
        upperRowPanel.setOpaque(false);
        return upperRowPanel;
    }


    protected JPanel lowerButtonrow() {
        JPanel lowerButtonrow = new JPanel();
        lowerButtonrow.setPreferredSize(new Dimension(1024, 100));
        lowerButtonrow.setLayout(layouts.mainButtonGridLayout());
        lowerButtonrow.add(iKnowButton);
        lowerButtonrow.add(turnAroundCardButton);
        lowerButtonrow.add(iDontKnowButton);
        lowerButtonrow.setBackground(Color.black);
        return lowerButtonrow;
    }


    protected JMenuBar getJMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(homeButton);
        menuBar.add(karteisetwechselButton);
        menuBar.add(neuesKarteisetAnlegenButton);
        menuBar.add(currentLevel);
        menuBar.setOpaque(false);
        return menuBar;
    }

    //karteiset Hinzufügen
    private JPanel addentryPanel() {
        JPanel entryPanel = getJPanelForNormalBackground(new JPanel());
        entryPanel.setLayout(layouts.newEntryGridLayout());
        entryPanel.add(frageLabel);
        frageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frageLabel.setForeground(Color.white);
        frageLabel.setFont(font);
        entryPanel.add(frageField);
        frageField.setBackground(Color.black);
        frageField.setForeground(Color.white);
        entryPanel.add(antwortLabel);
        antwortLabel.setHorizontalAlignment(SwingConstants.CENTER);
        antwortLabel.setForeground(Color.white);
        antwortLabel.setFont(font);
        entryPanel.add(antwortField);
        antwortField.setBackground(Color.black);
        antwortField.setForeground(Color.white);
        entryPanel.add(karteiSetLabel);
        karteiSetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        karteiSetLabel.setForeground(Color.white);
        karteiSetLabel.setFont(font);
        entryPanel.add(waehleKarteiSetBoxfürAddEntry);
        waehleKarteiSetBoxfürAddEntry.setBackground(Color.black);
        waehleKarteiSetBoxfürAddEntry.setForeground(Color.white);
        waehleKarteiSetBoxfürAddEntry.setFont(font);
        entryPanel.add(speichernButton);
        speichernButton.setBackground(Color.black);
        speichernButton.setForeground(Color.white);
        speichernButton.setFont(font);


        return entryPanel;
    }

    protected JTextField karteiText() {
        JTextField karteiText = new JTextField();
        karteiText.setEditable(true);
        return karteiText;
    }


    private void updateComboBox(JComboBox<String> cb) {
        cb.removeAllItems();
        for (String item : karteikarten.getAllSetNamesFromUser(login.getUserID())) {
            cb.addItem(item);
        }
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == neuesKarteisetAnlegenButton) {
            updateComboBox(waehleKarteiSetBoxfürAddEntry);
            System.out.println("Neues Karteiset anlegen Button gedrückt");
            layouts.mainCardLayout().show(cardpanel, "addentryPanel");


        }
        if (e.getSource() == karteisetwechselButton) {
            System.out.println("Karteiset wechseln Button gedrückt");
            layouts.mainCardLayout().show(cardpanel, "switchSetPanel");

            //fertig
        }
        if (e.getSource() == homeButton) {
            System.out.println("Home Button gedrückt");
            layouts.mainCardLayout().show(cardpanel, "MainPanel");
            setCurrentSet();
            karteikarten.kartenausgabe(Integer.parseInt(currentSet));
            setKarteiText(karteikarten.frageKarte);

            //fertig
        }

        if (e.getSource() == iDontKnowButton) {
            System.out.println("Ich weiss nicht gedrückt");
            karteikarten.kartenausgabe(Integer.parseInt(currentSet));
            setKarteiText(karteikarten.frageKarte);
            iDontKnowButton.setVisible(false);
            iKnowButton.setVisible(false);
            turnAroundCardButton.setVisible(true);

            // Funktion fehlt
        }
        if (e.getSource() == iKnowButton) {
            System.out.println("Ich weiss gedrückt");
            karteikarten.kartenausgabe(Integer.parseInt(currentSet));
            setKarteiText(karteikarten.frageKarte);
            iKnowButton.setVisible(false);
            turnAroundCardButton.setVisible(true);
            iDontKnowButton.setVisible(false);
            damageEnemy();
            if (isEnemyDead()) {
                enemyIsDead();
            }
        }
        if (e.getSource() == turnAroundCardButton) {
            System.out.println("Karte umdrehen gedrückt");
            setKarteiText(karteikarten.antwortKarte);
            turnAroundCardButton.setVisible(false);
            iKnowButton.setVisible(true);
            iDontKnowButton.setVisible(true);
        }
        if (e.getSource() == speichernButton) {
            System.out.println("kartei absenden gedrückt");
            karteikarten.karteikarteHinzu(frageField.getText(), antwortField.getText(), karteikarten.getAllSetIDsFromUser(login.getUserID())[waehleKarteiSetBoxfürAddEntry.getSelectedIndex()]);
        }
        if (e.getSource() == neuesSetButton) {
            System.out.println("Neues Set gedrückt");
            neuesKarteisetLabel.setVisible(true);
            neuesKarteisetField.setVisible(true);
            neusSetSpeichern.setVisible(true);

        }
        if (e.getSource() == neusSetSpeichern) {
            System.out.println("Neues Set Speichern gedrückt");
            neuesKarteisetLabel.setVisible(false);
            neuesKarteisetField.setVisible(false);
            neusSetSpeichern.setVisible(false);
            karteikarten.karteisetHinzu(neuesKarteisetField.getText(), login.getUserID());
            updateComboBox(waehleKarteiBox);
            updateComboBox(waehleKarteiSetBoxfürAddEntry);
        }
        if (e.getSource() == setLöschenButton) {
            System.out.println("Set Löschen gedrückt");
            int result = JOptionPane.showConfirmDialog(null, "Möchten sie wirklich das Karteset " + currentSet + " löschen?", "Löschen?", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.out.println("Löschen bestätigt");

                karteikarten.karteikartelöschen(currentSet);
                karteikarten.karteisetlöschen(Integer.parseInt(currentSet));
                updateComboBox(waehleKarteiBox);
                updateComboBox(waehleKarteiSetBoxfürAddEntry);
                setCurrentSet();
            } else {
                System.out.println("Löschen abgebrochen");
            }

        }
        if (e.getSource() == waehleKarteiBox) {
            setCurrentSet();
        }
    }


    public void damageEnemy() {
        currentEnemy.hp = String.valueOf(Integer.parseInt(currentEnemy.hp) - currentPlayer.getSchaden());
        cardlayout.show(cardheropanel, "2");
        // Erstelle einen Timer, der nach 500ms einmalig ausgeführt wird.
        Timer timer = new Timer(500, e -> {
            cardlayout.show(cardheropanel, "1"); // Wechsle zurück zum normalen Frame
        });
        timer.setRepeats(false); // Der Timer soll nur einmal laufen.
        timer.start();
        System.out.println("layout 1");

    }

    public boolean isEnemyDead() {
        return Integer.parseInt(currentEnemy.hp) <= 0;
    }

    public void enemyIsDead() {
        currentPlayer.aktuelleXP = currentPlayer.aktuelleXP + Integer.parseInt(currentEnemy.xp);
        currentPlayer.saveAktuelleXptoDB(currentPlayer.connection);
        currentPlayer.prüfenObLevelUp();
        currentPlayer.savePlayerLevelToDB(currentPlayer.connection);
        currentEnemy = karteikarten.getRandomGegner();
        setGegner(Integer.parseInt(currentEnemy.gegnerID));
        currentLevel.setText("Level: " + currentPlayer.playerLevel);
    }

}

