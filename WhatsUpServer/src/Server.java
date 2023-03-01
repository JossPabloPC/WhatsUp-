// A Java program for a Server
import java.net.*;
import java.io.*;
import java.util.Dictionary;
import java.util.Enumeration;


public class Server {
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket serverSocket = null;
    private DataInputStream in       =  null;


    //Method to start the server
    public void StartServer(int port) {

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a clients ...");

            while (true)
                new ClientListener(serverSocket.accept()).start();
        }
        catch(IOException i)
        {
            System.out.println(i + "   Couldn't create the server");
        }
    }
    public void StopServer() {
        try{
            serverSocket.close();
        }
        catch (IOException i) {
            System.out.println(i + "   Couldn't close the server");

        }
    }



}
