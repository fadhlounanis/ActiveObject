package activeobject.capteur;


import activeobject.ObserveurAsynchrone;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Aroua on 15/02/17.
 */

public class DiffusionAtomique implements AlgorithmeDiffusion {

    private List<ObserveurAsynchrone> observeurs;
    private ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(10);
    private Capteur capteur;

    public void execute() {

        scheduler.scheduleAtFixedRate(new Runnable() {
                                          public void run() {
                                              capteur.tick();

                                              if (observeurs != null && capteur != null) {
                                                  for (ObserveurAsynchrone observeur : observeurs) {
                                                      Future futur=observeur.update();
                                                      try {
                                                          futur.get();
                                                      } catch (Exception e) {
                                                      }
                                                  }
                                              }
                                          }
                                      },
                0,1000, TimeUnit.MILLISECONDS);
    }




    public void configure(Capteur capteur, List<ObserveurAsynchrone> observeurs) {
        this.capteur = capteur;
        this.observeurs = observeurs;
    }

    public void arreterScheduler() {
        scheduler.shutdownNow();
    }

    public short getId() {
        return 0;
    }

}
