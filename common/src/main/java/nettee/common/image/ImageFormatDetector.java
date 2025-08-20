package nettee.common.image;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import static nettee.common.image.ImageErrorCode.UNSUPPORTED_IMAGE_FORMAT;

public class ImageFormatDetector {

    @Getter
    public enum ImageFormat {
        JPG("jpg", ".jpg"),
        PNG("png", ".png"),
        GIF("gif", ".gif"),
        BMP("bmp", ".bmp"),
        WEBP("webp", ".webp");

        private final String formatName;
        private final String extension;

        ImageFormat(String formatName, String extension) {
            this.formatName = formatName;
            this.extension = extension;
        }
    }

    /**
    * 이미지 지원 포맷: JPG, PNG, GIF, BMP, WEBP
    */
    public static ImageFormat detect(MultipartFile file) throws Exception {
        byte[] header = new byte[12];
        try (var is = file.getInputStream()) {
            int read = is.read(header, 0, header.length);
            if (read < 0) {
                // 파일이 비어있거나 헤더를 읽을 수 없는 경우 예외 발생
                throw UNSUPPORTED_IMAGE_FORMAT.exception();
            };

            if (header[0] == (byte)0xFF && header[1] == (byte)0xD8) return ImageFormat.JPG;
            if (header[0] == (byte)0x89 && header[1] == (byte)0x50 &&
                    header[2] == (byte)0x4E && header[3] == (byte)0x47) return ImageFormat.PNG;
            if (header[0] == (byte)0x47 && header[1] == (byte)0x49 &&
                    header[2] == (byte)0x46 && header[3] == (byte)0x38) return ImageFormat.GIF;
            if (header[0] == (byte)0x42 && header[1] == (byte)0x4D) return ImageFormat.BMP;
            if (header[0] == (byte)0x52 && header[1] == (byte)0x49 &&
                    header[2] == (byte)0x46 && header[3] == (byte)0x46 &&
                    header[8] == (byte)0x57 && header[9] == (byte)0x45 &&
                    header[10] == (byte)0x42 && header[11] == (byte)0x50) return ImageFormat.WEBP;
        }

        // 지원하는 형식이 아닐 경우 예외 발생
        throw UNSUPPORTED_IMAGE_FORMAT.exception();
    }
}