package org.example.cipherguard;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoaderController implements Initializable {

    @FXML
    private VBox root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add loading delay
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // Load the next scene after the delay
                        Platform.runLater(() -> {
                            try {
                                // Load the main scene (replace "newrecord.fxml" with the name of your next FXML file)
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                                Scene scene = new Scene(fxmlLoader.load(), 335, 600);
                                Stage stage = (Stage) root.getScene().getWindow();
                                stage.setScene(scene);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                },
                3000 // Delay in milliseconds (3 seconds)
        );
    }
}