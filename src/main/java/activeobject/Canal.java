package activeobject;

import activeobject.capteur.Capteur;
import activeobject.capteur.CapteurAsynchrone;
import activeobject.capteur.ObserveurDeCap;
import activeobject.capteur.SujetDeCap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by fadhloun on 23/02/17.
 */
public class Canal implements  ObserveurAsynchrone, SujetDeCap,CapteurAsynchrone {

    // Pour le capteur
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(50);
    // Pour les Afficheur
    private ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(50);

    private List<ObserveurDeCap> observeurDeCaps =new ArrayList<ObserveurDeCap>();
    private Capteur capteur;
    private int timeStamp=0;


    public Canal(Capteur newCapteur){
        capteur = newCapteur;
    }



    public Future<Integer> getValue() {
        FutureTask<Integer> future=new FutureTask<Integer>(new Callable<Integer>() {
            public Integer call() throws Exception {
                return capteur.getValue();
            }
        });

        scheduler2.schedule(future,this.calculerDelaiAleatoire(),TimeUnit.MILLISECONDS);

        return future;
    }


    public Future<FutureEpoque> getValueEpoque() {
        final FutureEpoque futureEpoque =new FutureEpoque();

        FutureTask<FutureEpoque> future=new FutureTask<FutureEpoque>(new Callable<FutureEpoque>() {
            public FutureEpoque call() throws Exception {
                timeStamp=timeStamp+1;
                futureEpoque.setValue(capteur.getValue());
                futureEpoque.setTimeStamp(timeStamp);
                return futureEpoque;
            }
        });

        scheduler2.schedule(future,this.calculerDelaiAleatoire(),TimeUnit.MILLISECONDS);
        return future;
    }

    public Future updateTimestamp() {
        final CapteurAsynchrone capteurAsynchrone=this;

        FutureTask<Integer> future=new FutureTask<Integer>(new Callable<Integer>() {
            public Integer call() throws Exception {
                for(ObserveurDeCap obs: observeurDeCaps){
                    obs.updateTimestamp(capteurAsynchrone);
                }
                return 0;
            }
        });

        scheduler.submit(future);
        return future;
    }


    public Future update() {
        final CapteurAsynchrone fakeCap=this;

        FutureTask<Boolean> future=new FutureTask<Boolean>(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                for(ObserveurDeCap observeur: observeurDeCaps){
                    observeur.update((CapteurAsynchrone)fakeCap);
                }

                return true;
            }
        });
        scheduler.submit(future);
        return future;
    }



    public void attach(ObserveurDeCap observeur) {
        observeurDeCaps.add(observeur);
    }
    public void detach(ObserveurDeCap observeur) {
        observeurDeCaps.remove(observeur);
    }


    private int calculerDelaiAleatoire(){
        Random random=new Random();
        return random.nextInt(1000);

    }
}
