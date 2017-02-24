package activeobject.capteur;



import activeobject.SujetAsynchrone;


/**
 * Created by aroua on 19/01/17.
 */
public interface Capteur extends SujetAsynchrone {


    int getValue();
    void tick();
    void setAlgorithme(AlgorithmeDiffusion algorithme);
}
