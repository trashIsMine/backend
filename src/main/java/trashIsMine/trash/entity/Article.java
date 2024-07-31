package trashIsMine.trash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    private  String location;

    private String participants;

    private String time;

    private Long views;

    private String description;

    private String imgSrc;

    private Long lng;

    private Long lat;

//    title: string,
//    location: string,
//    participants: string,
//    time: string,
//    views: number,
//    description: string,
//    imgSrc: string,
//    content: string,
//    lng: number,
//    lat: number

}
