import java.awt.*;

/**
 * Created by corentinD on 08/01/2017.
 */
public class Collision {

    private int bX;
    private int bY;
    private int bR;


    private static boolean BallePaddle(Rectangle r,Point p){
        return r.contains(p);
    }

    private static boolean brickBalle(Brick b,Point p){

        return b.contains(p);
    }

    public void paddleBalle(Raquette Raquette, Balle Balle){

        bX = Balle.getX();
        bY = Balle.getY();
        bR = Balle.getR();

        if(BallePaddle(Raquette.getTopL(), new Point(bX, bY + bR))){
            Balle.setDy(Balle.getDy() * -1);
            Balle.setDx(-4);
        }


        if(BallePaddle(Raquette.getTopCL(), new Point(bX, bY + bR))){
            Balle.setDy(Balle.getDy() * -1);
            Balle.setDx(-2);
        }


        if(BallePaddle(Raquette.getTopCR(), new Point(bX, bY + bR))){
            Balle.setDy(Balle.getDy() * -1);
            Balle.setDx(2);
        }

        if(BallePaddle(Raquette.getTopR(), new Point(bX, bY + bR))){
            Balle.setDy(Balle.getDy() * -1);
            Balle.setDx(4);
        }


        if(BallePaddle(Raquette.getSideL() ,new Point(bX + bR, bY))){
            Balle.setDx(Balle.getDx() * -1);
            Balle.setDx(0);
        }


        if(BallePaddle(Raquette.getSideR(),new Point(bX - bR, bY))){
            Balle.setDx(Balle.getDx() * -1);
            Balle.setDx(0);
        }
    }




    public void BalleBrick(Balle Balle,Brick brick){
        bX = Balle.getX();
        bY = Balle.getY();
        bR = Balle.getR();


        if(brickBalle(brick, getCentre())){
            brick.setHealth(brick.getHealth() - 1);
            Balle.setDy(Balle.getDy() * -1);


        }


/**
        if(brickBalle(brick, new Point(bX + bR, bY)) || brickBalle(brick, new Point(bX - bR, bY))){
            brick.setHealth(brick.getHealth() - 1);
            Balle.setDx(Balle.getDx() * -1);

        }




        if(brickBalle(brick,new Point(bX,bY - bR)) || brickBalle(brick, new Point(bX, bY + bR))){
            brick.setHealth(brick.getHealth() - 1);
            Balle.setDy(Balle.getDy() *-1);
            Balle.setDx(Balle.getDx() *-1);

        }

**/

    }

    public Point getCentre()
    {
        return new Point(bX+bR/2,bY+bR/2);
    }


}
