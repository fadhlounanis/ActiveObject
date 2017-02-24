package activeobject.capteur;

import activeobject.ObserveurAsynchrone;

import java.util.List;

/**
 * Created by aroua on 20/01/17.
 */

public class GestionnaireAlgorithme {


    // Le premier algorithme vaut null
    private AlgorithmeDiffusion courant=null;


    public void changer(AlgorithmeDiffusion nouveau, Capteur capteur, List<ObserveurAsynchrone> observeurs){

        // Pas de changement si les algorithme sont les même
        if(!sameAlgo(nouveau)){
           if(courant!=null) {
               courant.arreterScheduler();
           }
           courant=nouveau;
           courant.configure(capteur, observeurs);
           courant.execute();
       }

    }

    private boolean sameAlgo(AlgorithmeDiffusion a){
        if(courant==null){
            return false;
        }
        return (a.getId()==courant.getId());
    }




}
