package CaesarsChipher;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Validator {

    // Проверяет, что ключ допустим для данного алфавита
    public boolean isValidKey(int key, char[] alphabet) {
        // Ключ должен быть неотрицательным и не превышать длину алфавита
        return key >= 0 && key <= alphabet.length;
    }

    // Проверяет существование файла
    public boolean isFileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }
}
