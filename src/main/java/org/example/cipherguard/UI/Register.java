package org.example.cipherguard.UI;



import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Register {
    public Scene createScene() {
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);

        // Add cipherguard logo
        Image img;
        try {
            img = new Image(new FileInputStream("C:/Users/Katzo/Desktop/CIpherGuard/CIpherGuard/src/public/cipherGuardlogo.png"));
            ImageView imageView = new ImageView(img);
            root.add(imageView, 0, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Adding the heading
        Text heading = new Text("ALL YOUR \n\nPASSWORDS ARE \n\nHERE.");
        heading.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        root.add(heading, 0, 5);

        Text subheading = new Text("Store and manage all your passwords from \none place. Don’t remember hundreds of \npasswords, just remember one.");
        subheading.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        subheading.setFill(Color.DIMGRAY);
        root.add(subheading, 0, 6);

        Button regisBtn = new Button("Register");
        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
            try {
                changeScene(loginBtn);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        root.add(regisBtn, 0, 8);
        root.add(loginBtn, 0, 9);

        Scene scene = new Scene(root, 335, 600);
        return scene;
    }

    private void changeScene(Button loginBtn) throws FileNotFoundException {
        // Create a new instance of the Login class and get the scene for the login page
        Login loginPage = new Login();
        Scene loginScene = loginPage.createScene();

        // Get the current stage and set the new scene
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.setScene(loginScene);
    }
}

