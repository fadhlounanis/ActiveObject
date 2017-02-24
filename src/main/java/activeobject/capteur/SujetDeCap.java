package activeobject.capteur;

/**
 * Created by fadhloun on 15/02/17.
 */

public interface SujetDeCap {

    void attach(ObserveurDeCap observeur);

    void detach(ObserveurDeCap observeur);
}
