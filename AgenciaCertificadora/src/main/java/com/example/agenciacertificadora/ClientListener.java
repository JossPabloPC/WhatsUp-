package com.example.agenciacertificadora;

import java.net.*;
import java.io.*;
public class ClientListener extends Thread{
    private Socket clientSocket;
    public PrintWriter out;
    private BufferedReader in;
    public ClientListener(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        System.out.println("New client running in thread: " + currentThread());
        try {
            //Streams de entrada y de salida
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));


            String      mssg;
            String      method;
            String[]    clientMsgDecoded;
            while ((mssg = in.readLine()) != null) {
                clientMsgDecoded    = DecodeMessage(mssg);
                method              = clientMsgDecoded[0];

                switch (method){
                    case "AskKey":
                    break;
                }

                if ("Exit".equals(mssg)) {
                    out.println("bye");
                    break;
                }
                System.out.println(mssg);

            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Cant receive message");

            throw new RuntimeException(e);
        }
    }


    private String[] DecodeMessage(String message){
        String [] clientMsgDecoded = message.split(",");
        return  clientMsgDecoded;
    }

    private void SendMessage(String message, String sender){
        out.println("ReceiveMessage," + message + "," + sender);
    }

}

