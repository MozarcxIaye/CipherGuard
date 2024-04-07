package org.example.cipherguard.UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddPassword extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label addNew = new Label("New Record");
        grid.add(addNew, 0, 0);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);

        Label userIDLabel = new Label("User id:");
        TextField userIDField = new TextField();
        grid.add(userIDLabel, 0, 2);
        grid.add(userIDField, 1, 2);

        Label lengthLabel = new Label("Length:");
        TextField lengthField = new TextField();
        grid.add(lengthLabel, 0, 3);
        grid.add(lengthField, 1, 3);

        CheckBox numbersCheck = new CheckBox("Numbers");
        CheckBox lowerCaseCheck = new CheckBox("Lowercase");
        CheckBox symbolsCheck = new CheckBox("Symbols");
        CheckBox upperCaseCheck = new CheckBox("Uppercase");
        grid.add(numbersCheck, 0, 4);
        grid.add(lowerCaseCheck, 1, 4);
        grid.add(symbolsCheck, 0, 5);
        grid.add(upperCaseCheck, 1, 5);

        Button newRecord = new Button("New record");
        grid.add(newRecord, 0, 6);

        Button regenerate = new Button("Regenerate");
        grid.add(regenerate, 1, 6);

        Label websiteLabel = new Label("Website or app name:");
        TextField websiteField = new TextField();
        grid.add(websiteLabel, 0, 7);
        grid.add(websiteField, 1, 7);

        Label userEmailLabel = new Label("Username or email id:");
        TextField userEmailField = new TextField();
        grid.add(userEmailLabel, 0, 8);
        grid.add(userEmailField, 1, 8);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 9);



        HBox buttons = new HBox(11);
        Button add = new Button("Add");
        Button save = new Button("Save password");
        buttons.getChildren().addAll(add, save);
        grid.add(buttons, 0, 10, 2, 1);

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}