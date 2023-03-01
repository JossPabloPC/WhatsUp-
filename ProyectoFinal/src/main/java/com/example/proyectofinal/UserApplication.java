package com.example.proyectofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("login-view.fxml"));
        stage.setTitle("Whats-UP!");
        stage.setScene(new Scene(root,240, 400) );
        stage.show();

        //Server
        Client.Instance.StartClient("192.168.0.5", 50000);
        Usuario.SetUsuario("Juan,Gerardo,Mariana,Pablo");
    }

    public static void main(String[] args) {
        launch();
    }
}