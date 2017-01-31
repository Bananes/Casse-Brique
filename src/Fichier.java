import java.io.*;

/**
 * Created by corentinD on 20/01/2017.
 */
public class Fichier {

    String score;
    String variablein;
    String nom;
    private String nl = System.getProperty("line.separator");



    public String lectureFicScore() {

        try {
            File fileIn = new File("highscore.txt"); //ouverture du fichier
            FileInputStream fis = new FileInputStream(fileIn);
            byte[] buffer = new byte[fis.available()]; //Récupération du contenu du fichier dans une variable de type byte
            fis.read(buffer); //Lecture du contenu de la variable buffer
            String stringIn = new String(buffer); //On parse le contenu de buffer dans notre chaîne
            this.variablein = stringIn;
            fis.close(); //Fermeture du fichier

        } catch(Exception e) {
            e.printStackTrace();
        }
        return variablein;
    }


    public String lectureFicNom() {

        try {
            File fileIn = new File("nom.txt"); //ouverture du fichier
            FileInputStream fis = new FileInputStream(fileIn);
            byte[] buffer = new byte[fis.available()]; //Récupération du contenu du fichier dans une variable de type byte
            fis.read(buffer); //Lecture du contenu de la variable buffer
            String stringIn = new String(buffer); //On parse le contenu de buffer dans notre chaîne
            this.variablein = stringIn;
            fis.close(); //Fermeture du fichier

        } catch(Exception e) {
            e.printStackTrace();
        }
        return variablein;
    }






    public void ecritureFicScore( String score){
        FileWriter writer = null;
        try {
            writer = new FileWriter("highscore.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void ecritureFicNom( String nom){
        FileWriter writer = null;
        try {
            writer = new FileWriter("nom.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(nom);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
