package trashIsMine.trash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import trashIsMine.trash.domain.Article;
import trashIsMine.trash.domain.User;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 3, max = 50)
    private String email;

    @Size(min = 3, max = 50)
    private String birth;

    @Size(min = 0, max = 1000)
    private String introduction;

    private String gender;

    private Set<AuthorityDto> authorityDtoSet;

//    private Set<Article> articles;

    public static UserDto from(User user) {
        if(user == null) return null;

        return UserDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .birth(user.getBirth())
                .gender(user.getGender())
                .introduction(user.getIntroduction())
//                .articles(user.getArticles())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthority()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}