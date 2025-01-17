package trashIsMine.trash.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import trashIsMine.trash.domain.Article;
import trashIsMine.trash.domain.User;

import java.util.Set;

@AllArgsConstructor
@ToString
@Data
public class ArticleForm {

//    private Long id;

    private String title;

//    private User author;

    private String content;

    private  String location;

    private String participants;

    private String time;

    private Long views = 0L;

    private String description;

    private MultipartFile imageFile;

    private String lng;

    private String lat;

    // 기본 생성자
    public ArticleForm() {
        this.views = 0L; // 기본값 설정
    }


    //    public Article toEntity() {return new Article( title ,content, location,  participants, time, views, description, imageFile, lng, lat); }
public Article toEntity(String imagePath) {
    return new Article(title, content, location, participants, time, views, description, imagePath, lng, lat);
}
}
