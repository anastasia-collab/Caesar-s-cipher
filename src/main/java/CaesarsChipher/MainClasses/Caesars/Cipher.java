package CaesarsChipher.MainClasses.Caesars;

import CaesarsChipher.MainClasses.Caesars.exception.CaesarAlphaetException;
import CaesarsChipher.MainClasses.FileManager.FilesManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Cipher {
    private final Alphabet alphabet;
    public Cipher (Alphabet alphabet){this.alphabet = alphabet;}
    public String encrypt(String originalText, int key){return process(originalText, key);}
    public String decrypt(String originalText, int key){return process(originalText, -key);}

    private String process(String originalText, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < originalText.length(); i++) {
            char originalChar = originalText.charAt(i);
            try {
                int originalCharIndex = alphabet.getCharIndex(originalChar);
                int newCharIndex = (alphabet.getSize() + (originalCharIndex + key)) % alphabet.getSize();
                result.append(alphabet.getCharByIndex(newCharIndex)[0]);
            } catch (CaesarAlphaetException e) {
                // Пропускаем символ, если его нет в алфавите
                result.append(originalChar);
            }
        }
        return result.toString();
    }

    private Character toLowerCase (Character character){return (character + "").toLowerCase().charAt(0);}
}
