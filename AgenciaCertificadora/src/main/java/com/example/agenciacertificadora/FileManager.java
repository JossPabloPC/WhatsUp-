package com.example.agenciacertificadora;

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

    public static String SearchKey(String name){
        String directoryPath = "C:\\Users\\pcjos\\Documents\\Escuela\\TemasSelectos\\AgenciaCertificadora\\Base" + ClientAgencia.ID;

        File directory = new File(directoryPath);

        // Check if the specified directory exists
        if (directory.exists() && directory.isDirectory()) {
            // Get all files in the directory
            File[] files = directory.listFiles();

            // Iterate through each file
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {

                    if(file.getName().equals(name + ".txt")) {
                        // Perform your desired operations on the text file
                        String key = getNLineFrom(1, file);
                        System.out.println("Found key: " + key);
                        return key;
                    }
                }
            }
        } else {
            System.out.println("Invalid directory path or the directory does not exist.");
        }
        return "error";
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
