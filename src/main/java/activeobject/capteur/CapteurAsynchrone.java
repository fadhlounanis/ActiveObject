package activeobject.capteur;

import activeobject.FutureEpoque;

import java.util.concurrent.Future;

/**
 * Created by fadhloun on 15/02/17.
 */

public interface CapteurAsynchrone {

    Future<Integer> getValue();
    Future<FutureEpoque> getValueEpoque();
}
