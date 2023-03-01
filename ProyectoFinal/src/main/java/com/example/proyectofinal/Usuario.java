package com.example.proyectofinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Usuario {
    public static List<String> contacts;
    public static String selectecUser;
    public static String name;
    public static List<Message> m_pendingMessages;
    public static void SetUsuario(String usuarios)
    {
        String [] datos = usuarios.split(",");
        contacts = new ArrayList<String>();
        for(int i = 0; i < datos.length; i++){
            contacts.add(datos[i]);
        }

        m_pendingMessages = new ArrayList<Message>();
    }
}
