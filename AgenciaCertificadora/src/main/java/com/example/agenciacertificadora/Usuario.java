package com.example.agenciacertificadora;
import javafx.fxml.FXMLLoader;

import java.util.*;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Usuario {
    private String m_id;
    private String m_password;
    public Boolean isConnected;
    public String GetID(){return m_id;}
    public String GetPassword(){return m_password;}

    public static FXMLLoader loader;
    public Usuario(String name, String password)
    {
        m_id = name;
        m_password  = password;
        isConnected = false;
    }
}
