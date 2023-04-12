package com.example.proyectofinal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Sha {

    public static String Digest(String input) {
        try {
            // Create a MessageDigest instance for SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // Apply the SHA-1 function to the input string
            byte[] hash = md.digest(input.getBytes());

            // Convert the resulting hash value to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            // Print the resulting hash value
            input = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the case where SHA-1 is not supported by the system
            e.printStackTrace();
        }
        return input;
    }
}

