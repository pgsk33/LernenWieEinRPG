import java.sql.*;

public class Spieler {
    public String userID;
    public int  playerLevel;
    public int aktuelleXP;
    public Connection connection;

    public Spieler(String userID, String url, String user, String passwort){
        this.userID = userID;
        try {
            connection = DriverManager.getConnection(url, user, passwort);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.playerLevel= loadPlayerLevelFromDB(connection,userID);
        this.aktuelleXP = loadAktuelleXPFromDB(connection,userID);
        System.out.println("Aktuelle XP; " + aktuelleXP);
    }

     public int getSchaden(){
         return playerLevel*2;
     }

    public void prüfenObLevelUp(){
        int erforderteXP;
        int tempplayerLevel = playerLevel;
        erforderteXP = (int) (tempplayerLevel*10*1.5);

        if(erforderteXP<=aktuelleXP){
            tempplayerLevel = tempplayerLevel+1;
            aktuelleXP = 0;
            saveAktuelleXptoDB(connection);
        }
        System.out.println("Tempplayerlevel: " + tempplayerLevel);
        //speichert das aktuelle level
        //        savePlayerLevelToDB(connection); des Spilerobjekts
        playerLevel =  tempplayerLevel;
        System.out.println("Playerlevel: " + playerLevel);
    }

    public void savePlayerLevelToDB(Connection conn){
       try{
           PreparedStatement pstmt = conn.prepareStatement("UPDATE Benutzer SET levelNr = '"+playerLevel + "' where UserID = '" + userID + "' ; ");
           pstmt.executeUpdate();
       }catch (SQLException e){
           e.printStackTrace();
       }
    }

    public int loadPlayerLevelFromDB(Connection conn, String userID){
        try{ Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT levelNr FROM Benutzer where userID = '" + userID + "';");
            while (rs.next()){
                playerLevel=rs.getInt(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return playerLevel;
    }

    public void saveAktuelleXptoDB(Connection conn){
        try{
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Benutzer SET aktuelleXP="+aktuelleXP+" where UserID = "+userID+";");
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int loadAktuelleXPFromDB(Connection conn, String userID){
        try{ Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT aktuelleXP FROM Benutzer where userID = '" + userID + "';");
            while (rs.next()){
                aktuelleXP=rs.getInt(1);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return aktuelleXP;
    }
}

