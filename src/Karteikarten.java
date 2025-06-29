import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;


public class Karteikarten {
    private final int karteiset;
    private String url = "", user = "", passwort = "";
    String antwortKarte="",frageKarte="";

    public Karteikarten(int karteiset, String url, String user, String passwort) {
        this.karteiset = karteiset;
        this.url = url;
        this.user = user;
        this.passwort = passwort;
  /*  try {
        Connection conn = DriverManager.getConnection(url, user, passwort);
    } catch (SQLException e) {
        e.printStackTrace();
    }*/
    }


    public void karteikarteHinzu(String frage, String antwort, String karteiset) {

        if (antwort.length() >= 100){
            JOptionPane.showMessageDialog(null, "Antwort zu lang. MAX 100 Zeichen! Zeichen zuviel: " +(antwort.length() - 100), "Zeichenlimit überschritten", JOptionPane.ERROR_MESSAGE);
        } else if (frage.length() >= 100) {
            JOptionPane.showMessageDialog(null, "Frage zu lang. MAX 100 Zeichen! Zeichen zuviel: " + (frage.length() - 100), "Zeichenlimit überschritten", JOptionPane.ERROR_MESSAGE);
        } else {
            try (Connection conn = DriverManager.getConnection(url, user, passwort);
                 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Kartei VALUES (NULL, ?, ?, ?)")) {

                pstmt.setString(1, frage);
                pstmt.setString(2, antwort);
                pstmt.setString(3, karteiset);
                pstmt.executeUpdate();

                System.out.println("Neue Karteikarte erfolgreich hinzugefügt!");
                JOptionPane.showConfirmDialog(null, "Karteikarte erfolgreich hinzugefügt!", "Gute Arbeit!", JOptionPane.DEFAULT_OPTION);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void karteikarteändern(int kartenNr, String frage, String antwort, String karteiset) {


        try (Connection conn = DriverManager.getConnection(url, user, passwort);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE Kartei SET Frage= ?,  Antwort =? ,  SetNr= ? WHERE KartenNR =" + kartenNr)) {

            pstmt.setString(1, frage);
            pstmt.setString(2, antwort);
            pstmt.setString(3, karteiset);
            pstmt.executeUpdate();

            System.out.println("Karteikarte erfolgreich geändert!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void karteikartelöschen(String karteiset) {


        try (Connection conn = DriverManager.getConnection(url, user, passwort);
             PreparedStatement pstmt = conn.prepareStatement("Delete FROM Kartei  WHERE SetNr = '" + karteiset + "';")) {

            pstmt.executeUpdate();

            System.out.println("Karteikarte erfolgreich gelöscht!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void karteisetHinzu(String setName, String userID) {


        try (Connection conn = DriverManager.getConnection(url, user, passwort);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Karteikartenset VALUES (NULL, ?, ?)")) {

            pstmt.setString(1, setName);
            pstmt.setString(2, userID);
            pstmt.executeUpdate();

            System.out.println("Neue Karteikarte erfolgreich hinzugefügt!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void karteisetändern(int setNR, String setName) {


        try (Connection conn = DriverManager.getConnection(url, user, passwort);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE Karteikartenset SET name= ? WHERE SetNr =" + setNR)) {

            pstmt.setString(1, setName);
            pstmt.executeUpdate();

            System.out.println("Karteiset erfolgreich geändert!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void karteisetlöschen(int setNR) {


        try (Connection conn = DriverManager.getConnection(url, user, passwort);
             PreparedStatement pstmt = conn.prepareStatement("Delete FROM Karteikartenset  WHERE SetNr=" + setNR + ";")) {

            pstmt.executeUpdate();

            System.out.println("Karteiset erfolgreich gelöscht!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void kartenausgabe(int setNR) {
        int zufallliste, zufallausgabeListe;
        try (Connection conn = DriverManager.getConnection(url, user, passwort);
             Statement stm = conn.createStatement()) {

            // Anzahl der Karten holen
            ResultSet result = stm.executeQuery("SELECT COUNT(KartenNR) FROM Kartei WHERE SetNr =" + setNR);
            int menge = result.next() ? result.getInt(1) : 0;
            System.out.println("Anzahl der Karten: " + menge);
            // Kleinste KartenNr holen
            ResultSet minResult = stm.executeQuery("SELECT MIN(KartenNR) FROM Kartei WHERE SetNr =" + setNR);
            int min = minResult.next() ? minResult.getInt(1) : 0;

            // Zufällige KartenNr auswählen
            ArrayList<Integer> liste = new ArrayList<>();
            ResultSet listenEintrag = stm.executeQuery("SELECT KartenNR FROM Kartei WHERE SetNr =" + setNR);
            while (listenEintrag.next()){
                liste.add(listenEintrag.getInt(1));
            }

            zufallliste=((int)(Math.random()*liste.size()))  ;
            zufallausgabeListe=liste.get(zufallliste);

            // Frage abrufen
            ResultSet frage = stm.executeQuery("SELECT Frage FROM Kartei WHERE KartenNR=" + zufallausgabeListe);
            if (frage.next()) {
                frageKarte=frage.getString(1);
                System.out.println("Frage: " + frage.getString(1));
            }

            // Antwort abrufen
            ResultSet antwort = stm.executeQuery("SELECT Antwort FROM Kartei WHERE KartenNR=" + zufallausgabeListe);
            if (antwort.next()) {
                antwortKarte=antwort.getString(1);
                System.out.println("Antwort: " + antwort.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String[] getAllSetIDsFromUser(String userid){
        String[] temparr = null;
        try {
            Connection con = DriverManager.getConnection(url, user, passwort);
            System.out.println("Verbindung erfolgreich hergestellt");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT SetNr,SetName FROM Karteikartenset where userID = '" + userid + "';");
            ArrayList<String> temp = new ArrayList<>();
            while (rs.next()) {
                // This part needs to be fixed to correctly add to the 2D array
                temp.add(rs.getString(1));
            }
            temparr = new String[temp.size()];
            for (int i = 0; i < temp.size(); i++) {
                temparr[i] = temp.get(i);
                System.out.println(temparr[i]);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return temparr;
    }


    public String[] getAllSetNamesFromUser(String userid){
        String[] temparr = null;
        try {
            Connection con = DriverManager.getConnection(url, user, passwort);
            System.out.println("Verbindung erfolgreich hergestellt");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT SetNr,SetName FROM Karteikartenset where userID = '" + userid + "';");
            ArrayList<String> temp = new ArrayList<>();
            while (rs.next()) {
                // This part needs to be fixed to correctly add to the 2D array
                temp.add(rs.getString(2));
            }
            temparr = new String[temp.size()];
            for (int i = 0; i < temp.size(); i++) {
                temparr[i] = temp.get(i);
                System.out.println(temparr[i]);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return temparr;
    }



    public Gegner getRandomGegner(){
        int xp = 0;
        int maxHP = 1000;
        String gegnerID = "1";
        try (Connection conn = DriverManager.getConnection(url, user, passwort);
             Statement stm = conn.createStatement()) {
            gegnerID = String.valueOf((int) Math.round(Math.random() * 11+1));
            ResultSet rs = stm.executeQuery("SELECT Namen, Leben, XPGewinn FROM Gegner WHERE GegnerID=" + gegnerID);
            if (rs.next()) {
                int lebenGeg = rs.getInt("Leben");
                int xpGewinn = rs.getInt("XPGewinn");
                maxHP =lebenGeg;
                xp =  xpGewinn;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        Gegner g = new Gegner(gegnerID, maxHP, xp);
        return g ;    }
}

