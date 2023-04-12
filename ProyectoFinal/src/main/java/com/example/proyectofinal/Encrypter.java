package com.example.proyectofinal;
import java.io.PrintStream;
import java.util.Random;

public class Encrypter
{

    //Length for test: 79
    static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            ' ', '.', '?', '!', ':', ';', '-', '_', '(', ')', '[', ']', '{', '}', 'ñ', 'Ñ', '0','1','2','3','4','5','6','7','8','9' };

    public static String encrypt(String input, int key) {
        int[] messageNumber = convertStringToIndexArray(input);
        for (int i = 0; i < input.length(); i++) {
            if (messageNumber[i] != -1)
                messageNumber[i] = (key + messageNumber[i]) % alphabet.length; // Add the key to the ASCII value
            else
                System.out.println("Found a -1");
        }

        String output = convertIndexArrayToString(messageNumber);

        return output;
        }
    public static String encryptAsym(String input, int key) {
        int[] messageNumber = convertStringToIndexArray(input);
        for (int i = 0; i < input.length(); i++) {
            messageNumber[i] = (key + 79 + messageNumber[i]) % alphabet.length; // Add the key to the ASCII value
        }

        String output = convertIndexArrayToString(messageNumber);

        return output;
    }
    public static String encryptEFirma(String input, int key){
        String hash     = Sha.Digest(input);
        int[] messageNumber = convertStringToIndexArray(hash);
        for (int i = 0; i < hash.length(); i++) {
            messageNumber[i] = (key + 79 + messageNumber[i]) % alphabet.length; // Add the key to the ASCII value
        }

        String cifrado  = convertIndexArrayToString(messageNumber);

        return input + "|" + cifrado;
    }
    public static String encryptSobre(String input, int key, int publicKey){
        String documentoFirmado =  encryptEFirma(input, key);

        Random rand = new Random();
        int llaveAleatoria = rand.nextInt(80);
        String documentoEncriptado = encrypt(documentoFirmado, llaveAleatoria);

        String llaveAleatoriaCifrada = encrypt(String.valueOf(llaveAleatoria), publicKey);

        return documentoEncriptado + "|" + llaveAleatoriaCifrada;
    }

    public static String decrypt(String input, int key) {
            String  [] res;
            res = getStringBeforeAndAfter(':', input);
            input = res[1];
            int     [] resIntArray = convertStringToIndexArray(input);
            for (int i = 0; i < resIntArray.length; i++) {
                if(resIntArray[i] >= 0)
                    resIntArray[i] = (alphabet.length + resIntArray[i] - (key)% alphabet.length) % alphabet.length; // Add the key to the ASCII value
            }

            String output = convertIndexArrayToString(resIntArray);
            return res[0] + ":" + output;

        }
    public static String ValidateEFirma(String input, int key){
        String  [] message;
        String  [] res;

        message = getStringBeforeAndAfter(':', input);
        res = getStringBeforeAndAfter('|', message[1]);
        String document = res[0];
        String firma = "a:" + res[1];

        String resumen = Sha.Digest(document);
        String supuestoResumen = getStringBeforeAndAfter(':', decrypt(firma, key))[1];


        String output;

        if(resumen.equals(supuestoResumen))
            output = "Sí verifica";
        else
            output = "No verifica";
        return message[0] + ": " + document + " | " + output;

    }
    public static String DecryptSobre(String input, int key, int publicKey) {
        String  [] message;

        message = getStringBeforeAndAfter(':', input);
        String  [] MensajeSobreLlave = message[1].split("\\" + '|');



        String documentoEncriptado = "";
        for(int i = 0; i < MensajeSobreLlave.length-1; i++){
            documentoEncriptado = documentoEncriptado + MensajeSobreLlave[i] + "|";
        }
        documentoEncriptado = documentoEncriptado.substring(0, documentoEncriptado.length() - 1);
        documentoEncriptado = "a:" + documentoEncriptado;

        String llaveRandEncriptada = "a:" + MensajeSobreLlave[MensajeSobreLlave.length-1];

        int     llaveRand           = Integer.parseInt(getStringBeforeAndAfter(':', decrypt(llaveRandEncriptada, key))[1]);


        String  documentoFirmado    = getStringBeforeAndAfter(':', decrypt(documentoEncriptado, llaveRand))[1];

        String output = ValidateEFirma(message[0] + ":" + documentoFirmado, publicKey);

        return  output;
    }

    private static String[] getStringBeforeAndAfter(char letter, String inputString) {
        int colonIndex = inputString.indexOf(letter);
        if (colonIndex != -1 && colonIndex < inputString.length() - 1) {
            String[] result = new String[2];
            result[0] = inputString.substring(0, colonIndex);
            result[1] = inputString.substring(colonIndex + 1);
            return result;
        } else {
            return null; // or throw an exception, depending on your requirements
        }
    }
    private static int[] convertStringToIndexArray(String inputString) {
        int[] indexArray = new int[inputString.length()];

        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            int index = -1;

            // Find the index of the current character in the array
            for (int j = 0; j < alphabet.length; j++) {
                if (currentChar == alphabet[j]) {
                    index = j;
                    indexArray[i] = index;
                    break;
                }
                if(j == 77)
                    indexArray[i] = index;
            }


        }

        return indexArray;
    }
    private static String convertIndexArrayToString(int[] inputArray) {
        String indexArray = "";
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < inputArray.length; i++) {
            int currentInt = inputArray[i];
            char c;
            if(currentInt >= 0)
                 c = alphabet[inputArray[i]];
            else
                c = '|';

            output.append(c);
        }

        return output.toString();
    }

}
