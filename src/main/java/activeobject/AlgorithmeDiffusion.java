package activeobject;
import activeobject.capteur.Capteur;


import java.util.List;

/**
 * Created by fadhloun on 20/01/17.
 */
public interface AlgorithmeDiffusion {


    void configure(Capteur capteur, List<ObserveurAsynchrone> observeurs);

    void arreterScheduler();

    short getId();

    void execute();

}
