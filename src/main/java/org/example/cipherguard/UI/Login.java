package org.example.cipherguard.UI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Login {

    public Scene createScene() throws FileNotFoundException {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);

//        add cipherguard logo
        Image img = new Image(new FileInputStream("C:/Users/Katzo/Desktop/CIpherGuard/CIpherGuard/src/public/cipherGuardlogo.png"));
        ImageView imageView = new ImageView(img);
        root.add(imageView,0 ,1);

//        add login title
        Label loginLabel = new Label("LOGIN");
        Label loginDescLabel = new Label("Let’s get you set up with a new account.");
        root.add(loginLabel, 0,2);
        root.add(loginDescLabel, 0,3);

//      email label
        Label emailLabel = new Label("Email");
        TextField emailTextField = new TextField();
        root.add(emailLabel, 0,8);
        root.add(emailTextField, 0,9);

//        passlabel
        Label passLabel = new Label("Phone: ");
        TextField passTextField = new TextField();
        root.add(passLabel, 0,10);
        root.add(passTextField, 0,11);

        Label forgotLabel = new Label("Forgot Password");
        forgotLabel.setTextFill(Color.CORAL);
        root.add(forgotLabel,0, 12);

        Button loginbtn = new Button("Login");
        loginbtn.setBackground(Background.fill(Color.CORAL));
        loginbtn.setTextFill(Color.WHITE);

        Text forgotText = new Text("Don’t have account yet?");
        Label registerText = new Label("Register");
        registerText.setTextFill(Color.CORAL);
        root.add(forgotText, 0, 13);
        root.add(registerText, 0, 14);

        Scene scene = new Scene(root, 335, 600);
        return scene;

    }

}
