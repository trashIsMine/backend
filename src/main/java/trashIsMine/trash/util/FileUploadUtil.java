package trashIsMine.trash.util;
//
//import org.springframework.web.multipart.MultipartFile;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class FileUploadUtil {
//
//    public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
//        Path uploadPath = Paths.get(uploadDir);
//
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        try (var inputStream = multipartFile.getInputStream()) {
//            Path filePath = uploadPath.resolve(fileName);
//            Files.copy(inputStream, filePath);
//            return filePath.toString(); // 저장된 파일의 경로 반환
//        }
//    }
//}

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtil {

    public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        // Normalize and ensure the directory exists
        Path uploadDirPath = Paths.get(uploadDir).normalize();
        if (Files.notExists(uploadDirPath)) {
            Files.createDirectories(uploadDirPath);
        }

        // Remove trailing slash from uploadDir if present
        if (uploadDir.endsWith(File.separator)) {
            uploadDir = uploadDir.substring(0, uploadDir.length() - 1);
        }

        // Define the file path
        Path filePath = Paths.get(uploadDir, sanitizeFileName(fileName)).normalize();

        // Save the file to the specified path
        File file = filePath.toFile();
        multipartFile.transferTo(file);

        // Return the file path relative to the upload directory
        return filePath.toString();
    }

    // Helper method to sanitize the file name
    private static String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[/\\\\]", "_");
    }
}
