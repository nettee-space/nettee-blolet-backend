package nettee.upload.port;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageStoragePort {

    String store(MultipartFile file, String targetName);

    List<String> storeFiles(List<MultipartFile> files, String targetName);

    String getFileUrl(String storedFileName, String targetName);

    List<String> getFileUrls(List<String> storedFileNames, String targetName);
}
