package com.example.demo1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProfileController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField AgeF;
    @FXML
    private TextField CityF;
    @FXML
    private Button Exit;
    @FXML
    private TextField LogF;
    @FXML
    private TextField Name2F;
    @FXML
    private TextField NameF;
    @FXML
    private TextField NumF;
    @FXML
    void initialize() {
        String Name = HelloController.getFirstname();
        String Name2 = HelloController.getLastname();
        String City = HelloController.getCity();
        String Age = HelloController.getAge();
        String Number = HelloController.getPhonenumber();
        String Login = HelloController.getUsername();
        NameF.setText(Name);
        Name2F.setText(Name2);
        CityF.setText(City);
        AgeF.setText(Age);
        NumF.setText(Number);
        LogF.setText(Login);
        Exit.setOnAction(actionEvent -> {
            Exit.getScene().getWindow().hide();
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
        });
    }

}