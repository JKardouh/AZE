package at.ac.fhcampuswien.javacourse;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class JavaCourseController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}