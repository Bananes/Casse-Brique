import javax.swing.*;
import java.awt.*;

/**
 * Created by corentinD on 14/01/2017.
 */
public class PanneauLateral extends JPanel implements Runnable {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 600;
    private Thread t2;
    private boolean isRunning = false;
    private int score = 0;
    private int level = 1;
    private int vie = 3;
    Fichier fichier;
    Fenetre frame;

    public PanneauLateral(Fichier fichier, Fenetre frame){
        this.fichier = fichier;
        this.frame = frame;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        start();
    }

    private void start() {

        isRunning = true;
        t2 = new Thread(this);
        t2.start();
    }

    public void run() {

        while (isRunning) {

            this.repaint();
            try {
                Thread.sleep(1600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLUE);
        g.fillRect(0,0,10,HEIGHT);
        g.setColor(Color.GREEN);
        Font f = new Font("Courier", Font.BOLD, 13);
        g.setFont(f);
        g.drawString("score : " + getScore(), 15, 580);
        g.drawString(" Vie du joueur : " + vie,13,560);
        g.drawString("Reprendre la partie : R",15,540);
        g.drawString("Mettre le jeu en pause : P ", 15,520);
        Font t = new Font("Courier", Font.BOLD, 12);
        g.setFont(t);
        g.drawString("Commencer le Jeu : ENTER", 15,500);
        Font h = new Font("Courier", Font.BOLD, 20);
        g.setColor(Color.ORANGE);
        g.setFont(h);
        g.drawString("Level : " + getLevel(),15, 30);
        g.drawString("HighScore :",15,100);
        g.drawString(fichier.lectureFicScore(),15,125);
        g.drawString(fichier.lectureFicNom(),15,150);





    }


    public int getScore() {
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
