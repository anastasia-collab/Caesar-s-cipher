package CaesarsChipher.MainClasses.Caesars;

import CaesarsChipher.MainClasses.FileManager.FileNameValidator;
import CaesarsChipher.MainClasses.FileManager.FilesManager;
import CaesarsChipher.MainClasses.FileManager.exception.FileProcessingException;

import java.util.ArrayList;
import java.util.List;

public class Coder {

    private final Cipher cipher;
    private final FileNameValidator fileNameValidator;
    private final FilesManager filesManager;
    private final List<String> commonWords = List.of(" и ", " в ", " не ", " на ", " что ", " это ", " с ", " по "); // Частые слова русского языка

    public Coder() {
        this.cipher = new Cipher(new Alphabet());
        this.fileNameValidator = new FileNameValidator();
        this.filesManager = new FilesManager();
    }

    public void encrypt(String inputFileName, String outputFileName, int key) {
        System.out.println("[DEBUG] Шифрование: input=" + inputFileName + ", output=" + outputFileName);

        fileNameValidator.validateForReading(inputFileName);
        fileNameValidator.validateForWriting(outputFileName);

        List<String> sourceLines = filesManager.readFile(inputFileName);
        System.out.println("[DEBUG] Прочитано строк: " + sourceLines.size());

        List<String> encryptedLines = new ArrayList<>();
        for (String line : sourceLines) {
            String encryptedLine = cipher.encrypt(line, key);
            encryptedLines.add(encryptedLine);
        }

        filesManager.writeFile(outputFileName, encryptedLines);
    }

    public void decrypt(String inputFileName, String outputFileName, int key) {
        System.out.println("[DEBUG] Дешифрование: input=" + inputFileName + ", output=" + outputFileName);

        fileNameValidator.validateForReading(inputFileName);
        fileNameValidator.validateForWriting(outputFileName);

        List<String> sourceLines = filesManager.readFile(inputFileName);
        System.out.println("[DEBUG] Прочитано строк: " + sourceLines.size());

        List<String> decryptedLines = new ArrayList<>();
        for (String line : sourceLines) {
            String decryptedLine = cipher.decrypt(line, key);
            decryptedLines.add(decryptedLine);
        }

        filesManager.writeFile(outputFileName, decryptedLines);
    }

    /**
     * Метод для взлома шифра перебором всех возможных ключей
     */
    public void bruteForce(String inputFileName, String outputFileName) throws FileProcessingException {
        System.out.println("[DEBUG] Brute force: input=" + inputFileName + ", output=" + outputFileName);

        fileNameValidator.validateForReading(inputFileName);
        fileNameValidator.validateForWriting(outputFileName);

        List<String> encryptedLines = filesManager.readFile(inputFileName);
        System.out.println("[DEBUG] Прочитано строк: " + encryptedLines.size());

        int bestKey = findBestKey(encryptedLines);
        System.out.println("[DEBUG] Найденный ключ: " + bestKey);

        decrypt(inputFileName, outputFileName, bestKey);
    }

    /**
     * Находит наиболее вероятный ключ путем перебора всех вариантов
     */
    private int findBestKey(List<String> encryptedLines) {
        Alphabet alphabet = cipher.getAlphabet(); // Получаем алфавит
        int alphabetSize = alphabet.getSize();    // Получаем размер алфавита
        int bestKey = 0;
        int maxMatches = 0;

        for (int key = 1; key < alphabetSize; key++) {
            int currentMatches = 0;
            for (String line : encryptedLines) {
                String decryptedLine = cipher.decrypt(line, key);
                currentMatches += countCommonWords(decryptedLine);
            }

            if (currentMatches > maxMatches) {
                maxMatches = currentMatches;
                bestKey = key;
            }
        }
        return bestKey;
    }

    /**
     * Подсчитывает количество известных слов в тексте
     */
    private int countCommonWords(String text) {
        String lowerText = text.toLowerCase();
        int count = 0;
        for (String word : commonWords) {
            if (lowerText.contains(word)) {
                count++;
            }
        }
        return count;
    }
}