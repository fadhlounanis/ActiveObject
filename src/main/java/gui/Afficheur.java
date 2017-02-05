package gui;


import activeobject.capteur.Capteur;
import javafx.application.Platform;
import javafx.scene.control.Label;


/**
 * Created by fadhloun on 21/01/17.
 */

//pour permettre d'onseerver le canal


public class Afficheur {

    private Label label;

    private int dernierTimestamp =-1;

    public void setLabel(Label newLabel){
        label=newLabel;
    }




    public void update(Capteur capteur) {




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
