import java.awt.*;
import java.awt.event.KeyEvent;


/**
 * Created by corentinD on 08/01/2017.
 */
public class Raquette extends Rectangle {

    private boolean left = false;
    private boolean right =false;

    private int moveSpeed = 6;

    private Rectangle topL;
    private Rectangle topCR;
    private Rectangle topCL;
    private Rectangle topR;
    private Rectangle sideL;
    private Rectangle sideR;
    private int taille = 180;




    public Raquette(){

        setBounds(Panneau.WIDTH / 2 - 90,Panneau.HEIGHT - 30, taille,20); // augmenter l'avant dernier nombre pour augmenter la taille de la barre

        topL = new Rectangle(x , y - 1, width / 4, 1);

        topCL = new Rectangle(x + width / 4, y - 1, width / 4 , 1);
        topCR = new Rectangle(x + 2 *(width / 4), y - 1, width / 4 , 1);

        topR = new Rectangle(x+3 * (width / 4), y - 1, width / 4, 1 );

        sideL = new Rectangle(x - 1, y , 1 , height);
        sideR = new Rectangle(x + width, y, 1 , height);


    }




    public void tick(){



        if( x <= 0){
            left = false;

        }

        if (x + taille >=  Panneau.WIDTH) {
            right = false;
        }

        if(left){

            x -= moveSpeed;



        }

        if(right){

            x += moveSpeed;

        }

        calculateEdges();

    }

    private void calculateEdges(){
        topL.setBounds(x , y , taille / 4, 1);
        topCL.setBounds(x + 1 *(taille / 4), y,taille / 4,1);
        topCR.setBounds(x + 2 *(taille / 4), y,taille / 4,1);
        topR.setBounds(x + taille - (taille / 4), y,taille / 4,1);
        sideL.setBounds(x - 1, y , 1 , height);
        sideR.setBounds(x + taille, y, 1 , height);

    }



    public void draw(Graphics g){

        g.setColor(Color.GREEN);
        g.fillRect(x,y,taille,height);
        //différentes parties de la raquette
        /**
        g.setColor(Color.BLUE);
        g.fillRect(topL.x, topL.y, topL.width,topL.height);
        g.setColor(Color.RED);
        g.fillRect(topCL.x, topCL.y, topCL.width,topCL.height);
        g.setColor(Color.MAGENTA);
        g.fillRect(topCR.x, topCR.y, topCR.width, topCR.height);
        g.setColor(Color.ORANGE);
        g.fillRect(topR.x, topR.y, topR.width,topR.height);
        g.setColor(Color.CYAN);
        g.fillRect(sideL.x, sideL.y, sideL.width,sideL.height);
        g.setColor(Color.YELLOW);
        g.fillRect(sideR.x, sideR.y, sideR.width,sideR.height);
        **/

    }


    public Rectangle getTopL() {
        return topL;
    }

    public Rectangle getTopCR() {return topCR;}

    public Rectangle getTopCL() {return topCL;}

    public Rectangle getTopR() {
        return topR;
    }

    public Rectangle getSideL() {
        return sideL;
    }

    public Rectangle getSideR() {
        return sideR;
    }

    public void setLeft(boolean left) {
        this.left = left;

    }

    public boolean isLeft() {
        return left;
    }

    public void setRight(boolean right) {
        this.right = right;

    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }



}
