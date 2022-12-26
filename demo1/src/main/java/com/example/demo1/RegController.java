package com.example.demo1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegController {
    private static String Name;

    private static String Name2;

    private static String Age;

    private static String City;

    private static String Number;

    private static String Login;

    private static String Password;

    private int PassFlag = 0;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Label AErr;
    @FXML
    private TextField AgeF;
    @FXML
    private Label CErr;
    @FXML
    private TextField CityF;
    @FXML
    private Label LogErr;
    @FXML
    private TextField LogF;
    @FXML
    private Label LogZan;
    @FXML
    private Label N2Err;
    @FXML
    private TextField Name2F;
    @FXML
    private TextField NameF;
    @FXML
    private Label Nerr;
    @FXML
    private TextField NumF;
    @FXML
    private Label PNErr;
    @FXML
    private PasswordField Pass2F;
    @FXML
    private Label AErrDed;
    @FXML
    private Label PassErr;
    @FXML
    private PasswordField PassF;
    @FXML
    private Label PassNotMatch;
    @FXML
    private Button RegButton;
    @FXML
    private Label RegErr;
    @FXML
    private Button Exit;

    Number person = new Number();
    public static boolean flag;
    private static void signUpMewUser() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        String firstName = Name; // Данные при регистрации
        String lastName = Name2;
        String phoneNumber = Number;
        String city = City;
        String age = Age;
        String userName = Login;
        String password = Password;


        User user = new User(firstName, lastName, phoneNumber, city, age, userName, password);

        dbHandler.signUpUser(user);
    }
    @FXML
    void initialize() {
        NameF.setOnAction(actionEvent -> {
            String name = NameF.getText();
            if (name.replaceAll(" ", "").equals("")) {
                Nerr.setVisible(true);
            }
            else {
                Nerr.setVisible(false);
                Name = name;
            }
        });
        Name2F.setOnAction(actionEvent -> {
            String name2 = Name2F.getText();
            if (name2.replaceAll(" ", "").equals("")) {
                N2Err.setVisible(true);
            }
            else {
                N2Err.setVisible(false);
                Name2 = name2;
            }
        });
        CityF.setOnAction(actionEvent -> {
            String city = CityF.getText();
            if (city.replaceAll(" ", "").equals("")) {
                CErr.setVisible(true);
            }
            else {
                CErr.setVisible(false);
                City = city;
            }
        });
        AgeF.setOnAction(actionEvent -> {
            String ageStr = AgeF.getText();
            int age;
            try {
                age = Integer.parseInt(ageStr);
            }
            catch(Exception e) {
                age = 0;
            }
            if (age == 0) {
                AErrDed.setVisible(false);
                AErr.setVisible(true);
            }
            else if (age > 100) {
                AErr.setVisible(false);
                AErrDed.setVisible(true);
            }
            else {
                AErrDed.setVisible(false);
                AErr.setVisible(false);
                Age = ""+age;
            }
        });
        NumF.setOnAction(actionEvent -> {
            String number = NumF.getText();
            person.setNumber(number);
            if (person.getNumber().equals("1")) {
                PNErr.setVisible(true);
            }
            else {
                PNErr.setVisible(false);
                Number = person.getNumber();
            }
        });
        LogF.setOnAction(actionEvent -> {
            String login = LogF.getText();
            if (login.replaceAll(" ", "").equals("")) {
                LogErr.setVisible(true);
            }
            else {
                LogErr.setVisible(false);
                Login = login;
            }
        });
        PassF.setOnAction(actionEvent -> {
            String password = PassF.getText();
            if (password.replaceAll(" ", "").equals("")) {
                PassErr.setVisible(true);
            }
            else {
                PassErr.setVisible(false);
                Password = password;
            }
        });
        Pass2F.setOnAction(actionEvent -> {
            String pass2 = Pass2F.getText();
            if (pass2.equals(Password)) {
                PassNotMatch.setVisible(false);
                PassFlag = 2;
            }
            else {
                PassNotMatch.setVisible(true);
                PassFlag = 1;
            }
        });
        RegButton.setOnAction(actionEvent -> {

            if ((Name != null)&(Name2 != null)&(!Age.equals("0"))&(City != null)&(Login != null)&(Number != null)&(Password != null)&(PassFlag == 2)) {
                DataBaseHandler dbH = new DataBaseHandler();
                signUpMewUser();
                flag = dbH.getFlagLogin();
                if (flag) {
                    LogZan.setVisible(true);
                    RegErr.setVisible(false);
                    RegButton.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("hello-view.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Log In");
                    stage.show();
                }
                else {
                    LogZan.setVisible(true);
                }
            }
            else {
                RegErr.setVisible(true);
            }
        });
        Exit.setOnAction(actionEvent -> {
            Exit.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("hello-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Log In");
            stage.show();
        });
    }
}
