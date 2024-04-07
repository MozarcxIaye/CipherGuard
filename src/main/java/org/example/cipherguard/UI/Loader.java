package org.example.cipherguard.UI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;

public class Loader extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Image img = new Image(new FileInputStream("C:/Users/Katzo/Desktop/CIpherGuard/CIpherGuard/src/public/cipherGuardlogo.png"));
        ImageView imageView = new ImageView(img);
        GridPane root = new GridPane();

        Scene scene = new Scene(root, 335, 600);
        root.setAlignment(Pos.CENTER);
        root.add(imageView, 0, 0);

        stage.setScene(scene);
        stage.setTitle("CipherGuard");
        stage.show();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    changeScene(stage);
                });
            }
        }, 3000);
    }

    private void changeScene(Stage stage) {

        // Create a new instance of the Register class and launch it
        Register registerPage = new Register();
        Scene registerScene = registerPage.createScene();
        stage.setScene(registerScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
