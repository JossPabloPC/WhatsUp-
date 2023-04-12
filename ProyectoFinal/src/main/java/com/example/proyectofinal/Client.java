package com.example.proyectofinal;
// A Java program for a Client
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.*;
public class Client extends Thread {

    //Sockets y streams de información
    private static Socket socket = null;
    private static PrintWriter out;
    private static BufferedReader in;

    public static Client Instance;

    public static int key;
    public static int publicKey;


    public String response;
    // constructor to put ip address and port
    public static void StartClient(String address, int port)
    {

        // establish a connection
        try {
            Instance = new Client();
            Instance.response = "";
            socket = new Socket(address, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected");
        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }

    }

    public void run(){
        try {
            System.out.println("Listener running in thread: " + currentThread());
            String      mssg;
            String      method;
            String[]    clientMsgDecoded;
            while ((mssg = in.readLine()) != null) {
                clientMsgDecoded    = DecodeMessage(mssg);
                method              = clientMsgDecoded[0];
                System.out.println("Desde el hilo:" + mssg);
                switch (method){
                    case "ReceiveMessage":
                        System.out.println("Just received a message!");
                        FileManager.CreateFile(Usuario.selectecUser);
                        FileManager.AddMessage(Usuario.selectecUser, clientMsgDecoded[1], false);


                        ChatController temp = Usuario.loader.getController();

                        Platform.runLater(() -> {
                            temp.messages.getChildren().clear();
                            FileManager.ReadChat(Usuario.selectecUser, temp.messages);
                        });

                        break;
                    default:
                        System.out.println("Unknown command");
                        break;

                }
            }

            in.close();
            out.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String SendStringToServer(String message) {

        out.println(message);
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void SendMessageToServer(String message) {

        out.println(message);
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String[] DecodeMessage(String message){
        String [] clientMsgDecoded = message.split(",");
        return  clientMsgDecoded;
    }
}
