package com.example.proyectofinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.scene.Parent;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {

    @FXML
    private  TextField passwordTxt;

    @FXML
    private  TextField userTxt;
    @FXML
    private  Button enterBtn;

    @FXML
    protected void OnEnterClicked() throws IOException {
        if(!userTxt.getText().equals("") || !passwordTxt.getText().equals("")) {
            String response = Client.SendStringToServer("LoggMeIn" + "," + userTxt.getText() + "," + passwordTxt.getText());
            System.out.println("Send login to Server");
            changeToContacts(response);
        }
         else {
            System.out.println("Invalid user or password");
        }
    }

    public void changeToContacts(String response)  throws IOException{
        if (response.equals("true")) {
            Parent root = FXMLLoader.load(getClass().getResource("contact-view.fxml"));
            Stage window = (Stage) enterBtn.getScene().getWindow();
            window.setScene(new Scene(root));
            Usuario.name = userTxt.getText();
            Client.Instance.start();
        }
        else {
            System.out.println("Incorrect user or password");
        }
    }
}
