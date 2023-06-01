package com.example.agenciacertificadora;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    public TextArea textArea;
    @FXML
    private TextField AgTxt;

    @FXML
    protected void onHelloButtonClick() {
        int id = Integer.parseInt(AgTxt.getText());
        ClientAgencia.ID = id;
        ClientAgencia.StartClient("192.168.0.14", 50000, id);
        ClientAgencia.Instance.start();
    }
}