package at.ac.fhcampuswien.AZEApplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.ResourceBundle;

public class userDashboard2Controller implements Initializable{
    @FXML private ImageView timesheetArt;
    @FXML private Button logoutButton;
    @FXML private Button timesheetButton;
    @FXML private Label queryStatusLabel;
    @FXML private Label warningLabel;
    @FXML private ChoiceBox<String> eventChoiceBox2;
    @FXML private DatePicker datePicker;
    @FXML private TextField timeTextField;
    @FXML private TextField commentTextField;
    private final String[] eventType = {"come", "Go", "Home-office start","Home-office end"};

    @FXML
    protected void closeButtonOnClick(ActionEvent event) throws IOException {
        user.changeName(""); //for it to 'log out and forget the username'

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        Stage registerStage = (Stage) logoutButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }

    @FXML
    protected void fastCoverageButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("userDashboard.fxml")));
        Stage registerStage = (Stage) logoutButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }

    @FXML
    protected void timesheetButtonOnClick(ActionEvent event) throws IOException {
        user.changeName(""); //for it to 'log out and forget the username'

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("userTimesheet.fxml")));
        Stage registerStage = (Stage) logoutButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }

    @FXML
    protected void saveDetailedCoverageButtonOnClick(ActionEvent event) throws SQLException {
        String username = user.getUsername();

        //event type choice
        String eventChoice =  eventChoiceBox2.getValue();
        if(eventChoice == null) eventChoice = "come";

        //Picked time
        String time = timeTextField.getText();

        //date
        String date;
        if(datePicker.getValue() == null) date = "";
        else date= datePicker.getValue().toString();
        //comment
        String comment = commentTextField.getText();

        String formattedDateTime = date + " " + time;
        String insertQuery = "INSERT INTO timesheet_table (username, event_type, date, comment) VALUES ('"+ username+ "','"+ eventChoice +"','"+ formattedDateTime +"', '"+comment+"');";

        validateQuery(formattedDateTime, insertQuery);

        // clear this entry!! or else it will keep remembering old values and will still be able to save wrong/unwanted data in DB.
        timeTextField.setText("");
    }

    private void validateQuery(String formattedDateTime, String insertQuery) throws SQLException {
        if (isValidDate(formattedDateTime) && isNotInFuture(formattedDateTime)){
            databaseConnector connector = new databaseConnector();
            Connection connect = connector.getConnection();
            Statement statement = connect.createStatement();
            statement.executeUpdate(insertQuery);
            connect.close();
            warningLabel.setText("");
            queryStatusLabel.setText("event successfully saved!");
        }else{
            queryStatusLabel.setText("");
            warningLabel.setText("");
            if(!isValidDate(formattedDateTime)) warningLabel.setText("Something went Wrong, Please check your inputs");
            else warningLabel.setText("Time cannot be in the future!");
        }
    }

    private boolean isNotInFuture(String formattedDateTime) {
        boolean result = false;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(formattedDateTime, formatter);
            result = dateTime.isBefore(LocalDateTime.now());
        }catch (DateTimeParseException ignored){}
        return result;
    }

    public static boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //load the picture from images.
        File registerImageFile = new File("Images/timesheetArt.png");
        Image registerImage = new Image(registerImageFile.toURI().toString());
        timesheetArt.setImage(registerImage);

        //add choices to the drop-down menu
        eventChoiceBox2.getItems().addAll(eventType);
        eventChoiceBox2.setOnAction(this::getEventType);
    }

    private void getEventType(ActionEvent event) {
       String eventType = eventChoiceBox2.getValue();
    }

    public void getDate(ActionEvent event) {
        LocalDate date = datePicker.getValue();
    }
}

