package activeobject;


/**
 * Created by fadhloun on 05/02/17.
 */
public interface SujetAsynchrone {

    void attach(ObserveurAsynchrone observeur);


    void detach(ObserveurAsynchrone observeur);
}
