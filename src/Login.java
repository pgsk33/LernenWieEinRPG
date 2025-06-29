import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class Login implements ActionListener {
    public static final String SYSTEM_PROPERTIES = "res/System.properties";
    private String name = "";
    private String email = "";
    private String passwort = "";
    private String url = "";
    private String user = "";
    private String passwortSQL = "";
    private String userID;
    public static Properties prop = new Properties();


    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPasswortSQL() {
        return passwortSQL;
    }

    public String getUserID() {
        return userID;
    }


    public boolean isLoggedin() {
        return loggedin;
    }

    private boolean loggedin = false;

    public Login() {
        if (configLogin()) {
            if (anmelden(email, passwort, url, user, passwortSQL)){
                int answer = JOptionPane.showConfirmDialog(null,"Wilkommen zurück " + name + "! Als " + name +" fortfahren?", "Hi", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION){
                    loggedin = true;
                }
                if (answer == JOptionPane.NO_OPTION){
                    loginGUI();
                }
            }
        } else {
            loginGUI();
        }
    }

    public String accouterstellen(String name, String email, String passwort, String url, String user, String passwortSQL) {
        this.name = name;
        this.email = email;
        this.passwort = passwort;
        try (Connection conn = DriverManager.getConnection(url, user, passwortSQL);
             PreparedStatement pstmt = conn.prepareStatement("Insert Benutzer Values (Null,?,?,?,1,0)")) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, passwort);
            pstmt.executeUpdate();

            System.out.println("Account wurde erfolgreich erstellt!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name + "," + email + "," + passwort;
    }

    public boolean anmelden(String eingabeEmail, String eingabePasswort, String url, String user, String pass) {
        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("Verbindung erfolgreich hergestellt");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT passwort, UserID,levelNr,aktuelleXP FROM Benutzer WHERE  email = '" + eingabeEmail + "';");
            if (rs.next()) {
                passwort = rs.getString(1);
                userID = rs.getString(2);
            } else {
                passwort = ""; // Falls die E-Mail nicht gefunden wurde
                System.out.println("email adress not found");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (passwort.equals(eingabePasswort)) {
            try {
                saveConfig();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        } else return false;
    }

    JFrame frame = new JFrame("Login");

    JLabel nameLabel = new JLabel("Name:");
    JTextField nameField = new JTextField();

    JLabel emailLabel = new JLabel("E-Mail:");
    JTextField emailField = new JTextField();

    JLabel passwordLabel = new JLabel("Passwort:");
    JPasswordField passwordField = new JPasswordField();

    JLabel dbUrlLabel = new JLabel("Datenbank URL:");
    JTextField dbUrlField = new JTextField();

    JLabel dbUserLabel = new JLabel("Datenbank Nutzer:");
    JTextField dbUserField = new JTextField();

    JLabel dbPasswordLabel = new JLabel("Datenbank Passwort:");
    JPasswordField dbPasswordField = new JPasswordField();

    JButton loginButton = new JButton("Login");
    JButton mitEingegebenZugangsdatenEinenNeuenAccErstellen = new JButton("Mit Eingegeben Zugangsdaten neuen Account erstellen");

    public void loginGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(12, 2));
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(dbUrlLabel);
        frame.add(dbUrlField);
        frame.add(dbUserLabel);
        frame.add(dbUserField);
        frame.add(dbPasswordLabel);
        frame.add(dbPasswordField);
        frame.add(loginButton);
        frame.add(mitEingegebenZugangsdatenEinenNeuenAccErstellen);
        frame.setVisible(true);
        loginButton.addActionListener(this);
        mitEingegebenZugangsdatenEinenNeuenAccErstellen.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            name = nameField.getText();
            email = emailField.getText();
            passwort = new String(passwordField.getPassword());
            url = dbUrlField.getText();
            user = dbUserField.getText();
            passwortSQL = new String(dbPasswordField.getPassword());
            System.out.println("Login gedrückt");
            loggedin = anmelden(email, passwort, url, user, passwortSQL);
        }

        if (e.getSource() == mitEingegebenZugangsdatenEinenNeuenAccErstellen) {
            System.out.println("Accout erstellung!");
            name = nameField.getText();
            email = emailField.getText();
            passwort = new String(passwordField.getPassword());
            url = dbUrlField.getText();
            user = dbUserField.getText();
            passwortSQL = new String(dbPasswordField.getPassword());
            System.out.println("Accout erstellung!");
            accouterstellen(name, email, passwort, url, user, passwortSQL);
            loggedin = true;
        }
        frame.dispose();
    }

    public void readProperties() throws IOException {
        Properties prop = new Properties();
        InputStream input = new FileInputStream(SYSTEM_PROPERTIES);
        prop.load(input);
        name = prop.getProperty("name");
        email = prop.getProperty("email");
        passwort = prop.getProperty("passwort");
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        passwortSQL = prop.getProperty("passwortSQL");
        System.out.println(name + " " + email + " " + passwort + " " + url + " " + user + " " + passwortSQL);
    }

    public boolean configLogin() {
        File logFile = new File(SYSTEM_PROPERTIES);
        if (logFile.exists()) {
            try {
                readProperties();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            if (name == null || email == null || passwort == null || url == null || user == null || passwortSQL == null){
                return false;
            }
            System.out.println("Config loaded");
            return true;
        }
        return false;
    }

    public void saveConfig() throws IOException {
        File logFile = new File(SYSTEM_PROPERTIES);
        if (logFile.exists()){
            logFile.delete();
        }
        logFile.createNewFile();
        Properties properties = new Properties();
        properties.setProperty("name", name);
        properties.setProperty("email", email);
        properties.setProperty("passwort", passwort);
        properties.setProperty("url", url);
        properties.setProperty("user", user);
        properties.setProperty("passwortSQL", passwortSQL);
        try (FileOutputStream out = new FileOutputStream(SYSTEM_PROPERTIES)) {
            properties.store(out, null);
        }


        System.out.println("Config saved");
    }
}