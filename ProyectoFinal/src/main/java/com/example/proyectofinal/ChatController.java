package com.example.proyectofinal;




import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ChatController {

    @FXML
    private Label lastmessage;
    @FXML
    private TextField Key;
    @FXML
    private VBox messages;
    @FXML
    private TextField message;
    @FXML
    private Button sendButton;
    @FXML
    private Button loadChat;
    @FXML
    private Button criptAsym;
    @FXML
    private Button criptSym;
    @FXML
    protected void OnSendMessage(){

        if(!message.getText().equals("")) {
            Client.SendMessageToServer("SendMessage" + "," + Usuario.selectecUser + "," + message.getText()+ "," + Usuario.name);
            System.out.println(Client.Instance.response);
            lastmessage = new Label("Yo:" + message.getText());
            FileManager.CreateFile(Usuario.selectecUser);
            FileManager.AddMessage(Usuario.selectecUser, message.getText(), true);
            message.setText("");
            messages.getChildren().add(lastmessage);
        }
    }

    @FXML
    protected void OnLoadChat(){
        messages.getChildren().clear();
        FileManager.ReadChat(Usuario.selectecUser, messages);
    }
    @FXML
    protected void OnEncryptMessage(){
        message.setText(Encrypter.encrypt(message.getText(), Integer.parseInt(Key.getText())));
    }
    @FXML
    protected void OnEncryptAsym(){
        message.setText(Encrypter.encryptAsym(message.getText(), Integer.parseInt(Key.getText())));
    }
    @FXML
    protected void OnDencryptMessage(){
        Label lastMsg = ((Label)messages.getChildren().get(messages.getChildren().size()-1));
        lastMsg.setText(Encrypter.decrypt(lastMsg.getText(), Integer.parseInt(Key.getText())));
    }
}
