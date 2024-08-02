package trashIsMine.trash.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    private String location;

    private String participants;

    private String time;

    private Long views;

    private String description;

    private String imagePath; // 이미지 파일의 경로를 저장

    private String lng;

    private String lat;

    public Article(String title, String content, String location, String participants, String time, Long views, String description, String imagePath, String lng, String lat) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.participants = participants;
        this.time = time;
        this.views = views;
        this.description = description;
        this.imagePath = imagePath;
        this.lng = lng;
        this.lat = lat;
    }
}
