
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class CaesarCipher {
    
    //function for reading text from text file into arrays and returns the arrays in one struct
    public static Object[] readTextFile(String fileName) {
        //read content of text file line by line, while spliting them apart at the delimiter ":", and inserting into arrays
        int[] array1 = new int[4];
        int[] array2 = new int[4];
        int[] array3 = new int[4];
        String[] array4 = new String[4];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                array1[index] = Integer.parseInt(parts[0]);
                array2[index] = Integer.parseInt(parts[1]);
                array3[index] = Integer.parseInt(parts[2]);
                array4[index] = parts[3];
                index++;
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
        }
        return new Object[] { array1, array2, array3, array4 };
    }
    
    //function for encrypting strings obtained from the arrays based on number of shifts and language code
    public static String caesarCipherEncrypt(int shift, int language, String plaintext) {
        String ciphertext = ""; // the encrypted message
    
    // Create a mapping of each letter to its shifted counterpart
        HashMap<Character, Character> mapping = new HashMap<>();
        
        //conditional statement for shifting and mapping language with code zero (English)
        if(language == 0){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for (int i = 0; i < letters.length(); i++) {
        
            char c = letters.charAt(i);
            char shifted = letters.charAt((i + shift) % letters.length());
            mapping.put(c, shifted);
        }
        }
        //conditional statement for shifting and mapping language with code one (French)
        if(language == 1){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZÀÂÆÇÉÈÊËÎÏÔŒÙÛÜ";
        
        for (int i = 0; i < letters.length(); i++) {
        
            char c = letters.charAt(i);
            char shifted = letters.charAt((i + shift) % letters.length());
            mapping.put(c, shifted);
        }
        }
        //conditional statement for shifting and mapping language with code two (Spanish)
        if(language == 2){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÑÜ";
        
        for (int i = 0; i < letters.length(); i++) {
        
            char c = letters.charAt(i);
            char shifted = letters.charAt((i + shift) % letters.length());
            mapping.put(c, shifted);
        }
        }
        //conditional statement for shifting and mapping language with code three (Turkish)
        if(language == 3){
        String letters = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ";
        
        for (int i = 0; i < letters.length(); i++) {
        
            char c = letters.charAt(i);
            char shifted = letters.charAt((i + shift) % letters.length());
            mapping.put(c, shifted);
        }
        }
    
    // Iterate over each character in the plaintext
        for (int i = 0; i < plaintext.length(); i++) {
            char esp = plaintext.toUpperCase().charAt(i);
        
        // Encrypt the character using the mapping
            char encrypted = mapping.getOrDefault(esp, esp);
        
        // Append the encrypted character to the ciphertext
            ciphertext += encrypted;
        }
        mapping.clear();
        return ciphertext;
    }
    
    
    //function for decrypting strings obtained from the arrays based on number of shifts and language code
    public static String caesarCipherDecrypt(int shift, int language, String ciphertext) {
    String plaintext = ""; // the decrypted message
    
    // Create a mapping of each letter to its unshifted counterpart
    HashMap<Character, Character> mapping2 = new HashMap<>();
    
    //conditional statement for unshifting and mapping encrypted language with code zero (English)
    if(language == 0){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    for (int i = 0; i < letters.length(); i++) {
        char c = letters.charAt(i);
        char unshifted = letters.charAt((i - shift + letters.length()) % letters.length());
        mapping2.put(c, unshifted);
    }
    }
    //conditional statement for unshifting and mapping encrypted language with code one (French)
    if(language == 1){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZÀÂÆÇÉÈÊËÎÏÔŒÙÛÜ";
    
    for (int i = 0; i < letters.length(); i++) {
        char c = letters.charAt(i);
        char unshifted = letters.charAt((i - shift + letters.length()) % letters.length());
        mapping2.put(c, unshifted);
    }
    }
    //conditional statement for unshifting and mapping encrypted language with code two (Spanish)
    if(language == 2){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÑÜ";
    
    for (int i = 0; i < letters.length(); i++) {
        char c = letters.charAt(i);
        char unshifted = letters.charAt((i - shift + letters.length()) % letters.length());
        mapping2.put(c, unshifted);
    }
    }
    //conditional statement for unshifting and mapping encrypted language with code three (Turkish)
    if(language == 3){
        String letters = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ";
    
    for (int i = 0; i < letters.length(); i++) {
        char c = letters.charAt(i);
        char unshifted = letters.charAt((i - shift + letters.length()) % letters.length());
        mapping2.put(c, unshifted);
    }
    }
    
    // Iterate over each character in the ciphertext
    for (int i = 0; i < ciphertext.length(); i++) {
        char dcrpt = ciphertext.charAt(i);
        
        // Decrypt the character using the mapping
        char decrypted = mapping2.getOrDefault(dcrpt, dcrpt);
        
        // Append the decrypted character to the plaintext
        plaintext += decrypted;
    }
    mapping2.clear();
    return plaintext;
}

    
    
    public static void main(String[] args) {
        
        try{
            //read content from text file into arrays using the function readTextFile
            Object[] arrays = readTextFile("text_info.txt");
            int[] array1 = (int[]) arrays[0];
            int[] array2 = (int[]) arrays[1];
            int[] array3 = (int[]) arrays[2];
            String[] array4 = (String[]) arrays[3];
            String ciphertext2 = "";
            for (int i = 0; i < arrays.length; i++) {
                int shift = array1[i];
                int operation = array2[i];
                int language = array3[i];
                String plaintext = array4[i];

                //conditional statement for encryption
                if(operation == 0){
                    
                    //encrypting string message using the caesarCipherEncrypt function
                    String ciphertext = caesarCipherEncrypt(shift, language, plaintext);
                    System.out.println(ciphertext);
                    
                    //add information to aid decryption to each line of encrypted message
                    ciphertext2 += Integer.toString(shift) + ":1:" + Integer.toString(language) + ":" + ciphertext + "\n";
                }

            }
            
            //write all lines of encrypted string to text file cipher_text.txt
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("cipher_text.txt"));
                writer.write(ciphertext2);
                writer.close();
                System.out.println("Successfully wrote ENCRYPTED MESSAGES to the file cipher_text.txt");
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file cipher_text.txt");
               

            }
            
            //read the encrypted(all) content of text file cipher_text.txt into arrays using readTextFile function 
            Object[] arrays2 = readTextFile("cipher_text.txt");
            int[] array11 = (int[]) arrays2[0];
            int[] array12 = (int[]) arrays2[1];
            int[] array13 = (int[]) arrays2[2];
            String[] array14 = (String[]) arrays2[3];
            
            for (int i = 0; i < arrays2.length; i++) {
                int shift = array11[i];
                int operation = array12[i];
                int language = array13[i];
                String encryptedtext = array14[i];
                
                //conditional statement for decryption
                if(operation == 1){
                    
                    //decrypt message using caesarCipherDecrypt function
                    String normaltext = caesarCipherDecrypt(shift, language, encryptedtext);
                    System.out.println(normaltext.toLowerCase());

                }


            }
            
        }catch (Exception ee) {
            System.err.println("An error occurred while reading/writing one of the text files\n Check if the files are available\n and if their content has the expected structure");
        }
    }

}
