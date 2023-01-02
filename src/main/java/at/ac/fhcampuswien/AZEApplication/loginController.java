package at.ac.fhcampuswien.AZEApplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView logoImageView;

    @FXML
    private ImageView loginArtImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    protected void cancelButtonOnClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

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

    public void loginButtonOnClick(ActionEvent event){
        if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false){
            ValidateLogin();
        }
        else {
            loginMessageLabel.setText("please enter your credentials!");
        }
    }

    @FXML
    protected void ValidateLogin(){
        databaseConnector connector = new databaseConnector();
        Connection connectDB = connector.getConnection();
        String verifyLogin = "SELECT count(1) FROM worker_account WHERE username = '"+ usernameTextField.getText() +"' and password = '"+ passwordTextField.getText() +"'";
        //loginMessageLabel.setText("Invalid username or password");

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if(queryResult.getInt(1)== 1) loginMessageLabel.setText("YOU SHALL ENTER");
                else loginMessageLabel.setText("Incorrect username or password, please try again");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}