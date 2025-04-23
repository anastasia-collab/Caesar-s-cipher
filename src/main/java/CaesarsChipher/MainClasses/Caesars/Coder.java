package CaesarsChipher.MainClasses.Caesars;

import CaesarsChipher.MainClasses.FileManager.FileNameValidator;
import CaesarsChipher.MainClasses.FileManager.FilesManager;

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

    public void encrypt(String inputFileName, String outputFileName, int key){
        fileNameValidator.valiateForReading(inputFileName);
        fileNameValidator.validateForWriting(outputFileName);

        List<String> sourceLines = filesManager.readFile(inputFileName);
        for (String sourceLine : sourceLines){
            String encryptedLine = cipher.encrypt(sourceLine, key);
            filesManager.writeFile(outputFileName, encryptedLine);
        }
    }
    public void decrypt(String inputFileName, String outputFileName, int key){
        fileNameValidator.valiateForReading(inputFileName);
        fileNameValidator.validateForWriting(outputFileName);

        List<String> sourceLines = filesManager.readFile(inputFileName);
        for (String sourceLine : sourceLines){
            String encryptedLine = cipher.decrypt(sourceLine, key);
            filesManager.writeFile(outputFileName, encryptedLine);
        }
    }
}
