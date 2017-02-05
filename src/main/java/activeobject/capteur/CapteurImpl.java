package activeobject.capteur;

import activeobject.AlgorithmeDiffusion;
import activeobject.GestionnaireAlgorithme;
import activeobject.ObserveurAsynchrone;
import activeobject.capteur.Capteur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by fadhloun on 19/01/17.
 */
public class CapteurImpl implements Capteur {

    private List<ObserveurAsynchrone> observeurs = new ArrayList<ObserveurAsynchrone>();
    private int valeur;
    private Random rand=new Random();
    private GestionnaireAlgorithme gestionnaireAlgorithme=new GestionnaireAlgorithme();

    public CapteurImpl(){
        valeur=0;
        gestionnaireAlgorithme.changer(new AlgorithmeDiffusion() {
            public void configure(Capteur capteur, List<ObserveurAsynchrone> observeurs) {

            }

            public void arreterScheduler() {

            }

            public short getId() {
                return 0;
            }

            public void execute() {

            }
        }, this, observeurs);

    }


    public int getValue() {
        return valeur;
    }


    public void setAlgorithme(AlgorithmeDiffusion algorithme) {
        gestionnaireAlgorithme.changer(algorithme, this,observeurs);
    }

    public void tick() {
        valeur=valeur+1;

    }
    public void attach(ObserveurAsynchrone observeur) {
        observeurs.add(observeur);
    }
    public void detach(ObserveurAsynchrone observeur) {
        observeurs.remove(observeur);
    }

}
