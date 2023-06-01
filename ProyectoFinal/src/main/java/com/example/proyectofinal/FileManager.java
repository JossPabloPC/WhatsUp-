package com.example.proyectofinal;
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
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
                Label text = new Label(myReader.nextLine());
                chat.getChildren().add(text);

                HBox hbox = new HBox(); // Create an HBox container
                hbox.setSpacing(10); // Set the spacing between buttons

                Button button1 = new Button("Descifrar");
                Button button3 = new Button("E-Firma");
                Button button4 = new Button("Ensobretado");

                button1.setOnAction(event -> {
                    String tmp = Encrypter.decrypt(text.getText(), Client.key);
                    text.setText(tmp);
                });

                button3.setOnAction(event -> {

                    text.setText(Encrypter.ValidateEFirma(text.getText(), Client.key));
                });

                button4.setOnAction(event -> {
                    text.setText(Encrypter.DecryptSobre(text.getText(), Client.key, Client.friendPublicKey));
                });

                hbox.getChildren().addAll(button1, button3, button4);

                chat.getChildren().add(hbox);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("no conversation yet.");
            e.printStackTrace();
        }
    }

    public static String getNLineFrom(int n, File file){
        String res ="";
        try {
            Scanner myReader = new Scanner(file);
            for(int i = 0; i < n; i++) {
                res = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file.");
            e.printStackTrace();
        }

        return res;
    }
}
