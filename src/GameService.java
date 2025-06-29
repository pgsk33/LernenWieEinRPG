import java.sql.*;
import java.util.Properties;

public class GameService {
    static String url;
    static String user;
    static String passwort;
    public static Properties prop = new Properties();

    public static void main(String[] args) {

        Login l = new Login();
        while (l.isLoggedin() == false) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        url = l.getUrl();
        user = l.getUser();
        passwort = l.getPasswortSQL();

        Spieler s = new Spieler(l.getUserID(), l.getUrl(), l.getUser(), l.getPasswortSQL());
        Karteikarten k = new Karteikarten(1, l.getUrl(), l.getUser(), l.getPasswortSQL());
        UIelements ui = new UIelements(k, l, s);
    }
}


