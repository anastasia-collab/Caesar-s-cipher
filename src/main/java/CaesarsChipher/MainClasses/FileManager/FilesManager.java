package CaesarsChipher.MainClasses.FileManager;


import CaesarsChipher.MainClasses.FileManager.exception.FileProcessingException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class FilesManager {
    public List<String> readFile(String fileName) {
        try {
            Path filePath = Path.of(fileName);
            return Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException | InvalidPathException e) {
            throw new FileProcessingException("Failed to read file: " + e.getMessage(), e);
        }
    }

    public void writeFile(String fileName, List<String> content) {
        Path filePath = Path.of(fileName).toAbsolutePath(); // Абсолютный путь для отладки
        System.out.println("[DEBUG] Попытка записи в файл: " + filePath);

        try {
            // Удаляем файл, если он существует (для перезаписи)
            Files.deleteIfExists(filePath);

            // Записываем данные
            Files.write(
                    filePath,
                    content,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE
            );

            // Проверяем результат
            if (!Files.exists(filePath)) {
                throw new FileProcessingException("Файл не был создан!");
            }

            if (Files.size(filePath) == 0) {
                throw new FileProcessingException("Файл пуст после записи!");
            }

            System.out.println("[SUCCESS] Файл успешно записан: " + filePath);

        } catch (IOException e) {
            System.err.println("[ERROR] Ошибка записи: " + e.getMessage());
            e.printStackTrace();
            throw new FileProcessingException("Ошибка при записи файла", e);
        }
    }
}