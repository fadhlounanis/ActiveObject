package activeobject.capteur;

/**
 * Created by fadhloun on 15/02/17.
 */

public interface ObserveurDeCap {

    void update(CapteurAsynchrone capteur);

    void updateTimestamp(CapteurAsynchrone capteur);
}
