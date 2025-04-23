package CaesarsChipher.MainClasses.Caesars;

import CaesarsChipher.MainClasses.FileManager.FileNameValidator;
import CaesarsChipher.MainClasses.FileManager.FilesManager;

import java.util.ArrayList;
import java.util.List;

public class Coder {

    private  Cipher cipher;
    private FileNameValidator fileNameValidator;
    private FilesManager filesManager;

    public Coder(){
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
        System.out.println("[DEBUG] Шифрование: input=" + inputFileName + ", output=" + outputFileName);

        fileNameValidator.validateForReading(inputFileName);
        fileNameValidator.validateForWriting(outputFileName);

        List<String> sourceLines = filesManager.readFile(inputFileName);
        System.out.println("[DEBUG] Прочитано строк: " + sourceLines.size());

        List<String> decryptedLines = new ArrayList<>();
        for (String line : sourceLines) {
            String encryptedLine = cipher.decrypt(line, key);
            decryptedLines.add(encryptedLine);
        }

        filesManager.writeFile(outputFileName, decryptedLines);
    }
}
