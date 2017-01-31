import java.awt.*;
import java.util.ArrayList;
import java.io.*;
/**
 * Created by corentinD on 08/01/2017.
 */
public class State {




    private Fichier fichier;
    private Raquette raquette;
    private Balle balle;
    private Balle balle1;
    private Balle balle2;
    private ArrayList<Brick> bricks;
    private Collision collision = new Collision();
    private PanneauLateral panneauLateral;
    private int[][] brickTab;
    private int compteurLevel = 1;
    private boolean bonusTouche = false;
    private int randomNum;
    private boolean gameOver = false;
    private Fenetre frame;
    private int compteurBegin = 0;
    private int compteurBrickBroken = 0;
    private boolean drawBalle = false;
    private  boolean balle1Tombee =false;
    private  boolean balle2Tombee =false;
    private  boolean balle3Tombee =false;
    private  int compteurBalle = 1 ;
    private int compteurBonus = 0;
    boolean bonused = false;











    public State(Raquette raquette, PanneauLateral panneauLateral, Fichier fichier, Fenetre frame){

        this.frame = frame;
        this.raquette = raquette;
        this.panneauLateral = panneauLateral;
        this.fichier = fichier;
        init();
    }

    public void init() {
        balle = new Balle(Panneau.WIDTH / 2 - 5, Panneau.HEIGHT / 2 +260, 5);
        createbricks();
    }

    public void tick() {
        beginGame();
        raquette.tick();
        balle.tick();
        if(drawBalle == true) {
        balle1.tick();
        balle2.tick();
        }
        modifVie();
        gameOver();
        stop();
        partieGagne();
        levelGagne();
        BonusMultipleBalle();
        collision.paddleBalle(raquette,balle);
        if(drawBalle == true) {
            collision.paddleBalle(raquette,balle1);
            collision.paddleBalle(raquette,balle2);
        }
        balleTombee();



        for (int i = 0; i < bricks.size(); i++){
            collision.BalleBrick(balle, bricks.get(i));

            if(drawBalle == true) {
                collision.BalleBrick(balle1, bricks.get(i));
                collision.BalleBrick(balle2, bricks.get(i));
            }

            if(bricks.get(i).getHealth() <= 0){
                bricks.remove(i);
                i--;
                compteurBrickBroken ++;
                panneauLateral.setScore(panneauLateral.getScore() + 10);
                if(bricks.size() ==0){
                    compteurLevel ++;
                }
            }
        }
    }




    // -----------------------------------DRAW--------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------

    public void draw(Graphics g){
        raquette.draw(g);
            balle.draw(g);
        if(drawBalle == true) {
            balle1.draw(g);
            balle2.draw(g);
        }
        for (int i =0; i < bricks.size() ; i++) {
            bricks.get(i).draw(g);
        }



            if (compteurBalle == 0 && panneauLateral.getVie() != 0){
                g.setColor(Color.RED);
                Font f = new Font("Rockwell", Font.BOLD, 20);
                g.setFont(f);
                g.drawString("Perdu ! :( ",400,300);

                g.drawString("Il vous reste " + panneauLateral.getVie()  +" vies", 350,325);
            }




        if (balle.getY() >= Panneau.HEIGHT && panneauLateral.getVie() == 0){
            g.setColor(Color.RED);
            Font f = new Font("Rockwell", Font.BOLD, 30);
            g.setFont(f);
            g.drawString("Game Over ",340,300);
            g.setColor(Color.ORANGE);
            Font o = new Font("Rockwell", Font.BOLD, 15);
            g.setFont(o);
            g.drawString("score: " + panneauLateral.getScore(),350, 320);
            g.setColor(Color.RED);
            g.drawString("Appuyez sur A pour recommencer",350,350);
        }



        if(bricks.size() ==0 && compteurLevel != 4){
            g.setColor(Color.ORANGE);
            Font o = new Font("Rockwell", Font.BOLD, 15);
            g.setFont(o);
            g.drawString("Level " + (panneauLateral.getLevel() + -1) + " completed",400,300);
            g.drawString("Get ready for the level " + (panneauLateral.getLevel() ) ,350,325);
        }



        if(compteurLevel == 4){
            g.setColor(Color.ORANGE);
            Font o = new Font("Rockwell", Font.BOLD, 30);
            g.setFont(o);
            g.drawString("YEAH YOU WON !",300,300);
        }

    }

    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------

    private void beginGame() {
        if(compteurBegin == 0) {
            Thread.currentThread().suspend();
            compteurBegin ++;
        }
    }

    public void modifVie(){
        if (compteurBalle == 0 && panneauLateral.getVie() != 0) {
            panneauLateral.setVie(panneauLateral.getVie() - 1);
        }
    }


    private void balleTombee() {
        if (balle.getY() >= Panneau.HEIGHT && !balle1Tombee) {
            compteurBalle--;
            balle1Tombee = true;

        }
        if (drawBalle){
            if(balle1.getY() >= Panneau.HEIGHT && !balle2Tombee) {
                compteurBalle--;
                balle2Tombee = true;

            }
            if(balle2.getY() >= Panneau.HEIGHT && !balle3Tombee){
                compteurBalle--;
                balle3Tombee = true;

            }
        }

    }


