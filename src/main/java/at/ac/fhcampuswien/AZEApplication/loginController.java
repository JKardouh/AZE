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
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML private ImageView logoImageView;
    @FXML private ImageView loginArtImageView;
    @FXML private Button cancelButton;
    @FXML private Label loginMessageLabel;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;

    //without this we cant get the pictures because they need to be serialized.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Images/Logo.png");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);

        File loginArtImageFile = new File("Images/loginart.jpg");
        Image loginArtImage = new Image(loginArtImageFile.toURI().toString());
        loginArtImageView.setImage(loginArtImage);
    }

    @FXML
    protected void cancelButtonOnClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnClick(ActionEvent event) throws SQLException, IOException {
        if(!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) ValidateLogin();
        else loginMessageLabel.setText("please enter your credentials!");
    }

    @FXML
    protected void ValidateLogin() throws SQLException, IOException {
        databaseConnector connector = new databaseConnector();
        Connection connectDB = connector.getConnection();
        String verifyLogin = "SELECT count(1) FROM worker_account WHERE username = '"+ usernameTextField.getText() +"' and password = '"+ passwordTextField.getText() +"'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(verifyLogin);

        while (queryResult.next()){
            if(queryResult.getInt(1)== 1){
                //Set the current username!
                user user = new user(usernameTextField.getText());
                CreateUserDashboard();
            }
            else loginMessageLabel.setText("Incorrect username or password, please try again");
        }
    }

    private void CreateUserDashboard() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userDashboard.fxml"));
        Stage userDashboardStage = (Stage) cancelButton.getScene().getWindow();
        userDashboardStage.setScene(new Scene(root, 800, 588));
    }

    public void registerButtonOnClick(ActionEvent event) throws IOException {
        //createAccountForm
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Stage registerStage = (Stage) cancelButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }
}