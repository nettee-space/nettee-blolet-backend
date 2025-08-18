package nettee.upload.config;

import nettee.upload.properties.ImageUploadProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ImageUploadProperties.class)
public class ImageUploadConfig {
}
