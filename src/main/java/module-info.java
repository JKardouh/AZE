module at.ac.fhcampuswien.javacourse {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens at.ac.fhcampuswien.AZEApplication to javafx.fxml;
    exports at.ac.fhcampuswien.AZEApplication;
}