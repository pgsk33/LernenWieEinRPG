import java.sql.*;
import java.util.Random;

public class Gegner {

    public String gegnerID;
    public int maxLeben;
    public String hp;
    public String xp;

    public Gegner(String gegnerID, int maxLeben, int xp) {
        this.gegnerID = gegnerID;
        this.maxLeben = maxLeben;
        this.hp = String.valueOf(maxLeben);
        this.xp = String.valueOf(xp);
    }


}


