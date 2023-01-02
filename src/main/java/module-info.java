module at.ac.fhcampuswien.javacourse {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens at.ac.fhcampuswien.AZEApplication to javafx.fxml;
    exports at.ac.fhcampuswien.AZEApplication;
}