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
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * class/ controller for the employee registration form.
 */
public class registerController implements Initializable {

    @FXML private Button closeButton;
    @FXML private ImageView registerImageView;
    @FXML private Label registerMessageLabel;
    @FXML private TextField firstnameTextField;
    @FXML private TextField lastnameTextField;
    @FXML private TextField usernameTextField;
    @FXML private Label confirmPasswordLabel;
    @FXML private PasswordField passwordTextField;
    @FXML private PasswordField confirmPasswordTextField;

    /**
     * Initializes the stage, This is basically what lets the pictures get displayed.
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
        File registerImageFile = new File("Images/registerArt.png");
        Image registerImage = new Image(registerImageFile.toURI().toString());
        registerImageView.setImage(registerImage);
    }

    /**
     * Goes back to log in page (like a go back button).
     * @throws IOException which is thrown if something happens.
     */
    @FXML
    protected void closeButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        Stage registerStage = (Stage) closeButton.getScene().getWindow();
        registerStage.setScene(new Scene(root, 800, 588));
    }

    /**
     * Handles the register logic with the text fields. and prints status for confirmation.
     * @throws SQLException which is thrown if something happens.
     */
    public void registerButtonOnClick(ActionEvent event) throws SQLException {
        if(passwordTextField.getText().equals(confirmPasswordTextField.getText()) && !firstnameTextField.getText().isBlank() && !lastnameTextField.getText().isBlank() && !usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()){
            if(!checkIfUsernameExistsAlready()){ //this was causing java.lang.reflect.InvocationTargetException
                registerEmployee();
            }
            else confirmPasswordLabel.setText("User Already Exists!");
        }else confirmPasswordLabel.setText("Unable to complete action, please check all entries.");
    }

    /**
     * checks if user exists already from database.
     * @return a boolean if a user exists or not.
     * @throws SQLException Throws exception in case anything wrong happens.
     */
    private boolean checkIfUsernameExistsAlready() throws SQLException {
        databaseConnector connector = new databaseConnector();
        Connection connect = connector.getConnection();

        String result = "SELECT count(1) FROM worker_account WHERE username = '"+ usernameTextField.getText() +"';";
        Statement statement = connect.createStatement();
        ResultSet queryResult = statement.executeQuery(result);

        if (queryResult.next()) return queryResult.getInt(1) == 1;
        return false;
    }

    /**
     * Registers the employee text field inputted info through database connection.
     * @throws SQLException which is thrown if something happens.
     */
    public void registerEmployee() throws SQLException {
        confirmPasswordLabel.setText("");

        databaseConnector connector = new databaseConnector();
        Connection connect = connector.getConnection();

        String insertStatement = "INSERT INTO worker_account(firstname, lastname, username, password) VALUES ('"+
                firstnameTextField.getText()+"', '"+
                lastnameTextField.getText() + "', '"+
                usernameTextField.getText() +"', '"+
                passwordTextField.getText() +"');";

        Statement statement = connect.createStatement();
        statement.executeUpdate(insertStatement);
        registerMessageLabel.setText("Employee registered! you can go back to login Page!");
        connect.close();
    }
}

