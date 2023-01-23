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
    int index = -1;


    @FXML
    protected void fastCoverageButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("userDashboard.fxml")));
        Stage registerStage = (Stage) logoutButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }

    @FXML
    protected void exportButtonOnClick(ActionEvent event) throws SQLException {
        displayData();
    }

    public static ObservableList<userData> getUserData(){
        ObservableList<userData> list = FXCollections.observableArrayList();

        try {
            databaseConnector connector = new databaseConnector();
            Connection connect = connector.getConnection();
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
        return  list;
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

        displayData();

    }

    private void displayData() {
        col_username.setCellValueFactory(new PropertyValueFactory<userData, String>("username"));
        col_event_type.setCellValueFactory(new PropertyValueFactory<userData, String>("event_type"));
        col_date.setCellValueFactory(new PropertyValueFactory<userData, String>("date"));
        col_comment.setCellValueFactory(new PropertyValueFactory<userData, String>("comment"));

        list = getUserData();
        timesheetTable.setItems(list);
    }

}

