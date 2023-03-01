package com.example.proyectofinal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

import java.io.IOException;

public class ContactsController {

    @FXML
    private ToolBar toolBar;
    @FXML
    private Button[] contacts;
    @FXML
    public Button showContactsBtn;

    @FXML
    protected void AddContacts(){
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Parent root = null;
                Usuario.selectecUser = ((Button)e.getSource()).getText();
                try {
                    root = FXMLLoader.load(getClass().getResource("chat-view.fxml"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Stage window = (Stage) toolBar.getScene().getWindow();
                window.setScene(new Scene(root));
            }
        };

        contacts = new Button[4];
        toolBar.getItems().removeAll();

        for (int i = 0; i < Usuario.contacts.size(); i++){
            contacts[i] = new Button("Contact " + i);
            toolBar.getItems().add(contacts[i]);
            contacts[i].setText (Usuario.contacts.get(i));
            contacts[i].setOnAction(event);
        }



    }

}
