
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panneau extends JPanel implements Runnable, KeyListener {


    public static final int WIDTH = 830;
    public static final int HEIGHT = 600;
    private boolean enPause =false;
    private Font p = new Font("Rockwell", Font.BOLD, 30);
    private Font r = new Font("Rockwell", Font.BOLD, 15);
    private boolean perdu = false;
    private BufferedImage image;
    private Thread t1;
    private boolean isRunning = false;
    private State state;
    private Raquette raquette;
    private Fenetre frame;




    public Panneau(Raquette raquette, PanneauLateral panneauLateral,Fichier fichier, Fenetre frame) {
        this.frame = frame;
        this.raquette = raquette;
        this.state = new State(raquette,panneauLateral,fichier,frame);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        setFocusable(true);

        try {
            image = ImageIO.read(new File("fond_casse_brique.jpg"));
        } catch (IOException ex){
            System.out.print("l'image n'a pas pu être chargée");
        }

        start();
    }



    private void start() {
        isRunning = true;
        t1  = new Thread(this);
        t1.start();

    }



    public void run() {

        while (isRunning) {
            repaint();
            state.tick();




            try {
                Thread.sleep(9);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(image, 0, 0, this);
        state.draw(g);
        if(enPause){
            g.setColor(Color.BLUE);
            g.setFont(p);
            g.drawString("en pause",350,300);
            g.setFont(r);
            g.drawString("appuyez sur R pour reprendre la partie",300,320);

        }


    }





    public void keyPressed(KeyEvent e) {


        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            raquette.setLeft(true);
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            raquette.setRight(true);

        }



        if(e.getKeyCode() == KeyEvent.VK_P && !state.isGameOver() == true){
            repaint();
            enPause = true;
            t1.suspend();

        }



        if(e.getKeyCode() == KeyEvent.VK_R && t1.getState()==Thread.State.TIMED_WAITING){
            //System.out.print(t1.getState());
            try {
                t1.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            resume();
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER ){
            resume();
        }

        if(e.getKeyCode() == KeyEvent.VK_A  && state.isGameOver() == true){
            state.restartGame();
            repaint();
            resume();
        }

    }





    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            raquette.setLeft(false);
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            raquette.setRight(false);
        }
    }






    public void keyTyped(KeyEvent e) {

    }

    public void resume(){
        t1.resume();
        enPause = false;
        repaint();
    }





    public boolean isPerdu() {
        return perdu;
    }

    public void setPerdu(boolean perdu) {
        this.perdu = perdu;
    }


}