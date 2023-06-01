package com.example.agenciacertificadora;

import java.util.Hashtable;

public class UserManager {
    public static Hashtable userDatabase;

    public static void LoadUsers(){
        //LEER DE ARCHIVO MEJOR
        userDatabase = new Hashtable();
        userDatabase.put("100", new Usuario("100", "100"));
        userDatabase.put("101", new Usuario("101", "200"));

    }
}
