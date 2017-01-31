import java.awt.*;

/**
 * Created by corentinD on 09/01/2017.
 */
public class Brick  extends Rectangle {

    private int health;

    public Brick (int x,int y, int health){

        setBounds(x, y, 50, 10);
        this.health = health;

    }





    public void draw(Graphics g){
        if (health == 1){
            g.setColor(Color.BLUE);
        }

        if (health == 2){
            g.setColor(Color.GREEN);
        }

        if (health == 3){
            g.setColor(Color.ORANGE);
        }

        if (health == 4){
            g.setColor(Color.RED);
        }



        g.fillRect(x ,y, width, height);

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
