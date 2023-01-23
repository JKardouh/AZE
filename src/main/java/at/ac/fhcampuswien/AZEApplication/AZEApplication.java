package at.ac.fhcampuswien.AZEApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class AZEApplication extends Application {

    /**
     * Entry point of application, the start method
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AZEApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); //get rid of fix dimensions
        stage.setTitle("AZE");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method of the app
     * @param args not necessary for this app.
     */
    public static void main(String[] args) {
        launch();
    }
}