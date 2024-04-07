package org.example.cipherguard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    public Button createAccountButton;
    public TextField passwordField;
    public TextField emailField;
    public TextField usernameField;
    @FXML
    VBox root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void handleCreateNewAccountButton(ActionEvent actionEvent) throws IOException {

        String inputUsername = usernameField.getText();
        String inputEmail = emailField.getText();
        String inputPassword = passwordField.getText();

        // Database connection and data retrieval
        String sql = "INSERT INTO `userinfo` (`Username`, `Email`, `Password`) VALUES (?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cipherguard?useSSL=false", "root", "")) {
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    // Set parameters for the prepared statement
                    pst.setString(1, inputUsername);
                    pst.setString(2, inputEmail);
                    pst.setString(3, inputPassword);

                    // Execute the query
                    pst.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 335, 600);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

        System.out.println("Registered");


    }
}
