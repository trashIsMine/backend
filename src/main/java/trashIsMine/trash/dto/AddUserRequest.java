package trashIsMine.trash.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private Long id; //회원식별자
    private String email;
    private String gender;
    private String introduction;
    private String birth;
    private String name;
    private String password;
    private String username;

}
