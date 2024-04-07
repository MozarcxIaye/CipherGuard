package org.example.cipherguard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class NewPasswordController implements Initializable {

    @FXML
    VBox root;

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField passwordLengthField;

    @FXML
    private Slider lengthSlider;

    @FXML
    private CheckBox checkNumber;

    @FXML
    private CheckBox checkLowerCase;

    @FXML
    private CheckBox checkSymbol;

    @FXML
    private CheckBox checkUpperCase;

    private int userId; // Added variable to store logged-in user's UserId

    public void initializeWithUserId(int userId){
        this.userId = userId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generatePassword();
        System.out.println(userId + " userid in new password controller");

        lengthSlider.setValue(8);

        // Add listener to the length slider
        lengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the password length field with the slider value
            passwordLengthField.setText(String.valueOf(newValue.intValue()));
        });

        // Add listener to the password length field
        passwordLengthField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Ensure the new value is a valid integer
            try {
                int newLength = Integer.parseInt(newValue);
                // Update the slider value with the new length
                lengthSlider.setValue(newLength);
            } catch (NumberFormatException e) {
                // Handle invalid input if necessary
                // For example, reset the password length field to the previous valid value
                passwordLengthField.setText(oldValue);
            }
        });
    }

    public void handlePrevPageBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("passwords.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 335, 600);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
    }
    public void handlePrevPageBtn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("passwords.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 335, 600);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
    }

    public void handleRegeneratePasswordBtn(ActionEvent actionEvent) {
        generatePassword();
    }

    public void generatePassword() {
        int len = Integer.parseInt(passwordLengthField.getText());
//        System.out.println(len);

        List<String> characters = new ArrayList<>();
        if (checkLowerCase.isSelected()) {
            characters.addAll(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "y", "z"));
        }
        if (checkUpperCase.isSelected()) {
            characters.addAll(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z"));
        }
        if (checkNumber.isSelected()) {
            characters.addAll(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        }
        if (checkSymbol.isSelected()) {
            characters.addAll(Arrays.asList("!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "[", "]", "{", "}", "|", ";", ":", "'", "\"", ",", ".", "/", "<", ">", "?"));
        }

        List<String> passwordGenerated = new ArrayList<>();
        Random randomIndex = new Random();
        for (int i = 0; i < len; i++) {
            int index = randomIndex.nextInt(characters.size());
            passwordGenerated.add(characters.get(index));
        }
        String generatedPassword = String.join("", passwordGenerated);

        // Print the generated password
        passwordField.setText(generatedPassword);
    }

    public void handleAddPassword(ActionEvent actionEvent) throws IOException {

        String inputName = nameField.getText();
        String inputUsername = usernameField.getText();
        String password = passwordField.getText();

        if (inputName.isEmpty() || inputUsername.isEmpty()) {
            System.out.println("Please enter the name and username");
            // Handle UI feedback for missing input fields if needed
        } else {
            try {
                // Establish database connection
                String jdbcUrl = "jdbc:mysql://localhost:3306/cipherguard";
                String username = "root";
                String pass = "";
                Connection connection = DriverManager.getConnection(jdbcUrl, username, pass);

                // Prepare SQL statement to insert password data
                String sql = "INSERT INTO userspasswordinfo (UserId, Name, Username, Password) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                System.out.println(userId);
                statement.setInt(1, userId); // Set the UserId
                statement.setString(2, inputName);
                statement.setString(3, inputUsername);
                statement.setString(4, password);

                // Execute the query
                statement.executeUpdate();

                // Close resources
                statement.close();
                connection.close();

                // Clear input fields
                nameField.clear();
                usernameField.clear();
                passwordField.clear();

                // Redirect to the previous page
                handlePrevPageBtn(actionEvent);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}























//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.CheckBox;
//import javafx.scene.control.Slider;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.net.URL;
//import java.sql.*;
//import java.util.*;
//
//public class NewPasswordController implements Initializable {
//    public Button prevPage;
//    private String inputName;
//    private String inputUsername;
//    private String password;
//    private String passwordLength;
//
//    @FXML
//    VBox root;
//
//    public TextField nameField;
//    public TextField usernameField;
//    public TextField passwordField;
//    public TextField passwordLengthField;
//    public Slider lengthSlider;
//    public CheckBox checkNumber;
//    public CheckBox checkLowerCase;
//    public CheckBox checkSymbol;
//    public CheckBox checkUpperCase;
//    public Button regenerateBtn;
//    public Button addBtn;
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        generatePassword();
//
//        lengthSlider.setValue(8);
//
//        // Add listener to the length slider
//        lengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
//            // Update the password length field with the slider value
//            passwordLengthField.setText(String.valueOf(newValue.intValue()));
//        });
//
//        // Add listener to the password length field
//        passwordLengthField.textProperty().addListener((observable, oldValue, newValue) -> {
//            // Ensure the new value is a valid integer
//            try {
//                int newLength = Integer.parseInt(newValue);
//                // Update the slider value with the new length
//                lengthSlider.setValue(newLength);
//            } catch (NumberFormatException e) {
//                // Handle invalid input if necessary
//                // For example, reset the password length field to the previous valid value
//                passwordLengthField.setText(oldValue);
//            }
//        });
//
//    }
//
//
//    public void handlePrevPageBtn(ActionEvent actionEvent) throws IOException {
//
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("passwords.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 335, 600);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//    }
//    public void handlePrevPageBtn() throws IOException {
//
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("passwords.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 335, 600);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//    }
//
//    public void handleRegeneratePasswordBtn(ActionEvent actionEvent) {
//        generatePassword();
//    }
//
//    public void generatePassword() {
//        int len = Integer.parseInt(passwordLengthField.getText());
//        System.out.println(len);
//
//        List<String> characters = new ArrayList<>();
//        if (checkLowerCase.isSelected()) {
//            characters.addAll(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "y", "z"));
//        }
//        if (checkUpperCase.isSelected()) {
//            characters.addAll(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z"));
//        }
//        if (checkNumber.isSelected()) {
//            characters.addAll(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
//        }
//        if (checkSymbol.isSelected()) {
//            characters.addAll(Arrays.asList("!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "[", "]", "{", "}", "|", ";", ":", "'", "\"", ",", ".", "/", "<", ">", "?"));
//        }
//
//        List<String> passwordGenerated = new ArrayList<>();
//        Random randomIndex = new Random();
//        for (int i = 0; i < len; i++) {
//            int index = randomIndex.nextInt(characters.size());
//            passwordGenerated.add(characters.get(index));
//        }
//        String generatedPassword = String.join("", passwordGenerated);
//
//        // Print the generated password
//        passwordField.setText(generatedPassword);
//    }
//
//    public void handleAddPassword(ActionEvent actionEvent) throws IOException {
//        inputName = nameField.getText();
//        inputUsername = usernameField.getText();
//        password = passwordField.getText();
//
//        if (nameField.getText().isEmpty() || usernameField.getText().isEmpty()) {
//            System.out.println("Please enter the name and username");
//            if (nameField.getText().isEmpty()) {
//                nameField.setStyle("-fx-background-color: transparent; -fx-border-color: EC4338; -fx-border-width: 0 0 1 0; -fx-prompt-text-fill: EC4338;");
//            } else{
//                nameField.setStyle("-fx-background-color: transparent; -fx-border-color: green; -fx-border-width: 0 0 1 0; -fx-text-fill: Black;");
//            }
//            if (usernameField.getText().isEmpty()) {
//                usernameField.setStyle("-fx-background-color: transparent; -fx-border-color: EC4338; -fx-border-width: 0 0 1 0; -fx-prompt-text-fill: EC4338;");
//            }else{
//                usernameField.setStyle("-fx-background-color: transparent; -fx-border-color: green; -fx-border-width: 0 0 1 0; -fx-text-fill: Black;");
//            }
//        } else {
//            // Proceed with inserting data into the database
//
//            // Database connection and data retrieval
//            String sql = "INSERT INTO `userspasswordinfo` (`Name`, `Username`, `Password`) VALUES (?, ?, ?)";
//
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//
//                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cipherguard?useSSL=false", "root", "")) {
//                    try (PreparedStatement pst = con.prepareStatement(sql)) {
//                        // Set parameters for the prepared statement
//                        pst.setString(1, inputName);
//                        pst.setString(2, inputUsername);
//                        pst.setString(3, password);
//
//                        // Execute the query
//                        pst.executeUpdate();
//                    }
//                }
//            } catch (SQLException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            nameField.clear();
//            usernameField.clear();
//            passwordField.clear();
//
//            handlePrevPageBtn();
//        }
//    }
//
//
//}