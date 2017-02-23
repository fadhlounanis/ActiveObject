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
    public void setLabel(Label newLabel){
        label=newLabel;
    }


    public void updateTimestamp(final CapteurAsynchrone capteur) {

        // pour pas d'erreur

        try {
            Future<FutureEpoque> future=capteur.getValueEpoque();
            FutureEpoque futureEpoque=future.get();
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


}
