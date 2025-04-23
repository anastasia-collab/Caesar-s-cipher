package CaesarsChipher.MainClasses.FileManager;

import CaesarsChipher.MainClasses.FileManager.exception.FileProcessingException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class FileNameValidator {
    private static final List<String> FORBIDEN_DIRS_FILES =
            List.of(".bash_history", ".bash_profile", "etc", "proc");

    public void validateForWriting(String fileName) {
        Path path = validatePath(fileName);
        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                throw new FileProcessingException("File is directory");
            }
            if (!Files.isWritable(path)) {
                throw new FileProcessingException("File is not accessible for writing");
            }
        }
    }

    public void valiateForReading(String fileName){
        Path path = validatePath(fileName);
        if(Files.notExists(path)){
            throw new FileProcessingException("File doesn't exist");
        }
        if(Files.isDirectory(path)){
            throw new FileProcessingException("File is directory");
        }
        if(Files.isReadable(path)){
            throw new FileProcessingException("You don't have right to read from the file.");
        }
    }

    private Path validatePath(String fileName) {
        for (String pathPart : fileName.split(System.getProperty("file.separator"))) {
            if (FORBIDEN_DIRS_FILES.contains(pathPart)) {
                throw new FileProcessingException("Path contains forbidden part: " + pathPart);
            }
        }
        try {
            Path path = Path.of(fileName);
            return path;
        } catch (InvalidPathException e) {
            throw new FileProcessingException("Invalid path.Reason: " + e.getMessage(), e);
        }
    }
}
