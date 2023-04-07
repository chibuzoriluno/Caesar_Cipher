
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class CaesarCipher {
    
    
    public static Object[] readTextFile(String fileName) {
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
    
    
    public static String caesarCipherEncryptSpanish(String plaintext, int shift) {
        String ciphertext = ""; // the encrypted message
    
    // Create a mapping of each Spanish letter to its shifted counterpart
        HashMap<Character, Character> mapping = new HashMap<>();
        
        
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÑÜ";
        
        for (int i = 0; i < letters.length(); i++) {
        
            char c = letters.charAt(i);
            char shifted = letters.charAt((i + shift) % letters.length());
            mapping.put(c, shifted);
        }
    
    // Iterate over each character in the plaintext
        for (int i = 0; i < plaintext.length(); i++) {
            char esp = plaintext.toUpperCase().charAt(i);
        
        // Encrypt the character using the mapping
            char encrypted = mapping.getOrDefault(esp, esp);
        
        // Append the encrypted character to the ciphertext
            ciphertext += encrypted;
        }
        return ciphertext;
    }
    
    
    public static String caesarCipherDecryptSpanish(String ciphertext, int shift) {
    String plaintext = ""; // the decrypted message
    
    // Create a mapping of each Spanish letter to its unshifted counterpart
    HashMap<Character, Character> mapping = new HashMap<>();
    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÑÜ";
    for (int i = 0; i < letters.length(); i++) {
        char c = letters.charAt(i);
        char unshifted = letters.charAt((i - shift + letters.length()) % letters.length());
        mapping.put(c, unshifted);
    }
    
    // Iterate over each character in the ciphertext
    for (int i = 0; i < ciphertext.length(); i++) {
        char c = ciphertext.charAt(i);
        
        // Decrypt the character using the mapping
        char decrypted = mapping.getOrDefault(c, c);
        
        // Append the decrypted character to the plaintext
        plaintext += decrypted;
    }
    
    return plaintext;
}

    
    
    public static void main(String[] args) {
    Object[] arrays = CaesarCipher.readTextFile("text_info.txt");
    int[] array1 = (int[]) arrays[0];
    int[] array2 = (int[]) arrays[1];
    int[] array3 = (int[]) arrays[2];
    String[] array4 = (String[]) arrays[3];
    
    for (int i = 0; i < arrays.length; i++) {
        int shift = array1[i];
        int operation = array2[i];
        int language = array3[i];
        String plaintext = array4[i];
        
        if(operation == 0 && language == 2){
        
            String ciphertext = caesarCipherEncryptSpanish(plaintext, shift);
            System.out.println(ciphertext);
            
            String normaltext = caesarCipherDecryptSpanish(ciphertext, shift);
            System.out.println(normaltext.toLowerCase());
        
        }
        
        
    }
    
}

}
