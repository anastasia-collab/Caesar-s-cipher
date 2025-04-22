package CaesarsChipher.MainClasses;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesManager {

    // Чтение файла и возвращение содержимого в виде строки
    public String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    // Запись строки в файл (если файла нет, он создаётся)
    public String writeFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes());
        return filePath;
    }
}