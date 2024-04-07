module org.example.cipherguard {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;

    opens org.example.cipherguard to javafx.fxml;
    exports org.example.cipherguard;
    exports org.example.cipherguard.UI;
    opens org.example.cipherguard.UI to javafx.fxml;
}