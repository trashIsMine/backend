package trashIsMine.trash.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

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

//    @ManyToOne
//    @JoinColumn(name = "user_id") // User 엔티티의 id를 참조하는 외래 키
//    private User author;

//    private String username; //username이 있어야지만 글을 쓸 수 있겠끔

    @Column
    private String content;

    private  String location;

    private String participants;

    private String time;

    private Long views;

    private String description;


    @Column(name = "image_data")
    private MultipartFile imageData; // BLOB 데이터 타입으로 이미지를 저장

    private Long lng;

    private Long lat;

    public Article(String title, String content, String location, String participants, String time, Long views, String description, byte[] imageData, Long lng, Long lat) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.participants = participants;
        this.time = time;
        this.views = views;
        this.description = description;
        this.imageData = imageData;
        this.lng = lng;
        this.lat = lat;
    }

}
