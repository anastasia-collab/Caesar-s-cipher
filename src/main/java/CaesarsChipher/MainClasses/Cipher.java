package CaesarsChipher.MainClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Cipher {
    private static char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public Cipher(char[] alphabet) throws FileNotFoundException {
        this.ALPHABET = alphabet;
    }
    FilesManager files = new FilesManager();

    public String encrypt(String text, int shift) throws IOException {
        files.readFile(text);
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = findCharIndex(c); // Ищем индекс символа в алфавите
            if (index == -1) {
                result.append(c); // Не меняем символы вне алфавита
            } else {
                int newIndex = (index + shift) % ALPHABET.length;
                if (newIndex < 0) {
                    newIndex += ALPHABET.length; // Обработка отрицательного сдвига
                }
                result.append(ALPHABET[newIndex]);
            }
        }
        return result.toString();
    }
    private int findCharIndex(char c) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == c) {
                return i;
            }
        }
        return -1; // Символ не найден
    }


    public String decrypt(String encryptedText, int shift) {
        return null;
    }
}
