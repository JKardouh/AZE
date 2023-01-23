package at.ac.fhcampuswien.AZEApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class userTimesheetController implements Initializable{
    @FXML private ImageView timesheetArt;
    @FXML private Button logoutButton;
    @FXML private Button detailedCoverageButton;
    @FXML private Button exportButton;
    @FXML private TableView<userData> timesheetTable;
    @FXML private TableColumn <userData,String> col_username;
    @FXML private TableColumn <userData,String> col_event_type;
    @FXML private TableColumn <userData,String> col_date;
    @FXML private TableColumn <userData,String> col_comment;
    ObservableList<userData> list;

    /**
     * Goes to fast coverage page.
     * @throws IOException which is thrown if something happens.
     */
    @FXML
    protected void fastCoverageButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("userDashboard.fxml")));
        Stage registerStage = (Stage) logoutButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }

    /**
     * Works like a refresh button to get data for the timesheet table.
     */
    @FXML
    protected void exportButtonOnClick(ActionEvent event){
        DisplayData();
    }

    /**
     * list that allows listeners to track changes when they occur.
     * @return the list of observable items.
     */
    public static ObservableList<userData> getUserData(){
        ObservableList<userData> list = FXCollections.observableArrayList();

        try {
            databaseConnector connector = new databaseConnector();
            connector.getConnection();
            String username = user.getUsername();
            PreparedStatement ps = connector.databaseLink.prepareStatement("SELECT * FROM timesheet_table WHERE username = '"+username+ "';");

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                list.add(new userData(resultSet.getString("username"),
                        resultSet.getString("event_type"),
                        resultSet.getString("date"),
                        resultSet.getString("comment")));
            }
        }catch (SQLException ignored) {
        }
        return list;
    }

    /**
     * Logs user out, and heads back to log in main page.
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
     * Initializes the picture used in the scene. and displays data in the database table UI.
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
        //load the picture from images.
        File registerImageFile = new File("Images/timesheetArt.png");
        Image registerImage = new Image(registerImageFile.toURI().toString());
        timesheetArt.setImage(registerImage);

        DisplayData();
    }

    /**
     * Displays the data of specific user.
     */
    private void DisplayData() {
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_event_type.setCellValueFactory(new PropertyValueFactory<>("event_type"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_comment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        list = getUserData();
        timesheetTable.setItems(list);
    }

}

