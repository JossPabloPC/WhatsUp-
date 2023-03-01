package com.example.proyectofinal;

public class Encrypter
{
        public static String encrypt(String input, int Key) {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                char c = (char) (input.charAt(i) + Key); // Add the key to the ASCII value
                output.append(c);
            }
            return output.toString();
        }

    public static String encryptAsym(String input, int Key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = (char) (input.charAt(i) + Key + 10); // Add the key to the ASCII value
            output.append(c);
        }
        return output.toString();
    }

        public static String decrypt(String input, int key) {

            String []res;
            res = getStringBeforeAndAfterColon(input);
            input = res[1];
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                char c = (char) (input.charAt(i) - key); // Subtract the key from the ASCII value
                output.append(c);
            }

            return res[0]+":" + output.toString();
        }

    public static String[] getStringBeforeAndAfterColon(String inputString) {
        int colonIndex = inputString.indexOf(':');
        if (colonIndex != -1 && colonIndex < inputString.length() - 1) {
            String[] result = new String[2];
            result[0] = inputString.substring(0, colonIndex);
            result[1] = inputString.substring(colonIndex + 1);
            return result;
        } else {
            return null; // or throw an exception, depending on your requirements
        }
    }
}
