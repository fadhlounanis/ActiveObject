package gui;

import activeobject.capteur.Capteur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by fadhloun on 17/01/17.
 */
public class Controlleur {

    @FXML
    private List<Afficheur> afficheursObs =new ArrayList<Afficheur>();

    @FXML
    private Label aff1;
    @FXML
    private Label aff2;
    @FXML
    private Label aff3;

    @FXML
    private Label aff4;

    private Capteur capteur;

    @FXML
    public void initialize() {
        afficheursObs.add(new Afficheur());
        afficheursObs.add(new Afficheur());
        afficheursObs.add(new Afficheur());
        afficheursObs.add(new Afficheur());

        afficheursObs.get(0).setLabel(aff1);
        afficheursObs.get(1).setLabel(aff2);
        afficheursObs.get(2).setLabel(aff3);
        afficheursObs.get(3).setLabel(aff4);


    }



    public void configure(Capteur capteur){this.capteur=capteur;}

    public List<Afficheur> getAfficheursObs() {
        return afficheursObs;
    }




}
