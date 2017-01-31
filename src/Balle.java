import javax.xml.bind.SchemaOutputResolver;
import java.awt.*;

/**
 * Created by corentinD on 08/01/2017.
 */
public class Balle {

    private int x;
    private int y;



    private int r;
    private double dx;
    private double dy;

    public Balle(int x,int y,int r){

        this.x = x;
        this.y = y;
        this.r = r;
        dx = 0;
        dy = 5;
    }



    public void tick(){

        if (x + r >= Panneau.WIDTH ||x - r <= 0){
            dx *= -1;
        }

        if (y -r <= 0){
            dy *= -1;
        }

        x += dx;
        y += dy;



    }


    public void draw(Graphics g){

        g.setColor(Color.WHITE);
        g.fillOval(x - r, y - r, 2 * r, 2 * r);

    }

    public  int getX() {
        return x;
    }


    public  int getY() {
        return y;
    }


    public int getR() {
        return r;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {this.dx = dx;}

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

}
