package com.example.demo1;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChatController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextArea ChatArea;
    @FXML
    private Button Exit;
    @FXML
    private TextArea MessageArea;
    @FXML
    private Button Prof;
    @FXML
    private Button Send;
    @FXML
    private AnchorPane Attention;
    @FXML
    private Button KnOst;
    @FXML
    private Button KnOut;
    private static String Message;
    private static String Prishlo = "Начало общения";

    String Name = HelloController.getFirstname();
    String Name2 = HelloController.getLastname();
    String Login = HelloController.getUsername();
    private PrivateKey privateKey;
    private PublicKey publicKey;


    @FXML
    void initialize() {
        Exit.setOnAction(actionEvent -> {
            Attention.setVisible(true);
            KnOst.setOnAction(actionEvent1 -> {
                Attention.setVisible(false);
            });
            KnOut.setOnAction(actionEvent1 -> {
                KnOut.getScene().getWindow().hide();
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
                stage.setTitle("Chat");
                stage.show();
            });
        });
        ChatArea.setText(Prishlo);
        String serverName = "37.143.10.48";
        int port = 3335;
        Send.setOnAction(actionEvent -> {
            if (MessageArea.getText().replaceAll(" ", "").equals("")) {
            }
            else {
                Shifr shifr = new Shifr();
                Message = MessageArea.getText();
                shifr.Crypto();
                try {
                    Message = shifr.encrypt(Message);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    Socket client = new Socket(serverName, port);
                    OutputStream outToServer = client.getOutputStream();
                    DataOutputStream out = new DataOutputStream(outToServer);
                    SimpleDateFormat formats = new SimpleDateFormat("dd.MM.yy HH:mm");
                    String date = (formats.format(Calendar.getInstance().getTime()));
                    out.writeUTF(Message);
                    out.writeUTF(Name);
                    out.writeUTF(Name2);
                    out.writeUTF(Login);
                    out.writeUTF(date);
                    InputStream inFromServer = client.getInputStream();
                    DataInputStream in = new DataInputStream(inFromServer);
                    ChatArea.setText(in.readUTF());
                    Prishlo = in.readUTF();
                    String Msg = in.readUTF();
                    try {
                        Msg = shifr.decrypt(Msg);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    ChatArea.appendText(Prishlo);
                    ChatArea.appendText(Msg);
                    out.writeUTF(ChatArea.getText());
                    out.writeUTF(Msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Prof.setOnAction(actionEvent -> {
            Prof.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Profile.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Profile");
            stage.show();
        });
    }
}