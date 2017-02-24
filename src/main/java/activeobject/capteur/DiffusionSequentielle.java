package activeobject.capteur;

import activeobject.ObserveurAsynchrone;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aroua on 24/02/17.
 */
public class DiffusionSequentielle implements AlgorithmeDiffusion {

    private List<ObserveurAsynchrone> observeurs;
    private ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(10);
    private Capteur capteur;



    public void execute() {

        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if (observeurs != null && capteur != null) {

                    for (ObserveurAsynchrone observeur : observeurs) {

                        Future future = observeur.update();
                        try {
                            future.get();
                        } catch (Exception e){}

                    }

                    capteur.tick();
                }


            }
        }, 0,1000, TimeUnit.MILLISECONDS);

    }


    public void configure(final Capteur capteur, List<ObserveurAsynchrone> observeurs) {
        this.capteur = capteur;

        this.observeurs = observeurs;

        // Séparation du tick et du update
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {

                capteur.tick();
            }
        }, 0,800,TimeUnit.MILLISECONDS);
    }
    public void arreterScheduler() {
        scheduler.shutdownNow();
    }

    public short getId() {
        return 2;
    }

}
