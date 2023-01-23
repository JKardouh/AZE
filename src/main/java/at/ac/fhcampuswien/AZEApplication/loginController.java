package at.ac.fhcampuswien.AZEApplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML private ImageView logoImageView;
    @FXML private ImageView loginArtImageView;
    @FXML private Button cancelButton;
    @FXML private Label loginMessageLabel;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;
    //without this we cant get the pictures

    /**
     * is called when fxml file is loaded.
     * loads the images
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Images/Logo.png");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);


        File loginArtImageFile = new File("Images/loginart.jpg");
        Image loginArtImage = new Image(loginArtImageFile.toURI().toString());
        loginArtImageView.setImage(loginArtImage);
    }


    /**
     * when clicking the cancel button, this should close the window
     */
    @FXML
    protected void cancelButtonOnClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    /**
     * is called when clicking the login button
     */
    public void loginButtonOnClick(ActionEvent event) throws SQLException, IOException {
        if(!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) validateLogin();
        else loginMessageLabel.setText("please enter your credentials!");
    }


    /**
     * Is called when the login button is clicked.
     * Checks if credentials entered match any in the database.
     */
    @FXML
    protected void validateLogin() throws SQLException, IOException {
        databaseConnector connector = new databaseConnector();
        Connection connectDB = connector.getConnection();
        String verifyLogin = "SELECT count(1) FROM worker_account WHERE username = '"+ usernameTextField.getText() +"' and password = '"+ passwordTextField.getText() +"'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(verifyLogin);

        while (queryResult.next()){
            if(queryResult.getInt(1)== 1){
                //Set the current username!
                user user = new user(usernameTextField.getText());
                createUserDashboard();
            }
            else loginMessageLabel.setText("Incorrect username or password, please try again");
        }
    }

    /**
     * If login is successful, this will load the user dashboard scene.
     */
    private void createUserDashboard() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("userDashboard.fxml")));
        Stage userDashboardStage = (Stage) cancelButton.getScene().getWindow();
        userDashboardStage.setScene(new Scene(root, 800, 588));
    }


    /**
     * Is called when the register button is clicked.
     */
    public void registerButtonOnClick(ActionEvent event) throws IOException {
        //createAccountForm
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        Stage registerStage = (Stage) cancelButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }
}