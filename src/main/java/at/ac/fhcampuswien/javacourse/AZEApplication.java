package at.ac.fhcampuswien.javacourse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AZEApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AZEApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); //get rid of fix dimensions
        stage.setTitle("AZE");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}