package at.ac.fhcampuswien.AZEApplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class registerController implements Initializable {

    @FXML
    private ImageView registerImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File registerImageFile = new File("Images/registerArt.png");
        Image registerImage = new Image(registerImageFile.toURI().toString());
        registerImageView.setImage(registerImage);
    }
}
