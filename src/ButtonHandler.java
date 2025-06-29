import javax.swing.*;
import java.awt.*;

public class ButtonHandler {

    private JButton turnAroundCardButton = turnAroundCard();
    private JButton iKnowButton = IKnow();
    private JButton iDontKnowButton = IDontKnow();
    private JButton neuesKarteisetAnlegenButton = neuesKarteisetAnlegen();
    private JButton karteisetWechsenButton = karteisetWechsen();



    private JButton turnAroundCard(){
        JButton turnAroundCard = new JButton("Karteikarte umdrehen");
        turnAroundCard.setSize(100,50);
        return turnAroundCard;
    }

    private JButton IKnow(){
        JButton iKnow = new JButton("Bekannt");
        iKnow.setSize(100,50);
        return iKnow;
    }

    private JButton IDontKnow(){
        JButton iDontKnow = new JButton("Nicht bekannt");
        iDontKnow.setSize(100,50);
        return iDontKnow;
    }

    private JButton neuesKarteisetAnlegen(){
        JButton neuesKarteisetAnlegen = new JButton("Karteikarte erstellen");
        neuesKarteisetAnlegen.setSize(100,50);
        neuesKarteisetAnlegen.setBackground(Color.black);
        neuesKarteisetAnlegen.setForeground(Color.white);
        return neuesKarteisetAnlegen;
    }

    private JButton karteisetWechsen(){
        JButton karteisetWechsen = new JButton("Sets verwalten");
        karteisetWechsen.setSize(100,50);
        karteisetWechsen.setBackground(Color.black);
        karteisetWechsen.setForeground(Color.white);
        return karteisetWechsen;
    }

    public JButton absenden(){
        JButton absenden = new JButton("Absenden");
        absenden.setSize(100,50);
        return absenden;
    }

    public JButton home() {
        JButton home = new JButton("HOME");
        home.setSize(100, 50);
        home.setBackground(Color.black);
        home.setForeground(Color.white);
        return home;
    }



        public JButton getTurnAroundCardButton() {
            turnAroundCardButton.setBackground(Color.black);
            turnAroundCardButton.setForeground(Color.white);
        return turnAroundCardButton;
    }

    public JButton getiKnowButton() {
        iKnowButton.setBackground(Color.black);
        iKnowButton.setForeground(Color.white);
        return iKnowButton;
    }

    public JButton getiDontKnowButton() {
        iDontKnowButton.setBackground(Color.black);
        iDontKnowButton.setForeground(Color.white);
        return iDontKnowButton;
    }

    public JButton getNeuesKarteisetAnlegenButton() {
        return neuesKarteisetAnlegenButton;
    }

    public JButton getKarteisetWechsenButton() {
        return karteisetWechsenButton;
    }

}
