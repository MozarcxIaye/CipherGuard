package org.example.cipherguard;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.nio.BufferUnderflowException;

public class AddressForm extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Address Book - Add");
//        using Grid pane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

//        adding the components
        Label forenameLabel = new Label("Forename: ");
        TextField forenameTextField = new TextField();
        grid.add(forenameLabel, 0, 0);
        grid.add(forenameTextField, 1, 0);

        Label surnameLabel = new Label("Surname: ");
        TextField surnameTextField = new TextField();
        grid.add(surnameLabel, 0, 1);
        grid.add(surnameTextField, 1, 1);

        Label emailLabel = new Label("Email: ");
        TextField emailTextField = new TextField();
        grid.add(emailLabel, 0, 2);
        grid.add(emailTextField, 1, 2);

        Label phoneLabel = new Label("Phone: ");
        TextField phoneTextField = new TextField();
        grid.add(phoneLabel, 0, 3);
        grid.add(phoneTextField, 1, 3);

        Button regis = new Button("Register");
        grid.add(regis, 1, 4);






//        scene and stage
        Scene scene = new Scene(grid, 300, 400);
        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {
        launch(args);

    }
}
