package org.example.cipherguard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.cipherguard.PasswordController;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label forgotPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void handleRegisterButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 335, 600);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
    }

    public void handleLoginButtonAction(ActionEvent actionEvent) throws IOException {
        String inputEmail = emailField.getText().trim();
        String inputPassword = passwordField.getText();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            String jdbcUrl = "jdbc:mysql://localhost:3306/cipherguard";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Prepare SQL statement to check credentials
            String sql = "SELECT UserId FROM userinfo WHERE email = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, inputEmail);
            statement.setString(2, inputPassword);

            // Execute query
            resultSet = statement.executeQuery();

            // Check if the result set has any rows
            if (resultSet.next()) {
                // Credentials are correct
                int userId = resultSet.getInt("UserId");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("passwords.fxml"));
                Scene scene = new Scene(loader.load(), 335, 600);
                PasswordController passwordController = loader.getController();
                passwordController.initializeWithUserId(userId); // Initialize with userId

                // Obtain the instance of NewPasswordController after loading the new_password.fxml file
                FXMLLoader npLoader = new FXMLLoader(getClass().getResource("newrecord.fxml"));
                Scene npScene = new Scene(npLoader.load(), 335, 600);
                NewPasswordController npController = npLoader.getController();
                npController.initializeWithUserId(userId); // Pass userId to NewPasswordController

                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
//                System.out.println(controller);
            } else {
                // Credentials are incorrect
                System.out.println("Incorrect email or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}































//package org.example.cipherguard;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import org.w3c.dom.Text;
//import java.io.IOException;
//import java.sql.*;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class LoginController implements Initializable {
//
//
//
//    @FXML
//    private VBox root;
//
//    @FXML
//    private TextField emailField;
//
//    @FXML
//    private TextField passwordField;
//
//    @FXML
//    private Label forgotPassword;
//
//    @FXML
//    private Button loginButton;
//
//    @FXML
//    private Button registerBtn;
//
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//    }
//
//    public void handleRegisterButtonAction(ActionEvent actionEvent) throws IOException{
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 335, 600);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//    }
//
//    public void handleLoginButtonAction(ActionEvent actionEvent) throws IOException {
//        String inputEmail = emailField.getText().trim();
//        String inputPassword = passwordField.getText();
//
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            // Establish database connection
//            String jdbcUrl = "jdbc:mysql://localhost:3306/cipherguard";
//            String username = "root";
//            String password = "";
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
//
//            // Prepare SQL statement to check credentials
//            String sql = "SELECT * FROM userinfo WHERE email = ? AND password = ?";
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, inputEmail);
//            statement.setString(2, inputPassword);
//
//            // Execute query
//            resultSet = statement.executeQuery();
//
//            // Check if the result set has any rows
//            if (resultSet.next()) {
//                // Credentials are correct
//                System.out.println("Logged in");
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("passwords.fxml"));
//                Scene scene = new Scene(fxmlLoader.load(), 335, 600);
//                Stage stage = (Stage) root.getScene().getWindow();
//                stage.setScene(scene);
//            } else {
//                // Credentials are incorrect
//                System.out.println("Incorrect email or password");
//            }
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        } finally {
//            // Close resources
//            try {
//                if (resultSet != null) resultSet.close();
//                if (statement != null) statement.close();
//                if (connection != null) connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
