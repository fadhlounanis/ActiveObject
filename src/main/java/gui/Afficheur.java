package gui;


import activeobject.FutureEpoque;
import activeobject.capteur.Capteur;
import activeobject.capteur.CapteurAsynchrone;
import activeobject.capteur.ObserveurDeCap;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * Created by fadhloun on 21/01/17.
 */

//pour permettre d'onseerver le canal


public class Afficheur implements ObserveurDeCap {

    private Label label;

    private int dernierTimestamp =-1;

    public void setLabel(Label newLabel){
        label=newLabel;
    }


    public void updateTimestamp(final CapteurAsynchrone capteur) {

        // pour pas d'erreur

        try {
            Future<FutureEpoque> future=capteur.getValueEpoque();
            FutureEpoque futureEpoque=future.get();
            if(dernierTimestamp <futureEpoque.getTimeStamp()) {
                setTextWithTimestamp(futureEpoque.getValue(),futureEpoque.getTimeStamp());
            }
        } catch (Exception ex){
            ex.printStackTrace();


        }


    }


    public void update(CapteurAsynchrone capteur) {

        final CapteurAsynchrone capteurAsynchrone=(CapteurAsynchrone) capteur;
        final int a=0;
        (new Thread() {
            public void run() {
                Future<Integer> future=capteurAsynchrone.getValue();
                try {
                    setText(future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void setText(final int val){
        Platform.runLater(new Runnable() {
            public void run() {
                label.setText("Valeur : " + val);
            }
        });
    }

    private void setTextWithTimestamp(final int val, final int timestamp){
        Platform.runLater(new Runnable() {
            public void run() {
                label.setText("Valeur : " + val + " Timestamp : "+timestamp);
            }
        });
    }


}
