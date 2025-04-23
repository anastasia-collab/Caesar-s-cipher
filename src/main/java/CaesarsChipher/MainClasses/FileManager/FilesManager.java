package CaesarsChipher.MainClasses.FileManager;


import CaesarsChipher.MainClasses.FileManager.exception.FileProcessingException;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FilesManager {
private static final StandardOpenOption[] FILE_WRITE_OPTIONS =
        {StandardOpenOption.CREATE, StandardOpenOption.APPEND};
    // Чтение файла и возвращение содержимого в виде строки
    public List<String> readFile(String fileName) {
        try{
            Path filePath = Path.of(fileName);
            return Files.readAllLines(filePath);
        } catch (IOException | InvalidPathException e) {
            throw new FileProcessingException(e.getMessage(), e);
        }

    }

    // Запись строки в файл (если файла нет, он создаётся)
    public void writeFile(String fileName, String content) {
        try{
            Path filePath = Path.of(fileName);
            Files.writeString(filePath, content, FILE_WRITE_OPTIONS);
        } catch (IOException | InvalidPathException e) {
            throw new FileProcessingException(e.getMessage(), e);
        }
    }
}