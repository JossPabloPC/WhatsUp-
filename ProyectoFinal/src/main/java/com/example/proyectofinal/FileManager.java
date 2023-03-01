package com.example.proyectofinal;
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import java.util.Scanner; // Import the Scanner class to read text files
public class FileManager {
    public static void CreateFile(String ConvName) {
        try {
            File myObj = new File( ConvName+ ".txt");
            if (myObj.createNewFile()) {
                System.out.println("New Conv: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void AddMessage(String convName, String message, Boolean fromThisDevice) {
        try {
            FileWriter myWriter = new FileWriter(convName + ".txt", true);
            if (fromThisDevice){
                myWriter.write("Yo:"+ message + "\n");
            }
            else{
                myWriter.write(Usuario.selectecUser + ":" + message + "\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void ReadChat(String convName, VBox chat) {
        try {
            File myObj = new File(convName + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                chat.getChildren().add(new Label(myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("no conversation yet.");
            e.printStackTrace();
        }
    }
}
