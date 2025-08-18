package nettee.upload.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Objects;

@ConfigurationProperties(prefix = "app.upload.image")
public record ImageUploadProperties(Map<String, Target> targets) {

    public ImageUploadProperties {
        Objects.requireNonNull(targets, "설정 파일에 'app.upload.image.targets' 항목이 필요합니다.");
    }

    public record Target(String directory, String baseUrl) {
        public Target {
            Objects.requireNonNull(directory, "target의 'directory' 설정은 필수입니다.");
            Objects.requireNonNull(baseUrl, "target의 'baseUrl' 설정은 필수입니다.");
        }
    }
}