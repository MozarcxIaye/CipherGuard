package org.example.cipherguard.UI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create UI elements
        Label password = new Label("Passwords");

        Label appleLabel = new Label("Apple:");
        TextField appleField = new TextField("moz@gmail.com");

        Label codepenLabel = new Label("Codepen:");
        TextField codepenField = new TextField("moz12@gmail.com");

        Label figmaLabel = new Label("Figma:");
        TextField figmaField = new TextField("moz.iaye@gmail.com");

        Label passwordsLabel = new Label("Passwords:");
        PasswordField passwordsField = new PasswordField();

        Label canvaLabel = new Label("Canva:");
        TextField canvaField = new TextField("moz.iaye12@gmail.com");

        Label facebookLabel = new Label("Facebook:");
        TextField facebookField = new TextField("moz.iaye0@gmail.com");

        Button searchButton = new Button("SEARCH");
        Button homeButton = new Button("HOME");
        Button plusButton = new Button("+");

        HBox btnContainer = new HBox();
        btnContainer.getChildren().addAll(searchButton, homeButton, plusButton);

        // Add UI elements to layout
        VBox layout = new VBox();
        layout.getChildren().addAll(password,
                appleLabel, appleField,
                codepenLabel, codepenField,
                figmaLabel, figmaField,
                passwordsLabel, passwordsField,
                canvaLabel, canvaField,
                facebookLabel, facebookField,
                btnContainer
        );

        // Create scene and add layout
        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}