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

//    private String username;
//    private Set<Article> articles;

    private String participants;

    private String time;

    private Long views;

    private String description;

    private MultipartFile imageData;

    private Long lng;

    private Long lat;

    public Article toEntity() {return new Article( title ,content, location,  participants, time, views, description, imageData, lng, lat); }
}