    public void stop(){  //plus aucune balle en jeu-----------------

        if (compteurBalle == 0 && panneauLateral.getVie() != 0) {


            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            balle.setX(Panneau.WIDTH / 2 - 5);
            balle.setY(Panneau.HEIGHT / 2 - 5);
            raquette.x=Panneau.WIDTH / 2 - 90;
            balle.setDx(0);
            compteurBalle = 1;
            drawBalle = false;
            compteurBrickBroken = 0;
            balle1Tombee = false;
            balle2Tombee = false;
            balle3Tombee = false;
            bonused = false;

        }
    }




    private void partieGagne() {  //3 niveaux terminés----------------------

        if(compteurLevel == 4){
            if (panneauLateral.getScore() > Integer.parseInt(fichier.lectureFicScore())) {
                fichier.ecritureFicScore(String.valueOf(panneauLateral.getScore()));
                frame.createFenetre();
            }
            Thread.currentThread().suspend();
        }
    }





    public void gameOver(){ //le joueur n'a plus de vie --------------------------

        if (compteurBalle == 0 && panneauLateral.getVie() == 0) {
            if (panneauLateral.getScore() > Integer.parseInt(fichier.lectureFicScore())) {
                fichier.ecritureFicScore(String.valueOf(panneauLateral.getScore()));
                frame.createFenetre();
            }
            gameOver = true;
            compteurBalle = 1;
            drawBalle = false;
            compteurBrickBroken = 0;
            balle1Tombee = false;
            balle2Tombee = false;
            balle3Tombee = false;
            bonused = false;
            Thread.currentThread().suspend();




        }
        }




    private void levelGagne(){  //Niveau terminé ------------------------

        if(bricks.size() ==0){
            panneauLateral.setLevel(panneauLateral.getLevel() + 1);
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if(panneauLateral.getLevel() == 2) {
                balle.setX(Panneau.WIDTH / 2 - 5);
                balle.setY(Panneau.HEIGHT / 2 - 5);
                raquette.x = Panneau.WIDTH / 2 - 90;
                raquette.setTaille(100);
                balle.setDx(0);
                balle.setDy(5);
                createbricks();

            }
            if(panneauLateral.getLevel() == 3) {
                balle.setX(Panneau.WIDTH / 2 - 5);
                balle.setY(Panneau.HEIGHT / 2 - 5);
                raquette.x = Panneau.WIDTH / 2 - 90;
                raquette.setTaille(180);
                balle.setDy(9);
                balle.setDx(0);
                createbricks();
            }

            compteurBalle = 1;
            drawBalle = false;
            compteurBrickBroken = 0;
            balle1Tombee = false;
            balle2Tombee = false;
            balle3Tombee = false;
            bonused = false;
        }
    }




    public void createbricks(){  //methode de création des briques
        bricks = new ArrayList<Brick>();

        brickTab = new int[8][14];
        for (int i = 0; i < brickTab.length; i = i + 1) {
            for (int j = 0; j < brickTab[0].length; j = j + 1) {
                if (i <= 1) {
                    brickTab[i][j] = 4;
                }

                if (i <= 3 && i >= 2) {
                    brickTab[i][j] = 3;
                }

                if (i <= 5 && i >= 4) {
                    brickTab[i][j] = 2;
                }

                if (i <= 7 && i >= 6) {
                    brickTab[i][j] = 1;
                }

            }
        }

        for (int i = 0; i < brickTab.length; i++) {
            for (int j = 0; j < brickTab[0].length; j++) {
                bricks.add(new Brick(getRandomNum() * 55 + 30, getRandomNum() * 15 +40 , brickTab[i][j]));
            }
        }

    }



    public int getRandomNum() {
        randomNum = (int) (Math.random() * 14);
        return randomNum;
    }

    public void restartGame() {
        init();
        createbricks();
        balle.setX(Panneau.WIDTH / 2 - 5);
        balle.setY(Panneau.HEIGHT / 2 - 5);
        raquette.x=Panneau.WIDTH / 2 - 90;
        balle.setDx(0);
        panneauLateral.setScore(0);
        panneauLateral.setVie(3);
        panneauLateral.setLevel(1);
        gameOver = false;
    }

    private void BonusMultipleBalle() {     //bonus de multiplicateur de balles
        if(compteurBrickBroken == 20 && compteurBonus == 0){
            balle1 = new Balle(balle.getX(), balle.getY(), 5);
            balle1.setDx(balle.getDx() - 1);
            balle1.setDy(balle.getDy()*-1);
            balle2 = new Balle(balle.getX(), balle.getY(), 5);
            balle2.setDx(balle.getDx() + 1);
            balle2.setDy(balle.getDy()*-1);
            compteurBrickBroken ++;
            drawBalle = true;
            compteurBalle = 3;
            compteurBonus ++;

        }
        else if (compteurBrickBroken == 20 && compteurBonus > 0 && !bonused){
            System.out.print("test");
            balle1.setX(balle.getX());
            balle1.setY(balle.getY());
            balle1.setDx(balle.getDx() - 1);
            balle1.setDy(balle.getDy()*-1);
            balle2.setX(balle.getX());
            balle2.setY(balle.getY());
            balle2.setDx(balle.getDx() + 1);
            balle2.setDy(balle.getDy()*-1);
            drawBalle = true;
            bonused = true;
            compteurBalle = 3;
            compteurBrickBroken ++;

        }
    }

    public boolean isGameOver() {
        return gameOver;
    }


}
