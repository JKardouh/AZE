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

/**
 * class/ controller for the fast coverage view (first menu item on the left)
 */
public class userDashboardController implements Initializable{
    @FXML private ImageView timesheetArt;
    @FXML private Button logoutButton;
    @FXML private Button detailedCoverageButton;
    @FXML private Button timesheetButton;
    @FXML private Label timeLabel;
    @FXML private Label queryStatusLabel;
    @FXML private ChoiceBox<String> eventChoiceBox;
    @FXML private DatePicker datePicker;
    private final String[] eventType = {"come", "Go", "Home-office start","Home-office end"};

    /**
     * Closes current page (logs user out) and goes to log in page.
     * @throws IOException which is thrown if something happens.
     */
    @FXML
    protected void closeButtonOnClick(ActionEvent event) throws IOException {
        user.changeName(""); //for it to 'log out and forget the username'

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        Stage registerStage = (Stage) logoutButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }

    /**
     * Goes to timesheet page.
     * @throws IOException which is thrown if something happens.
     */
    @FXML
    protected void timesheetButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("userTimesheet.fxml")));
        Stage registerStage = (Stage) logoutButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }

    /**
     * Goes to detailed coverage page.
     * @throws IOException which is thrown if something happens.
     */
    @FXML
    protected void detailedCoverageButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("userDashboard2.fxml")));
        Stage registerStage = (Stage) detailedCoverageButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }

    /**
     * Saves the fast coverage data from user (employee time stamp) in the database.
     * @throws SQLException which is thrown if something happens.
     */
    @FXML
    protected void saveButtonOnClick(ActionEvent event) throws SQLException {
        databaseConnector connector = new databaseConnector();
        Connection connect = connector.getConnection();

        // How it knows which user it has to use for query.
        String username = user.getUsername();

        // event choice (come? go? etc...)
        String eventChoice =  eventChoiceBox.getValue();
        if(eventChoice == null) eventChoice = "come";

        //Current time
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Europe/Berlin"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);

        // put all info in query.
        String insertQuery = "INSERT INTO timesheet_table (username, event_type, date, comment) VALUES ('"+ username+ "','"+ eventChoice +"','"+ formattedDateTime +"', '');";

        Statement statement = connect.createStatement();
        statement.executeUpdate(insertQuery);

        // clean up connection.
        connect.close();
        queryStatusLabel.setText("event successfully saved!");
    }

    /**
     * initializes the page picture and the live clock.
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //start the clock
        initClock();
        //load the picture from images.
        File registerImageFile = new File("Images/timesheetArt.png");
        Image registerImage = new Image(registerImageFile.toURI().toString());
        timesheetArt.setImage(registerImage);

        //add choices to the drop-down menu
        eventChoiceBox.getItems().addAll(eventType);
        eventChoiceBox.setOnAction(this::getEventType);
    }

    /**
     * Gets the event type from the drop-down.
     */
    private void getEventType(ActionEvent event) {
        String eventType = eventChoiceBox.getValue();
    }

    /**
     * Responsible for showing the live clock every second. (current time)
     */
    private void initClock() {
        // reference https://stackoverflow.com/questions/42383857/javafx-live-time-and-date last visit 22.01.2023
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            timeLabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}

