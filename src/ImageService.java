import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageService {
    File herofilef1 = new File("res/img/heroslime frame 1.png");
    File herofilef2 = new File("res/img/heroslime frame 2.png");
    Image backimg = null;
    File tempfile = new File("res/img/sword.png");
    File herofile = new File("res/img/sword.png");
    File Atomfile = new File("res/img/Atomschleim.png");
    File Eisfile = new File("res/img/EisSchleim.png");
    File Feuerfile = new File("res/img/LavaSchleim.png");
    File Goldfile = new File("res/img/GoldSchleim.png");
    File keineahnungfile = new File("res/img/KeineAhnungSchleim.png");
    File standartfile = new File("res/img/standartSchleim.png");
    File Steinfile = new File("res/img/SteinSchleim.png");
    File TNTfile = new File("res/img/TNTSchleim.png");
    File Wasserfile = new File("res/img/wasserSchleim.png");
    File Windfile = new File("res/img/WindSchleim.png");
    File Lavafile = new File("res/img/fireSlime.png");

    protected Image backGround() throws IOException {
        File file = new File("res/img/background.png");
        Image image = ImageIO.read(file);
        return image;
    }

    protected JPanel getJPanelForNormalBackground(JPanel panel) {
        File myFile = new File("res/img/background.jpg");
        try {
            backimg = getBufferedBImage(myFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(backimg, 0, 0, this);
            }

            private BufferedImage createImage() throws IOException {
                return getHeroImageBuffered(herofile);
            }
        };
        return panel;
    }

    protected JPanel getJPanelwithbackgroundForMainPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(backGround(), 0, 0, this);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        return panel;
    }

    protected JPanel getJPanelwithbackgroundForEnemyPanel(int gegener) {
        switch (gegener) {
            case 1:
                tempfile = standartfile;
                break;
            case 2:
                tempfile = Wasserfile;
                break;
            case 3:
                tempfile = Eisfile;
                break;
            case 4:
                tempfile = Feuerfile;
                break;
            case 5:
                tempfile = Lavafile;
                break;
            case 6:
                tempfile = TNTfile;
                break;
            case 7:
                tempfile = Steinfile;
                break;
            case 8:
                tempfile = Windfile;
                break;
            case 9:
                tempfile = Goldfile;
                break;
            case 10:
                tempfile = Atomfile;
                break;
            case 11:
                tempfile = keineahnungfile;
                break;
        }

        JPanel panel = getJPanel();
        return panel;
    }

    protected JPanel getJPanelHero1() {

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(createImage(herofilef1), 0, 0, this);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            private BufferedImage createImage(File file) throws IOException {
                return getHeroImageBuffered(file);
            }
        };
        panel.setOpaque(false);
        return panel;
    }
    protected JPanel getJPanelHero2 (){

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(createImage(herofilef2), 0, 0, this);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            private BufferedImage createImage(File file) throws IOException {
                return getHeroImageBuffered(file);
            }
        };
        panel.setOpaque(false);
        return panel;
    }

    @NotNull
    private JPanel getJPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(createImage(), 0, 0, this);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            private BufferedImage createImage() throws IOException {
                return getBufferedImage(tempfile);
            }
        };
        panel.setOpaque(false);
        return panel;
    }

    @NotNull
    private BufferedImage getBufferedImage(File tempfile) throws IOException {
        BufferedImage image = ImageIO.read(tempfile);
        Image temp = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        BufferedImage scaledImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setComposite(AlphaComposite.Src); // Transparenz erhalten
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();
        return scaledImage;
    }

    @NotNull
    private BufferedImage getBufferedBImage(File tempfile) throws IOException {
        BufferedImage image = ImageIO.read(tempfile);
        Image temp = image.getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);
        BufferedImage scaledImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setComposite(AlphaComposite.Src); // Transparenz erhalten
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();
        return scaledImage;
    }

    private BufferedImage getHeroImageBuffered(File tempfile) throws IOException {
        BufferedImage image = ImageIO.read(tempfile);
        Image temp = image.getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        BufferedImage scaledImage = new BufferedImage(600, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setComposite(AlphaComposite.Src); // Transparenz erhalten
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();
        return scaledImage;
    }

}
