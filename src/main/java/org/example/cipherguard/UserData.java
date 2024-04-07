package org.example.cipherguard;
//      Java Beans -- reusable Communication between ui and service or other layers

//DTO -- Data transfer object

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class UserData {


//    1. Private instance variable
    private int id;
    private String name;
    private String username;

//    2. No Args Constructor
    UserData() {

    }

//    3. Public Getter and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}






















//package org.example.cipherguard;
//
//import javafx.beans.property.*;
//
//public class UserData {
//
//    private final IntegerProperty sn = new SimpleIntegerProperty();
//    private final StringProperty name = new SimpleStringProperty();
//    private final StringProperty username = new SimpleStringProperty();
//
//    public UserData(int sn, String name, String username) {
//        this.sn.set(sn);
//        this.name.set(name);
//        this.username.set(username);
//    }
//
//    public int getSn() {
//        return sn.get();
//    }
//
//    public IntegerProperty snProperty() {
//        return sn;
//    }
//
//    public String getName() {
//        return name.get();
//    }
//
//    public StringProperty nameProperty() {
//        return name;
//    }
//
//    public String getUsername() {
//        return username.get();
//    }
//
//    public StringProperty usernameProperty() {
//        return username;
//    }
//
//}
