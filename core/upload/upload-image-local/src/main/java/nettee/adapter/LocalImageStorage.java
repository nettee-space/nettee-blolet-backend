package nettee.adapter;

import jakarta.annotation.PostConstruct;
import nettee.common.image.ImageFormatDetector;
import nettee.common.image.ImageFormatDetector.ImageFormat;
import nettee.upload.port.ImageStorage;
import nettee.upload.properties.ImageUploadProperties;
import nettee.upload.properties.ImageUploadProperties.Target;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LocalImageStorage implements ImageStorage {

    private final ImageUploadProperties imageUploadProperties;
    private final Map<String, Path> targetLocations = new ConcurrentHashMap<>();

    public LocalImageStorage(ImageUploadProperties imageUploadProperties) {
        this.imageUploadProperties = imageUploadProperties;
    }

    /**
     * 서비스 초기화 시, 설정 파일에 명시된 모든 대상 디렉터리를 생성합니다.
     */
    @PostConstruct
    public void init() {
        imageUploadProperties.targets().forEach((targetName, targetProps) -> {
            try {
                Path location = Paths.get(targetProps.directory()).toAbsolutePath().normalize();
                Files.createDirectories(location);
                targetLocations.put(targetName, location);
            } catch (IOException ex) {
                throw new RuntimeException(targetName + " 대상의 업로드 디렉터리를 생성할 수 없습니다.", ex);
            }
        });
    }

    /**
     * 지정된 대상(target)에 단일 파일을 저장합니다.
     */
    public String store(MultipartFile file, String targetName) {
        Objects.requireNonNull(file, "파일이 null일 수 없습니다.");

        Path targetLocation = targetLocations.get(targetName);
        if (targetLocation == null) {
            throw new IllegalArgumentException("유효하지 않은 업로드 대상입니다: " + targetName);
        }

        String originalFileName = Objects.requireNonNull(file.getOriginalFilename(), "원본 파일 이름이 없습니다.");
        if (originalFileName.isBlank()) {
            throw new RuntimeException("원본 파일 이름이 비어있습니다.");
        }

        ImageFormat format;
        try {
            format = ImageFormatDetector.detect(file);
        } catch (Exception e) {
            throw new RuntimeException("이미지 타입을 판별할 수 없습니다.", e);
        }

        String storedFileName = UUID.randomUUID() + format.getExtension();

        try (var inputStream = file.getInputStream()) {
            Path destination = targetLocation.resolve(storedFileName);
            Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
            return storedFileName;
        } catch (IOException ex) {
            throw new RuntimeException("파일을 저장할 수 없습니다. 파일 이름: " + storedFileName, ex);
        }
    }

    /**
     * 지정된 대상(target)에 여러 파일을 한 번에 저장합니다.
     */
    public List<String> storeFiles(List<MultipartFile> files, String targetName) {
        return files.stream()
                .map(file -> store(file, targetName))
                .collect(Collectors.toList());
    }

    /**
     * 저장된 파일에 접근할 수 있는 전체 URL을 생성합니다.
     */
    public String getFileUrl(String storedFileName, String targetName) {
        Target targetProps = imageUploadProperties.targets().get(targetName);
        if (targetProps == null) {
            throw new IllegalArgumentException("URL 생성을 위한 대상이 유효하지 않습니다: " + targetName);
        }
        return UriComponentsBuilder.fromHttpUrl(targetProps.baseUrl())
                .pathSegment(storedFileName)
                .toUriString();
    }

    /**
     * 여러 개의 저장된 파일에 대한 URL 리스트를 생성합니다.
     */
    public List<String> getFileUrls(List<String> storedFileNames, String targetName) {
        return storedFileNames.stream()
                .map(fileName -> getFileUrl(fileName, targetName))
                .collect(Collectors.toList());
    }
}
