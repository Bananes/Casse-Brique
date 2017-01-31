import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by corentinD on 07/01/2017.
 */
public class Fenetre extends JFrame implements ActionListener{



    private JTextField textField;
    private Fichier fichier;
    private Fenetre winner;


    public void createFenetre() {
        this.fichier = new Fichier();
        this.winner = new Fenetre();
        winner.setResizable(false);
        winner.setLocationRelativeTo(null);
        winner.setTitle("Meilleur score");
        winner.setSize(250, 150);
        this.textField = new JTextField("Saisir nom");
        JTextArea text = new JTextArea("Bravo vous avez réalisé le Meilleur score");
        JButton button = new JButton("ok");
        winner.setLayout(new BorderLayout());
        button.addActionListener(this);
        winner.add(textField,BorderLayout.NORTH);
        winner.add(text,BorderLayout.CENTER);
        winner.add(button,BorderLayout.SOUTH);
        winner.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(!textField.getText().equals("Saisir nom")) {
            fichier.ecritureFicNom(textField.getText());
            winner.setVisible(false);
        }

    }


    public static void main (String[] args){

        boolean visible = false;
        Raquette raquette = new Raquette();
        Fichier fichier = new Fichier();
        Fenetre frame = new Fenetre();
        frame.setTitle("Casse-Brique");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        PanneauLateral panneauLateral = new PanneauLateral(fichier, frame);
        frame.add(panneauLateral, BorderLayout.EAST);
        frame.add(new Panneau(raquette, panneauLateral, fichier,frame), BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
