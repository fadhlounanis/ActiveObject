package activeobject.capteur;



import activeobject.AlgorithmeDiffusion;


/**
 * Created by fadhloun on 19/01/17.
 */
public interface Capteur  {


    int getValue();
    void tick();
    void setAlgorithme(AlgorithmeDiffusion algorithme);
}
