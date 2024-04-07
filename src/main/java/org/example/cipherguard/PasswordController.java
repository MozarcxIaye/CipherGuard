package org.example.cipherguard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class PasswordController implements Initializable {

    public Button addPasswordBtn;
    @FXML
    private VBox root;

    @FXML
    private VBox cardContainer;

    private ObservableList<UserData> userDataList = FXCollections.observableArrayList();

    private int userId; // Added variable to store logged-in user's UserId



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initializeWithUserId(int userId){
        // Database retrieval and UI update
        this.userId = userId;
        System.out.println(userId + " userid in password controller");
        retrieveDataAndDisplayCards();
    }

    public int getUserId() {
        return this.userId;
    }

    private void retrieveDataAndDisplayCards() {
        // Database connection and data retrieval
        try {
            String sql = "SELECT * from userspasswordinfo WHERE UserId = ?";
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cipherguard?useSSL=false", "root", "")) {
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    System.out.println(userId + " userid in password controller");
                    pst.setInt(1, userId); // Set the UserId parameter
                    try (ResultSet rs = pst.executeQuery()) {
                        updateUIWithResultSet(rs);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateUIWithResultSet(ResultSet rs) throws SQLException {
        // Clear existing cards
        cardContainer.getChildren().clear();

        while (rs.next()) {
            int Sn = rs.getInt("Sn");
            String name = rs.getString("Name");
            String username = rs.getString("Username");

            // Create card
            HBox card = createCard(name, username, Sn);
            cardContainer.getChildren().add(card);
        }
    }

    private HBox createCard(String name, String username, int Sn) {
        HBox card = new HBox();
        VBox cardinfo = new VBox();
        cardinfo.getStyleClass().add("card");

        Button editBtn = new Button("Edit");
        Button delBtn = new Button("Delete");
        delBtn.setOnAction((e) -> handleDeletePasswordButton(Sn));

        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
        Label usernameLabel = new Label(username);
        cardinfo.getChildren().addAll(nameLabel, usernameLabel);
        cardinfo.setPrefWidth(150);

        card.getChildren().addAll(cardinfo, editBtn, delBtn);
        card.setStyle("-fx-border-color: black; -fx-padding: 3px; -fx-border-radius: 7px;");
        card.setPrefWidth(270);
        card.setSpacing(8);
        card.setAlignment(Pos.CENTER);

        return card;
    }

    private void handleDeletePasswordButton(int Sn) {
        System.out.println("clicked");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cipherguard?useSSL=false", "root", "")) {
            String sql = "DELETE FROM userspasswordinfo WHERE `userspasswordinfo`.`Sn` = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, Sn);
                pst.executeUpdate();
                // Refresh UI after deletion
                retrieveDataAndDisplayCards();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleAddPasswordButton(ActionEvent actionEvent) throws IOException {
        System.out.println("clicked");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newrecord.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 335, 600);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
    }
}


























//package org.example.cipherguard;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//
//import java.awt.*;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.*;
//import java.util.ResourceBundle;
//
//import static java.awt.Color.black;
//
//
//public class PasswordController implements Initializable {
//
//    public Button addPasswordBtn;
//    @FXML
//    private VBox root;
//
//    @FXML
//    private VBox cardContainer;
//
//    private ObservableList<UserData> userDataList = FXCollections.observableArrayList();
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        // Database retrieval and UI update
//        retrieveDataAndDisplayCards();
//    }
//
//    private void retrieveDataAndDisplayCards() {
//        // Database connection and data retrieval
//        try {
//            String sql = "SELECT * from userspasswordinfo";
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cipherguard?useSSL=false", "root", "")) {
//                try (PreparedStatement pst = con.prepareStatement(sql)) {
//                    try (ResultSet rs = pst.executeQuery()) {
//                        updateUIWithResultSet(rs);
//                    }
//                }
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateUIWithResultSet(ResultSet rs) throws SQLException {
//        // Clear existing cards
//        cardContainer.getChildren().clear();
//
//        while (rs.next()) {
//            int Sn = rs.getInt("Sn");
//            int UserId = rs.getInt("UserId");
//            String name = rs.getString("Name");
//            String username = rs.getString("Username");
//
//            // Create card
//            HBox card = createCard(name, username, Sn);
//            cardContainer.getChildren().add(card);
//        }
//    }
//
//    private HBox createCard(String name, String username, int Sn) {
//        HBox card = new HBox();
//        VBox cardinfo = new VBox();
//        cardinfo.getStyleClass().add("card");
//
//        Button editBtn = new Button("Edit");
//        Button delBtn = new Button("Delete");
//        delBtn.setOnAction((e) -> handleDeletePasswordButton(Sn));
//
//        Label nameLabel = new Label(name);
//        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
//        Label usernameLabel = new Label(username);
//        cardinfo.getChildren().addAll(nameLabel, usernameLabel);
//        cardinfo.setPrefWidth(150);
//
//        card.getChildren().addAll(cardinfo, editBtn, delBtn);
//        card.setStyle("-fx-border-color: black; -fx-padding: 3px; -fx-border-radius: 7px;");
//        card.setPrefWidth(270);
//        card.setSpacing(8);
//        card.setAlignment(Pos.CENTER);
//
//        return card;
//    }
//
//    private void handleDeletePasswordButton(int Sn) {
//        System.out.println("clicked");
//        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cipherguard?useSSL=false", "root", "")) {
//            String sql = "DELETE FROM userspasswordinfo WHERE `userspasswordinfo`.`Sn` = ?";
//            try (PreparedStatement pst = con.prepareStatement(sql)) {
//                pst.setInt(1, Sn);
//                pst.executeUpdate();
//                // Refresh UI after deletion
//                retrieveDataAndDisplayCards();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void handleAddPasswordButton(ActionEvent actionEvent) throws IOException {
//        System.out.println("clicked");
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newrecord.fxml"));
//          Scene scene = new Scene(fxmlLoader.load(), 335, 600);
//          Stage stage = (Stage) root.getScene().getWindow();
//          stage.setScene(scene);
//
//    }
//}
