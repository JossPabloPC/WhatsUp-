package com.example.agenciacertificadora;

import javafx.application.Platform;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientAgencia extends Thread {
    //Sockets y streams de información
    private static Socket socket = null;
    private static PrintWriter out;
    private static BufferedReader in;
    public static ClientAgencia Instance;

    public static int ID;
    public String response;

    public static void StartClient(String address, int port, int ID)
    {

        // establish a connection
        try {
            Instance = new ClientAgencia();
            Instance.response = "";
            socket = new Socket(address, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("AgenciaConnected," + ID);

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
                    case "AskKey":
                        String res = FileManager.SearchKey(clientMsgDecoded[1]);

                        HelloController temp2 = Usuario.loader.getController();
                        if(res != "error") {
                            Platform.runLater(()->{
                                temp2.textArea.setText( "Key "+ res + " found\n");
                            });

                            out.println("GiveKey," + res + "," + clientMsgDecoded[2]);
                        }else{
                            Platform.runLater(()->{
                                temp2.textArea.setText("Key not found, asking next agency: ");
                            });
                            out.println("AskKey," + clientMsgDecoded[1] + "," + ID +"," + clientMsgDecoded[2]);
                        }
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
