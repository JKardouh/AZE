package at.ac.fhcampuswien.AZEApplication;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.util.Duration.seconds;

public class userTimesheetController implements Initializable{
    @FXML private ImageView timesheetArt;
    @FXML private Button logoutButton;
    @FXML private Button detailedCoverageButton;

    @FXML
    protected void fastCoverageButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("userDashboard.fxml")));
        Stage registerStage = (Stage) logoutButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }

    @FXML
    protected void closeButtonOnClick(ActionEvent event) throws IOException {
        user.changeName(""); //for it to 'log out and forget the username'

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        Stage registerStage = (Stage) logoutButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }

    @FXML
    protected void detailedCoverageButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("userDashboard2.fxml")));
        Stage registerStage = (Stage) detailedCoverageButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //load the picture from images.
        File registerImageFile = new File("Images/timesheetArt.png");
        Image registerImage = new Image(registerImageFile.toURI().toString());
        timesheetArt.setImage(registerImage);

    }

}

