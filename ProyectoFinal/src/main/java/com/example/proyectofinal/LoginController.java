package com.example.proyectofinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;


public class LoginController {

    @FXML
    private  TextField passwordTxt;
    @FXML
    private TextField CerPath;
    @FXML
    private  TextField userTxt;
    @FXML
    private  Button enterBtn;
    @FXML
    private Button kyBtn;
    @FXML
    private Button crBtn;

    private File cerFile;
    private File keyFile;
    @FXML
    protected void OnEnterClicked() throws IOException {
        if(!userTxt.getText().equals("") || !passwordTxt.getText().equals("")) {
            System.out.println("Send login to Server");
            String response = Client.SendStringToServer("LoggMeIn" + "," + Usuario.name + "," + passwordTxt.getText());
            changeToContacts(response);
        }
         else {
            System.out.println("Invalid user or password");
        }
    }
    @FXML
    private void OnSearchKey(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter fileExtension = new FileChooser.ExtensionFilter("select your key file (*.key)", "*_key.txt");
        fileChooser.setInitialDirectory(new File("C:\\Users\\pcjos\\Documents\\Escuela\\TemasSelectos\\ProyectoFinal"));
        fileChooser.getExtensionFilters().add(fileExtension);
        keyFile= fileChooser.showOpenDialog(new Stage());
        if(keyFile != null){
            userTxt.setText(keyFile.getPath());
            String modifiedString = keyFile.getName().substring(0, keyFile.getName().indexOf('_'));
            System.out.println(modifiedString);
            Client.key = Integer.parseInt(modifiedString);
        }

    }
    @FXML
    private void OnSearchCer(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\pcjos\\Documents\\Escuela\\TemasSelectos\\ProyectoFinal"));
        FileChooser.ExtensionFilter fileExtension = new FileChooser.ExtensionFilter("select your key file (*.cer)", "*_cer.txt");
        fileChooser.getExtensionFilters().add(fileExtension);
        cerFile= fileChooser.showOpenDialog(new Stage());
        Usuario.name = FileManager.getNLineFrom(2, cerFile);
        if(cerFile != null){
            CerPath.setText(cerFile.getPath());

        }
    }

    public void changeToContacts(String response)  throws IOException{
        if (response.equals("true")) {
            Parent root = FXMLLoader.load(getClass().getResource("contact-view.fxml"));
            Stage window = (Stage) enterBtn.getScene().getWindow();
            window.setScene(new Scene(root));
            Client.publicKey = Integer.parseInt(FileManager.getNLineFrom(1, cerFile));
            Client.Instance.start();
        }
        else {
            System.out.println("Incorrect user or password");
        }
    }
}
