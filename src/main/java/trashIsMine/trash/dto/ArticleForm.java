package trashIsMine.trash.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import trashIsMine.trash.entity.Article;

@AllArgsConstructor
@ToString
@Data
public class ArticleForm {

    @Schema(description = "id",example = "id값")
    private Long id;

    private String title;

    private String content;

    private  String location;

    private String participants;

    private String time;

    private Long views;

    private String description;

    private String imgSrc;

    private Long lng;

    private Long lat;

    public Article toEntity() {return new Article(id, title, content, location, participants, time, views, description, imgSrc, lng, lat); }
}
