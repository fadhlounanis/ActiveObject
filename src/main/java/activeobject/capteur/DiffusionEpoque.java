package activeobject.capteur;

import activeobject.ObserveurAsynchrone;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fadhloun on 25/02/17.
 */
public class DiffusionEpoque implements AlgorithmeDiffusion {

    private List<ObserveurAsynchrone> observeurs;
    private ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(10);
    private Capteur capteur;

    public void execute() {
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if (observeurs != null && capteur != null) {
                    for (ObserveurAsynchrone observeur : observeurs) {
                        Future futur=observeur.updateTimestamp();
                        try {

                            futur.get();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }, 0,1000, TimeUnit.MILLISECONDS);
    }

    public void configure(final Capteur capteur, List<ObserveurAsynchrone> observeurs) {
        this.observeurs = observeurs;

        this.capteur = capteur;

        // Séparation du tick et du update
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {

                capteur.tick();

            }
        }, 0,1000,TimeUnit.MILLISECONDS);

    }
    public void arreterScheduler() {
        scheduler.shutdownNow();
    }

    public short getId() {
        return 1;
    }

}
