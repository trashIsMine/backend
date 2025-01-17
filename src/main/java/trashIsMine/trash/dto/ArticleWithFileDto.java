package trashIsMine.trash.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import trashIsMine.trash.domain.Article;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleWithFileDto {

    private Long id;

    private String title;

    private String content;

    private  String location;

    private String participants;

    private String time;

    private Long views = 0L;

    private String description;

    private String imagePath;

    private String lng;

    private String lat;


    public ArticleWithFileDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.location = article.getLocation();
        this.participants = article.getParticipants();
        this.time = article.getTime();
        this.views = article.getViews();
        this.description = article.getDescription();
        this.imagePath = article.getImagePath();
        this.lng = article.getLng();
        this.lat = article.getLat();

    }
    public String getDownloadUrl() {
        if (imagePath == null || imagePath.isEmpty()) {
            // imagePath가 null이거나 빈 문자열인 경우 기본값을 반환하거나 URL을 생성하지 않습니다.
            // 예를 들어, 기본 URL을 반환하거나 예외를 던질 수 있습니다.
            return "/files/" + id + "/default-image.png"; // 기본 이미지 URL
            // 또는
            // throw new IllegalArgumentException("Image path is missing");
        }

        try {
            // URL 인코딩을 적용하여 파일 이름을 안전하게 처리합니다.
            String encodedFileName = URLEncoder.encode(imagePath, StandardCharsets.UTF_8.toString());
            return "/uploads/" + encodedFileName;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to encode file name", e);
        }
    }
}
