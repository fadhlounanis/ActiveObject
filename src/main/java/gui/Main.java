package gui;
import activeobject.capteur.Capteur;
import activeobject.capteur.CapteurImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;



/**
 * Created by aroua on 18/01/17.
 */

public class Main extends Application{

    private FXMLLoader fxmlLoader;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        fxmlLoader= new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);

        // Lien des composants
        Controlleur controlleur =(Controlleur)fxmlLoader.getController();
        Capteur capteur=new CapteurImpl();
        controlleur.configure(capteur);




        primaryStage.setTitle("Capteur avec active object !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
