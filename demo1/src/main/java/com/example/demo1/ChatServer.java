package com.example.demo1;

import java.net.*;
import java.io.*;

public class ChatServer extends Thread {
    private static File Logs = new File("C:/Users/Jarzz/IdeaProjects/demo1/log.txt");
    private final ServerSocket serverSocket;

    public static String Msg;
    public static String N1;
    public static String N2;
    public static String Log;
    public static String Dat;
    public static String Memory = "Начало общения";
    public static String Chat;

    public static String getChat() {
        return Chat;
    }

    public ChatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        System.out.println("Server is online...");
        while(true) {
            try {
                Socket server = serverSocket.accept();
                DataInputStream in = new DataInputStream(server.getInputStream());
                Msg = in.readUTF();
                N1 = in.readUTF();
                N2 = in.readUTF();
                Log = in.readUTF();
                Dat = in.readUTF();
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                Chat = "\n" + "\n" + N1 + " " + N2 + " " + Log + " [" + Dat + "]" + "\n";
                out.writeUTF(Memory);
                out.writeUTF(Chat);
                out.writeUTF(Msg);
                Memory = in.readUTF();
                String mes = in.readUTF();
                FileWriter fw = new FileWriter("log.txt", true);
                fw.write(Chat);
                fw.write(mes);
                fw.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Время сокета истекло!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String [] args) {
        int port = 64123;
        try {
            Thread t = new ChatServer(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}