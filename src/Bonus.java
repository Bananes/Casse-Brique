/**
  *Created by corentinD on 19/01/2017.
**/
import java.awt.*;

/**
  Created by corentinD on 09/01/2017.
**/


public class Bonus  extends Rectangle {

    private String type;

    public Bonus (int x,int y, String type){

        setBounds(x, y, 10, 10);
        this.type = type;

    }





    public void draw(Graphics g){

        if (type.equals("balle")){
            g.setColor(Color.BLUE);
        }

        if (type.equals("raquetteUp")){
            g.setColor(Color.GREEN);
        }

        if (type.equals("raquetteDown")){
            g.setColor(Color.ORANGE);
        }


        g.fillRect(x ,y, width, height);

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

