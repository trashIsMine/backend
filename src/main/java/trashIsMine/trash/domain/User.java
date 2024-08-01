package trashIsMine.trash.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@Entity //entity 붙혀두면 jpa가 이걸 읽어들여서 적용시키는 것이다.
public class User  implements  UserDetails{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //회원식별자

    private String email;

    private String gender;

    private String username;

    private String introduction;

    private String birth;

    private String name;

    private String password;

    private boolean activated;

    @ManyToMany
    private Set<Authority> authorities;

//    @OneToMany(mappedBy = "author")
//    private Set<Article> articles; // 작성한 게시글들을 저장

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    //id 반환시키면 됨
    @Override
    public String getUsername() {
        return email;
    }

    //여기가 문제가 되면 수정할 수 있어야 함
//    @Override
//    public String getId() {
//        return id;
//    }g

    //패스워드 반환
    @Override
    public String getPassword() {
        return password;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
//        return UserDetails.super.isAccountNonExpired();
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
//        return UserDetails.super.isAccountNonLocked();
    }

    //패스워드의 만료 여부 확인
    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    //계정 사용 가능 확인
    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return true;
    }
}
