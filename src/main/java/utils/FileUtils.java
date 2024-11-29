package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileUtils {
    private static final String BASE_PATH = "/home/vini/";

    public static boolean createFile(String fileHash, String compressedContent) {
        String folderName = fileHash.substring(0, 2);
        String fileName = fileHash.substring(2);
        Path directoryPath = Path.of(BASE_PATH, folderName);
        Path filePath = directoryPath.resolve(fileName);

        try {
            Files.createDirectories(directoryPath);

            if (Files.exists(filePath)) {
                System.out.println("File already exists: " + filePath);
                return false;
            }

            Files.writeString(filePath, compressedContent, StandardOpenOption.CREATE_NEW);
            System.out.println("File created: " + filePath);
            return true;
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
            return false;
        }
    }
}
