package com.example.demo1;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {
    private String logi;
    private String passw;
    private static boolean flag = false;
    private static String firstname;

    public static String getFirstname() {
        return firstname;
    }

    private static String lastname;
    public static String getLastname() {
        return lastname;
    }
    private static String phonenumber;

    public static String getPhonenumber() {
        return phonenumber;
    }

    private static String city;

    public static String getCity() {
        return city;
    }

    private static String age;

    public static String getAge() {
        return age;
    }

    private static String username;

    public static String getUsername() {
        return username;
    }

    private static String password;

    public static String getPassword() {
        return password;
    }

    @FXML
    void initialize() {
        Log.setOnAction(actionEvent -> {
            logi = Log.getText();
        });
        Pass.setOnAction(actionEvent -> {
            passw = Pass.getText();
        });
        Vhod.setOnAction(actionEvent -> {
            loginUser(logi, passw);
            if (flag) {
                LogInErr.setVisible(false);
                try {
                    getUserData(logi);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Vhod.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Chat.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Chat");
                stage.show();
            }
            else {
                LogInErr.setVisible(true);
            }
        });
        Reg.setOnAction(actionEvent -> {
            Reg.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Registration.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Sign up");
            stage.show();
        });
    }
    private static void loginUser(String loginText, String loginPassword) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUserPass(user);

        int counter = 0;
        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (counter >= 1) {
            flag = true;
        } else {
            flag = false;
        }
    }
    private static void getUserData(String loginText) throws SQLException, ClassNotFoundException {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setUserName(loginText);
        ResultSet resSet = dbHandler.getUserData(user);

        while(resSet.next()){
            firstname = resSet.getString(2);
            lastname = resSet.getString(3);
            phonenumber = resSet.getString(4);
            city = resSet.getString(5);
            age = resSet.getString(6);
            username = resSet.getString(7);
            password = resSet.getString(8);
        }
    }
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField Log;
    @FXML
    private PasswordField Pass;
    @FXML
    private Label LogInErr;
    @FXML
    private Button Reg;
    @FXML
    private Button Vhod;

}
